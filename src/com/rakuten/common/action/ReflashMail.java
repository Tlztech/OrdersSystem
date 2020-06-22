package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class ReflashMail extends TimerTask {

	/**
	 * 　ReceiveEmail类测试
	 */
	public void excute() throws Exception {
		String host = "imap.163.com"; //
		String username = "dongtze123456"; //
		String password = "hncjcj1988"; //

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Store store = session.getStore("imap");
		store.connect(host, username, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		Message message[] = folder.getMessages();
		System.out.println("邮件数量:　" + message.length);
		ReceiveEmail re = null;

		for (int i = 0; i < message.length; i++) {
			re = new ReceiveEmail((MimeMessage) message[i]);
			System.out.println("邮件　" + i + "　主题:　" + re.getSubject());
			System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());
			System.out.println("邮件　" + i + "　是否需要回复:　" + re.getReplySign());
			System.out.println("邮件　" + i + "　是否已读:　" + re.isNew());
			System.out.println("邮件　" + i + "　是否包含附件:　"
					+ re.isContainAttach((Part) message[i]));
			System.out.println("邮件　" + i + "　发送人地址:　" + re.getFrom());
			System.out
					.println("邮件　" + i + "　收信人地址:　" + re.getMailAddress("to"));
			System.out.println("邮件　" + i + "　抄送:　" + re.getMailAddress("cc"));
			System.out.println("邮件　" + i + "　暗抄:　" + re.getMailAddress("bcc"));
			re.setDateFormat("yy年MM月dd日　HH:mm");
			System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());
			System.out.println("邮件　" + i + "　邮件ID:　" + re.getMessageId());
			re.getMailContent((Part) message[i]);
			System.out.println("邮件　" + i + "　正文内容:　\r\n" + re.getBodyText());
			// re.setAttachPath("e:\\");
			// re.saveAttachMent((Part) message[i]);

			// message[i].saveChanges();
			if (!Utility.isEmptyString(re.getSubject())
					&& "【楽天市場】注文内容ご確認（自動配信メール）".equals(re.getSubject().trim())) {
				String mail = formatMail(re.getBodyText());
				saveOrder(mail);
			}
			message[i].setFlag(Flags.Flag.DELETED, true);
		}
		folder.close(true);

	}

	private void saveOrder(String message) throws Exception {
		String orderNo = "";// 受注番号
		String chumonsha = "";// 注文者
		String time = "";// 日時
		String soufusaki = "";// 送付先者
		String address = "";// 住所
		String phone = "";// 電話
		String payway = "";// 支払方法
		String sendway = "";// 配送方法
		String timetoship = "";// 配送日時指定
		String shop = "";// ショップ名
		List<String> commodityNameList = new ArrayList<String>();// 商品名
		List<String> commodityIdList = new ArrayList<String>();// 商品番号
		List<String> typeList = new ArrayList<String>();// 型号
		List<String> priceList = new ArrayList<String>();// 価格
		List<String> quantityList = new ArrayList<String>();// 数量
		String souryou = "";// 送料
		String daibikiryou = "";// 代引料
		String totalPrice = "";// 合計
		String remarks = "";// 備考

		message = message.substring(message.indexOf("<DIV>[受注番号]"));
		orderNo = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", "").replace("[受注番号]", "")
				.replaceAll("<DIV>", "");

		message = message.substring(message.indexOf("<DIV>[日時]"));
		time = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("[日時]", "")
				.replaceAll("<DIV>", "");
		time = time.replaceAll("  ", " ");
		time = time.replaceAll("  ", " ");
		time = time.replaceAll("  ", " ");
		time = time.replaceAll("  ", " ");
		time = time.replaceAll("  ", " ");
		time = time.trim();

		message = message.substring(message.indexOf("<DIV>[注文者]"));
		chumonsha = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("[注文者]", "")
				.replaceAll("<DIV>", "");
		chumonsha = chumonsha.replaceAll("  ", " ");
		chumonsha = chumonsha.replaceAll("  ", " ");
		chumonsha = chumonsha.replaceAll("  ", " ");
		chumonsha = chumonsha.replaceAll("  ", " ");
		chumonsha = chumonsha.replaceAll("  ", " ");
		chumonsha = chumonsha.trim();

		message = message.substring(message.indexOf("<DIV>[支払方法]"));
		payway = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("[支払方法]", "")
				.replaceAll("<DIV>", "");
		payway = payway.replaceAll("  ", " ");
		payway = payway.replaceAll("  ", " ");
		payway = payway.replaceAll("  ", " ");
		payway = payway.replaceAll("  ", " ");
		payway = payway.replaceAll("  ", " ");
		payway = payway.trim();

		message = message.substring(message.indexOf("<DIV>[配送方法]"));
		sendway = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("[配送方法]", "")
				.replaceAll("<DIV>", "");
		sendway = sendway.replaceAll("  ", " ");
		sendway = sendway.replaceAll("  ", " ");
		sendway = sendway.replaceAll("  ", " ");
		sendway = sendway.replaceAll("  ", " ");
		sendway = sendway.replaceAll("  ", " ");
		sendway = sendway.trim();
		if (message.indexOf("<DIV>[配送日時指定]") > 0) {
			message = message.substring(message.indexOf("<DIV>[配送日時指定]"));
			timetoship = message.substring(0, message.indexOf("<DIV>[備考]"))
					.replaceAll("&nbsp;", " ").replace("[配送日時指定]", "")
					.replaceAll("<DIV>", "").replaceAll("</DIV>", "");
			timetoship = timetoship.replaceAll("  ", " ");
			timetoship = timetoship.replaceAll("  ", " ");
			timetoship = timetoship.replaceAll("  ", " ");
			timetoship = timetoship.replaceAll("  ", " ");
			timetoship = timetoship.replaceAll("  ", " ");
			timetoship = timetoship.trim().replaceFirst("\r\n", "");
		}
		message = message.substring(message.indexOf("<DIV>[備考]"));
		remarks = message.substring(0, message.indexOf("<DIV>[ショップ名]"))
				.replaceAll("&nbsp;", " ").replace("[備考]", "")
				.replaceAll("<DIV>", "").replaceAll("</DIV>", "");
		remarks = remarks.replaceAll("  ", " ");
		remarks = remarks.replaceAll("  ", " ");
		remarks = remarks.replaceAll("  ", " ");
		remarks = remarks.replaceAll("  ", " ");
		remarks = remarks.replaceAll("  ", " ");
		remarks = remarks.trim().replaceFirst("\r\n", "");

		message = message.substring(message.indexOf("<DIV>[ショップ名]"));
		shop = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("[ショップ名]", "")
				.replaceAll("<DIV>", "");
		shop = shop.replaceAll("  ", " ");
		shop = shop.replaceAll("  ", " ");
		shop = shop.replaceAll("  ", " ");
		shop = shop.replaceAll("  ", " ");
		shop = shop.replaceAll("  ", " ");
		shop = shop.trim();

		message = message.substring(message.indexOf("<DIV>[送付先]"));
		soufusaki = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("[送付先]", "")
				.replaceAll("<DIV>", "");
		soufusaki = soufusaki.replaceAll("  ", " ");
		soufusaki = soufusaki.replaceAll("  ", " ");
		soufusaki = soufusaki.replaceAll("  ", " ");
		soufusaki = soufusaki.replaceAll("  ", " ");
		soufusaki = soufusaki.replaceAll("  ", " ");
		soufusaki = soufusaki.trim();

		message = message.substring(message.indexOf("</DIV>") + 6)
				.replaceFirst("\r\n", "");
		address = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replaceAll("<DIV>", "");
		address = address.replaceAll("  ", " ");
		address = address.replaceAll("  ", " ");
		address = address.replaceAll("  ", " ");
		address = address.replaceAll("  ", " ");
		address = address.replaceAll("  ", " ");
		address = address.trim();

		message = message.substring(message.indexOf("</DIV>") + 6)
				.replaceFirst("\r\n", "");
		phone = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replaceAll("<DIV>", "");
		phone = phone.replaceAll("  ", " ");
		phone = phone.replaceAll("  ", " ");
		phone = phone.replaceAll("  ", " ");
		phone = phone.replaceAll("  ", " ");
		phone = phone.replaceAll("  ", " ");
		phone = phone.trim();

		message = message.substring(message.indexOf("<DIV>[商品]</DIV>"));
		message = message.replaceFirst("</DIV>", "");
		String commodityName = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("[商品]", "")
				.replaceAll("<DIV>", "");
		commodityName = commodityName.replaceAll("  ", " ");
		commodityName = commodityName.replaceAll("  ", " ");
		commodityName = commodityName.replaceAll("  ", " ");
		commodityName = commodityName.replaceAll("  ", " ");
		commodityName = commodityName.replaceAll("  ", " ");
		commodityName = commodityName.trim();
		commodityNameList.add(commodityName);

		String commodityId = commodityName.substring(
				commodityName.lastIndexOf("(") + 1,
				commodityName.lastIndexOf(")"));
		commodityIdList.add(commodityId);

		message = message.substring(message.indexOf("</DIV>") + 6)
				.replaceFirst("\r\n", "");
		String type = "";
		type = message.substring(0, message.indexOf("<DIV>価格"))
				.replaceAll("&nbsp;", " ").replaceAll("<DIV>", "")
				.replaceAll("</DIV>", "");
		type = type.replaceAll("  ", " ");
		type = type.replaceAll("  ", " ");
		type = type.replaceAll("  ", " ");
		type = type.replaceAll("  ", " ");
		type = type.replaceAll("  ", " ");
		type = type.trim();
		typeList.add(type);

		message = message.substring(message.indexOf("<DIV>価格"));
		String price = "";
		price = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("価格", "")
				.replaceAll("<DIV>", "");
		price = price.replaceAll("  ", " ");
		price = price.replaceAll("  ", " ");
		price = price.replaceAll("  ", " ");
		price = price.replaceAll("  ", " ");
		price = price.replaceAll("  ", " ");
		price = price.trim().replace("(円)", "").replace(",", "");
		String quantity = "";
		quantity = price.substring(price.indexOf("x") + 1, price.indexOf("("))
				.trim();
		price = price.substring(0, price.indexOf("x")).trim();
		priceList.add(price);
		quantityList.add(quantity);

		while (message.indexOf("<DIV>----------</DIV>") > 0) {
			message = message.substring(message
					.indexOf("<DIV>----------</DIV>"));
			message = message.replaceFirst("</DIV>", "");
			commodityName = message.substring(0, message.indexOf("</DIV>"))
					.replaceAll("&nbsp;", " ").replaceFirst("----------", "")
					.replaceAll("<DIV>", "").replaceFirst("\r\n", "");
			commodityName = commodityName.replaceAll("  ", " ");
			commodityName = commodityName.replaceAll("  ", " ");
			commodityName = commodityName.replaceAll("  ", " ");
			commodityName = commodityName.replaceAll("  ", " ");
			commodityName = commodityName.replaceAll("  ", " ");
			commodityName = commodityName.trim();
			commodityNameList.add(commodityName);
			message = message.replaceFirst("<DIV>----------", "")
					.replaceFirst("\r\n", "");

			commodityId = commodityName.substring(
					commodityName.lastIndexOf("(") + 1,
					commodityName.lastIndexOf(")"));
			commodityIdList.add(commodityId);

			message = message.substring(message.indexOf("</DIV>") + 6)
					.replaceFirst("\r\n", "");
			type = message.substring(0, message.indexOf("<DIV>価格"))
					.replaceAll("&nbsp;", " ").replaceAll("<DIV>", "")
					.replaceAll("</DIV>", "");
			type = type.replaceAll("  ", " ");
			type = type.replaceAll("  ", " ");
			type = type.replaceAll("  ", " ");
			type = type.replaceAll("  ", " ");
			type = type.replaceAll("  ", " ");
			type = type.trim();
			typeList.add(type);

			message = message.substring(message.indexOf("<DIV>価格"));
			price = message.substring(0, message.indexOf("</DIV>"))
					.replaceAll("&nbsp;", " ").replace("価格", "")
					.replaceAll("<DIV>", "");
			price = price.replaceAll("  ", " ");
			price = price.replaceAll("  ", " ");
			price = price.replaceAll("  ", " ");
			price = price.replaceAll("  ", " ");
			price = price.replaceAll("  ", " ");
			price = price.trim().replace("(円)", "").replace(",", "");
			quantity = price.substring(price.indexOf("x") + 1,
					price.indexOf("(")).trim();
			price = price.substring(0, price.indexOf("x")).trim();
			priceList.add(price);
			quantityList.add(quantity);
		}

		message = message.substring(message.indexOf("<DIV>送料"));
		souryou = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("送料", "")
				.replaceAll("<DIV>", "")
				.replace("離島・一部地域では別途料金が必要な場合があります", "");
		souryou = souryou.replaceAll("  ", " ");
		souryou = souryou.replaceAll("  ", " ");
		souryou = souryou.replaceAll("  ", " ");
		souryou = souryou.replaceAll("  ", " ");
		souryou = souryou.replaceAll("  ", " ");
		souryou = souryou.trim().replace("(円)", "").replace(",", "");

		if (message.indexOf("<DIV>代引料") > 0) {
			message = message.substring(message.indexOf("<DIV>代引料"));
			daibikiryou = message.substring(0, message.indexOf("</DIV>"))
					.replaceAll("&nbsp;", " ").replace("代引料", "")
					.replaceAll("<DIV>", "");
			daibikiryou = daibikiryou.replaceAll("  ", " ");
			daibikiryou = daibikiryou.replaceAll("  ", " ");
			daibikiryou = daibikiryou.replaceAll("  ", " ");
			daibikiryou = daibikiryou.replaceAll("  ", " ");
			daibikiryou = daibikiryou.replaceAll("  ", " ");
			daibikiryou = daibikiryou.trim().replace("(円)", "")
					.replace(",", "");
		}
		message = message.substring(message.indexOf("<DIV>合計"));
		totalPrice = message.substring(0, message.indexOf("</DIV>"))
				.replaceAll("&nbsp;", " ").replace("合計", "")
				.replaceAll("<DIV>", "");
		totalPrice = totalPrice.replaceAll("  ", " ");
		totalPrice = totalPrice.replaceAll("  ", " ");
		totalPrice = totalPrice.replaceAll("  ", " ");
		totalPrice = totalPrice.replaceAll("  ", " ");
		totalPrice = totalPrice.replaceAll("  ", " ");
		totalPrice = totalPrice.trim().replace("(円)", "").replace(",", "");

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "INSERT INTO ORDER_TBL VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.setString(2, chumonsha);
			ps.setString(3, time);
			ps.setString(4, soufusaki);
			ps.setString(5, address);
			ps.setString(6, phone);
			ps.setString(7, sendway);
			ps.setString(8, payway);
			ps.setString(9, shop);
			ps.setString(10, souryou);
			ps.setString(11, daibikiryou);
			ps.setString(12, totalPrice);
			ps.setString(13, "1");
			ps.setString(14, remarks);
			ps.setString(15, "");
			ps.setString(16, timetoship);
			ps.execute();

			sql = "INSERT INTO ORDER_SUB_TBL VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < commodityNameList.size(); i++) {
				ps.setString(1, orderNo);
				ps.setString(2, commodityIdList.get(i));
				ps.setString(3, commodityNameList.get(i));
				ps.setString(4, typeList.get(i));
				ps.setString(5, priceList.get(i));
				ps.setString(6, quantityList.get(i));
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

	@Override
	public void run() {
		try {
			excute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String formatMail(String message) {
		String mail = "";
		message = message.replaceAll(" ", "&nbsp;");
		String[] strArr = message.split("\r\n");
		for (int i = 0; i < strArr.length; i++) {
			mail += ("<DIV>" + strArr[i] + "</DIV>" + "\r\n");
		}

		return mail;
	}
}
