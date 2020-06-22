package shohinmodel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import com.rakuten.util.Utility;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class vxcvsdfe {

	public static void main(String[] args) throws Exception {
		 getpic();
//		getpic2();
		// File rootDic = new File("D:\\coverforefront");
		// shori(rootDic.getPath());

		// resizeImage(new File("D:\\nzlyq113_01.jpg"), new File("D:\\out.jpg"),
		// 750, "jpg");

	}

	public static void getpic() throws Exception {
		List<String[]> dateList = Utility.readCsvFile(new File("D:\\item.csv"), false);
		List<String[]> picList = getPicUrl(dateList);
		String shohinbango1 = "";
		List<List<String[]>> shoriListList = new ArrayList<List<String[]>>();
		List<String[]> shoriList = null;
		for (String[] picdate : picList) {
			String shohinbango = picdate[1];
			if (!shohinbango1.equals(shohinbango)) {
				shoriList = new ArrayList<String[]>();
				shoriListList.add(shoriList);
			}
			String path = picdate[0];
			if (path.contains("nouki")) {
				continue;
			}
			path = path.replace("https://image.rakuten.co.jp/coverforefront/cabinet/", "");
			if (path.contains("https://www.rakuten.ne.jp/gold/coverforefront/")) {
				path = path.replace("https://www.rakuten.ne.jp/gold/coverforefront/", "");
			}
			path = "D:\\coverforefront\\" + path;

			shoriList.add(new String[] { shohinbango, path });

			shohinbango1 = shohinbango;
		}

		for (List<String[]> dateArr : shoriListList) {
			String shohinbango = dateArr.get(0)[0];
			String path1 = ("D:\\hebin2\\" + shohinbango + "_sp.jpg");
			System.out.println(shohinbango);
			yPic(dateArr, new File(path1));
		}
	}

	public static void getpic2() throws Exception {
		List<String[]> dateList = Utility.readCsvFile(new File("D:\\item20160325213537.csv"), false);
		List<String[]> picList = getPicUrl(dateList);

		List<String[]> shoriList = new ArrayList<String[]>();
		for (String[] picdate : picList) {

			String path = picdate[0];

			path = path.replace("https://image.rakuten.co.jp/coverforefront/cabinet/", "");
			if (path.contains("https://www.rakuten.ne.jp/gold/coverforefront/")) {
				path = path.replace("https://www.rakuten.ne.jp/gold/coverforefront/", "");
			}

			shoriList.add(new String[] { path });

		}
		Utility.writeCsvFile(shoriList, "d:\\del_piclist.csv");

	}

	public static List<String[]> getPicUrl(List<String[]> csvList_123mart) {
		String shohinsetumeibun = "";
		String picUrl = "";
		boolean ariFlg = false;
		List<String[]> picList = new ArrayList<String[]>();
		for (int i = 0; i < csvList_123mart.size(); i++) {
			shohinsetumeibun = csvList_123mart.get(i)[27];
			while (shohinsetumeibun.contains(".jpg")) {
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf("http"));
				picUrl = shohinsetumeibun.substring(0, shohinsetumeibun.indexOf(".jpg") + 4);
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf(".jpg") + 4);
				for (int j = 0; j < picList.size(); j++) {
					if (picUrl.equals(picList.get(j))) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					picList.add(new String[] { picUrl, csvList_123mart.get(i)[1] });
				}
				ariFlg = false;
			}
		}

		return picList;
	}

	public static void shori(String path) throws Exception {
		File file = new File(path);
		if (file.isDirectory()) {
			String[] paths = file.list();
			for (String apath : paths) {
				shori(file.getPath() + "\\" + apath);
			}
		} else {
			String apath = path.substring(0, path.lastIndexOf("\\")).replace("PIC_DOWNLOAD\\", "");
			File dic = new File(apath);
			if (!dic.exists()) {
				dic.mkdirs();
			}

			System.out.println(path);
			resizeImage(new File(path), new File(path.replace("PIC_DOWNLOAD\\", "")), 750, "jpg");
		}
	}

	public static void yPic(List<String[]> dateArr, File outFile) {// 纵向处理图片
		try {
			List<BufferedImage> imageList = new ArrayList<BufferedImage>();
			List<Integer> widthList = new ArrayList<Integer>();
			List<Integer> heightList = new ArrayList<Integer>();
			List<int[]> imageArrayList = new ArrayList<int[]>();
			BufferedImage bufferedImage = null;
			int[] imageArray = null;
			int allheight = 0;
			for (String[] date : dateArr) {
				if ("D:\\coverforefront\\images/commodity/size-1.jpg".equals(date[1])) {
					continue;
				}
				bufferedImage = ImageIO.read(new File(date[1]));

				imageList.add(bufferedImage);
				int width = bufferedImage.getWidth();// 图片宽度
				widthList.add(width);
				int height = bufferedImage.getHeight();// 图片高度
				heightList.add(height);
				imageArray = new int[width * height];// 从图片中读取RGB
				imageArray = bufferedImage.getRGB(0, 0, width, height, imageArray, 0, width);
				imageArrayList.add(imageArray);
				allheight = allheight + height;
			}

			// 生成新图片
			BufferedImage imageResult = new BufferedImage(750, allheight, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < imageList.size(); i++) {
				if (i == 0) {
					imageResult.setRGB(0, 0, 750, heightList.get(i), imageArrayList.get(i), 0, 750);
				} else {
					int nowheight = 0;
					for (int j = 0; j < i; j++) {
						nowheight = nowheight + heightList.get(j);
					}
					imageResult.setRGB(0, nowheight, 750, heightList.get(i), imageArrayList.get(i), 0, 750);
				}
			}

			ImageIO.write(imageResult, "jpg", outFile);// 写图片
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 改变图片的大小到宽为size，然后高随着宽等比例变化
	 * 
	 * @param is
	 *            上传的图片的输入流
	 * @param os
	 *            改变了图片的大小后，把图片的流输出到目标OutputStream
	 * @param size
	 *            新图片的宽
	 * @param format
	 *            新图片的格式
	 * @throws IOException
	 */
	public static void resizeImage(File is, File os, int size, String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		double percent = size / width;
		int newWidth = (int) (width * percent);
		int newHeight = (int) (height * percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);

		FileOutputStream newimage = new FileOutputStream(os); // 输出到文件流
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
//		JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);
//		jep.setQuality((float) 1.0, true);
//		encoder.encode(image, jep);
		newimage.close();
	}

}
