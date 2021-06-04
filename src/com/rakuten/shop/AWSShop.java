package com.rakuten.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.platform.commons.util.StringUtils;

import com.amazon.spapi.SellingPartnerAPIAA.AWSAuthenticationCredentials;
import com.amazon.spapi.SellingPartnerAPIAA.AWSAuthenticationCredentialsProvider;
import com.amazon.spapi.SellingPartnerAPIAA.AWSSigV4Signer;
import com.amazon.spapi.SellingPartnerAPIAA.LWAAuthorizationCredentials;
import com.amazon.spapi.SellingPartnerAPIAA.LWAAuthorizationSigner;
import com.amazon.spapi.api.OrdersV0Api;
import com.amazon.spapi.api.SellersApi;
import com.amazon.spapi.client.ApiClient;
import com.amazon.spapi.client.ApiException;
import com.amazon.spapi.model.orders.GetOrderAddressResponse;
import com.amazon.spapi.model.orders.GetOrderItemsResponse;
import com.amazon.spapi.model.orders.GetOrdersResponse;
import com.amazon.spapi.model.orders.OrderAddress;
import com.amazon.spapi.model.orders.OrderItemList;
import com.amazon.spapi.model.orders.OrderItemsList;
import com.amazon.spapi.model.orders.OrderList;
import com.amazon.spapi.model.sellers.GetMarketplaceParticipationsResponse;
import com.rakuten.common.MessageFromAWS;
import com.rakuten.order.AWSOrder;
import com.rakuten.order.AWSOrder.Address;
import com.rakuten.order.AWSOrder.Item;
import com.rakuten.util.JdbcConnection;

public class AWSShop {

