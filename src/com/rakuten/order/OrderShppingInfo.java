package com.rakuten.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderShppingInfo {

	private List<String> orderNoList;
	private Map<String, String[]> orderShppingInfoMap;
	
	private OrderShppingInfo() {
		
	}
	
	public List<String> getOrderNoList() {
		return orderNoList;
	}

	public Map<String, String[]> getOrderShppingInfo() {
		return orderShppingInfoMap;
	}

	public static OrderShppingInfo convertOrderShppingInfoFromPage(List<String[]> orderShppingInfoFromPage) {
		OrderShppingInfo orderShppingInfo;
		if (null == orderShppingInfoFromPage || orderShppingInfoFromPage.size() == 0) {
			orderShppingInfo = null;
		} else {
			orderShppingInfo = new OrderShppingInfo();
			orderShppingInfo.orderNoList = new ArrayList<String>();
			orderShppingInfo.orderShppingInfoMap = new HashMap<String, String[]>();
			for (String[] info : orderShppingInfoFromPage) {
				if (null == info || info.length < 3 || null == info[0]) {
					
				} else {
					orderShppingInfo.orderNoList.add(info[0]);
					String[] valueArr = Arrays.copyOfRange(info, 1, 4);
					valueArr[2] = info[4];
					orderShppingInfo.orderShppingInfoMap.put(info[0], valueArr);
				}
			}
		}
		return orderShppingInfo;
	}
}
