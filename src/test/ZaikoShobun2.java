package test;

import java.io.File;
import java.util.List;

import com.rakuten.util.Utility;

public class ZaikoShobun2 {

	public static void main(String[] args) throws Exception {

		List<String[]> dataList = Utility.readCsvFile(new File("d:\\select.csv"), false);
		List<String[]> stockList = Utility.readCsvFile(new File("d:\\fuck.csv"), true);

		for (int i = 1; i < dataList.size(); i++) {
			String[] data = dataList.get(i);
			data[10] = "0";
			for (String[] stockdata : stockList) {
				if (stockdata[0].equals(data[1] + data[6] + data[8])) {
					data[10] = stockdata[1];

				}
			}
		}
		Utility.writeCsvFile(dataList, "D:\\stock.csv");
	}

}
