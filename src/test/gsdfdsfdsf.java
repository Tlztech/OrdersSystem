package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class gsdfdsfdsf {

	public static void main(String[] args) throws Exception {
		List<String[]> dataList = Utility.readCsvFileJpn(new File("d:\\item20160325143808 - 副本.csv"), true);
		List<String[]> dataListAll = Utility.readCsvFileJpn(new File("d:\\dl-item201603252150-1.csv"), false);
		List<String[]> shoriList = new ArrayList<String[]>();
		shoriList.add(dataListAll.get(0));
		for (String[] data : dataList) {
			String shohinbango = data[1];
			for (String[] data2 : dataListAll) {
				if (shohinbango.equals(data2[1])) {
					shoriList.add(data2);
				}
			}
		}
		Utility.writeCsvFile(shoriList, "d:\\All.csv");
	}

}
