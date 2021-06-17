package com.rakuten.r1301.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rakuten.common.DeliveryCompany;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0602.bean.DetailListBean;
import com.rakuten.r0602.bean.OrderBean;
import com.rakuten.r1001.form.DokonList;
import com.rakuten.r1001.form.F100102;
import com.rakuten.r1001.form.ShohinList;
import com.rakuten.r1301.form.F130101;
import com.rakuten.r1301.form.OrderList;
import com.rakuten.util.DetailTicketPdfUtil;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A13010103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130101 f130101 = null;
	private String fileName = null;
	String dirName = null;
	String fileNameMerubin = null;
	String fileNameYamatoTakubin = null;
	String fileNameSagawaTakubin = null;
	List<OrderBean> orderList1 = null;
	private boolean okurijonomi = false;

	protected void exec_SourceVersion() throws Exception {

		outCsv(orderList1);
		if (!okurijonomi) {
			setPrintzumi();

			HttpServletRequest request = ServletActionContext.getRequest();
			String basePath = request.getSession().getServletContext().getRealPath("/");
			Utility.copyFile(basePath + "/WEB-INF/classes/jquery-1.3.2.min.js", "c:/temp/" + dirName + "/",
					"jquery-1.3.2.min.js");
			Utility.copyFile(basePath + "/WEB-INF/classes/jquery-barcode-last.min.js", "c:/temp/" + dirName + "/",
					"jquery-barcode-last.min.js");
		}
		fileName = "out_" + dirName + ".zip";
		FileOutputStream fileOutputStream = new FileOutputStream(new File("c:/temp/" + fileName));
		CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
		ZipOutputStream out = new ZipOutputStream(cos);
		String basedir = "";
		Utility.compress(new File("c:/temp/" + dirName), out, basedir);
		out.close();
	}
	
	protected void exec() throws Exception {

		outCsv(orderList1);
		if (!okurijonomi) {
			setPrintzumi();
		}
		fileName = "out_" + dirName + ".zip";
		FileOutputStream fileOutputStream = new FileOutputStream(new File("c:/temp/" + fileName));
		CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
		ZipOutputStream out = new ZipOutputStream(cos);
		String basedir = "";
		Utility.compress(new File("c:/temp/" + dirName), out, basedir);
		out.close();
	}

	private void setPrintzumi() throws Exception {
		Connection conn = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "delete from tbl00025 where chumonbango = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			for (OrderBean order : orderList1) {
				ps.setString(1, order.getJuchubango());
				ps.execute();
			}
			sql = "insert into tbl00025 values(?)";
			ps = conn.prepareStatement(sql);
			String bango = "";
			for (int i = 0; i < orderList1.size(); i++) {
				bango = orderList1.get(i).getJuchubango();
				ps.setString(1, bango);
				ps.execute();
			}
			sql = "delete from kaisha_size_tbl where juchubango = ?";
			ps = conn.prepareStatement(sql);
			for (OrderBean order : orderList1) {
				ps.setString(1, order.getJuchubango());
				ps.execute();
			}

			sql = "insert into kaisha_size_tbl values(?,?,?)";
			ps = conn.prepareStatement(sql);
			for (OrderBean order : orderList1) {
				ps.setString(1, order.getJuchubango());
				ps.setString(2, order.getUnsokaisha());
				ps.setString(3, order.getSize());
				ps.execute();
			}

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	private List<OrderBean> getOrderList(List<OrderList> orderList_temp) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<OrderBean> orderList = new ArrayList<OrderBean>();
			DetailListBean detail = null;
			List<DetailListBean> detailList = null;
			OrderBean Order = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T2 ON T1.CHUMONBANGO = T2.JUCHUBANGO WHERE T1.CHUMONBANGO = ? ";
			for (int i = 0; i < orderList_temp.size(); i++) {
				String juchubango = orderList_temp.get(i).getChumonbango();
				String size = orderList_temp.get(i).getSize();
				String unsokaisha = orderList_temp.get(i).getUnsokaisha();
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ResultSet rs = ps.executeQuery();
				String juchubangocomp = "";
				while (rs.next()) {
					String juchubangotemp = rs.getString("T1.CHUMONBANGO");
					if (!juchubangotemp.equals(juchubangocomp)) {
						juchubangocomp = juchubangotemp;
						detailList = new ArrayList<DetailListBean>();
						Order = new OrderBean();
						Order.setDetailList(detailList);
						orderList.add(Order);

						// 受注番号
						Order.setJuchubango(rs.getString("T1.CHUMONBANGO"));
						// お届け時間帯
						Order.setOtodokejikandai(rs.getString("T1.OTODOKEJIKANTAI1"));
						// お届け日指定
						Order.setOtodokebishitei(rs.getString("OTODOKEBISHITEI"));
						// ひとことメモ
						// Order.setHitokotomemo(rs.getString(""));
						// 注文日時
						Order.setChumonnichiji(rs.getString("T1.CHUMONNICHIJI"));
						// 合計金額(-99999=無効値)
						Order.setGokeikingaku(rs.getString("T1.SEIKYUKINGAKU"));
						// 決済方法
						Order.setKesaihoho(rs.getString("T1.OSHIHARAISTS"));
						// 配送方法
						Order.setHaisouhoho(rs.getString("T1.HAISOUHOHO"));
						// 送付先郵便番号１
						Order.setSofusakiyubinbango1(rs.getString("T1.SOUFUSAKIYUBINBANGO1"));
						// 送付先郵便番号２
						Order.setSofusakiyubinbango2(rs.getString("T1.SOUFUSAKIYUBINBANGO2"));
						// 送付先住所：都道府県
						Order.setSofusakijusho1(rs.getString("T1.SOUFUSAKIJUSHOTODOFUKEN"));
						// 送付先住所：都市区
						Order.setSofusakijusho2(rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU"));
						// 送付先住所：町以降
						Order.setSofusakijusho3(rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU"));
						// 送付先名字
						Order.setSofusakimeiji(rs.getString("T1.SOFUSAKIMEIJI"));
						// 送付先名前
						Order.setSofusakinamae(rs.getString("T1.SOUFUSAKINAMAE"));
						// 送付先名字フリガナ
						Order.setSofusakimeijifurigana(rs.getString("T1.SOUFUSAKIMEIJIFURIGANA"));
						// 送付先名前フリガナ
						Order.setSofusakinamaefurigana(rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA"));
						// 送付先電話番号１
						Order.setSofusakidenwabango1(rs.getString("T1.SOFUSAKIDENWABANGO1"));
						// 送付先電話番号２
						Order.setSofusakidenwabango2(rs.getString("T1.SOFUSAKIDENWABANGO2"));
						// 送付先電話番号３
						Order.setSofusakidenwabango3(rs.getString("T1.SOFUSAKIDENWABANGO3"));
						// コメント
						Order.setKomento(rs.getString("T1.KOMENTO"));

						// 店舗別
						Order.setTenpobetsu(rs.getString("T1.SHOP"));

						Order.setSite(rs.getString("T1.SITE"));

						Order.setSize(size);
						Order.setUnsokaisha(unsokaisha);
					}
					// 商品詳細リスト
					detail = new DetailListBean();
					detailList.add(detail);

					// 商品名
					detail.setShouhinmei(rs.getString("T2.SHOUHINMEI"));
					// 商品番号
					detail.setShouhinbango(rs.getString("T2.SHOUHINBANGO"));
					// 単価
					detail.setTankaku(rs.getString("T2.TANKA"));
					// 個数
					detail.setKosu(rs.getString("T2.KOSU"));
					// 項目・選択肢
					detail.setKomokusentakushi(rs.getString("T2.KOMOKUSENTAKUSHI"));
				}
			}
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private void outCsv_SourceVersion(List<OrderBean> orderList) throws IOException, ParseException {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		fileNameMerubin = "Merubin_" + df.format(new Date()) + ".csv";
		fileNameSagawaTakubin = "Takkyubin_Sagawa" + df.format(new Date()) + ".csv";
		fileNameYamatoTakubin = "Takkyubin_Yamato" + df.format(new Date()) + ".csv";

		File file1 = new File("c://temp/" + dirName + "/" + fileNameMerubin);
		file1.createNewFile();
		File file2 = new File("c://temp/" + dirName + "/" + fileNameSagawaTakubin);
		file2.createNewFile();
		File file3 = new File("c://temp/" + dirName + "/" + fileNameYamatoTakubin);
		file2.createNewFile();
		BufferedWriter bufferedWriterYamato = null;
		BufferedWriter bufferedWriterYamatoMerubin = null;
		BufferedWriter bufferedWriterSagawa = null;

		bufferedWriterSagawa = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "shift-jis"));

		bufferedWriterYamato = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file3), "shift-jis"));

		bufferedWriterYamato.write(
				"お客様管理番号,送り状種別,クール区分,伝票番号,出荷予定日,お届け予定（指定）日,配達時間帯,お届け先コード,お届け先電話番号,お届け先電話番号枝番,お届け先郵便番号,お届け先住所,お届け先住所（アパートマンション名）,お届け先会社・部門名１,お届け先会社・部門名２,お届け先名,お届け先名略称カナ,敬称,ご依頼主コード,ご依頼主電話番号,ご依頼主電話番号枝番,ご依頼主郵便番号,ご依頼主住所,ご依頼主住所（アパートマンション名）,ご依頼主名,ご依頼主略称カナ,品名コード１,品名１,品名コード２,品名２,荷扱い１,荷扱い２,記事,コレクト代金引換額（税込）,コレクト内消費税額等,営業所止置き,営業所コード,発行枚数,個数口枠の印字,ご請求先顧客コード,ご請求先分類コード,運賃管理番号,注文時カード払いデータ登録,注文時カード払い加盟店番号,注文時カード払い申込受付番号１,注文時カード払い申込受付番号２,注文時カード払い申込受付番号３,お届け予定ｅメール利用区分,お届け予定ｅメールe-mailアドレス,入力機種,お届け予定eメールメッセージ,お届け完了ｅメール利用区分,お届け完了ｅメールe-mailアドレス,お届け完了eメールメッセージ,クロネコ収納代行利用区分,収納代行決済ＱＲコード印刷,収納代行請求金額(税込),収納代行内消費税額等,収納代行請求先郵便番号,収納代行請求先住所,収納代行請求先住所（アパートマンション名）,収納代行請求先会社・部門名１,収納代行請求先会社・部門名２,収納代行請求先名(漢字),収納代行請求先名(カナ),収納代行問合せ先名(漢字),収納代行問合せ先郵便番号,収納代行問合せ先住所,収納代行問合せ先住所（アパートマンション名）,収納代行問合せ先電話番号,収納代行管理番号,収納代行品名,収納代行備考");

		bufferedWriterYamatoMerubin = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file1), "shift-jis"));
		bufferedWriterYamatoMerubin.write(
				"お客様管理番号,送り状種類,クール区分,伝票番号,出荷予定日,お届け予定（指定）日,配達時間帯,お届け先コード,お届け先電話番号,お届け先電話番号枝番,お届け先郵便番号,お届け先住所,お届け先住所（アパートマンション名）,お届け先会社・部門名１,お届け先会社・部門名２,お届け先名,お届け先名略称カナ,敬称,ご依頼主コード,ご依頼主電話番号,ご依頼主電話番号枝番,ご依頼主郵便番号,ご依頼主住所,ご依頼主住所（アパートマンション名）,ご依頼主名,ご依頼主略称カナ,品名コード１,品名１,品名コード２,品名２,荷扱い１,荷扱い２,記事,コレクト代金引換額（税込）,コレクト内消費税額等,営業所止置き,営業所コード,発行枚数,個数口枠の印字,ご請求先顧客コード,ご請求先分類コード,運賃管理番号,クロネコwebコレクトデータ登録,クロネコwebコレクト加盟店番号,クロネコwebコレクト申込受付番号１,クロネコwebコレクト申込受付番号２,クロネコwebコレクト申込受付番号３,お届け予定ｅメール利用区分,お届け予定ｅメールe-mailアドレス,入力機種,お届け予定eメールメッセージ,お届け完了eメール利用区分,お届け完了ｅメールe-mailアドレス,お届け完了ｅメールメッセージ,クロネコ収納代行利用区分,収納代行決済ＱＲコード印刷,収納代行請求金額(税込),収納代行内消費税額等,収納代行請求先郵便番号,収納代行請求先住所,収納代行請求先住所（アパートマンション名）,収納代行請求先会社・部門名１,収納代行請求先会社・部門名２,収納代行請求先名(漢字),収納代行請求先名(カナ),収納代行問合せ先名(漢字),収納代行問合せ先郵便番号,収納代行問合せ先住所,収納代行問合せ先住所（アパートマンション名）,収納代行問合せ先電話番号,収納代行管理番号,収納代行品名,収納代行備考,複数口くくりキー,検索キータイトル１,検索キー１,検索キータイトル２,検索キー２,検索キータイトル３,検索キー３,検索キータイトル４,検索キー４,検索キータイトル５,検索キー５,予備,予備,投函予定メール利用区分,投函予定メールe-mailアドレス,投函予定メールメッセージ,投函完了メール（お届け先宛）利用区分,投函完了メール（お届け先宛）e-mailアドレス,投函完了メール（お届け先宛）メールメッセージ,投函完了メール（ご依頼主宛）利用区分,投函完了メール（ご依頼主宛）e-mailアドレス,投函完了メール（ご依頼主宛）メールメッセージ,連携管理番号,通知メールアドレス");

		// bufferedWriterSagawa
		// .write("住所録コード,お届け先電話番号,お届け先郵便番号,お届け先住所１,お届け先住所２,お届け先住所３,お届け先名称１,お届け先名称２,お客様管理ナンバー,お客様コード,部署・担当者（荷送人）,荷送人電話番号,ご依頼主電話番号,ご依頼主郵便番号,ご依頼主住所１,ご依頼主住所２,ご依頼主名称１,ご依頼主名称２,荷姿コード,品名１,品名２,品名３,品名４,品名５,出荷個数,便種（スピードで選択）,便種（商品）,配達日,配達指定時間帯,配達指定時間（時分）,代引金額,消費税,保険金額,指定シール①,指定シール②,指定シール③,営業店止め,営業店コード,元着区分");

		for (int i = 0; i < orderList.size(); i++) {
			OrderBean orderBean = orderList.get(i);
			String shop = orderBean.getTenpobetsu();
			if ("coverforefront".equals(shop)) {
				shop = "whiteSweet";
			}
			String site = orderBean.getSite();
			String shohinmei = orderBean.getDetailList().get(0).getShouhinmei();
			if (shohinmei.length() > 25) {
				shohinmei = shohinmei.substring(0, 25);
			}
			if ("宅配便".equals(orderBean.getHaisouhoho()) || "宅急便".equals(orderBean.getHaisouhoho())) {
				if ("1002".equals(orderBean.getUnsokaisha())) {
					// 出荷予定日
					// String yotebi = df2.format(new Date());
					// お届け先電話番号
					String denwabango = orderBean.getSofusakidenwabango1() + "-" + orderBean.getSofusakidenwabango2()
							+ "-" + orderBean.getSofusakidenwabango3();
					// お届け先郵便番号
					String yunbinbango = orderBean.getSofusakiyubinbango1() + "-" + orderBean.getSofusakiyubinbango2();
					// お届け先住所
					String jusho1 = "";
					// お届け先住所（アパートマンション名）
					String jusho2 = "";

					jusho1 = orderBean.getSofusakijusho1() + orderBean.getSofusakijusho2();

					jusho2 = orderBean.getSofusakijusho3();

					// お届け先名
					String name = orderBean.getSofusakimeiji() + orderBean.getSofusakinamae() + "　様";

					String peidari = "";
					String zhidingrishi = "";

					if (!Utility.isEmptyString(orderBean.getOtodokebishitei())) {
						peidari = orderBean.getOtodokebishitei().replace("-", "");
					}

					if (!Utility.isEmptyString(orderBean.getOtodokejikandai())) {
						zhidingrishi = orderBean.getOtodokejikandai();
					}

					String daiyingjinge = "";
					if ("代金引換".equals(orderBean.getKesaihoho())) {
						daiyingjinge = orderBean.getGokeikingaku();
					}
					if (i != 0) {
						bufferedWriterSagawa.newLine();
					}
					bufferedWriterSagawa.write("," + denwabango + "," + yunbinbango + "," + jusho1 + "," + jusho2 + ",,"
							+ name + ",,,,,," + Utility.getShopTel(shop, site) + ",,,,,,,,,,,," + "1," + "0," + "0,"
							+ peidari + "," + zhidingrishi + ",," + daiyingjinge + ",,,,,,,,");
				} else if ("1001".equals(orderBean.getUnsokaisha())) {
					String yamatoType = "";

					if (orderBean.getKesaihoho().contains("代")) {
						yamatoType = "2";
					} else {
						yamatoType = "0";
					}

					// 出荷予定日
					String yotebi = df2.format(new Date());
					// お届け先電話番号
					String denwabango = orderBean.getSofusakidenwabango1() + "-" + orderBean.getSofusakidenwabango2()
							+ "-" + orderBean.getSofusakidenwabango3();
					// お届け先郵便番号
					String yunbinbango = orderBean.getSofusakiyubinbango1() + "-" + orderBean.getSofusakiyubinbango2();
					// お届け先住所
					String jusho1 = "";
					// お届け先住所（アパートマンション名）
					String jusho2 = "";

					String sofusakijusho = orderBean.getSofusakijusho1() + orderBean.getSofusakijusho2()
							+ orderBean.getSofusakijusho3();
					jusho1 = sofusakijusho;

					String peidari = "";
					String zhidingrishi = "";
					String daiyingjinge = "";
					if (orderBean.getKesaihoho().contains("代")) {
						daiyingjinge = orderBean.getGokeikingaku();
					}
					if (!Utility.isEmptyString(orderBean.getOtodokebishitei())) {
						peidari = Utility.formatData(orderBean.getOtodokebishitei().replace("-", ""));
					}

					if (!Utility.isEmptyString(orderBean.getOtodokejikandai())) {
						zhidingrishi = orderBean.getOtodokejikandai();

						if ("01".equals(zhidingrishi)) {
							zhidingrishi = "0812";
						} else if ("12".equals(zhidingrishi)) {
							zhidingrishi = "1214";
						} else if ("14".equals(zhidingrishi)) {
							zhidingrishi = "1416";
						} else if ("16".equals(zhidingrishi)) {
							zhidingrishi = "1618";
						} else if ("04".equals(zhidingrishi)) {
							zhidingrishi = "1820";
						}

					}

					// お届け先名
					String name = orderBean.getSofusakimeiji() + orderBean.getSofusakinamae();
					bufferedWriterYamato.newLine();
					bufferedWriterYamato.write("," + yamatoType + ",,," + yotebi + "," + peidari + "," + zhidingrishi
							+ "," + "," + denwabango + ",," + yunbinbango + "," + jusho1 + "," + jusho2 + ",,," + name
							+ ",,,," + Utility.getShopTel(shop, site) + ",,3490114,埼玉県蓮田市馬込2-132エルディムセブン1-203,," + shop
							+ ",,," + shohinmei + ",,,,," + "," + daiyingjinge + ","
							+ ",,,,2,0486272559,,01,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

				}
			} else {

				// 出荷予定日
				String yotebi = df2.format(new Date());
				// お届け先電話番号
				String denwabango = orderBean.getSofusakidenwabango1() + "-" + orderBean.getSofusakidenwabango2() + "-"
						+ orderBean.getSofusakidenwabango3();
				// お届け先郵便番号
				String yunbinbango = orderBean.getSofusakiyubinbango1() + "-" + orderBean.getSofusakiyubinbango2();
				// お届け先住所
				String jusho1 = "";
				// お届け先住所（アパートマンション名）
				String jusho2 = "";

				String sofusakijusho = orderBean.getSofusakijusho1() + orderBean.getSofusakijusho2()
						+ orderBean.getSofusakijusho3();
				jusho1 = sofusakijusho;

				// お届け先名
				String name = orderBean.getSofusakimeiji() + orderBean.getSofusakinamae();
				bufferedWriterYamatoMerubin.newLine();
				bufferedWriterYamatoMerubin.write(",7,0,," + yotebi + ",,,," + denwabango + ",," + yunbinbango + ","
						+ jusho1 + "," + jusho2 + ",,," + name + ",," + "様" + ",," + Utility.getShopTel(shop, site)
						+ ",,3490114,埼玉県蓮田市馬込2-132,エルディムセブン1-203," + shop
						+ ",,,衣類,,,,,,,,0,,1,,05035675168,,01,0,,,,,0,,,,0,,,0,,,,,,,,,,,,,,,,,,,,,,,,,,,,ユーザーID,,,,0,,,0,,,0,,,");

			}
		}
		if (bufferedWriterYamato != null) {
			bufferedWriterYamato.flush();
			bufferedWriterYamato.close();
		}
		if (bufferedWriterYamatoMerubin != null) {
			bufferedWriterYamatoMerubin.flush();
			bufferedWriterYamatoMerubin.close();
		}
		if (bufferedWriterSagawa != null) {
			bufferedWriterSagawa.flush();
			bufferedWriterSagawa.close();
		}
	}
	
	private void outCsv(List<OrderBean> orderList) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
