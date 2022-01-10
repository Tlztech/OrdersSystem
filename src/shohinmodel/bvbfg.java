package shohinmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class bvbfg {

	public static void main(String[] args) throws Exception {
		List<String[]> dateList = Utility.readCsvFileJpn(new File("D:\\item.csv"), false);

		File file1 = new File("D:\\hebin2\\");
		// File file2 = new File("D:\\cover\\");
		String[] fileList1 = file1.list();
		
		// String[] fileList2 = file2.list();
		for (String filname : fileList1) {

			for (String[] date : dateList) {
				if (filname.replace("_sp.jpg", "").equals(date[1])) {
					String sphtml = date[26].substring(0, date[26].indexOf("</td></tr></table>") + 18);

					date[26] = date[26].replace(sphtml,
							("<table width=100%><tr><td><img width=98% src=https://www.rakuten.ne.jp/gold/coverforefront/cover/"
									+ filname + "></td></tr></table>"));

					break;
				}
			}

		}
		// for (String filname : fileList2) {
		// for (String[] date : dateList) {
		// if (filname.replace("_sp.jpg", "").equals(date[1])) {
		// String sphtml = date[2].substring(0,
		// date[2].indexOf("</td></tr></table>") + 18);
		// shoriList
		// .add(new String[] {
		// date[1],
		// date[2].replace(
		// sphtml,
		// ("<table width=100%><tr><td><img width=98%
		// src=https://www.rakuten.ne.jp/gold/coverforefront/cover/"
		// + filname + "></td></tr></table>")) });
		// break;
		// }
		// }
		// }
		Utility.writeCsvFile(dateList, "D:\\cover.csv");
	}
}
