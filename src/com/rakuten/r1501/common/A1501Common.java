package com.rakuten.r1501.common;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.r1501.bean.AlipayCsvBean;
import com.rakuten.r1501.bean.AlipayMeisaiBean;
import com.rakuten.r1501.form.Account;
import com.rakuten.r1501.form.AlipayMeisai;
import com.rakuten.r1501.form.Fenlei;
import com.rakuten.r1501.form.Meisai;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A1501Common {
	public static List<Fenlei> getJiaoyiFenlei(String area) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<Fenlei> fenleiList = new ArrayList<Fenlei>();
		Fenlei fenlei = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM fenleiinfo WHERE area = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, area);

			rs = ps.executeQuery();
			while (rs.next()) {
				fenlei = new Fenlei();
				fenleiList.add(fenlei);
				fenlei.setCode(rs.getString("kanribango"));
				fenlei.setName(rs.getString("fenlei_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return fenleiList;
	}

	public static List<Account> getAccountList(Connection conn, String area)
			throws Exception {
		List<Account> accountList = new ArrayList<Account>();
		Account account = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		sql = "select * from accountinfo where area = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, area);
		rs = ps.executeQuery();
		while (rs.next()) {
			account = new Account();
			accountList.add(account);
			account.setAccountId(rs.getString("account_id"));
			account.setName(rs.getString("account_name"));
			account.setCardNo(rs.getString("card_no"));
			account.setType(rs.getString("bank_code"));
			account.setTypeName(Utility.getBankName(rs.getString("bank_code")));
			String[] data = getAccountBalanceAndUpdateTime(conn,
					rs.getString("account_id"), rs.getString("bank_code"));
			account.setBalance(data[0]);
			account.setUpdateTime(data[1]);
			account.setUndidCount(getUndidCount(conn,
					rs.getString("account_id"), rs.getString("bank_code")));
		}

		return accountList;
	}

	public static Account getAccountInfo(String account_id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		Account account = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "select * from accountinfo where account_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, account_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				account = new Account();
				account.setName(rs.getString("account_name"));
				account.setCardNo(rs.getString("card_no"));
				account.setTypeName(Utility.getBankName(rs
						.getString("bank_code")));
				account.setAccountId(rs.getString("account_id"));
				account.setArea(rs.getString("area"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return account;
	}

	public static List<Meisai> getCashAccountInfo(String account_id,
			String area, String dayStart, String dayEnd, String kubun,
			String biko, String leibie, String outStart, String outEnd,
			String inStart, String inEnd) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Meisai> meisaiList = new ArrayList<Meisai>();
		Meisai meisai = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "";
			if ("0".equals(area)) {
				sql = "select * from cash_jp_tbl where account_id = ?";
			} else {
				sql = "select * from cash_sh_tbl where account_id = ?";
			}

			if (Utility.isEmptyString(dayStart)) {
				dayStart = "0";
			}
			if (Utility.isEmptyString(dayEnd)) {
				dayEnd = "2099-01-01";
			}
			if (Utility.isEmptyString(outStart)) {
				outStart = "0";
			}
			if (Utility.isEmptyString(outEnd)) {
				outEnd = "9999999999";
			}
			if (Utility.isEmptyString(inStart)) {
				inStart = "0";
			}
			if (Utility.isEmptyString(inEnd)) {
				inEnd = "9999999999";
			}

			if (!Utility.isEmptyString(kubun)) {
				sql += " AND kubun = '" + kubun + "'";
			}
			if (!Utility.isEmptyString(leibie)) {
				sql += " AND type = '" + leibie + "'";
			}
			if (!Utility.isEmptyString(biko)) {
				sql += " AND comment LIKE  '%" + biko + "%'";
			}

			sql += " AND REPLACE(account_date,'-','') >= "
					+ dayStart.replace("-", "");
			sql += " AND REPLACE(account_date,'-','') <= "
					+ dayEnd.replace("-", "");
			;
			sql += " AND incoming >= " + inStart;
			sql += " AND incoming <= " + inEnd;
			sql += " AND outgoing >= " + outStart;
			sql += " AND outgoing <= " + outEnd;

			sql += " order by kanribango desc";

			ps = conn.prepareStatement(sql);
			ps.setString(1, account_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				meisai = new Meisai();
				meisaiList.add(meisai);
				meisai.setJiaoyiri(rs.getString("account_date"));
				meisai.setIncoming(rs.getString("incoming"));
				meisai.setOut(rs.getString("outgoing"));
				meisai.setKubun(Utility.getTypeNameCN(rs.getString("kubun")));
				meisai.setBalance(rs.getString("balance"));
				meisai.setLeibie(Utility.getFenleiName(rs.getString("type")));
				meisai.setBiko(rs.getString("comment"));
				meisai.setAddedFile1(Utility.isEmptyString(rs
						.getString("addedFile1")) ? "#" : rs
						.getString("addedFile1"));
				meisai.setAddedFile2(Utility.isEmptyString(rs
						.getString("addedFile2")) ? "#" : rs
						.getString("addedFile2"));
				meisai.setAddedFile3(Utility.isEmptyString(rs
						.getString("addedFile3")) ? "#" : rs
						.getString("addedFile3"));
				meisai.setAddedFile4(Utility.isEmptyString(rs
						.getString("addedFile4")) ? "#" : rs
						.getString("addedFile4"));
				meisai.setAddedFile5(Utility.isEmptyString(rs
						.getString("addedFile5")) ? "#" : rs
						.getString("addedFile5"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return meisaiList;
	}

	public static String getAccountBalance(String accountId, String bankCode)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		ResultSet rs = null;
		String balance = "0";
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;
			String tblName = Utility.getTblName(bankCode);
			sql = ("select * from " + tblName + " where account_id = ? order by kanribango desc limit 0,1");
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountId);
			rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getString("balance");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return balance;
	}

	public static String getBankCode(String accountId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		ResultSet rs = null;
		String bankcode = "";
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;
			sql = ("select * from accountinfo where account_id = ? ");
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountId);
			rs = ps.executeQuery();
			while (rs.next()) {
				bankcode = rs.getString("bank_code");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return bankcode;
	}

	public static String[] getAccountBalanceAndUpdateTime(Connection conn,
			String accountId, String bankCode) throws Exception {
		String[] data = new String[] { "0", "1900-01-01 00:00:00" };
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String tblName = Utility.getTblName(bankCode);
		sql = ("select * from " + tblName + " where account_id = ? order by kanribango desc limit 0,1");
		ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		rs = ps.executeQuery();
		while (rs.next()) {
			data[0] = rs.getString("balance");
			data[1] = rs.getString("update_time");
		}

		return data;
	}

	public static String getUndidCount(Connection conn, String accountId,
			String bankCode) throws Exception {
		String count = "0";
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String tblName = Utility.getTblName(bankCode);
		sql = ("select count(*) count from " + tblName + " where account_id = ? and type = '0'");
		ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		rs = ps.executeQuery();
		while (rs.next()) {
			count = rs.getString("count");
		}
		return count;
	}

	public static List<AlipayCsvBean> getAlipayCsvData(File alipayCsvFile)
			throws Exception {
		List<AlipayCsvBean> alipayDataList = new ArrayList<AlipayCsvBean>();
		AlipayCsvBean alipayData = null;

		List<String[]> dataList = Utility.readCsvFileUncode(alipayCsvFile,
				false, "GBK");
		boolean nextFlg = true;
		for (String[] data : dataList) {
			if ("账务流水号".equals(data[0])) {
				nextFlg = false;
				continue;
			} else if ("#----------------------------------------账务明细列表结束-------------------------------------"
					.equals(data[0])) {
				nextFlg = true;
				continue;
			}
			if (nextFlg) {
				continue;
			}
			alipayData = new AlipayCsvBean();
			alipayDataList.add(alipayData);

			int i = 0;
			// 账务流水号
			alipayData.setZhangwuliushuihao(data[i++]);
			// 业务流水号
			alipayData.setYewuliushuihao(data[i++]);
			// 商户订单号
			alipayData.setShanghudingdanhao(data[i++]);
			// 商品名称
			alipayData.setShangpinmingcheng(data[i++]);
			// 发生时间
			alipayData.setFashengshijian(data[i++]);
			// 对方账号
			alipayData.setDuifangzhanghao(data[i++]);
			// 收入金额（+元）
			alipayData.setShourujine(data[i++]);
			// 支出金额（-元）
			alipayData.setZhichujine(data[i++]);
			// 账户余额（元）
			alipayData.setZhanghuyue(data[i++]);
			// 交易渠道
			alipayData.setJiaoyiqudao(data[i++]);
			// 业务类型
			alipayData.setYewuleixing(data[i++]);
			// 备注
			alipayData.setBeizhu(data[i++]);
		}

		return alipayDataList;
	}

	public static List<AlipayMeisaiBean> converAliFormToBean(
			List<AlipayMeisai> meisaiList) {

		List<AlipayMeisaiBean> beanList = new ArrayList<AlipayMeisaiBean>();
		AlipayMeisaiBean bean = null;

		for (AlipayMeisai meisai : meisaiList) {
			bean = new AlipayMeisaiBean();
			beanList.add(bean);
			bean.setZhangdanfenlei(meisai.getZhangdanfenlei());
			bean.setFenleiKanrigango(meisai.getFenleiKanrigango());
			bean.setZhangwuliushuihao(meisai.getZhangwuliushuihao());
			bean.setYewuliushuihao(meisai.getYewuliushuihao());
			bean.setShanghudingdanhao(meisai.getShanghudingdanhao());
			bean.setShangpinmingcheng(meisai.getShangpinmingcheng());
			bean.setFashengshijian(meisai.getFashengshijian());
			bean.setDuifangzhanghao(meisai.getDuifangzhanghao());
			bean.setShourujine(meisai.getShourujine());
			bean.setZhichujine(meisai.getZhichujine());
			bean.setZhanghuyue(meisai.getZhanghuyue());
			bean.setJiaoyiqudao(meisai.getJiaoyiqudao());
			bean.setYewuleixing(meisai.getYewuleixing());
			bean.setBeizhu(meisai.getBeizhu());
			bean.setBeizhu2(meisai.getBeizhu2());
			bean.setAddedFile1(meisai.getAddedFile1());
			bean.setAddedFile2(meisai.getAddedFile2());
			bean.setAddedFile3(meisai.getAddedFile3());
			bean.setAddedFile4(meisai.getAddedFile4());
			bean.setAddedFile5(meisai.getAddedFile5());
		}

		return beanList;

	}

	public static String[] getAlipayZidongFenlei(AlipayCsvBean csvBean) {
		return new String[] { "0", "0" };

	}

	public static List<AlipayMeisaiBean> converAliCsvToBean(
			List<AlipayCsvBean> meisaiList) {

		List<AlipayMeisaiBean> beanList = new ArrayList<AlipayMeisaiBean>();
		AlipayMeisaiBean bean = null;

		for (AlipayCsvBean meisai : meisaiList) {
			bean = new AlipayMeisaiBean();
			beanList.add(bean);
			String[] data = getAlipayZidongFenlei(meisai);
			bean.setZhangdanfenlei(data[0]);
			bean.setFenleiKanrigango(data[1]);
			bean.setZhangwuliushuihao(Utility.strTrim(meisai
					.getZhangwuliushuihao()));
			bean.setYewuliushuihao(Utility.strTrim(meisai.getYewuliushuihao()));
			bean.setShanghudingdanhao(Utility.strTrim(meisai
					.getShanghudingdanhao()));
			bean.setShangpinmingcheng(Utility.strTrim(meisai
					.getShangpinmingcheng()));
			bean.setFashengshijian(Utility.strTrim(meisai.getFashengshijian()));
			bean.setDuifangzhanghao(Utility.strTrim(meisai.getDuifangzhanghao()));
			bean.setShourujine(Utility.strTrim(meisai.getShourujine()));
			bean.setZhichujine(Utility.strTrim(meisai.getZhichujine()));
			bean.setZhanghuyue(Utility.strTrim(meisai.getZhanghuyue()));
			bean.setJiaoyiqudao(Utility.strTrim(meisai.getJiaoyiqudao()));
			bean.setYewuleixing(Utility.strTrim(meisai.getYewuleixing()));
			bean.setBeizhu(Utility.strTrim(meisai.getBeizhu()));

		}

		return beanList;

	}

	public static void insertIntoAlipayTbl(String accountId,
			List<AlipayMeisaiBean> dataList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;
			sql = "INSERT INTO alipay_tbl (kanribango, account_id, zhangwuliushuihao, yewuliushuihao, shanghudingdanhao, shangpinming, account_date, duifangzhanghao, incoming, outgoing, balance, kubun, jiaoyiqudao, yewuleixing, beizhu, comment, type, addedFile1, addedFile2, addedFile3, addedFile4, addedFile5, CREATE_TIME, CREATE_USER, UPDATE_TIME, UPDATE_USER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			for (AlipayMeisaiBean meisai : dataList) {
				int i = 0;
				ps.setString(++i, Utility.getShoribango());
				ps.setString(++i, accountId);
				ps.setString(++i, meisai.getZhangwuliushuihao());
				ps.setString(++i, meisai.getYewuliushuihao());
				ps.setString(++i, meisai.getShanghudingdanhao());
				ps.setString(++i, meisai.getShangpinmingcheng());
				ps.setString(++i, meisai.getFashengshijian());
				ps.setString(++i, meisai.getDuifangzhanghao());
				ps.setString(++i, meisai.getShourujine());
				ps.setString(++i, meisai.getZhichujine());
				ps.setString(++i, meisai.getZhanghuyue());
				String kubun = "";
				if ("0".equals(meisai.getZhichujine())) {
					kubun = "0";
				} else {
					kubun = "1";
				}
				ps.setString(++i, kubun);
				ps.setString(++i, meisai.getJiaoyiqudao());
				ps.setString(++i, meisai.getYewuleixing());
				ps.setString(++i, meisai.getBeizhu());
				ps.setString(++i, meisai.getBeizhu2());
				ps.setString(++i, meisai.getFenleiKanrigango());
				ps.setString(++i, meisai.getAddedFile1());
				ps.setString(++i, meisai.getAddedFile2());
				ps.setString(++i, meisai.getAddedFile3());
				ps.setString(++i, meisai.getAddedFile4());
				ps.setString(++i, meisai.getAddedFile5());
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());

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

	public static List<AlipayMeisai> getAlipayDataList(String accountId,
			String limit) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<AlipayMeisai> meisaiList = new ArrayList<AlipayMeisai>();
		AlipayMeisai meisai = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "select * from alipay_tbl order by account_date desc limit 0,"
					+ limit;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				meisai = new AlipayMeisai();
				meisaiList.add(meisai);
				meisai.setZhangdanfenlei(Utility.getFenleiNameConn(
						rs.getString("type"), conn));
				meisai.setFenleiKanrigango(rs.getString("type"));
				meisai.setZhangwuliushuihao(rs.getString("zhangwuliushuihao"));
				meisai.setYewuliushuihao(rs.getString("yewuliushuihao"));
				meisai.setShanghudingdanhao(rs.getString("shanghudingdanhao"));
				String spm = "";
				if (!Utility.isEmptyString(rs.getString("shangpinming"))) {
					if (rs.getString("shangpinming").length() > 50) {
						spm = rs.getString("shangpinming").substring(0, 50)
								+ "...";
					} else {
						spm = rs.getString("shangpinming");
					}
				}
				meisai.setShangpinmingcheng(spm);
				meisai.setFashengshijian(rs.getString("account_date"));
				meisai.setDuifangzhanghao(rs.getString("duifangzhanghao"));
				meisai.setShourujine(rs.getString("incoming"));
				meisai.setZhichujine(rs.getString("outgoing"));
				meisai.setZhanghuyue(rs.getString("balance"));
				meisai.setJiaoyiqudao(rs.getString("jiaoyiqudao"));
				meisai.setYewuleixing(rs.getString("yewuleixing"));
				meisai.setBeizhu(rs.getString("beizhu"));
				meisai.setBeizhu2(rs.getString("comment"));
				meisai.setAddedFile1(rs.getString("addedFile1"));
				meisai.setAddedFile2(rs.getString("addedFile2"));
				meisai.setAddedFile3(rs.getString("addedFile3"));
				meisai.setAddedFile4(rs.getString("addedFile4"));
				meisai.setAddedFile5(rs.getString("addedFile5"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return meisaiList;

	}

}
