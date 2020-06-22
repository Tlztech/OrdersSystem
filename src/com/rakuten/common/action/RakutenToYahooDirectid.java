package com.rakuten.common.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.rakuten.util.Utility;

public class RakutenToYahooDirectid {

	public static void main(String[] args) throws Exception {

		String itemCsvPath = "d:\\dl-item201811292341-1.csv";
		String yahooCsvPath = "d:\\data201811291302.csv";
		List<String[]> rakutenList = Utility.readCsvFile(new File(itemCsvPath), true);
		List<String[]> yahooList = Utility.readCsvFile(new File(yahooCsvPath), true);
		List<String[]> outPutList = new ArrayList<String[]>();
		for (String[] yahoo : yahooList) {
			for (String[] info : rakutenList) {
				if (info[1].equals(yahoo[0])) {
					String product_code = "";
					String rakutendirectid = info[2];
					List<String[]> directidList = Utility.readCsvFile(new File("D:\\directid.csv"), false);
					for (String[] directid : directidList) {
						if (directid[0].equals(rakutendirectid)) {
							product_code = directid[1];
							break;
						}
					}
					outPutList.add(new String[] { yahoo[0], product_code });

				}
			}
		}
		Utility.writeCsvFile(outPutList, "D:\\yahoo_pid.csv");
	}

}