//		fileNameMerubin = "Merubin_" + df.format(new Date()) + ".csv";
//		fileNameSagawaTakubin = "Takkyubin_Sagawa" + df.format(new Date()) + ".csv";
//		fileNameYamatoTakubin = "Takkyubin_Yamato" + df.format(new Date()) + ".csv";
		fileName = "Post_" + df.format(new Date()) + ".csv";

//		File file1 = new File("c://temp/" + dirName + "/" + fileNameMerubin);
//		file1.createNewFile();
//		File file2 = new File("c://temp/" + dirName + "/" + fileNameSagawaTakubin);
//		file2.createNewFile();
//		File file3 = new File("c://temp/" + dirName + "/" + fileNameYamatoTakubin);
//		file2.createNewFile();
		File file = new File("c://temp/" + dirName + "/" + fileName);
		file.createNewFile();
//		BufferedWriter bufferedWriterYamato = null;
//		BufferedWriter bufferedWriterYamatoMerubin = null;
//		BufferedWriter bufferedWriterSagawa = null;
		BufferedWriter bufferedWriter = null;
		Connection conn = null;
		PreparedStatement ps = null;

//		bufferedWriterSagawa = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "shift-jis"));
//
//		bufferedWriterYamato = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file3), "shift-jis"));
//
//		bufferedWriterYamato.write(
//				"お客様管理番号,送り状種別,クール区分,伝票番号,出荷予定日,お届け予定（指定）日,配達時間帯,お届け先コード,お届け先電話番号,お届け先電話番号枝番,お届け先郵便番号,お届け先住所,お届け先住所（アパートマンション名）,お届け先会社・部門名１,お届け先会社・部門名２,お届け先名,お届け先名略称カナ,敬称,ご依頼主コード,ご依頼主電話番号,ご依頼主電話番号枝番,ご依頼主郵便番号,ご依頼主住所,ご依頼主住所（アパートマンション名）,ご依頼主名,ご依頼主略称カナ,品名コード１,品名１,品名コード２,品名２,荷扱い１,荷扱い２,記事,コレクト代金引換額（税込）,コレクト内消費税額等,営業所止置き,営業所コード,発行枚数,個数口枠の印字,ご請求先顧客コード,ご請求先分類コード,運賃管理番号,注文時カード払いデータ登録,注文時カード払い加盟店番号,注文時カード払い申込受付番号１,注文時カード払い申込受付番号２,注文時カード払い申込受付番号３,お届け予定ｅメール利用区分,お届け予定ｅメールe-mailアドレス,入力機種,お届け予定eメールメッセージ,お届け完了ｅメール利用区分,お届け完了ｅメールe-mailアドレス,お届け完了eメールメッセージ,クロネコ収納代行利用区分,収納代行決済ＱＲコード印刷,収納代行請求金額(税込),収納代行内消費税額等,収納代行請求先郵便番号,収納代行請求先住所,収納代行請求先住所（アパートマンション名）,収納代行請求先会社・部門名１,収納代行請求先会社・部門名２,収納代行請求先名(漢字),収納代行請求先名(カナ),収納代行問合せ先名(漢字),収納代行問合せ先郵便番号,収納代行問合せ先住所,収納代行問合せ先住所（アパートマンション名）,収納代行問合せ先電話番号,収納代行管理番号,収納代行品名,収納代行備考");
//
//		bufferedWriterYamatoMerubin = new BufferedWriter(
//				new OutputStreamWriter(new FileOutputStream(file1), "shift-jis"));
//		bufferedWriterYamatoMerubin.write(
//				"お客様管理番号,送り状種類,クール区分,伝票番号,出荷予定日,お届け予定（指定）日,配達時間帯,お届け先コード,お届け先電話番号,お届け先電話番号枝番,お届け先郵便番号,お届け先住所,お届け先住所（アパートマンション名）,お届け先会社・部門名１,お届け先会社・部門名２,お届け先名,お届け先名略称カナ,敬称,ご依頼主コード,ご依頼主電話番号,ご依頼主電話番号枝番,ご依頼主郵便番号,ご依頼主住所,ご依頼主住所（アパートマンション名）,ご依頼主名,ご依頼主略称カナ,品名コード１,品名１,品名コード２,品名２,荷扱い１,荷扱い２,記事,コレクト代金引換額（税込）,コレクト内消費税額等,営業所止置き,営業所コード,発行枚数,個数口枠の印字,ご請求先顧客コード,ご請求先分類コード,運賃管理番号,クロネコwebコレクトデータ登録,クロネコwebコレクト加盟店番号,クロネコwebコレクト申込受付番号１,クロネコwebコレクト申込受付番号２,クロネコwebコレクト申込受付番号３,お届け予定ｅメール利用区分,お届け予定ｅメールe-mailアドレス,入力機種,お届け予定eメールメッセージ,お届け完了eメール利用区分,お届け完了ｅメールe-mailアドレス,お届け完了ｅメールメッセージ,クロネコ収納代行利用区分,収納代行決済ＱＲコード印刷,収納代行請求金額(税込),収納代行内消費税額等,収納代行請求先郵便番号,収納代行請求先住所,収納代行請求先住所（アパートマンション名）,収納代行請求先会社・部門名１,収納代行請求先会社・部門名２,収納代行請求先名(漢字),収納代行請求先名(カナ),収納代行問合せ先名(漢字),収納代行問合せ先郵便番号,収納代行問合せ先住所,収納代行問合せ先住所（アパートマンション名）,収納代行問合せ先電話番号,収納代行管理番号,収納代行品名,収納代行備考,複数口くくりキー,検索キータイトル１,検索キー１,検索キータイトル２,検索キー２,検索キータイトル３,検索キー３,検索キータイトル４,検索キー４,検索キータイトル５,検索キー５,予備,予備,投函予定メール利用区分,投函予定メールe-mailアドレス,投函予定メールメッセージ,投函完了メール（お届け先宛）利用区分,投函完了メール（お届け先宛）e-mailアドレス,投函完了メール（お届け先宛）メールメッセージ,投函完了メール（ご依頼主宛）利用区分,投函完了メール（ご依頼主宛）e-mailアドレス,投函完了メール（ご依頼主宛）メールメッセージ,連携管理番号,通知メールアドレス");

		// bufferedWriterSagawa
		// .write("住所録コード,お届け先電話番号,お届け先郵便番号,お届け先住所１,お届け先住所２,お届け先住所３,お届け先名称１,お届け先名称２,お客様管理ナンバー,お客様コード,部署・担当者（荷送人）,荷送人電話番号,ご依頼主電話番号,ご依頼主郵便番号,ご依頼主住所１,ご依頼主住所２,ご依頼主名称１,ご依頼主名称２,荷姿コード,品名１,品名２,品名３,品名４,品名５,出荷個数,便種（スピードで選択）,便種（商品）,配達日,配達指定時間帯,配達指定時間（時分）,代引金額,消費税,保険金額,指定シール①,指定シール②,指定シール③,営業店止め,営業店コード,元着区分");
		
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "shift-jis"));
		bufferedWriter.write("出荷予定日,お届け予定（指定）日,配達時間帯,お届け先電話番号,お届け先電話番号枝番,お届け先郵便番号,お届け先住所,お届け先住所（アパートマンション名）,お届け先会社・部門名１,お届け先会社・部門名２,お届け先名,お届け先名略称カナ,敬称,ご依頼主電話番号,ご依頼主電話番号枝番,ご依頼主郵便番号,ご依頼主住所,ご依頼主住所（アパートマンション名）,ご依頼主名,ご依頼主略称カナ,品名１");

		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 WHERE T1.CHUMONBANGO = ? ";
			
			for (int i = 0; i < orderList.size(); i++) {
				OrderBean orderBean = orderList.get(i);
				String shop = orderBean.getTenpobetsu();
				if ("coverforefront".equals(shop)) {
					shop = "whiteSweet";
				}
				String site = orderBean.getSite();
				String tenpodenwabango = "";
				if (!"楽天".equals(site) && !"Yahoo".equals(site) 
						&& !"DENA".equals(site) && !"ヤフオク".equals(site) 
						&& !"ポンパレモール".equals(site) && !"qoo10".equals(site)
						&& !"AU".equals(site)) {
					
	
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderBean.getJuchubango());
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						tenpodenwabango = rs.getString("T1.BIKO");
					}
				} else {
					tenpodenwabango = Utility.getShopTel(shop, site);
				}
	
				String shohinmei = orderBean.getDetailList().get(0).getShouhinmei();
				if (shohinmei.length() > 25) {
					shohinmei = shohinmei.substring(0, 25);
				}
				if ("宅配便".equals(orderBean.getHaisouhoho()) || "宅急便".equals(orderBean.getHaisouhoho())) {
					if (DeliveryCompany.SAGAWA.getTag().equals(orderBean.getUnsokaisha()) || DeliveryCompany.POST.getTag().equals(orderBean.getUnsokaisha())) {
						// 出荷予定日
						 String yotebi = df2.format(new Date());
						// お届け先電話番号
						String denwabango = orderBean.getSofusakidenwabango1() + "-" + orderBean.getSofusakidenwabango2()
								+ "-" + orderBean.getSofusakidenwabango3();
						// お届け先郵便番号
						String yunbinbango = orderBean.getSofusakiyubinbango1() + "-" + orderBean.getSofusakiyubinbango2();
						// お届け先住所
						String jusho1 = "";
						// お届け先住所（アパートマンション名）
						String jusho2 = "";
	
						jusho1 = orderBean.getSofusakijusho1() + orderBean.getSofusakijusho2();
	
						jusho2 = orderBean.getSofusakijusho3();
	
						// お届け先名
						String name = orderBean.getSofusakimeiji() + orderBean.getSofusakinamae();
	
						String peidari = "";
						String zhidingrishi = "";
	
						if (!Utility.isEmptyString(orderBean.getOtodokebishitei())) {
							peidari = orderBean.getOtodokebishitei().replace("-", "");
						}
	
						if (!Utility.isEmptyString(orderBean.getOtodokejikandai())) {
							zhidingrishi = orderBean.getOtodokejikandai();
						}
	
						String daiyingjinge = "";
						if ("代金引換".equals(orderBean.getKesaihoho())) {
							daiyingjinge = orderBean.getGokeikingaku();
						}
						
						bufferedWriter.newLine();
						bufferedWriter.write(yotebi+","+peidari+","+zhidingrishi+","+denwabango+",,"+yunbinbango+","+jusho1+","+jusho2+",,,"+name+",,"+"様"+","+tenpodenwabango+",,3490114,埼玉県蓮田市馬込2-132エルディムセブン1-203,,"+shop+",,"+shohinmei+",");
					} else if (DeliveryCompany.YAMATO.getTag().equals(orderBean.getUnsokaisha())) {
						String yamatoType = "";
	
						if (orderBean.getKesaihoho().contains("代")) {
							yamatoType = "2";
						} else {
							yamatoType = "0";
						}
	
						// 出荷予定日
						String yotebi = df2.format(new Date());
						// お届け先電話番号
						String denwabango = orderBean.getSofusakidenwabango1() + "-" + orderBean.getSofusakidenwabango2()
								+ "-" + orderBean.getSofusakidenwabango3();
						// お届け先郵便番号
						String yunbinbango = orderBean.getSofusakiyubinbango1() + "-" + orderBean.getSofusakiyubinbango2();
						// お届け先住所
						String jusho1 = "";
						// お届け先住所（アパートマンション名）
						String jusho2 = "";
	
						String sofusakijusho = orderBean.getSofusakijusho1() + orderBean.getSofusakijusho2()
								+ orderBean.getSofusakijusho3();
						jusho1 = sofusakijusho;
	
						String peidari = "";
						String zhidingrishi = "";
						String daiyingjinge = "";
						if (orderBean.getKesaihoho().contains("代")) {
							daiyingjinge = orderBean.getGokeikingaku();
						}
						if (!Utility.isEmptyString(orderBean.getOtodokebishitei())) {
							peidari = Utility.formatData(orderBean.getOtodokebishitei().replace("-", ""));
						}
	
						if (!Utility.isEmptyString(orderBean.getOtodokejikandai())) {
							zhidingrishi = orderBean.getOtodokejikandai();
	
							if ("01".equals(zhidingrishi)) {
								zhidingrishi = "0812";
							} else if ("12".equals(zhidingrishi)) {
								zhidingrishi = "1214";
							} else if ("14".equals(zhidingrishi)) {
								zhidingrishi = "1416";
							} else if ("16".equals(zhidingrishi)) {
								zhidingrishi = "1618";
							} else if ("04".equals(zhidingrishi)) {
								zhidingrishi = "1820";
							}
	
						}
	
						// お届け先名
						String name = orderBean.getSofusakimeiji() + orderBean.getSofusakinamae();
	//					bufferedWriterYamato.newLine();
	//					bufferedWriterYamato.write("," + yamatoType + ",,," + yotebi + "," + peidari + "," + zhidingrishi
	//							+ "," + "," + denwabango + ",," + yunbinbango + "," + jusho1 + "," + jusho2 + ",,," + name
	//							+ ",,,," + Utility.getShopTel(shop, site) + ",,3490114,埼玉県蓮田市馬込2-132エルディムセブン1-203,," + shop
	//							+ ",,," + shohinmei + ",,,,," + "," + daiyingjinge + ","
	//							+ ",,,,2,0486272559,,01,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
						bufferedWriter.newLine();
						bufferedWriter.write(yotebi+","+peidari+","+zhidingrishi+","+denwabango+",,"+yunbinbango+","+jusho1+","+jusho2+",,,"+name+",,様,"+tenpodenwabango+",,3490114,埼玉県蓮田市馬込2-132エルディムセブン1-203,,"+shop+",,"+shohinmei+",");
	
					}
				} else {
	
					// 出荷予定日
					String yotebi = df2.format(new Date());
					// お届け先電話番号
					String denwabango = orderBean.getSofusakidenwabango1() + "-" + orderBean.getSofusakidenwabango2() + "-"
							+ orderBean.getSofusakidenwabango3();
					// お届け先郵便番号
					String yunbinbango = orderBean.getSofusakiyubinbango1() + "-" + orderBean.getSofusakiyubinbango2();
					// お届け先住所
					String jusho1 = "";
					// お届け先住所（アパートマンション名）
					String jusho2 = "";
	
					String sofusakijusho = orderBean.getSofusakijusho1() + orderBean.getSofusakijusho2()
							+ orderBean.getSofusakijusho3();
					jusho1 = sofusakijusho;
	
					// お届け先名
					String name = orderBean.getSofusakimeiji() + orderBean.getSofusakinamae();
	//				bufferedWriterYamatoMerubin.newLine();
	//				bufferedWriterYamatoMerubin.write(",7,0,," + yotebi + ",,,," + denwabango + ",," + yunbinbango + ","
	//						+ jusho1 + "," + jusho2 + ",,," + name + ",," + "様" + ",," + Utility.getShopTel(shop, site)
	//						+ ",,3490114,埼玉県蓮田市馬込2-132,エルディムセブン1-203," + shop
	//						+ ",,,衣類,,,,,,,,0,,1,,05035675168,,01,0,,,,,0,,,,0,,,0,,,,,,,,,,,,,,,,,,,,,,,,,,,,ユーザーID,,,,0,,,0,,,0,,,");
					bufferedWriter.newLine();
					bufferedWriter.write(yotebi+",,,"+denwabango+",,"+yunbinbango+","+jusho1+","+jusho2+",,,"+name+",,様,"+tenpodenwabango+",,3490114,埼玉県蓮田市馬込2-132,エルディムセブン1-203,"+shop+",,衣類,");
	
				}
			}
	//		if (bufferedWriterYamato != null) {
	//			bufferedWriterYamato.flush();
	//			bufferedWriterYamato.close();
	//		}
	//		if (bufferedWriterYamatoMerubin != null) {
	//			bufferedWriterYamatoMerubin.flush();
	//			bufferedWriterYamatoMerubin.close();
	//		}
	//		if (bufferedWriterSagawa != null) {
	//			bufferedWriterSagawa.flush();
	//			bufferedWriterSagawa.close();
	//		}
			if (bufferedWriter != null) {
				bufferedWriter.flush();
				bufferedWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() throws Exception {
		setTitle("V130101:発送処理一覧");
	}

	private F100102 getOrderInfo(String orderNo, Connection conn) throws Exception {
		F100102 f100102 = new F100102();
		List<ShohinList> shohinList = new ArrayList<ShohinList>();
		f100102.setShohinList(shohinList);

		PreparedStatement ps = null;

		ShohinList shohin = null;
		String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T3 ON T1.CHUMONBANGO = T3.JUCHUBANGO WHERE T1.CHUMONBANGO = ? ";

		ps = conn.prepareStatement(sql);
		ps.setString(1, orderNo);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			f100102.setChumonsts1(rs.getString("T1.CHUMONSTS1"));
			f100102.setChumonsts2(rs.getString("T1.CHUMONSTS2"));
			f100102.setChumonsts3(rs.getString("T1.CHUMONSTS3"));
			f100102.setChumonsts4(rs.getString("T1.CHUMONSTS4"));
			f100102.setChumonsts5(rs.getString("T1.CHUMONSTS5"));
			f100102.setChumonsts6(rs.getString("T1.CHUMONSTS6"));
			// f100102.setDokonFlg(rs.getString("T2.DOKONSUTETASU"));
			f100102.setOkurisakuchuiFlg((rs.getString("T1.CHUMONSHAJUSHOTODOFUKEN")
					+ rs.getString("T1.CHUMONSHAJUSHOTOSHIKU") + rs.getString("T1.CHUMONSHAJUSHOCHOIJOU")).equals(
							rs.getString("T1.SOUFUSAKIJUSHOTODOFUKEN") + rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU")
									+ rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU")) ? "1" : "0");

			f100102.setMobileMailFlg(rs.getString("T1.MERUADORESU").contains("@pc.") ? "0" : "1");

			f100102.setAsurakuFlg(rs.getString("T1.ASURAKUKIBOU"));

			f100102.setJuchubango(rs.getString("T1.CHUMONBANGO"));
			f100102.setChumonnichiji(rs.getString("T1.CHUMONNICHIJI"));
			f100102.setChumonshanamae(rs.getString("T1.CHUMONSHAMEIJI") + rs.getString("T1.CHUMONSHANAMAE") + "["
					+ (Utility.isEmptyString(rs.getString("T1.CHUMONSHAMEIJIFURIGANA")) ? ""
							: rs.getString("T1.CHUMONSHAMEIJIFURIGANA"))
					+ " " + (Utility.isEmptyString(rs.getString("T1.CHUMONSHANAMAEFURIGANA")) ? ""
							: rs.getString("T1.CHUMONSHANAMAEFURIGANA"))
					+ "]");
			f100102.setChumonshameruadoresu(rs.getString("T1.MERUADORESU"));
			f100102.setChumonshatanjoubi(rs.getString("T1.CHUNONSHATANJOUBI"));
			f100102.setChumonshajusho("〒 " + rs.getString("T1.CHUMONSHAYUBINBANGO1") + "-"
					+ rs.getString("T1.CHUMONSHAYUBINBANGO2") + " " + rs.getString("T1.CHUMONSHAJUSHOTODOFUKEN")
					+ rs.getString("T1.CHUMONSHAJUSHOTOSHIKU") + rs.getString("T1.CHUMONSHAJUSHOCHOIJOU"));
			f100102.setChumonshadenwabango(rs.getString("T1.CHUMONSHADENWABANGO1") + "-"
					+ rs.getString("T1.CHUMONSHADENWABANGO2") + "-" + rs.getString("T1.CHUMONSHADENWABANGO3"));
			f100102.setOshiharaihoho(rs.getString("T1.OSHIHARAISTS"));
			f100102.setHaisohoho(rs.getString("T1.HAISOUHOHO"));
			f100102.setHassobi(rs.getString("T1.HASSOUBI"));
			f100102.setOtodokebishitei(rs.getString("T1.OTODOKEBISHITEI"));
			f100102.setOtodokejikantai1(rs.getString("T1.OTODOKEJIKANTAI1"));
			f100102.setOtodokejikantai2(rs.getString("T1.OTODOKEJIKANTAI1"));
			f100102.setOtodokejikantai3(rs.getString("T1.OTODOKEJIKANTAI1"));
			f100102.setBiko(rs.getString("T1.BIKO"));
			f100102.setBikorakuten(rs.getString("T1.KOMENTO"));
			f100102.setOkyakusamahenomeseji(rs.getString("T1.MERUSASHIKOMIBUN"));
			f100102.setSofusakijoho(rs.getString("T1.SOFUSAKIMEIJI") + rs.getString("T1.SOUFUSAKINAMAE") + "["
					+ (Utility.isEmptyString(rs.getString("T1.SOUFUSAKIMEIJIFURIGANA")) ? ""
							: rs.getString("T1.SOUFUSAKIMEIJIFURIGANA"))
					+ " "
					+ (Utility.isEmptyString(rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA")) ? ""
							: rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA"))
					+ "]" + "<br>" + "〒 " + rs.getString("T1.SOUFUSAKIYUBINBANGO1") + "-"
					+ rs.getString("T1.SOUFUSAKIYUBINBANGO2") + "<br>" + rs.getString("T1.SOUFUSAKIJUSHOTODOFUKEN")
					+ rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU") + rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU") + "<br>"
					+ rs.getString("T1.SOFUSAKIDENWABANGO1") + "-" + rs.getString("T1.SOFUSAKIDENWABANGO2") + "-"
					+ rs.getString("T1.SOFUSAKIDENWABANGO3"));
			// f100102.setOnimotsudenpyobango(rs
			// .getString("T1.ONIMOTUDENPYOUBANGO"));
			// f100102.setHaisokaisha(rs.getString("T1.HAISOUKAISHA"));
			// f100102.setNoshi(rs.getString("T1.NOSHI"));

			f100102.setPointriyou(
					Utility.isEmptyString(rs.getString("T1.POINTRIYO")) ? "0" : rs.getString("T1.POINTRIYO"));

			f100102.setTenpobetsu(rs.getString("T1.SHOP"));
			f100102.setSite(rs.getString("T1.SITE"));
			f100102.setGokeishouhin(rs.getString("T1.GOKEISHOHIN"));
			f100102.setGokeizei(rs.getString("T1.GOKEIZEI"));
			f100102.setGokeisouryou(rs.getString("T1.GOKEISOURYOU"));
			f100102.setGokeidaibikiryou(rs.getString("T1.GOKEIDAIBIKIRYOU"));
			f100102.setSeikyukingaku(rs.getString("T1.SEIKYUKINGAKU"));

			f100102.setOyaFlg(rs.getString("T1.DONKONOYAFLG"));
			if (!"0".equals(rs.getString("T1.DONKONID"))) {
				f100102.setDokonFlg("1");
				List<DokonList> dokonList = new ArrayList<DokonList>();
				f100102.setDokonList(dokonList);
				DokonList donkon = null;

				sql = "SELECT * FROM common_order_tbl WHERE DONKONID = ? ";

				ps = conn.prepareStatement(sql);
				ps.setString(1, rs.getString("DONKONID"));

				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					donkon = new DokonList();
					dokonList.add(donkon);
					donkon.setJuchubango(rs2.getString("CHUMONBANGO"));
					donkon.setType("1".equals(rs2.getString("DONKONOYAFLG")) ? "親" : "子");
				}

			}

			if (!Utility.isEmptyString(rs.getString("T3.SHOUHINBANGO"))) {
				shohin = new ShohinList();
				shohinList.add(shohin);

				shohin.setShohinbango(rs.getString("T3.SHOUHINBANGO"));
				shohin.setShouhinmei(Utility.getShohinmei(rs.getString("T3.SHOUHINBANGO")) + "("
						+ rs.getString("T3.SHOUHINBANGO") + ")");
				shohin.setKomokusentakushi(rs.getString("T3.KOMOKUSENTAKUSHI").replace("\n", "<br>"));
				shohin.setTankaku(rs.getString("T3.TANKA"));
				shohin.setKosu(rs.getString("T3.KOSU"));
				shohin.setPointobairitsu(rs.getString("T3.POINTOBAIRITSU"));
				// // shohin.setotodokenomeyasu = null;
				shohin.setShoukei(String.valueOf(rs.getLong("T3.TANKA") * rs.getLong("T3.KOSU")));
				shohin.setZei(rs.getString("T3.ZEIKOMIBETSU"));
				shohin.setSourou(rs.getString("T3.SOURYOUKOMIBETSU"));
				shohin.setDaibiki(rs.getString("T3.DAIBIKITESURYOUKOMIBETSU"));
				shohin.setShohinURL(rs.getString("T3.SHOUHINURL"));
			}
		}

		return f100102;

	}

	protected void isValidated_SourceVersion() throws Exception {
		String type = f130101.getOuttype();

		if ("0".equals(type)) {
			boolean result = false;
			for (OrderList order : f130101.getOrderList()) {
				if (Utility.isEmptyString(order.getUnsokaisha())) {
					addError("", order.getChumonbango() + "運送会社未設定！");
				}
				if (Utility.isEmptyString(order.getSize())) {
					addError("", order.getChumonbango() + "サイズ未設定！");
				}
				if (order.isIschecked()) {
					result = true;
				}
			}
			if (!result) {
				addError("", "注文を選択してください！");
			}
		}

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		dirName = df.format(new Date());
		File dir = new File("c://temp/" + dirName);
		dir.mkdirs();
		List<OrderList> orderList_temp = null;

		if ("1".equals(type)) {
			orderList_temp = (List<OrderList>) getSessionAttribute("orderList");
		} else {
			orderList_temp = new ArrayList<OrderList>();
			for (OrderList order : f130101.getOrderList()) {
				if (order.isIschecked()) {
					orderList_temp.add(order);
				}
			}
		}
		List<OrderList> orderList = null;
		int count = orderList_temp.size() / 50;

		for (int m = 0; m < count + 1; m++) {
			if (orderList_temp.size() - m * 50 > 50) {
				orderList = orderList_temp.subList(m * 50, (m + 1) * 50);
			} else {
				orderList = orderList_temp.subList(m * 50, orderList_temp.size());
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			String basePath = request.getSession().getServletContext().getRealPath("/");

			StringBuffer sb = new StringBuffer();
			sb.append(
					"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			sb.append("<head>");
			sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			sb.append("<style type=\"text/css\"></style>");
			sb.append("<script type=\"text/javascript\" src=\"jquery-1.3.2.min.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"jquery-barcode-last.min.js\"></script>");
			sb.append("<script type=\"text/javascript\">");
			sb.append("function init(){");

			int i = 0;

			for (OrderList order : orderList) {
				String barchumonbango = "";

				sb.append("$(\"#bcTarget" + i + "\").empty().barcode('" + barchumonbango
						+ "', 'code93',{barWidth:2, barHeight:27,showHRI:false});");
				i++;
			}
			sb.append("}");
			sb.append("</script>");
			sb.append("</head>");
			sb.append("<body onload=\"init();\">");

			StringBuffer html = new StringBuffer();
			html.append(sb.toString());
			i = 0;
			int j = 0;
			for (OrderList order : orderList) {
				Connection conn = null;
				F100102 f100102 = null;
				try {
					conn = JdbcConnection.getConnection();
					f100102 = getOrderInfo(order.getChumonbango(), conn);
				} catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
					throw e;
				} finally {
					conn.close();
				}

				if (f100102.getShohinList().size() <= 5) {
					// 商品种类小于等于5的场合用模板1
					html.append(model(basePath, order, f100102, j, "0"));
					j++;
				} else if (f100102.getShohinList().size() <= 8) {
					html.append(model(basePath, order, f100102, j, "1"));
					f100102.setShohinList(new ArrayList<ShohinList>());
					html.append(model(basePath, order, f100102, j, "4"));
					j++;
				} else {
					List<ShohinList> shohinList = f100102.getShohinList();

					List<ShohinList> shoriList = shohinList.subList(0, 8);
					f100102.setShohinList(shoriList);

					html.append(model(basePath, order, f100102, j, "1"));
					j++;

					shohinList = shohinList.subList(7, shohinList.size());

					if ((shohinList.size()) / 13 > 0) {
						int k = shohinList.size() / 13;
						for (int n = 0; n < k; n++) {
							shoriList = shohinList.subList(n * 13, (n + 1) * 13);
							f100102.setShohinList(shoriList);
							html.append(model(basePath, order, f100102, j, "2"));
						}
						shoriList = shohinList.subList(k * 13, shohinList.size());
						f100102.setShohinList(shoriList);
						if (shoriList.size() > 9) {
							html.append(model(basePath, order, f100102, j, "2"));
							html.append(model(basePath, order, f100102, j, "4"));
						} else {
							html.append(model(basePath, order, f100102, j, "3"));
						}
					} else {
						if (shohinList.size() < 9) {
							f100102.setShohinList(shohinList);
							html.append(model(basePath, order, f100102, j, "3"));
						}
					}
				}
				html.append("</body></html>");
				if (!okurijonomi) {
					write("C:\\temp\\" + dirName + "\\meisaisho_" + (m + 1) + ".html", html.toString());
				}
			}
		}

		orderList1 = getOrderList(orderList_temp);
		if (!okurijonomi) {
			if (f130101.isCheckFlg()) {
				List<String> printzumiList = new ArrayList<String>();
				Connection conn = null;
				try {
					conn = JdbcConnection.getConnection();
					String sql = "select chumonbango from tbl00025 where chumonbango = ?";
					PreparedStatement ps = conn.prepareStatement(sql);

					for (int i = 0; i < orderList1.size(); i++) {
						String bango = orderList1.get(i).getJuchubango();
						ps.setString(1, bango);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							printzumiList.add(rs.getString("chumonbango"));
						}
					}
					if (!Utility.isEmptyList(printzumiList)) {
						for (String msg : printzumiList) {
							addError(null, msg + "已经打印！请检查是否已经发送！");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
					throw e;
				} finally {
					conn.close();
				}
			}
		}

	}

	protected void isValidated() throws Exception {
		String type = f130101.getOuttype();

		if ("0".equals(type)) {
			boolean result = false;
			for (OrderList order : f130101.getOrderList()) {
				if (Utility.isEmptyString(order.getUnsokaisha())) {
					addError("", order.getChumonbango() + "運送会社未設定！");
				}
				if (Utility.isEmptyString(order.getSize())) {
					addError("", order.getChumonbango() + "サイズ未設定！");
				}
				if (order.isIschecked()) {
					result = true;
				}
			}
			if (!result) {
				addError("", "注文を選択してください！");
			}
		}

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		dirName = df.format(new Date());
		File dir = new File("c://temp/" + dirName);
		dir.mkdirs();
		List<OrderList> orderList_temp = null;

		if ("1".equals(type)) {
			orderList_temp = (List<OrderList>) getSessionAttribute("orderList");
		} else {
			orderList_temp = new ArrayList<OrderList>();
			for (OrderList order : f130101.getOrderList()) {
				if (order.isIschecked()) {
					orderList_temp.add(order);
				}
			}
		}
		
		DetailTicketPdfUtil pdfUtil = new DetailTicketPdfUtil();
		HttpServletRequest request = ServletActionContext.getRequest();
		String templatePdfPath = request.getSession().getServletContext().getRealPath("/");
		String file = "/WEB-INF/classes/template.pdf";
		for (OrderList order : orderList_temp) {
			Connection conn = null;
			F100102 f100102 = null;
			try {
				conn = JdbcConnection.getConnection();
				f100102 = getOrderInfo(order.getChumonbango(), conn);
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
			List<ShohinList> shohinList;
			while (true) {
				shohinList = f100102.getShohinList();
				if (shohinList.size() <= 4) {
					Map<String, Object> pdfData = model(order, f100102);
					pdfUtil.generateOnePageWithTemplate(templatePdfPath + file, pdfData);
					break;
				} else {
					Map<String, Object> pdfData = model(order, f100102);
					pdfUtil.generateOnePageWithTemplate(templatePdfPath + file, pdfData);
					f100102.setShohinList(shohinList.subList(4, shohinList.size()));
					shohinList = shohinList.subList(0, 4);
				}
			}
		}
		String targetPdfPath = "C:\\temp\\" + dirName + "\\meisaisho.pdf";
		if (!okurijonomi) {
			pdfUtil.generatePDF(targetPdfPath);
		}

		orderList1 = getOrderList(orderList_temp);
		if (!okurijonomi) {
			if (f130101.isCheckFlg()) {
				List<String> printzumiList = new ArrayList<String>();
				Connection conn = null;
				try {
					conn = JdbcConnection.getConnection();
					String sql = "select chumonbango from tbl00025 where chumonbango = ?";
					PreparedStatement ps = conn.prepareStatement(sql);

					for (int i = 0; i < orderList1.size(); i++) {
						String bango = orderList1.get(i).getJuchubango();
						ps.setString(1, bango);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							printzumiList.add(rs.getString("chumonbango"));
						}
					}
					if (!Utility.isEmptyList(printzumiList)) {
						for (String msg : printzumiList) {
							addError(null, msg + "已经打印！请检查是否已经发送！");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
					throw e;
				} finally {
					conn.close();
				}
			}
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f130101
	 */
	public F130101 getF130101() {
		return f130101;
	}

	/**
	 * @param f130101 the f130101 to set
	 */
	public void setF130101(F130101 f130101) {
		this.f130101 = f130101;
	}

	public static void write(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}

	public String model(String basePath, OrderList order, F100102 f100102, int i, String type) throws Exception {
		StringBuffer html = new StringBuffer();
		File file = null;
		if ("0".equals(type)) {
			file = new File(basePath + "/WEB-INF/classes/sample.html");
		} else if ("1".equals(type)) {
			file = new File(basePath + "/WEB-INF/classes/sample1.html");
		} else if ("2".equals(type)) {
			file = new File(basePath + "/WEB-INF/classes/sample2.html");
		} else if ("3".equals(type)) {
			file = new File(basePath + "/WEB-INF/classes/sample3.html");
		} else {
			file = new File(basePath + "/WEB-INF/classes/sample4.html");
		}
		InputStream is = new FileInputStream(file);

		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		// 最好在将字节流转换为字符流的时候 进行转码
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bf.readLine()) != null) {
			buffer.append(line);
		}
		bf.close();

		String div = buffer.toString();
		String shoriDiv = div;

		// 店铺名
		if ("coverforefront".equals(order.getTenpo())) {
			shoriDiv = shoriDiv.replace("#{shopname}", "WhiteSweet");
		} else {
			shoriDiv = shoriDiv.replace("#{shopname}", order.getTenpo());
		}
		// #{buyerpost}
		shoriDiv = shoriDiv.replace("#{buyeraddr}", f100102.getChumonshajusho());
		// #{buyername}
		shoriDiv = shoriDiv.replace("#{buyername}", f100102.getChumonshanamae());
		// #{shopurl}
		shoriDiv = shoriDiv.replace("#{shopurl}", Utility.getShopUrl(order.getTenpo(), order.getSite()));
		// #{shoppost}
		shoriDiv = shoriDiv.replace("#{shoppost}", Utility.getShopPost(order.getTenpo(), order.getSite()));
		// #{shopaddr}
		shoriDiv = shoriDiv.replace("#{shopaddr}", Utility.getShopAddr(order.getTenpo(), order.getSite()));
		// #{shoptel}
		shoriDiv = shoriDiv.replace("#{shoptel}", Utility.getShopTel(order.getTenpo(), order.getSite()));
		// #{shopfax}
		shoriDiv = shoriDiv.replace("#{shopfax}", Utility.getShopFax(order.getTenpo(), order.getSite()));
		shoriDiv = shoriDiv.replace("bcTarget", "bcTarget" + i);

		shoriDiv = shoriDiv.replace("#{height}", f130101.getPageHeight());

		List<ShohinList> shohinList = f100102.getShohinList();
		StringBuffer detail = new StringBuffer();
		for (ShohinList shohin : shohinList) {
			detail.append("<tr height=\"60px\">");
			detail.append(
					"<td style=\"font-size:12px;font-weight:600;border:1px black solid;border-style: none none solid solid\" >&nbsp;<span style=\"font-size:8px\">&nbsp;"
							+ (shohin.getShouhinmei().length() > 60 ? shohin.getShouhinmei().substring(1, 60)
									: shohin.getShouhinmei())
							+ "</span>　　" + shohin.getShohinbango() + "<br/> &nbsp;"
							+ shohin.getKomokusentakushi().replace("<br>", " ") + "</td>");
			detail.append(
					"<td style=\"font-size:12px;font-weight:600;border:1px black solid;border-style: none none solid solid\" align=\"right\">&nbsp;"
							+ shohin.getKosu() + "&nbsp;</td>");
			detail.append(
					"<td style=\"font-size:12px;font-weight:600;border:1px black solid;border-style: none none solid solid\" align=\"right\">&nbsp;"
							+ shohin.getTankaku() + "&nbsp;円&nbsp;</td>");
			detail.append(
					"<td style=\"font-size:12px;font-weight:600;border:1px black solid;border-style: none solid solid solid\" align=\"right\">&nbsp;"
							+ shohin.getShoukei() + "&nbsp;円&nbsp;</td>");
			detail.append("</tr>");
		}
		shoriDiv = shoriDiv.replace("#{detailList}", detail.toString());

		// #{receiveaddr}
		shoriDiv = shoriDiv.replace("#{receiveaddr}", f100102.getSofusakijoho().replaceFirst("<br>", "　様<br>"));

		// #{orderdatetime}
		shoriDiv = shoriDiv.replace("#{orderdatetime}", order.getChumonichiji().substring(0, 10));

		// #{orderNo}
		shoriDiv = shoriDiv.replace("#{orderNo}", order.getChumonbango());

		// #{payway}
		shoriDiv = shoriDiv.replace("#{payway}", order.getOshiharaihoho());

		// #{gokei}
		shoriDiv = shoriDiv.replace("#{gokei}", f100102.getGokeishouhin());

		// #{zei}
		if (!Utility.isEmptyString(f100102.getGokeizei()) && Integer.valueOf(f100102.getGokeizei()) > 0) {
			shoriDiv = shoriDiv.replace("#{zei}", f100102.getGokeizei());
		} else {
			shoriDiv = shoriDiv.replace("#{zei}", "内税");
		}

		// #{soryo}
		shoriDiv = shoriDiv.replace("#{soryo}", f100102.getGokeisouryou());

		// #{daibiki}
		if (!Utility.isEmptyString(f100102.getGokeidaibikiryou())) {
			StringBuffer dibiki = new StringBuffer();
			dibiki.append("<tr height=\"25px\" valign=\"middle\">");
			dibiki.append(
					"<td width=\"220px\" style=\"font-size:12px;font-weight:600;background:#AFAFAF;border:1px black solid;border-style: none none solid solid\" >&nbsp;代引き手数料</td>");
			dibiki.append(
					"<td width=\"130px\" style=\"font-size:12px;font-weight:600;border:1px black solid;border-style: none solid solid solid\" align=\"right\"><span style=\"font-size:12px;font-weight:600;\">"
							+ f100102.getGokeidaibikiryou() + "</span>&nbsp;円&nbsp;</td>");
			dibiki.append("<td width=\"50px\"></td>");
			dibiki.append("</tr>");

			shoriDiv = shoriDiv.replace("#{daibiki}", dibiki.toString());
		} else {
			shoriDiv = shoriDiv.replace("#{daibiki}", "");
		}
		// #{sogokei}
		Long sogokei = 0l;
		Long shohizei = 0l;
		Long daibikiryo = 0l;
		if (!Utility.isEmptyString(f100102.getGokeizei())) {
			shohizei = Long.valueOf(f100102.getGokeizei());
		}
		Long soryo = Long.valueOf(f100102.getGokeisouryou());
		if (!Utility.isEmptyString(f100102.getGokeidaibikiryou())) {
			daibikiryo = Long.valueOf(f100102.getGokeidaibikiryou());
		}

		sogokei = Long.valueOf(f100102.getGokeishouhin()) + shohizei + soryo + daibikiryo;
		shoriDiv = shoriDiv.replace("#{sogokei}", String.valueOf(sogokei));

		// #{point}
		shoriDiv = shoriDiv.replace("#{point}", f100102.getPointriyou());

		// #{sonota}
		Long seikyu = Long.valueOf(f100102.getSeikyukingaku());
		Long point = Long.valueOf(f100102.getPointriyou());

		Long sonota = 0l;

		sonota = seikyu + point - sogokei;
		shoriDiv = shoriDiv.replace("#{sonota}", String.valueOf(sonota));

		// #{seikyu}
		shoriDiv = shoriDiv.replace("#{seikyu}", f100102.getSeikyukingaku());

		html.append(shoriDiv);
		return html.toString();

	}

	private Map<String, Object> model(OrderList order, F100102 f100102) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		Map<String, Object> pdfData = new HashMap<String, Object>();
		String shopName;
		try {
			
			// 店铺名
			if ("coverforefront".equals(order.getTenpo())) {
				shopName = "WhiteSweet";
			} else {
				shopName = order.getTenpo();
			}
			pdfData.put("title", "[" + shopName + "] お買い上げ明細書");
			pdfData.put("buyeraddr", f100102.getChumonshajusho());
			pdfData.put("buyername", f100102.getChumonshanamae() + " 様 ");
			StringBuilder saleerAddr = new StringBuilder(shopName);
			saleerAddr.append(System.lineSeparator());
			saleerAddr.append(Utility.getShopUrl(order.getTenpo(), order.getSite()));
			saleerAddr.append(System.lineSeparator());
			saleerAddr.append(shopName);
			saleerAddr.append(System.lineSeparator());
			saleerAddr.append("〒").append(Utility.getShopPost(order.getTenpo(), order.getSite()));
			saleerAddr.append(System.lineSeparator());
			
			
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 WHERE T1.CHUMONBANGO = ? ";
				
			String site = order.getSite();
			String tenpodenwabango = "";
			if (!"楽天".equals(site) && !"Yahoo".equals(site) 
					&& !"DENA".equals(site) && !"ヤフオク".equals(site) 
					&& !"ポンパレモール".equals(site) && !"qoo10".equals(site)) {
				

				ps = conn.prepareStatement(sql);
				ps.setString(1, order.getChumonbango());
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					tenpodenwabango = rs.getString("T1.BIKO");
				}
			} else {
				tenpodenwabango = Utility.getShopTel(order.getTenpo(), order.getSite());
			}
			
			saleerAddr.append(Utility.getShopAddr(order.getTenpo(), order.getSite()));
			saleerAddr.append(System.lineSeparator());
			saleerAddr.append("TEL： ").append(tenpodenwabango);
			saleerAddr.append(System.lineSeparator());
			saleerAddr.append("FAX：").append(tenpodenwabango);
			pdfData.put("saleeraddr", saleerAddr.toString());
			StringBuilder content = new StringBuilder("明細内容に関してご不明な点は、下記のページアドレス（URL）を");
			content.append(System.lineSeparator()).append("ご確認の上、当店「");
			content.append(shopName);
			content.append("」までお問合わせください。");
			content.append(System.lineSeparator());
			content.append("「").append(shopName).append("}」 :")
					.append(Utility.getShopUrl(order.getTenpo(), order.getSite()));
			pdfData.put("content", content.toString());
			StringBuilder receiveAddr = new StringBuilder("■お届け先：");
			receiveAddr.append(System.lineSeparator()).append(f100102.getSofusakijoho()
					.replaceFirst("<br>", "　様" + System.lineSeparator()).replace("<br>", System.lineSeparator()));
			pdfData.put("receiveaddr", receiveAddr.toString());
			StringBuilder orderInfo = new StringBuilder("■ご注文日： ");
			orderInfo.append(order.getChumonichiji().substring(0, 10));
			orderInfo.append(System.lineSeparator()).append(System.lineSeparator());
			orderInfo.append("■受注番号：").append(order.getChumonbango());
			pdfData.put("orderinfo", orderInfo.toString());
			pdfData.put("payway", "■お支払い方法：" + order.getOshiharaihoho());
			List<ShohinList> shohinList = f100102.getShohinList();
			List<List<String>> detailTableList = new ArrayList<List<String>>();
			List<String> row = new ArrayList<String>();
			row.add("商品名／商品番号／項目：選択肢");
			row.add("個数");
			row.add("単価");
			row.add("小計");
			detailTableList.add(row);
			for (ShohinList shohin : shohinList) {
				row = new ArrayList<String>();
				row.add(shohin.getShouhinmei() + shohin.getShohinbango() + System.lineSeparator()
						+ shohin.getKomokusentakushi().replace("<br>", " "));
				row.add(shohin.getKosu());
				row.add(shohin.getTankaku() + " 円 ");
				row.add(shohin.getShoukei() + " 円 ");
				detailTableList.add(row);
			}
			pdfData.put("detailtable", detailTableList);
			List<List<String>> sumList = new ArrayList<List<String>>();
			row = new ArrayList<String>();
			row.add("合計");
			row.add(f100102.getGokeishouhin() + " 円 ");
			sumList.add(row);
			row = new ArrayList<String>();
			row.add("消費税");
			if (!Utility.isEmptyString(f100102.getGokeizei()) && Integer.valueOf(f100102.getGokeizei()) > 0) {
				row.add(f100102.getGokeizei());
			} else {
				row.add("内税");
			}
			sumList.add(row);
			row = new ArrayList<String>();
			row.add("送料");
			row.add(f100102.getGokeisouryou() + " 円 ");
			sumList.add(row);
			if (!Utility.isEmptyString(f100102.getGokeidaibikiryou())) {
				row = new ArrayList<String>();
				row.add("代引き手数料");
				row.add(f100102.getGokeidaibikiryou() + " 円 ");
				sumList.add(row);
			}
			row = new ArrayList<String>();
			row.add("総合計");
			Long sogokei = 0l;
			Long shohizei = 0l;
			Long daibikiryo = 0l;
			if (!Utility.isEmptyString(f100102.getGokeizei())) {
				shohizei = Long.valueOf(f100102.getGokeizei());
			}
			Long soryo = Long.valueOf(f100102.getGokeisouryou());
			if (!Utility.isEmptyString(f100102.getGokeidaibikiryou())) {
				daibikiryo = Long.valueOf(f100102.getGokeidaibikiryou());
			}
	
			sogokei = Long.valueOf(f100102.getGokeishouhin()) + shohizei + soryo + daibikiryo;
			row.add(sogokei + " 円 ");
			sumList.add(row);
			row = new ArrayList<String>();
			row.add("ポイント利用額");
			row.add(f100102.getPointriyou() + " 円 ");
			sumList.add(row);
			row = new ArrayList<String>();
			row.add("その他");
			Long seikyu = Long.valueOf(f100102.getSeikyukingaku());
			Long point = Long.valueOf(f100102.getPointriyou());
			Long sonota = 0l;
			sonota = seikyu + point - sogokei;
			row.add(sonota + " 円 ");
			sumList.add(row);
			row = new ArrayList<String>();
			row.add("請求金額");
			row.add(f100102.getSeikyukingaku() + " 円 ");
			sumList.add(row);
			pdfData.put("sumtable", sumList);
			pdfData.put("barcode", order.getChumonbango());
			return pdfData;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isOkurijonomi() {
		return okurijonomi;
	}

	public void setOkurijonomi(boolean okurijonomi) {
		this.okurijonomi = okurijonomi;
	}

}
