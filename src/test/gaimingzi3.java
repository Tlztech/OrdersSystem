package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.rakuten.util.Utility;

public class gaimingzi3 {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\一键抓图数据包\\123");
		qukuo(file);
	}

	private static void qukuo(File file) throws Exception {
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (String shohinbango : filelist) {

				String path = file.getPath() + "\\" + shohinbango;
				File file2 = new File(path);
				String[] filelist2 = file2.list();
				for (int i = 0; i < filelist2.length; i++) {
					String filename = filelist2[i];
					File file3 = new File(file.getPath() + "\\" + shohinbango + "\\" + filename);
					String newfilename = shohinbango + "_" + (i+1) + ".jpg";
					newfilename = newfilename.replace("_1.jpg", "_01.jpg");
					newfilename = newfilename.replace("_2.jpg", "_02.jpg");
					newfilename = newfilename.replace("_3.jpg", "_03.jpg");
					newfilename = newfilename.replace("_4.jpg", "_04.jpg");
					newfilename = newfilename.replace("_5.jpg", "_05.jpg");
					newfilename = newfilename.replace("_6.jpg", "_06.jpg");
					newfilename = newfilename.replace("_7.jpg", "_07.jpg");
					newfilename = newfilename.replace("_8.jpg", "_08.jpg");
					newfilename = newfilename.replace("_9.jpg", "_09.jpg");
					file3.renameTo(new File(file.getPath() + "\\" + shohinbango + "\\" + newfilename));
				}

			}
		}
	}

}
