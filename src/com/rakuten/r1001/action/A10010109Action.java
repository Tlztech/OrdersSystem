package com.rakuten.r1001.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r1001.bean.DenaDetailCsvBean;
import com.rakuten.r1001.bean.RakutenCsvBean;
import com.rakuten.r1001.form.F100101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A10010109Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	private File csvFile3 = null;
	private File csvFile4 = null;

	protected void exec() throws Exception {
		List<String[]> orderkeiDataList = Utility.readCsvFileJpn(csvFile3, true);
		List<String[]> shohinkeiDataList = Utility.readCsvFileJpn(csvFile4, true);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<String,Object> map =  ActionContext.getContext().getSession();
		int companyId;
		if (null == map.get("comp")) {
			companyId = -1;
		} else {
			companyId = (int)map.get("comp");
		}
		conn = JdbcConnection.getConnection();
		try {
			for (String[] orderkeiData : orderkeiDataList) {
				try {
					int j = 0;
					String OrderTime = orderkeiData[1].replace("/", "-");
					String orderNo = orderkeiData[2];
					String shipLastName = orderkeiData[3];
					String shipFirstName = orderkeiData[4];
					String shipLastNameKana = orderkeiData[5];
					String shipFirstNameKana = orderkeiData[6];
					String shipZipCode = orderkeiData[7];
					String shipPrefecture = orderkeiData[8];
					String shipCity = orderkeiData[9];
					String shipAddress1 = orderkeiData[10];
					String shipAddress2 = orderkeiData[11];
					String shipPhoneNumber = orderkeiData[12];
					String billMailAddress = orderkeiData[13];
					String payMethodName = orderkeiData[14];
					if ("商品代引".equals(payMethodName)) {
						payMethodName = "代金引換";
					}
					String buyerComments = orderkeiData[15];
					String quantityDetail = orderkeiData[16];
					String shipCharge = orderkeiData[17];
					String payCharge = orderkeiData[18];
					String total = orderkeiData[19];
					String totalPrice = orderkeiData[20];
					String shipMethodName = orderkeiData[21];
					String usePoint = orderkeiData[22];

					sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(++j, orderNo);
					ps.setString(++j, OrderTime.replace("  ", " "));
					ps.setString(++j, "0");// 未入金
					ps.setString(++j, "2");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "Yahoo");
					ps.setString(++j, "WhiteSweet");
					ps.setString(++j, "");
					ps.setString(++j, payMethodName);
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, Utility.isEmptyString(usePoint) ? "0" : usePoint);

					// あす楽希望
					ps.setString(++j, "");
					// 注文者名字
					ps.setString(++j, shipLastName);
					// 注文者名前
					ps.setString(++j, shipFirstName);
					// 注文者名字フリガナ
					ps.setString(++j, shipLastNameKana);
					// 注文者名前フリガナ
					ps.setString(++j, shipFirstNameKana);
					// メールアドレス
					ps.setString(++j, billMailAddress);
					// 注文者誕生日
					ps.setString(++j, "");
					String zipcode1 = "";
					String zipcode2 = "";
					if (shipZipCode.contains("-")) {
						zipcode1 = shipZipCode.split("-")[0];
						zipcode2 = shipZipCode.split("-")[1];
					} else {
						zipcode1 = shipZipCode.substring(0, 3);
						zipcode2 = shipZipCode.substring(3, 7);
					}
					// 注文者郵便番号１
					ps.setString(++j, zipcode1);
					// 注文者郵便番号２
					ps.setString(++j, zipcode2);
					// 注文者住所：都道府県
					ps.setString(++j, shipPrefecture);
					// 注文者住所：都市区
					ps.setString(++j, shipCity);
					// 注文者住所：町以降
					ps.setString(++j, shipAddress1 + shipAddress2);
					// 注文者電話番号１
					ps.setString(++j, shipPhoneNumber);
					// 注文者電話番号２
					ps.setString(++j, "");
					// 注文者電話番号３
					ps.setString(++j, "");
					// コメント
					ps.setString(++j, buyerComments);
					// メール差込文(お客様へのメッセージ)
					ps.setString(++j, "");

					// 送付先名字
					ps.setString(++j, shipLastName);
					// 送付先名前
					ps.setString(++j, shipFirstName);
					// 送付先名字フリガナ
					ps.setString(++j, shipLastNameKana);
					// 送付先名前フリガナ
					ps.setString(++j, shipFirstNameKana);
					// 配送方法
					ps.setString(++j, shipMethodName.replace("宅急便", "宅配便"));
					// 送付先郵便番号１
					ps.setString(++j, zipcode1);
					// 送付先郵便番号２
					ps.setString(++j, zipcode2);
					ps.setString(++j, shipPhoneNumber);
					ps.setString(++j, "");
					ps.setString(++j, "");
					// 送付先住所：都道府県
					ps.setString(++j, shipPrefecture);
					// 送付先住所：都市区
					ps.setString(++j, shipCity);
					// 送付先住所：町以降
					ps.setString(++j, shipAddress1 + shipAddress2);

					String gokeishouhin = "0";
					String gokeizei = "0";
					String gokeisouryou = "0";
					String gokeidaibikiryou = "0";
					String seikyukingaku = "0";

					gokeizei = "0";

					gokeisouryou = Utility.isEmptyString(shipCharge) ? "0" : shipCharge;

					gokeidaibikiryou = Utility.isEmptyString(payCharge) ? "0" : payCharge;
					gokeishouhin = String.valueOf(Integer.valueOf(totalPrice) - Integer.valueOf(gokeisouryou)
							- Integer.valueOf(gokeidaibikiryou));
					seikyukingaku = total;

					ps.setString(++j, gokeishouhin);
					ps.setString(++j, gokeizei);
					ps.setString(++j, gokeisouryou);
					ps.setString(++j, gokeidaibikiryou);
					ps.setString(++j, seikyukingaku);

					// 同梱ID
					ps.setString(++j, "0");
					// 同梱親FLG
					ps.setString(++j, "0");

					ps.setString(++j, "0");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");

					// 発送者へのコメント
					ps.setString(++j, "");
					// その他利用額
					String sonota = "0";
					sonota = String.valueOf(
							Integer.valueOf(gokeishouhin) + Integer.valueOf(gokeisouryou) - Integer.valueOf(usePoint)
									+ Integer.valueOf(gokeidaibikiryou) - Integer.valueOf(seikyukingaku));
					ps.setString(++j, sonota);
					ps.executeUpdate();
					
					sql = "INSERT INTO `rakuten`.`company_order_tbl` (`company_id`,`order_id`,`permission`) VALUES (?,?,?);";
					ps = conn.prepareStatement(sql);
					j = 0;
					ps.setInt(++j, companyId == 0 ? 1 : companyId);
					ps.setString(++j, Utility.strTrim(orderNo));
					ps.setInt(++j, 3);
					ps.executeUpdate();
					
					for (String[] shohinkeiData : shohinkeiDataList) {

						if (shohinkeiData[2].equals(orderNo)) {
							String orderId = shohinkeiData[0];
							String lineId = shohinkeiData[1];
							String id = shohinkeiData[2];
							String quantity = shohinkeiData[3];
							String itemId = shohinkeiData[4];
							String subCode = shohinkeiData[5];
							String title = shohinkeiData[6];
							String itemOptionName = shohinkeiData[7];
							String itemOptionValue = shohinkeiData[8];
							String subCodeOption = shohinkeiData[9];
							String inscriptionName = shohinkeiData[10];
							String inscriptionValue = shohinkeiData[11];
							String unitPrice = shohinkeiData[12];
							String unitGetPoint = shohinkeiData[13];
							String lineSubTotal = shohinkeiData[14];
							String lineGetPoint = shohinkeiData[15];
							String pointFspCode = shohinkeiData[16];
							String condition = shohinkeiData[17];
							String couponId = shohinkeiData[18];
							String couponDiscount = shohinkeiData[19];
							String originalPrice = shohinkeiData[20];
							String getPointFixDate = shohinkeiData[21];
							String releaseDate = shohinkeiData[22];
							String lineGetPointChargedToStore = shohinkeiData[23];

							sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(sql);

							j = 0;
							ps.setString(++j, Utility.getShoribango());

							ps.setString(++j, id);
							// 商品名
							ps.setString(++j, title);
							// 商品番号
							ps.setString(++j, subCode);
							// 商品URL
							ps.setString(++j, "");
							// 単価
							ps.setString(++j, unitPrice);
							// 個数
							ps.setString(++j, quantity);
							// 送料込別
							ps.setString(++j, Integer.valueOf(gokeisouryou) > 0 ? "別" : "込");
							// 税込別
							ps.setString(++j, "込");
							// 代引手数料込別
							ps.setString(++j, Integer.valueOf(gokeidaibikiryou) > 0 ? "別" : "込");
							// 項目選択肢
							ps.setString(++j, itemOptionName + itemOptionValue);

							// ponint bairitu
							ps.setString(++j, "");

							// nouki
							ps.setString(++j, "");

							ps.executeUpdate();
						}
					}
					String dateStr = "";
					int noukiday = 10;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, noukiday);
					dateStr = sdf.format(calendar.getTime());

					sql = "INSERT INTO TBL00027 VALUES(?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderNo);
					ps.setString(2, dateStr);
					ps.setString(3, Utility.getDateTime());
					ps.setString(4, Utility.getUser());
					ps.setString(5, Utility.getDateTime());
					ps.setString(6, Utility.getUser());
					ps.execute();

					conn.commit();
				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println(orderkeiData[2] + "已存在，不再添加");
					continue;
				}
			}
		} finally

		{
			conn.close();
		}
	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100101 getF100101() {
		return f100101;
	}

	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	public File getCsvFile3() {
		return csvFile3;
	}

	public void setCsvFile3(File csvFile3) {
		this.csvFile3 = csvFile3;
	}

	public File getCsvFile4() {
		return csvFile4;
	}

	public void setCsvFile4(File csvFile4) {
		this.csvFile4 = csvFile4;
	}

}
