package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.rakuten.util.Utility;

public class gaimingzi2 {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\一键抓图数据包\\201909122027");
		qukuo(file);
	}

	private static void qukuo(File file) throws Exception {
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (String shohinmei : filelist) {

				String path = file.getPath() + "\\" + shohinmei;
				File file2 = new File(path);
				String[] filelist2 = file2.list();
				for (String filename2 : filelist2) {
					String path2 = file2.getPath() + "\\" + filename2;
					File file3 = new File(path2);
					String[] filelist3 = file3.list();

					String path3 = file3.getPath();
					for (String filename3 : filelist3) {
						File file4 = new File(path3 + "\\" + filename3);
						file4.renameTo(
								new File(path3.replace("描述", "").replace("主图", "") + filename3.replace("主图", "")));
					}
					File file5 = new File(file2 + "\\" + "主图");
					File file6 = new File(file2 + "\\" + "描述");
					file5.delete();
					file6.delete();
				}
//				String path2 = file2.getPath() + "\\" + file2.renameTo(new File(file.getPath() + "\\" + shohinbango));

			}
		}
	}

}
