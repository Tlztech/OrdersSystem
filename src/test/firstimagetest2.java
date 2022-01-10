package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class firstimagetest2 {

	public static void main(String[] args) throws Exception {
		List<String[]> dataList = Utility.readCsvFileChn(new File("d:\\cying.csv"), true);

		File nzlf = new File("D:\\fuck\\nzlf");
		File nzlyqsp = new File("D:\\fuck\\nzlyqsp");
		File nzlyqsu = new File("D:\\fuck\\nzlyqsu");
		File nzpj = new File("D:\\fuck\\nzpj");
		File nztz = new File("D:\\fuck\\nztz");
		File tzlyq = new File("D:\\fuck\\tzlyq");

		String[] nzlflists = nzlf.list();
		String[] nzlyqsplists = nzlyqsp.list();
		String[] nzlyqsulists = nzlyqsu.list();
		String[] nzpjlists = nzpj.list();
		String[] nztzlists = nztz.list();
		String[] tzlyqlists = tzlyq.list();

		List<String[]> shoriList = new ArrayList<String[]>();
//		dosth(nzlf.getPath(), nzlflists, dataList, "nzlf", 43, shoriList);
//		 dosth(nzlyqsp.getPath(), nzlyqsplists, dataList, "nzlyq", 1017,
//		 shoriList);
		 dosth(nzlyqsu.getPath(), nzlyqsulists, dataList, "nzlyq", 1037,
		 shoriList);
//		dosth(nzpj.getPath(), nzpjlists, dataList, "nzpj", 1, shoriList);
//		dosth(nztz.getPath(), nztzlists, dataList, "nztz", 197, shoriList);
//		dosth(tzlyq.getPath(), tzlyqlists, dataList, "tzlyq", 43, shoriList);

		Utility.writeCsvFile2(shoriList, "d:\\vsdfgsdgf.csv");
	}

	private static void dosth(String path, String[] lists, List<String[]> dataList, String name, int start,
			List<String[]> shoriList) {

		for (String pic : lists) {
			for (String[] data : dataList) {
				if (pic.replace(".jpg", "").equals(data[4])) {
					String index = String.valueOf(start);
					if (start < 10) {
						index = "00" + start;
					} else if (start < 100) {
						index = "0" + start;
					}
					File file2 = new File(path + "\\" + pic);
					// 首图重命名
					file2.renameTo(new File(path + "\\" + name + index + "_01.jpg"));
					// 详情图
					String xiangxipath = data[3].substring(data[3].indexOf("file:///"), data[3].indexOf(".jpg"));
					xiangxipath = xiangxipath.substring(0, xiangxipath.lastIndexOf("/"));
					xiangxipath = xiangxipath.replace("file:///", "");

					File file3 = new File(xiangxipath);
					// file3.renameTo(
					// new File(xiangxipath.substring(0,
					// xiangxipath.lastIndexOf("/")) + "\\" + name + index));

					shoriList.add(new String[] { name + index, data[0], data[2], data[6] });
					start++;
				}
			}
		}
	}

}
