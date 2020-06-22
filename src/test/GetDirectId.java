package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class GetDirectId {

	public static void main(String[] args) throws Exception {
		List<String[]> rakutenList = Utility.readCsvFile(new File("D:\\item20160413184117.csv"), true);
		List<String[]> yahooList = Utility.readCsvFile(new File("D:\\data201604132124.csv"), true);
		List<String[]> shoriList = new ArrayList<String[]>();
		for (String[] rakuten : rakutenList) {
			for (String[] yahoo : yahooList) {
				if (rakuten[1].equals(yahoo[2])) {
					shoriList.add(new String[] { rakuten[3], yahoo[9] });
				}
			}

		}
		Utility.writeCsvFile(shoriList, "D:\\sgsdgf.csv");

	}

}
