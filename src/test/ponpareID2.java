package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class ponpareID2 {

	public static void main(String[] args) throws Exception {
		List<String[]> motoList = Utility.readCsvFileJpn(new File("d://ponpareItem.csv"), false);
		List<String[]> shoriList = new ArrayList<String[]>();
		List<String[]> switchList = new ArrayList<String[]>();

		for (String[] data : motoList) {
			if (data[26].length() == 9) {
				data[26] = "0" + data[26];
			}

			shoriList.add(data);
		}
		Utility.writeCsvFile(shoriList, "d:\\ponpareItem2.csv");
	}

}
