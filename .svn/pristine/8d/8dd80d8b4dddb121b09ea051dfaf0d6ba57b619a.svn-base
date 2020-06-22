package com.rakuten.r1501.action;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1501.bean.AlipayCsvBean;
import com.rakuten.r1501.bean.AlipayMeisaiBean;
import com.rakuten.r1501.common.A1501Common;
import com.rakuten.r1501.form.Account;
import com.rakuten.r1501.form.F150104;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15010403Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	F150104 f150104 = null;

	protected void exec() throws Exception {
		File csvFile = f150104.getAlipayCsvFile();
		List<AlipayCsvBean> alipayDataList = A1501Common
				.getAlipayCsvData(csvFile);
		List<AlipayMeisaiBean> beanList = A1501Common
				.converAliCsvToBean(alipayDataList);
		A1501Common.insertIntoAlipayTbl(f150104.getAccount_id(), beanList);
	}

	protected void init() throws Exception {
		Account account = A1501Common.getAccountInfo(f150104.getAccount_id());
		setTitle("V150104:账目明细 " + account.getName() + " "
				+ account.getCardNo() + " " + account.getTypeName());

	}

	protected void isValidated() throws Exception {
		File csvFile = f150104.getAlipayCsvFile();
		List<AlipayCsvBean> alipayDataList = A1501Common
				.getAlipayCsvData(csvFile);
		List<AlipayCsvBean> shoriList = new ArrayList<AlipayCsvBean>();
		// 获得导入前的余额

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "select count(*) count from alipay_tbl where zhangwuliushuihao = ?";
			ps = conn.prepareStatement(sql);

			// 数据连续性检查
			for (int i = alipayDataList.size() - 1; i > 0; i--) {
				AlipayCsvBean newData = alipayDataList.get(i);
				AlipayCsvBean nextData = alipayDataList.get(i - 1);
				Double yue = Double.valueOf(newData.getZhanghuyue())
						+ Double.valueOf(nextData.getShourujine())
						+ Double.valueOf(nextData.getZhichujine());
				BigDecimal yuebd = new BigDecimal(yue);
				yuebd = yuebd.setScale(2, BigDecimal.ROUND_HALF_UP);
				yue = yuebd.doubleValue();

				if (yue.compareTo(Double.valueOf(nextData.getZhanghuyue())) != 0) {
					addError(null, "数据连续性有误！");
					return;
				}
			}

			// 既存数据检查
			for (AlipayCsvBean csvBean : alipayDataList) {
				String zhangwuliushuihao = Utility.strTrim(csvBean
						.getZhangwuliushuihao());
				ps.setString(1, zhangwuliushuihao);
				rs = ps.executeQuery();
				while (rs.next()) {
					int count = rs.getInt("count");
					if (count == 0) {
						shoriList.add(csvBean);
					}
				}
			}
			if (Utility.isEmptyList(shoriList)) {
				addError(null, "没有需要导入的数据！");
				return;
			}

			Double balance = Double.valueOf(A1501Common
					.getAccountBalanceAndUpdateTime(conn,
							f150104.getAccount_id(), "002")[0]);
			AlipayCsvBean firstData = shoriList.get(shoriList.size() - 1);
			// 检查余额相加是否匹配
			if (balance + Double.valueOf(firstData.getShourujine())
					+ Double.valueOf(firstData.getZhichujine()) != Double
						.valueOf(firstData.getZhanghuyue())) {
				sql = "select count(*) count from alipay_tbl where 1=1";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				Long count = 0L;
				while (rs.next()) {
					count = rs.getLong("count");
				}
				if (count != 0L) {
					addError(null, "导入数据与最后操作日的余额数不符！");
					return;
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

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F150104 getF150104() {
		return f150104;
	}

	public void setF150104(F150104 f150104) {
		this.f150104 = f150104;
	}

}