	private final Long RETRY_INTERVAL = 5000L;
	private final Integer RETRY_MAX = 3;
	private final static String AUTH_ENDPOINT = "https://api.amazon.com/auth/o2/token";
	private final static String REGION = "us-west-2";
	private final static String BASEPATH = "https://sellingpartnerapi-fe.amazon.com";
	private String shopName;
	private ApiClient apiClient;
	private List<String> marketplaceIds;
	private List<MessageFromAWS> messageFromAWSList = new ArrayList<>();

//	ALTER TABLE `rakuten`.`shop` 
//	ADD COLUMN `AWS_ACCESS_KEY_ID` VARCHAR(100) NULL DEFAULT NULL AFTER `DELETE_FLG`,
//	ADD COLUMN `AWS_SECRET_KEY` VARCHAR(200) NULL DEFAULT NULL AFTER `AWS_ACCESS_KEY_ID`,
//	ADD COLUMN `AWS_ARN` VARCHAR(200) NULL DEFAULT NULL AFTER `AWS_SECRET_KEY`,
//	ADD COLUMN `AWS_CLIENT_ID` VARCHAR(200) NULL DEFAULT NULL AFTER `AWS_ARN`,
//	ADD COLUMN `AWS_CLIENT_SECRET` VARCHAR(200) NULL DEFAULT NULL AFTER `AWS_CLIENT_ID`,
//	ADD COLUMN `AWS_REFRESH_TOKEN` VARCHAR(500) NULL DEFAULT NULL AFTER `AWS_CLIENT_SECRET`;
	public AWSShop(String shopName) throws Exception {
		this.shopName = shopName;
		String accessKeyId = "";
		String secretKey = "";
		String roleArn = "";
		String clientId = "";
		String clientSecret = "";
		String refreshToken = "";
		Connection conn = JdbcConnection.getConnection();
		String sql = "SELECT AWS_ACCESS_KEY_ID, AWS_SECRET_KEY, AWS_ARN, AWS_CLIENT_ID, AWS_CLIENT_SECRET, AWS_REFRESH_TOKEN FROM rakuten.shop WHERE SITE = 'Amazon' AND SHOP_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, shopName);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			accessKeyId = rs.getString("AWS_ACCESS_KEY_ID");
			secretKey = rs.getString("AWS_SECRET_KEY");
			roleArn = rs.getString("AWS_ARN");
			clientId = rs.getString("AWS_CLIENT_ID");
			clientSecret = rs.getString("AWS_CLIENT_SECRET");
			refreshToken = rs.getString("AWS_REFRESH_TOKEN");
		}
		AWSAuthenticationCredentials awsAuthenticationCredentials = AWSAuthenticationCredentials.builder()
				// IAM user的accessKeyId
				.accessKeyId(accessKeyId)
				// IAM user的secretKey
				.secretKey(secretKey)
				// 这里按照amazon对不同region的分区填写
				.region(REGION).build();
		AWSAuthenticationCredentialsProvider awsAuthenticationCredentialsProvider = AWSAuthenticationCredentialsProvider
				.builder()
				// IAM role，特别注意：最好用IAM role当做IAM ARN去申请app
				// 而且IAM user需要添加内联策略STS关联上IAM
				// role，具体操作看：https://www.spapi.org.cn/cn/model2/_2_console.html
				.roleArn(roleArn).roleSessionName(shopName).build();
		LWAAuthorizationCredentials lwaAuthorizationCredentials = LWAAuthorizationCredentials.builder()
				// 申请app后LWA中的clientId
				.clientId(clientId)
				// 申请app后LWA中的clientSecret
				.clientSecret(clientSecret)
				// 店铺授权时产生的refreshToken或者app自授权生成的
				.refreshToken(refreshToken).endpoint(AUTH_ENDPOINT).build();
		apiClient = new ApiClient();
		apiClient
				.setAWSSigV4Signer(
						new AWSSigV4Signer(awsAuthenticationCredentials, awsAuthenticationCredentialsProvider))
				.setLWAAuthorizationSigner(new LWAAuthorizationSigner(lwaAuthorizationCredentials))
				.setBasePath(BASEPATH);
		conn.close();
	}

	public List<AWSOrder> getOrders(List<String> orderStatuses) {

		List<AWSOrder> orders = new ArrayList<>();
		try {
			if (null == marketplaceIds) {
				SellersApi sellersApi = new SellersApi(apiClient);
				GetMarketplaceParticipationsResponse response = sellersApi.getMarketplaceParticipations();
				marketplaceIds = response.getPayload().stream().map(n -> n.getMarketplace().getId())
						.collect(Collectors.toList());
			}
			String createdAfter = "2020-04-01T03:22:53.290Z";
			String createdBefore = null;
			String lastUpdatedAfter = null;
			String lastUpdatedBefore = null;
			List<String> fulfillmentChannels = null;
			List<String> paymentMethods = null;
			String buyerEmail = null;
			String sellerOrderId = null;
			Integer maxResultsPerPage = null;
			List<String> easyShipShipmentStatuses = null;
			String nextToken = null;
			List<String> amazonOrderIds = null;
			int retryCnt = 0;

			OrdersV0Api orderApi = new OrdersV0Api(apiClient);
			GetOrdersResponse ordersResponse = orderApi.getOrders(marketplaceIds, createdAfter, createdBefore,
					lastUpdatedAfter, lastUpdatedBefore, orderStatuses, fulfillmentChannels, paymentMethods, buyerEmail,
					sellerOrderId, maxResultsPerPage, easyShipShipmentStatuses, nextToken, amazonOrderIds);
			OrderList orderList = ordersResponse.getPayload().getOrders();
			while (StringUtils.isNotBlank(ordersResponse.getPayload().getNextToken())) {
				try {
					ordersResponse = orderApi.getOrders(marketplaceIds, null, null, null, null, null, null, null, null,
							null, null, null, ordersResponse.getPayload().getNextToken(), null);
//    	        	System.out.println(orderResponse.getErrors());
					orderList.addAll(ordersResponse.getPayload().getOrders());
					retryCnt = 0;

				} catch (ApiException e) {
					if (429 != e.getCode()) {
						retryCnt = 0;
						throw e;
					} else if (429 == e.getCode() && retryCnt < RETRY_MAX) {
						try {
							Thread.sleep(RETRY_INTERVAL);
						} catch (InterruptedException ie) {
							throw e;
						}
						System.out.println("retry getOrders " + marketplaceIds + " count:" + (++retryCnt));
						continue;
					} else {
						System.out.println("when get orders Reaches max retryCnt");
						retryCnt = 0;
						throw e;
					}
				}
			}
			orders = orderList.stream().map(order -> {
				AWSOrder awsOrder = new AWSOrder();
				awsOrder.setOrderId(order.getAmazonOrderId());
				return awsOrder;
			}).collect(Collectors.toList());
		} catch (ApiException e) {
			if (e.getCode() == 0 || null == e.getResponseBody()) {
				System.out.println(e.getMessage());
				MessageFromAWS messageFromAWS = new MessageFromAWS();
				messageFromAWS.setCode("");
				messageFromAWS.setMessage(e.getMessage());
				messageFromAWSList.add(messageFromAWS);
			} else {
				System.out.println(e.getCode());
				System.out.println(e.getResponseBody());
				MessageFromAWS messageFromAWS = new MessageFromAWS();
				messageFromAWS.setCode(String.valueOf(e.getCode()));
				messageFromAWS.setMessage(e.getResponseBody());
				messageFromAWSList.add(messageFromAWS);
			}
		}
		return orders;

	}

	public List<AWSOrder> getOrdersItem(List<AWSOrder> orders) {

		MessageFromAWS messageFromAWS = new MessageFromAWS();
		OrdersV0Api orderApi = new OrdersV0Api(apiClient);
		int retryCnt = 0;
		try {
			for (AWSOrder order : orders) {
				messageFromAWS.setOrderID(order.getOrderId());
				while (true) {
					try {
						GetOrderItemsResponse orderItemsResponse = orderApi.getOrderItems(order.getOrderId(), null);
						OrderItemsList orderItemsList = orderItemsResponse.getPayload();
						OrderItemList orderItemList = orderItemsList.getOrderItems();
						retryCnt = 0;
						while (StringUtils.isNotBlank(orderItemsList.getNextToken())) {
							System.out.println(orderItemsList.getNextToken());
							try {
								orderItemsResponse = orderApi.getOrderItems(order.getOrderId(), orderItemsList.getNextToken());
								orderItemsList = orderItemsResponse.getPayload();
								orderItemList.addAll(orderItemsList.getOrderItems());
								
								retryCnt = 0;
							} catch (ApiException e) {
								if (429 != e.getCode()) {
									retryCnt = 0;
									throw e;
								} else if (429 == e.getCode() && retryCnt < RETRY_MAX) {
									try {
										Thread.sleep(RETRY_INTERVAL);
									} catch (InterruptedException ie) {
										System.out.println("InterruptedException " + order.getOrderId());
										throw e;
									}
									System.out.println("retry getOrderItems " + order.getOrderId() + " count:" + (++retryCnt));
									continue;
								} else {
									System.out.println("Reaches max retryCnt" + order.getOrderId());
									throw e;
								}
							}
						}
						List<AWSOrder.Item> awsItemList = new ArrayList<>();
						order.setItemList(awsItemList);
						orderItemsList.getOrderItems().forEach(item->{
							Item awsItem = order.new Item();
							awsItem.setPrice(item.getItemPrice().getAmount());
							awsItemList.add(awsItem);
						});
						break;
					} catch (ApiException e) {
						if (429 != e.getCode()) {
							retryCnt = 0;
							throw e;
						} else if (429 == e.getCode() && retryCnt < RETRY_MAX) {
							try {
								Thread.sleep(RETRY_INTERVAL);
							} catch (InterruptedException ie) {
								System.out.println("InterruptedException " + order.getOrderId());
								throw e;
							}
							System.out.println("retry getOrderItems " + order.getOrderId() + " count:" + (++retryCnt));
							continue;
						} else {
							retryCnt = 0;
							System.out.println("Reaches max retryCnt" + order.getOrderId());
							throw e;
						}
					}
				}
				try {
					Thread.sleep(RETRY_INTERVAL);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					throw new ApiException(e1.getMessage());
				}
			}
		} catch (ApiException e) {
			if (e.getCode() == 0 || null == e.getResponseBody()) {
				System.out.println(e.getMessage());
				messageFromAWS.setCode("");
				messageFromAWS.setMessage(e.getMessage());
				messageFromAWSList.add(messageFromAWS);
			} else {
				System.out.println(e.getCode());
				System.out.println(e.getResponseBody());
				messageFromAWS.setCode(String.valueOf(e.getCode()));
				messageFromAWS.setMessage(e.getResponseBody());
				messageFromAWSList.add(messageFromAWS);
			}
		}

		return orders;
	}
	
	public List<AWSOrder> getOrdersAddress(List<AWSOrder> orders) {
		MessageFromAWS messageFromAWS = new MessageFromAWS();
		OrdersV0Api orderApi = new OrdersV0Api(apiClient);
		int retryCnt = 0;
		try {
			for (AWSOrder order : orders) {
				messageFromAWS.setOrderID(order.getOrderId());
				while(true) {
	        		try {
	        			GetOrderAddressResponse orderAddressResponse = orderApi.getOrderAddress(order.getOrderId());
	        			OrderAddress orderAddress = orderAddressResponse.getPayload();
	        			Address address = order.new Address();
	        			address.setShipPrefecture(orderAddress.getShippingAddress().getStateOrRegion());
	        			address.setShipPostalCode(orderAddress.getShippingAddress().getPostalCode());
	        			order.setAddress(address);
	            		break;
					} catch (ApiException e) {
						if (429 != e.getCode()) {
							retryCnt = 0;
							throw e;
						} else if (429 == e.getCode() && retryCnt < RETRY_MAX) {
							try {
								Thread.sleep(RETRY_INTERVAL);
							} catch (InterruptedException ie) {
								System.out.println("InterruptedException " + order.getOrderId());
								throw e;
							}
							System.out.println("retry getOrderAddress " + order.getOrderId() + " count:" + (++retryCnt));
							continue;
						} else {
							retryCnt = 0;
							System.out.println("Reaches max retryCnt" + order.getOrderId());
							throw e;
						}
					}
        		}
				try {
					Thread.sleep(RETRY_INTERVAL);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					throw new ApiException(e1.getMessage());
				}
			}
		} catch (ApiException e) {
			if (e.getCode() == 0 || null == e.getResponseBody()) {
				System.out.println(e.getMessage());
				messageFromAWS.setCode("");
				messageFromAWS.setMessage(e.getMessage());
				messageFromAWSList.add(messageFromAWS);
			} else {
				System.out.println(e.getCode());
				System.out.println(e.getResponseBody());
				messageFromAWS.setCode(String.valueOf(e.getCode()));
				messageFromAWS.setMessage(e.getResponseBody());
				messageFromAWSList.add(messageFromAWS);
			}
		}
		
		return orders;
	}

	/**
	 * @return the messageFromAWSList
	 */
	public List<MessageFromAWS> getMessageFromAWSList() {
		return messageFromAWSList;
	}
	
}
