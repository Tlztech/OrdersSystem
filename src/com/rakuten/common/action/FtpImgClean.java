package com.rakuten.common.action;

import java.io.File;
import java.util.List;

import com.rakuten.util.Utility;

public class FtpImgClean {

	public static void main(String[] args) throws Exception {
		List<String[]> shohinbangoList = Utility.readCsvFile(new File("d:\\dl-item201909032118-1.csv"), true);
		File file = new File("d:\\cover\\");
		String[] filenameList = file.list();
		for (String[] shohinbango : shohinbangoList) {
			for (String filename : filenameList) {
				if (filename.equals(shohinbango[1] + "_sp.jpg")) {
					Utility.copyFile(new File("d:\\cover\\" + filename), "d:\\cover2\\", filename);
				}
			}
		}
		File file2 = new File("d:\\cover2\\");
		String[] filenameList2 = file2.list();
		for (String[] shohinbango : shohinbangoList) {
			boolean ariFlg = false;
			for (String filename : filenameList2) {
				if (filename.equals(shohinbango[1] + "_sp.jpg")) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				System.out.println(shohinbango[1]);
			}
		}
	}

}
