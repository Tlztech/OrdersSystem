package com.rakuten.r0401.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.Utility;

public class A04010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	// file
	private File inputPath1 = null;

	private String exetype = null;

	private String tenpobetu = null;

	private boolean tuiguang = false;

	protected void exec() throws Exception {
		if ("0".equals(tenpobetu)) {
			tenpobetu = "123mart";
		} else if ("1".equals(tenpobetu)) {
			tenpobetu = "3eshop";
		}
		HSSFRow row = null;
		HSSFSheet sheet = null;
		HSSFWorkbook workbook = new HSSFWorkbook(
				new FileInputStream(inputPath1));
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			// Excel
			sheet = workbook.getSheetAt(i);
			row = sheet.getRow(1);

			// 商品管理番号
			String spglfh = row.getCell(1).getStringCellValue();

			// 商品名
			String spm = row.getCell(2).getStringCellValue();

			// PC用キャッチコピー
			String pcycpy = row.getCell(3).getStringCellValue();

			// モバイル用キャッチコピー
			String mbycoy = row.getCell(4).getStringCellValue();

			// 販売価格
			String fmjg = row.getCell(5).getStringCellValue();

			// 表示価格
			String bsjg = row.getCell(6).getStringCellValue();

			// 送料
			String sl = row.getCell(7).getStringCellValue();

			// 全商品ディレクトリID
			String qsptid = row.getCell(8).getStringCellValue();

			// 画像数量
			String hxsl = row.getCell(9).getStringCellValue();

			// 画像フォルダ
			String hxszwjj = row.getCell(10).getStringCellValue();

			// 画像１
			String hx1 = row.getCell(11).getStringCellValue();

			// 画像２
			String hx2 = row.getCell(12).getStringCellValue();

			// 画像３
			String hx3 = row.getCell(13).getStringCellValue();

			row = sheet.getRow(4);

			// 商品詳細１（名称）
			String spxxms1 = row.getCell(1).getStringCellValue();

			// 商品詳細１（内容）
			String spxxny1 = row.getCell(2).getStringCellValue();

			// 商品詳細２（名称）
			String spxxms2 = row.getCell(3).getStringCellValue();

			// 商品詳細２（内容）
			String spxxny2 = row.getCell(4).getStringCellValue();

			// 商品詳細３（名称）
			String spxxms3 = row.getCell(5).getStringCellValue();

			// 商品詳細３（内容）
			String spxxny3 = row.getCell(6).getStringCellValue();

			// 商品詳細４（名称）
			String spxxms4 = row.getCell(7).getStringCellValue();

			// 商品詳細４（内容）
			String spxxny4 = row.getCell(8).getStringCellValue();

			// 商品詳細５（名称）
			String spxxms5 = row.getCell(9).getStringCellValue();

			// 商品詳細５（内容）
			String spxxny5 = row.getCell(10).getStringCellValue();

			// 商品詳細６（名称）
			String spxxms6 = row.getCell(11).getStringCellValue();

			// 商品詳細６（内容）
			String spxxny6 = row.getCell(12).getStringCellValue();

			row = sheet.getRow(7);

			// 表示先カテゴリ1
			String category1 = row.getCell(1).getStringCellValue();

			// 表示先カテゴリ2
			String category2 = row.getCell(2).getStringCellValue();

			// 表示先カテゴリ3
			String category3 = row.getCell(3).getStringCellValue();

			// 表示先カテゴリ4
			String category4 = row.getCell(4).getStringCellValue();

			row = sheet.getRow(10);

			// 横軸項目名
			String hzxmm = row.getCell(1).getStringCellValue();

			// 縦軸項目名
			String zzxmm = row.getCell(2).getStringCellValue();

			// 横軸選択肢1-20

			List<String> yokoList111 = new ArrayList<String>();
			row = sheet.getRow(12);
			for (int j = 1; j < 21; j++) {
				if (row.getCell(j) != null) {
					if (!Utility.isEmptyString(row.getCell(j)
							.getStringCellValue())) {
						yokoList111.add(row.getCell(j).getStringCellValue());
					}
				}
			}

			List<String> shitagaList111 = new ArrayList<String>();
			row = sheet.getRow(14);
			for (int j = 1; j < 21; j++) {
				if (row.getCell(j) != null) {
					if (!Utility.isEmptyString(row.getCell(j)
							.getStringCellValue())) {
						shitagaList111.add(row.getCell(j).getStringCellValue());
					}
				}
			}

			// output
			List<String> outPutList = new ArrayList<String>();

			outPutList
					.add("javascript:void(document.getElementsByName('mng_number')[0].value='"
							+ spglfh.trim() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('item_number')[0].value='"
							+ spglfh.trim() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('item_name')[0].value='"
							+ spm.trim() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('catch_copy')[0].value='"
							+ pcycpy.trim() + "<br>');");
			outPutList
					.add("javascript:void(document.getElementsByName('mobile_catch_copy')[0].value='"
							+ mbycoy.trim() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('price')[0].value='"
							+ fmjg + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('regular_price')[0].value='"
							+ bsjg + "');");
			if (!"送料込".equals(sl.trim())) {
				outPutList
						.add("javascript:void(document.getElementsByName('postage_flag')[0].checked='true');");
			} else {
				outPutList
						.add("javascript:void(document.getElementsByName('postage_flag')[1].checked='true');");
			}
			StringBuffer sb1 = new StringBuffer();
			sb1.append("商品名<br>");
			sb1.append(spm + "<br>");
			if (spxxms1 != null && !"".equals(spxxms1)) {
				sb1.append(spxxms1 + "<br>");
				sb1.append(replaceStr(spxxny1) + "<br><br>");
			}
			if (spxxms2 != null && !"".equals(spxxms2)) {
				sb1.append(spxxms2 + "<br>");
				sb1.append(replaceStr(spxxny2) + "<br><br>");
			}
			if (spxxms3 != null && !"".equals(spxxms3)) {
				sb1.append(spxxms3 + "<br>");
				sb1.append(replaceStr(spxxny3) + "<br><br>");
			}
			if (spxxms4 != null && !"".equals(spxxms4)) {
				sb1.append(spxxms4 + "<br>");
				sb1.append(replaceStr(spxxny4) + "<br><br>");
			}
			if (spxxms5 != null && !"".equals(spxxms5)) {
				sb1.append(spxxms5 + "<br>");
				sb1.append(replaceStr(spxxny5) + "<br><br>");
			}
			if (spxxms6 != null && !"".equals(spxxms6)) {
				sb1.append(spxxms6 + "<br>");
				sb1.append(replaceStr(spxxny6) + "<br><br>");
			}

			StringBuffer sb10 = new StringBuffer();
			sb10.append("商品名<br>");
			sb10.append(spm + "<br>");
			if (spxxms1 != null && !"".equals(spxxms1)) {
				sb10.append(spxxms1 + "<br>");
				sb10.append(replaceStr(spxxny1) + "<br><br>");
			}
			if (spxxms2 != null && !"".equals(spxxms2)) {
				sb10.append(spxxms2 + "<br>");
				sb10.append(replaceStr(spxxny2) + "<br><br>");
			}

			StringBuffer sb2 = new StringBuffer();
			sb2.append("<style type=\"text/css\"><!--.STYLE1 {font-family: \"MS PGothic\"}--></style><table align=\"center\">");
			int index = Double.valueOf(hxsl).intValue();

			for (int j = 0; j < index; j++) {
				sb2.append("<tr><td><img src=\"http://image.rakuten.co.jp/"
						+ tenpobetu + "/cabinet/");
				sb2.append(hxszwjj + "/");
				sb2.append(spglfh.trim() + "_"
						+ numberPadding(String.valueOf(j + 1)) + ".jpg\"");
				sb2.append(" width=\"650\"></img></td></tr>");
			}
			if ("4".equals(exetype)) {
				sb2.append("<tr><td><img src=\"http://image.rakuten.co.jp/"
						+ tenpobetu + "/cabinet/");
				sb2.append("123mart/nzpy/model_common1.jpg\"");
				sb2.append(" width=\"650\"></img></td></tr>");

				sb2.append("<tr><td><img src=\"http://image.rakuten.co.jp/"
						+ tenpobetu + "/cabinet/");
				sb2.append("123mart/nzpy/xfpy_common.jpg\"");
				sb2.append(" width=\"650\"></img></td></tr>");
			}

			sb2.append("<table width=\"600px\" align=\"center\" style=\"font-size:12px\" cellpadding=\"0\" cellspacing=\"0\">");
			sb2.append("<tr height=\"35px\">");
			sb2.append("<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;background-color:#B2B2B2\">商品名</td>");
			sb2.append("<td style=\"border-top:1px solid #000;border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(spm);

			if (spxxms1 != null && !"".equals(spxxms1)) {
				sb2.append("<tr height=\"35px\">");
				sb2.append("<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
				sb2.append(spxxms1);
				sb2.append("</td>");
				sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
				sb2.append(spxxny1.replaceAll("\n", "<br>"));
				sb2.append("</td></tr>");
			}

			if (spxxms2 != null && !"".equals(spxxms2)) {
				sb2.append("<tr height=\"35px\">");
				sb2.append("<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
				sb2.append(spxxms2);
				sb2.append("</td>");
				sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
				sb2.append(spxxny2.replaceAll("\n", "<br>"));
				sb2.append("</td></tr>");
			}

			if (spxxms3 != null && !"".equals(spxxms3)) {
				sb2.append("<tr height=\"35px\">");
				sb2.append("<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
				sb2.append(spxxms3);
				sb2.append("</td>");
				sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
				sb2.append(spxxny3.replaceAll("\n", "<br>"));
				sb2.append("</td></tr>");
			}

			if (spxxms4 != null && !"".equals(spxxms4)) {
				sb2.append("<tr height=\"35px\">");
				sb2.append("<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
				sb2.append(spxxms4);
				sb2.append("</td>");
				sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
				sb2.append(spxxny4.replaceAll("\n", "<br>"));
				sb2.append("</td></tr>");
			}

			if (spxxms5 != null && !"".equals(spxxms5)) {
				sb2.append("<tr height=\"35px\">");
				sb2.append("<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
				sb2.append(spxxms5);
				sb2.append("</td>");
				sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
				sb2.append(spxxny5.replaceAll("\n", "<br>"));
				sb2.append("</td></tr>");
			}
			if (spxxms6 != null && !"".equals(spxxms6)) {
				sb2.append("<tr height=\"35px\">");
				sb2.append("<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
				sb2.append(spxxms6);
				sb2.append("</td>");
				sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
				sb2.append(spxxny6.replaceAll("\n", "<br>"));
				sb2.append("</td></tr>");
			}
			sb2.append("</table>");

			

			row = sheet.getRow(17);
			if (row != null && row.getCell(0) != null
					&& "size".equals(row.getCell(0).getStringCellValue())) {

				List<String> yokoList = new ArrayList<String>();
				for (int k = 2; row.getCell(k) != null; k++) {
					yokoList.add(row.getCell(k).getStringCellValue());
				}

				List<String> shitagaList = new ArrayList<String>();
				for (int k = 18; sheet.getRow(k) != null; k++) {
					shitagaList.add(sheet.getRow(k).getCell(1)
							.getStringCellValue());
				}

				List<List<String>> naiyoList = new ArrayList<List<String>>();
				for (int k = 0; k < shitagaList.size(); k++) {
					List<String> naiyo = new ArrayList<String>();
					for (int j = 0; j < yokoList.size(); j++) {
						sheet.getRow(18 + k).getCell(2 + j)
								.setCellType(Cell.CELL_TYPE_STRING);
						naiyo.add(sheet.getRow(18 + k).getCell(2 + j)
								.getStringCellValue());
					}
					naiyoList.add(naiyo);
				}

				sb2.append("<br>");
				sb2.append("<table width=\"550px\" align=\"center\" style=\"font-size:12px\" cellpadding=\"0\" cellspacing=\"0\">");
				sb2.append("<tr height=\"35px\">");
				sb2.append("<td width=\"50px\" style=\"border:1px solid #000;\">&nbsp;</td>");
				for (int k = 0; k < yokoList.size(); k++) {
					sb2.append("<td width=\"93px\" align=\"center\" style=\"border-top:1px solid #000;border-bottom:1px solid #000;border-right:1px solid #000;background-color:#B2B2B2\">");
					sb2.append(yokoList.get(k));
					sb2.append("</td>");
				}
				sb2.append("</tr>");

				for (int k = 0; k < shitagaList.size(); k++) {
					sb2.append("<tr height=\"35px\">");
					sb2.append("<td align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
					sb2.append(shitagaList.get(k));
					sb2.append("</td>");
					for (int j = 0; j < yokoList.size(); j++) {
						sb2.append("<td align=\"center\" style=\"border-bottom:1px solid #000;border-right:1px solid #000;\">");
						sb2.append(naiyoList.get(k).get(j));
						sb2.append("</td>");
					}
					sb2.append("</tr>");
				}
				sb2.append("</table>");

				sb1.append("サイズ:<br>");
				sb1.append("<table border=\"1\" width=\"320px\">");
				sb1.append("<tr>");
				sb1.append("<td align=\"center\" width=\"15%\">&nbsp;</td>");
				for (int k = 0; k < yokoList.size(); k++) {
					sb1.append("<td align=\"center\">");
					sb1.append(yokoList.get(k));
					sb1.append("</td>");
				}
				sb1.append("</tr>");

				for (int k = 0; k < shitagaList.size(); k++) {
					sb1.append("<tr>");
					sb1.append("<td align=\"center\" width=\"15%\">");
					sb1.append(shitagaList.get(k));
					sb1.append("</td>");
					for (int j = 0; j < yokoList.size(); j++) {
						sb1.append("<td align=\"center\">");
						sb1.append(naiyoList.get(k).get(j));
						sb1.append("</td>");
					}
					sb1.append("</tr>");
				}
				sb1.append("</table>");

				sb10.append("サイズ:<br>");
				sb10.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				for (int k = 0; k < yokoList.size(); k++) {
					sb10.append(yokoList.get(k));
					sb10.append("&nbsp;&nbsp;&nbsp;");
				}
				sb10.append("<br>");

				for (int k = 0; k < shitagaList.size(); k++) {
					sb10.append(shitagaList.get(k));
					sb10.append("&nbsp;&nbsp;&nbsp;");
					for (int j = 0; j < yokoList.size(); j++) {
						sb10.append(naiyoList.get(k).get(j));
						sb10.append("&nbsp;&nbsp;&nbsp;");
					}
					sb10.append("<br>");
				}

			}
			if ("4".equals(exetype) || "5".equals(exetype)) {
				sb2.append("<tr><td><img src=\"http://image.rakuten.co.jp/"
						+ tenpobetu + "/cabinet/");
				sb2.append("123mart/nzpy/common2.jpg\"");
				sb2.append(" width=\"650\"></img></td></tr>");
			}
			if ("1".equals(exetype)) {
				// 衣服尺寸
				sb2.append("<table><tr><td><img width=\"650px\" src=\"http://www.rakuten.ne.jp/gold/"
						+ tenpobetu
						+ "/images/commodity/size-1.jpg\"></td></tr></table>");
			} else if ("2".equals(exetype)) {
				sb2.append("<table><tr><td><img width=\"650px\" src=\"http://www.rakuten.ne.jp/gold/"
						+ tenpobetu + "/images/review.jpg\"></td></tr></table>");
			}
			outPutList
					.add("javascript:void(document.getElementsByName('mobile_caption')[0].value='"
							+ sb10.toString() + "');");

			outPutList
					.add("javascript:void(document.getElementsByName('smart_caption')[0].value='"
							+ sb1.toString() + "');");

			StringBuffer sb3 = new StringBuffer();
			sb3.append("http://image.rakuten.co.jp/" + tenpobetu + "/cabinet/");
			sb3.append(hxszwjj + "/");
			// todo
			sb3.append(spglfh.trim() + "_" + numberPadding(String.valueOf(hx1))
					+ ".jpg");

			StringBuffer sb4 = new StringBuffer();
			sb4.append("http://image.rakuten.co.jp/" + tenpobetu + "/cabinet/");
			sb4.append(hxszwjj + "/");
			sb4.append(spglfh.trim() + "_" + numberPadding(String.valueOf(hx2))
					+ ".jpg");

			StringBuffer sb5 = new StringBuffer();
			sb5.append("http://image.rakuten.co.jp/" + tenpobetu + "/cabinet/");
			sb5.append(hxszwjj + "/");
			sb5.append(spglfh.trim() + "_" + numberPadding(String.valueOf(hx3))
					+ ".jpg");
			outPutList
					.add("javascript:void(document.getElementsByName('display_caption')[0].value='"
							+ sb2.toString() + "');");

			outPutList
					.add("javascript:void(document.getElementsByName('image_url1')[0].value='"
							+ sb3.toString() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('image_url2')[0].value='"
							+ sb4.toString() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('image_url3')[0].value='"
							+ sb5.toString() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('image_alt1')[0].value='"
							+ spm.trim() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('image_alt2')[0].value='"
							+ spm.trim() + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('image_alt3')[0].value='"
							+ spm.trim() + "');");

			outPutList
					.add("javascript:void(document.getElementsByName('genre_id')[0].value='"
							+ qsptid.trim() + "');");

			outPutList
					.add("javascript:void(document.getElementsByName('category_1')[0].value='"
							+ category1.trim() + "');");

			if (!Utility.isEmptyString(category2)) {
				outPutList
						.add("javascript:void(document.getElementsByName('category_2')[0].value='"
								+ category2.trim() + "');");
			}
			if (!Utility.isEmptyString(category3)) {
				outPutList
						.add("javascript:void(document.getElementsByName('category_3')[0].value='"
								+ category3.trim() + "');");
			}
			if (!Utility.isEmptyString(category4)) {
				outPutList
						.add("javascript:void(document.getElementsByName('category_4')[0].value='"
								+ category4.trim() + "');");
			}
			outPutList
					.add("javascript:void(document.getElementsByName('ha_name')[0].value='"
							+ hzxmm.trim() + "');");

			if (!Utility.isEmptyString(hzxmm)) {
				outPutList
						.add("javascript:void(document.getElementsByName('va_name')[0].value='"
								+ zzxmm.trim() + "');");
			}
			outPutList
					.add("javascript:void(document.getElementsByName('inventory_type')[2].checked='"
							+ "true" + "');");
			outPutList
					.add("javascript:void(document.getElementsByName('inventory_disp_type')[1].checked='"
							+ "true" + "');");

			outPutList
					.add("javascript:void(document.getElementsByName('smart_caption')[0].value = ('<table>'+'<tr><td><img width=\"310\" src='+document.getElementsByName('image_url1')[0].value+' /></td></tr>'+'<tr><td><img width=\"310\" src='+document.getElementsByName('image_url2')[0].value+' /></td></tr>'+'<tr><td><img width=\"310\" src='+document.getElementsByName('image_url3')[0].value+' /></td>'+'</tr></table>'+document.getElementsByName('smart_caption')[0].value));");

			outPutList
					.add("javascript:void(document.getElementsByName('item_weight')[0].value = '5');");

			if (tuiguang) {

				String setumeibun = "<style type=\"text/css\"><!--a:hover img{filter: alpha(opacity=70);-moz-opacity:0.7;opacity:0.7;}--></style>";
				setumeibun += "<table align=\"center\"><tr><td><a href=\"";
				setumeibun += "http://item.rakuten.co.jp/3eshop/";
				setumeibun += spglfh;
				setumeibun += "\" target=\"_top\"><img border=\"0\" src=\"http://www.rakuten.ne.jp/gold/123mart/images/3e_link.jpg\"></img></a></td></tr></table>";
				outPutList
						.add("javascript:void(document.getElementsByName('display_caption')[0].value += '"
								+ setumeibun + "');");

				setumeibun = "<table align=\"center\"><tr><td><a href=\"";
				setumeibun += "http://item.rakuten.co.jp/3eshop/";
				setumeibun += spglfh;
				setumeibun += "\" target=\"_top\"><img width=\"500px\" border=\"0\" src=\"http://www.rakuten.ne.jp/gold/123mart/images/3e_link.jpg\"></img></a></td></tr></table>";
				outPutList
						.add("javascript:void(document.getElementsByName('catalog_caption')[0].value += '"
								+ setumeibun + "');");

				setumeibun = "<br><br><a href=\"http://item.rakuten.co.jp/3eshop/"
						+ spglfh
						+ "\" target=\"_top\"><font size=\"+1\" color=\"black\"><b>同商品姉妹店舗3Eショップでご購入の場合</b></font><br><font size=\"+3\" color=\"red\">10%OFF＋10倍ポイント獲得</font><br><font size=\"+2\" color=\"blue\">マジで？！</font><br><font size=\"+1\">下記のリンクをクリックして3Eショップで買おう (ﾟoﾟ;;<br>"
						+ "http://item.rakuten.co.jp/3eshop/"
						+ spglfh
						+ "</font></a>";
				outPutList
						.add("javascript:void(document.getElementsByName('smart_caption')[0].value += '"
								+ setumeibun + "');");

			}

			File dir = new File("D://output");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File("D://output/" + spglfh + ".txt");
			file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					file));
			for (String str : outPutList) {
				str = str.replace("\\", "\\\\");
				bufferedWriter.write(str);
			}

			List<String> outputList2 = new ArrayList<String>();
			for (int k = 0; k < yokoList111.size(); k++) {
				outputList2
						.add("javascript:void(document.getElementsByTagName('input')["
								+ k
								+ "].value = '"
								+ yokoList111.get(k)
								+ "');");
			}
			for (int k = 0; k < shitagaList111.size(); k++) {
				outputList2
						.add("javascript:void(document.getElementsByTagName('input')["
								+ (k + 40)
								+ "].value = '"
								+ shitagaList111.get(k) + "');");
			}
			File file2 = new File("D://output/" + spglfh + "_select.txt");
			file.createNewFile();
			BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(
					file2));
			for (String str : outputList2) {
				str = str.replace("\\", "\\\\");
				bufferedWriter2.write(str);
			}

			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedWriter2.flush();
			bufferedWriter2.close();
		}

	}

	protected void init() {
		setTitle("V040101:上架代码生成");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the inputPath1
	 */
	public File getInputPath1() {
		return inputPath1;
	}

	/**
	 * @param inputPath1
	 *            the inputPath1 to set
	 */
	public void setInputPath1(File inputPath1) {
		this.inputPath1 = inputPath1;
	}

	private String replaceStr(String str) {
		return str.replaceAll("\n", "<br>");
	}

	private String numberPadding(String str) {
		return str.length() == 1 ? "0" + str : str;
	}

	public String getExetype() {
		return exetype;
	}

	public void setExetype(String exetype) {
		this.exetype = exetype;
	}

	/**
	 * @return the tenpobetu
	 */
	public String getTenpobetu() {
		return tenpobetu;
	}

	/**
	 * @param tenpobetu
	 *            the tenpobetu to set
	 */
	public void setTenpobetu(String tenpobetu) {
		this.tenpobetu = tenpobetu;
	}

	public boolean isTuiguang() {
		return tuiguang;
	}

	public void setTuiguang(boolean tuiguang) {
		this.tuiguang = tuiguang;
	}

}
