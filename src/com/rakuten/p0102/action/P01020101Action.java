package com.rakuten.p0102.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShohinStsInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.p0102.form.P010201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class P01020101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private P010201 p010201 = null;
	private String shohinbango = null;
	private String testReslut = null;

	protected void exec() throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderinfoBean = orderCommon.getOrderInfo();

		List<ShouhinStsBean> shouhinStsBeanList = orderinfoBean
				.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderinfoBean
				.getCommonOrderBeanList();
		StringBuffer sb = new StringBuffer();
		boolean ariFlg = false;
		for (ShouhinStsBean bean : shouhinStsBeanList) {
			if (bean.getShouhinbango().equals(shohinbango)) {
				ariFlg = true;
				// ��Ʒ����
				sb.append(bean.getShouhinbango());
				sb.append("\r\n");
				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("�������:");
				sb.append("\r\n");
				sb.append("\r\n");

				sb.append("��Ʒ����:" + bean.getShouhinbango() + ",");
				sb.append("\r\n");
				// �Ϻ��ڎ���
				sb.append("�Ϻ��ڎ���:" + bean.getShanghaiStock() + ",     ");
				// �ձ�����
				sb.append("�ձ�����:" + bean.getNihonStock() + ",     ");
				// ����Ђ���
				sb.append("����Ђ���:" + bean.getNyukachukosu() + ",     ");
				// �\��;�Ђ���
				sb.append("�\��;�Ђ���:" + bean.getUnsochukosu() + ",");
				sb.append("\r\n");
				// �Фꂀ��(�Ϻ�)
				sb.append("�Фꂀ��(�Ϻ�):" + bean.getNokorikosuSh() + ",     ");
				// �Фꂀ��(�ձ�)
				sb.append("�Фꂀ��(�ձ�):" + bean.getNokorikosuJp() + ",     ");
				// �Фꂀ��(�\��)
				sb.append("�Фꂀ��(�\��):" + bean.getNokorikosuUnso() + ",     ");
				// �Фꂀ��(���)
				sb.append("�Фꂀ��(���):" + bean.getNokorikosuNyuka() + ",");
				sb.append("\r\n");
				sb.append("\r\n");
				List<ShohinStsInfoBean> detailList = bean
						.getShohinStsInfoBeanList();

				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("�\�����:");
				sb.append("\r\n");
				sb.append("\r\n");
				List<String[]> unsoList = orderCommon.getUnsochuList();
				for (int i = 0; i < unsoList.size(); i++) {
					if (unsoList.get(i)[0].equals(shohinbango)) {
						sb.append("�k����:" + unsoList.get(i)[3] + "  ");
						sb.append("��Ʊ����:" + unsoList.get(i)[2] + "  ");
						sb.append("����:" + unsoList.get(i)[1]);
						sb.append("\r\n");
					}
				}

				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("������:");
				sb.append("\r\n");
				sb.append("\r\n");
				List<String[]> nyukaList = orderCommon.getNyukaChuList();
				for (int i = 0; i < nyukaList.size(); i++) {
					if (nyukaList.get(i)[0].equals(shohinbango)) {
						sb.append("�����:" + nyukaList.get(i)[2] + "  ");
						sb.append("����:" + nyukaList.get(i)[1]);
						sb.append("\r\n");
					}
				}

				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("ע�����:");
				sb.append("\r\n");
				sb.append("\r\n");

				for (ShohinStsInfoBean detail : detailList) {
					String juchubango = detail.getJuchubango();
					for (CommonOrderBean commonOrder : commonOrderBeanList) {
						if (commonOrder.getJuchubango().equals(juchubango)) {
							sb.append("���Ʃ`������K�O���Օr:");
							sb.append("\r\n");

							sb.append("ע�ĥ��Ʃ`����:");
							sb.append("\r\n");
						}
					}
					sb.append("��ע����:" + juchubango);
					sb.append("\r\n");
					sb.append("ע���Օr:" + detail.getChumonnichiji());
					sb.append("\r\n");
					sb.append("��Ҫ����:"
							+ String.valueOf(Integer.valueOf(detail
									.getHoryukosuSh())
									+ Integer.valueOf(detail.getHoryukosuJp())
									+ Integer.valueOf(detail.getHoryukosuUnso())
									+ Integer.valueOf(detail
											.getHoryukosuNyuka())
									+ Integer.valueOf(detail.getHiyuyokosu())));
					sb.append("\r\n");
					sb.append("������(�Ϻ�stock):" + detail.getHoryukosuSh());
					sb.append("\r\n");

					sb.append("������(�ձ�stock):" + detail.getHoryukosuJp());
					sb.append("\r\n");

					sb.append("������(�\��;��stock):" + detail.getHoryukosuUnso());
					sb.append("\r\n");
					if (!Utility.isEmptyList(detail.getUnsohoryuList())) {
						for (int i = 0; i < detail.getUnsohoryuList().size(); i++) {
							sb.append("��Ʊ����:" + detail.getDenpyoubango().get(i)
									+ "  ");
							sb.append("�k����:" + detail.getHasoubi().get(i)
									+ "  ");
							sb.append("������:"
									+ detail.getUnsohoryuList().get(i));
							sb.append("\r\n");
						}
					}

					sb.append("������(�����stock):" + detail.getHoryukosuNyuka());
					sb.append("\r\n");
					if (!Utility.isEmptyList(detail.getNyukahoryuList())) {
						for (int i = 0; i < detail.getNyukahoryuList().size(); i++) {
							sb.append("�����:" + detail.getNyukabi().get(i)
									+ "  ");
							sb.append("������:"
									+ detail.getNyukahoryuList().get(i));
							sb.append("\r\n");
						}
					}

					sb.append("�Ф��Ҫ����:" + detail.getHiyuyokosu());
					sb.append("\r\n");

					sb.append("�������Ʃ`����:" + detail.getHoryusts());
					sb.append("\r\n");
					sb.append("\r\n");
				}
			}
		}
		if (!ariFlg) {
			sb.append(shohinbango);
			sb.append("\r\n");
			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("�������:");
			sb.append("\r\n");
			sb.append("\r\n");

			sb.append("��Ʒ����:" + shohinbango + ",");
			sb.append("\r\n");
			List<String[]> unsoList = orderCommon.getUnsochuList();
			List<String[]> nyukaList = orderCommon.getNyukaChuList();

			Connection conn = null;
			String stocksh = "";
			try {
				conn = JdbcConnection.getConnection();

				PreparedStatement ps = null;

				String sql = "SELECT * FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, Utility.getCommodityId(shohinbango));
				ps.setString(2, Utility.getDetailN0(shohinbango));
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					stocksh = rs.getString("STOCK_SH");
				}
			} finally {
				conn.close();
			}

			String stockjp = "";
			try {
				conn = JdbcConnection.getConnection();

				PreparedStatement ps = null;

				String sql = "SELECT * FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, Utility.getCommodityId(shohinbango));
				ps.setString(2, Utility.getDetailN0(shohinbango));
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					stockjp = rs.getString("STOCK_JP");
				}
			} finally {
				conn.close();
			}

			// �Ϻ��ڎ���
			sb.append("�Ϻ��ڎ���:" + stocksh + ",     ");
			// �ձ�����
			sb.append("�ձ�����:" + stockjp + ",     ");

			sb.append("\r\n");
			sb.append("\r\n");

			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("�\�����:");
			sb.append("\r\n");
			sb.append("\r\n");

			for (int i = 0; i < unsoList.size(); i++) {
				if (unsoList.get(i)[0].equals(shohinbango)) {
					sb.append("�k����:" + unsoList.get(i)[3] + "  ");
					sb.append("��Ʊ����:" + unsoList.get(i)[2] + "  ");
					sb.append("����:" + unsoList.get(i)[1]);
					sb.append("\r\n");
				}
			}

			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("������:");
			sb.append("\r\n");
			sb.append("\r\n");

			for (int i = 0; i < nyukaList.size(); i++) {
				if (nyukaList.get(i)[0].equals(shohinbango)) {
					sb.append("�����:" + nyukaList.get(i)[2] + "  ");
					sb.append("����:" + nyukaList.get(i)[1]);
					sb.append("\r\n");
				}
			}

			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("ע�����:�ʤ�");

		}
		testReslut = sb.toString();
	}

	protected void init() {
		setTitle("P010101:��Ʒ�������");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public P010201 getP010201() {
		return p010201;
	}

	public void setP010201(P010201 p010201) {
		this.p010201 = p010201;
	}

	public String getTestReslut() {
		return testReslut;
	}

	public void setTestReslut(String testReslut) {
		this.testReslut = testReslut;
	}

}
