package com.rakuten.r1001.action;

import java.util.List;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1001.common.A1001Common;

public class Test {

	public static void main(String[] args) throws Exception {
		A1001Common a1001Common = new A1001Common();
		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		List<String> bangoList = orderCommon.getHasomachiYoteibiariList();
		List<String> nyukachu = orderCommon.getNyukachuList(orderInfoBean, bangoList);
		for(String bango :nyukachu){
			System.out.println(bango);
		}
	}

}
