package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.rakuten.util.Utility;

public class gaimingzi {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\一键抓图数据包\\201909131044");
		qukuo(file);
	}

	private static void qukuo(File file) throws Exception {
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (String shohinmei : filelist) {
				String shohinbango = Utility.getShohinbango(shohinmei);
				String path = file.getPath() + "\\" + shohinmei;
				File file2 = new File(path);

				file2.renameTo(new File(file.getPath() + "\\" + shohinbango));

			}
		}
	}

}
