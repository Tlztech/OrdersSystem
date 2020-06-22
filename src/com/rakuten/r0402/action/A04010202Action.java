package com.rakuten.r0402.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.rakuten.common.action.BaseAction;

public class A04010202Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String inputText2 = null;
	private String mngnumber = null;
	private String picUrl = null;;

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	protected void exec() throws Exception {
		try {
			inputText2 = inputText2.replaceAll("\\\\", "\\\\\\\\");
			inputText2 = inputText2.replaceAll("【10P24Aug13】", "");
			inputText2 = inputText2.replaceAll("#F9C", "#000");
			inputText2 = inputText2.replaceAll("#FCC", "#CAC4BD");
			inputText2 = inputText2.replaceAll("#09F", "#808080");
			inputText2 = inputText2.replaceAll("color:white;", "");
			inputText2 = inputText2.replaceAll("6CF", "DBDBDB");
			File dir = new File("D://output");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File("D://output/" + mngnumber + "_01.txt");
			file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					file));

			bufferedWriter.write(inputText2);
			bufferedWriter.flush();
			bufferedWriter.close();

			File file1 = new File("D://output/" + "picUrl" + ".txt");
			file1.createNewFile();
			BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(
					file1));

			bufferedWriter1.write(picUrl);
			bufferedWriter1.flush();
			bufferedWriter1.close();

			InputStream file2 = new FileInputStream(new File(
					"D:\\work\\workspace\\OrdersSystem\\WebContent\\conv\\"
							+ mngnumber + "_02.htm"));
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(file2, "x-euc-jp"));
			String str = "";
			String tmp = "";
			while ((tmp = bufferedReader.readLine()) != null) {
				str += (tmp + "\r\n");
			}
			String stock = str;
			int i = str.indexOf("<tr align=\"center\" bgcolor=\"#FFC670\">");
			str = str.substring(i);
			i = str.indexOf("</tr>");
			String yoko = str.substring(0, i + 5);
			yoko = yoko.replaceAll("<td><font size=\"-1\">", "");
			yoko = yoko.replaceAll("</font></td>", "");
			yoko = yoko.replaceAll("&nbsp;", "");
			yoko = yoko.replaceAll("<tr align=\"center\" bgcolor=\"#FFC670\">",
					"");
			yoko = yoko.replaceAll("</tr>", "");
			yoko = yoko.replaceAll("\r\n\r\n", "\r\n");
			yoko = yoko.replaceAll("\r\n\r\n", "\r\n");
			if (yoko.startsWith("\r\n")) {
				yoko = yoko.replaceFirst("\r\n", "");
			}
			String[] yokoArray = yoko.split("\r\n");
			String[] shitagaArray = null;
			String temp = "";
			String shitaga = "";
			while (str
					.indexOf("<td align=\"center\" bgcolor=\"#FFC670\" nowrap>") != -1) {
				str = str
						.substring(str
								.indexOf("<td align=\"center\" bgcolor=\"#FFC670\" nowrap>"));
				i = str.indexOf("</td>");
				shitaga = str.substring(0, i + 5);
				shitaga = shitaga.replaceAll("<font size=\"-1\">", "");
				shitaga = shitaga.replaceAll("</font>", "");
				shitaga = shitaga.replaceAll(
						"<td align=\"center\" bgcolor=\"#FFC670\" nowrap>", "");
				shitaga = shitaga.replaceAll("</td>", "");

				str = str.substring(i + 5);
				temp += shitaga + "\r\n";
			}
			if (!"".equals(temp)) {
				shitagaArray = temp.split("\r\n");
			}
			String tppp2 = "";
			while (stock.indexOf("<td valign=\"top\"><font size=\"-1\">") != -1) {
				stock = stock.substring(stock
						.indexOf("<td valign=\"top\"><font size=\"-1\">"));
				String tppp = stock.substring(0, stock.indexOf("<br>") + 4);

				tppp = tppp
						.replace("<td valign=\"top\"><font size=\"-1\">", "");
				tppp = tppp.replace("<br>", "");
				tppp2 += tppp.substring(6).trim() + "/r/n";
				stock = stock.replaceFirst(
						"<td valign=\"top\"><font size=\"-1\">", "");
			}
			String[] stockArr = tppp2.split("/r/n");

			String output2 = "";
			for (int k = 0; k < yokoArray.length; k++) {
				output2 += "javascript:void(document.getElementsByName('choice_name_hor_"
						+ String.valueOf(k + 1)
						+ "')[0].value='"
						+ yokoArray[k].split("/-")[0] + "');";
				output2 += "javascript:void(document.getElementsByName('child_no_hor_"
						+ String.valueOf(k + 1)
						+ "')[0].value='"
						+ yokoArray[k].split("/-")[1] + "');";
			}
			if (shitagaArray != null) {
				for (int k = 0; k < shitagaArray.length; k++) {
					output2 += "javascript:void(document.getElementsByName('choice_name_ver_"
							+ String.valueOf(k + 1)
							+ "')[0].value='"
							+ shitagaArray[k].split("/-")[0] + "');";
					output2 += "javascript:void(document.getElementsByName('child_no_ver_"
							+ String.valueOf(k + 1)
							+ "')[0].value='"
							+ shitagaArray[k].split("/-")[1] + "');";
				}
			}
			output2 += "javascript:void(document.form[0].submit());";
			File file3 = new File("D://output/" + mngnumber + "_02.txt");
			file3.createNewFile();
			BufferedWriter bufferedWriter3 = new BufferedWriter(new FileWriter(
					file3));

			bufferedWriter3.write(output2);
			bufferedWriter3.flush();
			bufferedWriter3.close();

			int yokosu = yokoArray.length;
			int shitgasu = 0;
			if (shitagaArray != null) {
				shitgasu = shitagaArray.length;
			}
			String output3 = "";
			if (shitgasu == 0) {
				for (int h = 0; h < yokosu; h++) {
					output3 += "javascript:void(document.getElementsByName('inventory_"
							+ String.valueOf(h + 1) + "_0"
							+ "')[0].value='" + stockArr[h] + "');";
				}
			} else {
				int b = 0;
				for (int m = 0; m < shitgasu; m++) {
					for (int h = 0; h < yokosu; h++) {
						output3 += "javascript:void(document.getElementsByName('inventory_"
								+ String.valueOf(h + 1)
								+ "_"
								+ String.valueOf(m + 1)
								+ "')[0].value='"
								+ stockArr[b] + "');";
						b++;
					}
				}
			}
			output3 += "javascript:void(document.getElementsByName(\"inventory_update_form\")[0].submit());";

			File file4 = new File("D://output/" + mngnumber + "_03.txt");
			file4.createNewFile();
			BufferedWriter bufferedWriter4 = new BufferedWriter(new FileWriter(
					file4));

			bufferedWriter4.write(output3);
			bufferedWriter4.flush();
			bufferedWriter4.close();
		} catch (Exception e) {
			File file5 = new File("D://output/log.txt");
			BufferedWriter bufferedWriter4 = new BufferedWriter(new FileWriter(
					file5,true));

			bufferedWriter4.write(mngnumber);
			bufferedWriter4.flush();
			bufferedWriter4.close();
			e.printStackTrace();
		}
	}

	protected void init() {
		setTitle("V040201:复制代码生成");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getInputText2() {
		return inputText2;
	}

	public void setInputText2(String inputText2) {
		this.inputText2 = inputText2;
	}

	public String getMngnumber() {
		return mngnumber;
	}

	public void setMngnumber(String mngnumber) {
		this.mngnumber = mngnumber;
	}

}
