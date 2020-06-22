package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.rakuten.util.Utility;

public class qukuohao {
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\coverforefront");
		qukuo(file);
	}

	private static void qukuo(File file) throws IOException {
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (String path : filelist) {
				path = file.getPath() + "\\" + path;
				File file2 = new File(path);
				if (file2.isDirectory()) {
					qukuo(file2);
				} else {
					String newPath = path.replace("(", "_").replace(")", "").replace(" ", "");
					newPath = newPath.replace("_1.jpg", "_01.jpg");
					newPath = newPath.replace("_2.jpg", "_02.jpg");
					newPath = newPath.replace("_3.jpg", "_03.jpg");
					newPath = newPath.replace("_4.jpg", "_04.jpg");
					newPath = newPath.replace("_5.jpg", "_05.jpg");
					newPath = newPath.replace("_6.jpg", "_06.jpg");
					newPath = newPath.replace("_7.jpg", "_07.jpg");
					newPath = newPath.replace("_8.jpg", "_08.jpg");
					newPath = newPath.replace("_9.jpg", "_09.jpg");
					file2.renameTo(new File(newPath));

					System.out.println(newPath);
				}
			}
		}

	}

}
