package com.rakuten.r0602.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class ZaikoMain {

	public static void main(String[] args) throws Exception {
		// // 更新库存
		// List<String[]> csvList_3eshop_sel = new ArrayList<String[]>();
		// csvList_3eshop_sel = Utility.readCsvFile(new File(
		// "D:\\select20140904131137.csv"), false);
		//
		// List<String[]> reslutSel = ZaikoTool.zaikoKoshin(csvList_3eshop_sel,
		// false, false);
		// Utility.writeCsvFileTemp(reslutSel, "D://3eshopSel.csv");
		//
		// // 新商品列表

		List<String> bangoList = ZaikoTool.getNewShohin(
				Utility.readCsvFileJpn(new File("D:\\3e.csv"), false),
				Utility.readCsvFileJpn(new File("D:\\cy.csv"), false));
		for (String bango : bangoList) {
			System.out.println(bango);
		}
		System.out.println("done");

	}
}
