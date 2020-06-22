package test;

import java.io.File;
public class gaimingzi5 {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\一键抓图数据包\\新建文件夹");
		qukuo(file);
	}

	private static void qukuo(File file) throws Exception {
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
