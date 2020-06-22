package page.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class test2 {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\32213\\Desktop\\整理商品.csv");
		List<String[]> shohinbangoArray = Utility.readCsvFile(file, false);
		File file2 = new File("C:\\Users\\32213\\Desktop\\dl-item-cat201907192351-1.csv");
		List<String[]> cateArray = Utility.readCsvFile(file2, true);
		List<shohincatebean> shohincateList = new ArrayList<shohincatebean>();
		shohincatebean shohincate = null;
		for (String[] shohinbangos : shohinbangoArray) {
			shohincate = new shohincatebean();
			shohincateList.add(shohincate);
			List<String> categoryList = new ArrayList<String>();
			shohincate.setCategoryList(categoryList);
			String shohinbango = shohinbangos[0];
			shohincate.setShohinbango(shohinbango);
			shohincate.setShohinurl(Utility.getPicUrl(shohinbango));
			for (String[] cates : cateArray) {
				String shohinbango2 = cates[1];
				if (shohinbango2.equals(shohinbango)) {
					categoryList.add(cates[3]);
				}
			}
		}
		List<String[]> outdata = new ArrayList<String[]>();
		for (shohincatebean shohin : shohincateList) {
			List<String> categoryList = shohin.getCategoryList();
			for (String cate : categoryList) {
				outdata.add(new String[] { shohin.getShohinbango(), shohin.getShohinurl(), cate });
			}
		}
		Utility.writeCsvFile(outdata, "D://oudata.csv");
	}
}
