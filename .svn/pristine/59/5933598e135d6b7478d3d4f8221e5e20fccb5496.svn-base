package com.rakuten.r1202.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.PicList;
import com.rakuten.r1202.form.ShohinList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A12020101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String shumokuid = null;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();

	protected void exec() throws Exception {
		f120201 = new F120201();
		f120201.setShumokuid(shumokuid);

		List<F120201Detail> detailList = getDetailList();
		a120201Common.honykuAndSet(detailList);
		setSessionAttribute("f120201DetailList", detailList);

		List<ShohinList> shohinList = a120201Common.getshohinList(detailList,
				f120201);
		f120201.setShohinList(shohinList);

		if (!Utility.isEmptyList(detailList)) {
			detail = detailList.get(0);
			f120201.setShohinbango_selected(detail.getShohinbango());
		}

	}

	private List<F120201Detail> getDetailList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<F120201Detail> detailList = new ArrayList<F120201Detail>();
		F120201Detail detail = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT *  FROM tbl00023 WHERE SHUMOKUID = ? ORDER BY SHURUI";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokuid);
			rs = ps.executeQuery();

			while (rs.next()) {
				detail = new F120201Detail();
				detailList.add(detail);
				detail.setDetailDelFlg(rs.getString("SHURUI"));
				detail.setShohinbango_moto(rs.getString("SHOHINBANGO"));
				detail.setShohinbango(rs.getString("SHOHINBANGO"));
				detail.setShohinmei_cn(rs.getString("SHOINMEICN"));
				detail.setShiireurl(rs.getString("SHIIREURL"));
				detail.setShiirekakaku(rs.getString("SHIIREKAKAKU"));
				detail.setMerubin(rs.getString("MERUBIN"));
				detail.setKategori1(rs.getString("KATEGORI1"));
				detail.setKategori2(rs.getString("KATEGORI2"));
				detail.setKategori3(rs.getString("KATEGORI3"));
				detail.setKategori4(rs.getString("KATEGORI4"));

				detail.setYokomei(rs.getString("YOKOKOMOKUMEI"));
				detail.setShitagamei(rs.getString("SHITAGAKOMOKUMEI"));
				detail.setSozainaiyo(rs.getString("SOZAINAIYO"));
				detail.setShohinshosaimeisho1(rs
						.getString("SHOHINSHOSAI1MEISHO"));
				detail.setShohinshosainaiyo1(rs.getString("SHOHINSHOSAI1NAIYO"));
				detail.setShohinshosaimeisho2(rs
						.getString("SHOHINSHOSAI2MEISHO"));
				detail.setShohinshosainaiyo2(rs.getString("SHOHINSHOSAI2NAIYO"));
				detail.setShohinshosaimeisho3(rs
						.getString("SHOHINSHOSAI3MEISHO"));
				detail.setShohinshosainaiyo3(rs.getString("SHOHINSHOSAI3NAIYO"));
				detail.setShohinshosaimeisho4(rs
						.getString("SHOHINSHOSAI4MEISHO"));
				detail.setShohinshosainaiyo4(rs.getString("SHOHINSHOSAI4NAIYO"));
				detail.setShohinshosaimeisho5(rs
						.getString("SHOHINSHOSAI5MEISHO"));
				detail.setShohinshosainaiyo5(rs.getString("SHOHINSHOSAI5NAIYO"));
				detail.setShohinshosaimeisho6(rs
						.getString("SHOHINSHOSAI6MEISHO"));
				detail.setShohinshosainaiyo6(rs.getString("SHOHINSHOSAI6NAIYO"));

				detail.setShohinmei_jp(rs.getString("SHOHINMEI"));
				detail.setPckyachikopi(rs.getString("PCYOKYACHI"));
				detail.setMobairukyachikopi(rs.getString("MOBAIRUYOKYACHI"));
				detail.setDirekutoriid(rs.getString("DIREKUTORIID"));
				detail.setTaguid(rs.getString("TAGUID"));
				detail.setHanbaikakaku(rs.getString("HANBAIKAKAKU"));
				detail.setHyojikakaku(rs.getString("HYOJIKAKAKU"));
				detail.setSoryobetsu(rs.getString("SORYOBETSU"));
				detail.setShohizeibetsu(rs.getString("ZEIBETSU"));

				detail.setYokonaiyo1(rs.getString("YOKONAIYO1"));
				detail.setYokonaiyo2(rs.getString("YOKONAIYO2"));
				detail.setYokonaiyo3(rs.getString("YOKONAIYO3"));
				detail.setYokonaiyo4(rs.getString("YOKONAIYO4"));
				detail.setYokonaiyo5(rs.getString("YOKONAIYO5"));
				detail.setYokonaiyo6(rs.getString("YOKONAIYO6"));
				detail.setYokonaiyo7(rs.getString("YOKONAIYO7"));
				detail.setYokonaiyo8(rs.getString("YOKONAIYO8"));
				detail.setYokonaiyo9(rs.getString("YOKONAIYO9"));
				detail.setYokonaiyo10(rs.getString("YOKONAIYO10"));
				detail.setYokonaiyo11(rs.getString("YOKONAIYO11"));
				detail.setYokonaiyo12(rs.getString("YOKONAIYO12"));
				detail.setYokonaiyo13(rs.getString("YOKONAIYO13"));
				detail.setYokonaiyo14(rs.getString("YOKONAIYO14"));
				detail.setYokonaiyo15(rs.getString("YOKONAIYO15"));
				detail.setYokonaiyo16(rs.getString("YOKONAIYO16"));
				detail.setYokonaiyo17(rs.getString("YOKONAIYO17"));
				detail.setYokonaiyo18(rs.getString("YOKONAIYO18"));
				detail.setYokonaiyo19(rs.getString("YOKONAIYO19"));
				detail.setYokonaiyo20(rs.getString("YOKONAIYO20"));

				detail.setShitaganaiyo1(rs.getString("SHITAGANAIYO1"));
				detail.setShitaganaiyo2(rs.getString("SHITAGANAIYO2"));
				detail.setShitaganaiyo3(rs.getString("SHITAGANAIYO3"));
				detail.setShitaganaiyo4(rs.getString("SHITAGANAIYO4"));
				detail.setShitaganaiyo5(rs.getString("SHITAGANAIYO5"));
				detail.setShitaganaiyo6(rs.getString("SHITAGANAIYO6"));
				detail.setShitaganaiyo7(rs.getString("SHITAGANAIYO7"));
				detail.setShitaganaiyo8(rs.getString("SHITAGANAIYO8"));
				detail.setShitaganaiyo9(rs.getString("SHITAGANAIYO9"));
				detail.setShitaganaiyo10(rs.getString("SHITAGANAIYO10"));
				detail.setShitaganaiyo11(rs.getString("SHITAGANAIYO11"));
				detail.setShitaganaiyo12(rs.getString("SHITAGANAIYO12"));
				detail.setShitaganaiyo13(rs.getString("SHITAGANAIYO13"));
				detail.setShitaganaiyo14(rs.getString("SHITAGANAIYO14"));
				detail.setShitaganaiyo15(rs.getString("SHITAGANAIYO15"));
				detail.setShitaganaiyo16(rs.getString("SHITAGANAIYO16"));
				detail.setShitaganaiyo17(rs.getString("SHITAGANAIYO17"));
				detail.setShitaganaiyo18(rs.getString("SHITAGANAIYO18"));
				detail.setShitaganaiyo19(rs.getString("SHITAGANAIYO19"));
				detail.setShitaganaiyo20(rs.getString("SHITAGANAIYO20"));

				String[] sizeyokoArr = rs.getString("SIZEYOKOLIST").split("&",
						10);
				int j = 0;
				detail.setSizeyoko1(sizeyokoArr[j++]);
				detail.setSizeyoko2(sizeyokoArr[j++]);
				detail.setSizeyoko3(sizeyokoArr[j++]);
				detail.setSizeyoko4(sizeyokoArr[j++]);
				detail.setSizeyoko5(sizeyokoArr[j++]);
				detail.setSizeyoko6(sizeyokoArr[j++]);
				detail.setSizeyoko7(sizeyokoArr[j++]);
				detail.setSizeyoko8(sizeyokoArr[j++]);
				detail.setSizeyoko9(sizeyokoArr[j++]);
				detail.setSizeyoko10(sizeyokoArr[j++]);

				String[] sizeshitagaArr = rs.getString("SIZESHITAGALIST")
						.split("&", 10);
				j = 0;
				detail.setSizeshitaga1(sizeshitagaArr[j++]);
				detail.setSizeshitaga2(sizeshitagaArr[j++]);
				detail.setSizeshitaga3(sizeshitagaArr[j++]);
				detail.setSizeshitaga4(sizeshitagaArr[j++]);
				detail.setSizeshitaga5(sizeshitagaArr[j++]);
				detail.setSizeshitaga6(sizeshitagaArr[j++]);
				detail.setSizeshitaga7(sizeshitagaArr[j++]);
				detail.setSizeshitaga8(sizeshitagaArr[j++]);
				detail.setSizeshitaga9(sizeshitagaArr[j++]);
				detail.setSizeshitaga10(sizeshitagaArr[j++]);

				String[] sizeArr = rs.getString("SIZELIST").split("&", 100);
				j = 0;
				detail.setSize11(sizeArr[j++]);
				detail.setSize12(sizeArr[j++]);
				detail.setSize13(sizeArr[j++]);
				detail.setSize14(sizeArr[j++]);
				detail.setSize15(sizeArr[j++]);
				detail.setSize16(sizeArr[j++]);
				detail.setSize17(sizeArr[j++]);
				detail.setSize18(sizeArr[j++]);
				detail.setSize19(sizeArr[j++]);
				detail.setSize110(sizeArr[j++]);

				detail.setSize21(sizeArr[j++]);
				detail.setSize22(sizeArr[j++]);
				detail.setSize23(sizeArr[j++]);
				detail.setSize24(sizeArr[j++]);
				detail.setSize25(sizeArr[j++]);
				detail.setSize26(sizeArr[j++]);
				detail.setSize27(sizeArr[j++]);
				detail.setSize28(sizeArr[j++]);
				detail.setSize29(sizeArr[j++]);
				detail.setSize210(sizeArr[j++]);

				detail.setSize31(sizeArr[j++]);
				detail.setSize32(sizeArr[j++]);
				detail.setSize33(sizeArr[j++]);
				detail.setSize34(sizeArr[j++]);
				detail.setSize35(sizeArr[j++]);
				detail.setSize36(sizeArr[j++]);
				detail.setSize37(sizeArr[j++]);
				detail.setSize38(sizeArr[j++]);
				detail.setSize39(sizeArr[j++]);
				detail.setSize310(sizeArr[j++]);

				detail.setSize41(sizeArr[j++]);
				detail.setSize42(sizeArr[j++]);
				detail.setSize43(sizeArr[j++]);
				detail.setSize44(sizeArr[j++]);
				detail.setSize45(sizeArr[j++]);
				detail.setSize46(sizeArr[j++]);
				detail.setSize47(sizeArr[j++]);
				detail.setSize48(sizeArr[j++]);
				detail.setSize49(sizeArr[j++]);
				detail.setSize410(sizeArr[j++]);

				detail.setSize51(sizeArr[j++]);
				detail.setSize52(sizeArr[j++]);
				detail.setSize53(sizeArr[j++]);
				detail.setSize54(sizeArr[j++]);
				detail.setSize55(sizeArr[j++]);
				detail.setSize56(sizeArr[j++]);
				detail.setSize57(sizeArr[j++]);
				detail.setSize58(sizeArr[j++]);
				detail.setSize59(sizeArr[j++]);
				detail.setSize510(sizeArr[j++]);

				detail.setSize61(sizeArr[j++]);
				detail.setSize62(sizeArr[j++]);
				detail.setSize63(sizeArr[j++]);
				detail.setSize64(sizeArr[j++]);
				detail.setSize65(sizeArr[j++]);
				detail.setSize66(sizeArr[j++]);
				detail.setSize67(sizeArr[j++]);
				detail.setSize68(sizeArr[j++]);
				detail.setSize69(sizeArr[j++]);
				detail.setSize610(sizeArr[j++]);

				detail.setSize71(sizeArr[j++]);
				detail.setSize72(sizeArr[j++]);
				detail.setSize73(sizeArr[j++]);
				detail.setSize74(sizeArr[j++]);
				detail.setSize75(sizeArr[j++]);
				detail.setSize76(sizeArr[j++]);
				detail.setSize77(sizeArr[j++]);
				detail.setSize78(sizeArr[j++]);
				detail.setSize79(sizeArr[j++]);
				detail.setSize710(sizeArr[j++]);

				detail.setSize81(sizeArr[j++]);
				detail.setSize82(sizeArr[j++]);
				detail.setSize83(sizeArr[j++]);
				detail.setSize84(sizeArr[j++]);
				detail.setSize85(sizeArr[j++]);
				detail.setSize86(sizeArr[j++]);
				detail.setSize87(sizeArr[j++]);
				detail.setSize88(sizeArr[j++]);
				detail.setSize89(sizeArr[j++]);
				detail.setSize810(sizeArr[j++]);

				detail.setSize91(sizeArr[j++]);
				detail.setSize92(sizeArr[j++]);
				detail.setSize93(sizeArr[j++]);
				detail.setSize94(sizeArr[j++]);
				detail.setSize95(sizeArr[j++]);
				detail.setSize96(sizeArr[j++]);
				detail.setSize97(sizeArr[j++]);
				detail.setSize98(sizeArr[j++]);
				detail.setSize99(sizeArr[j++]);
				detail.setSize910(sizeArr[j++]);

				detail.setSize101(sizeArr[j++]);
				detail.setSize102(sizeArr[j++]);
				detail.setSize103(sizeArr[j++]);
				detail.setSize104(sizeArr[j++]);
				detail.setSize105(sizeArr[j++]);
				detail.setSize106(sizeArr[j++]);
				detail.setSize107(sizeArr[j++]);
				detail.setSize108(sizeArr[j++]);
				detail.setSize109(sizeArr[j++]);
				detail.setSize1010(sizeArr[j++]);

				detail.setPicdir(rs.getString("PICDIR"));
				String pic = rs.getString("PICLIST");
				String check = rs.getString("PICSELECTLIST");
				if (!Utility.isEmptyString(pic)) {
					List<PicList> picList = new ArrayList<PicList>();
					detail.setPicList(picList);

					String[] picArr = pic.split("&");

					for (String picurl : picArr) {
						PicList picinfo = new PicList();
						picinfo.setPicurl(picurl);
						picList.add(picinfo);
					}
					if (!Utility.isEmptyString(check)) {
						String[] checkArr = check.split("&");
						for (String checkinfo : checkArr) {
							int index = Integer.valueOf(checkinfo);
							picList.get(index).setChecked(true);
						}
					}
				}

				detail.setMerubinsoryomuryokyanpen("0".equals(rs
						.getString("MERUBINSORYOMURYOKYNPEN")) ? false : true);
				detail.setHogofirumu("0".equals(rs.getString("HOGOFIRUMU")) ? false
						: true);
				detail.setSizepic("0".equals(rs.getString("SIZEPIC")) ? false
						: true);
				detail.setPycommonpic("0".equals(rs.getString("PYCOMMON")) ? false
						: true);
				detail.setXfpycommonpic("0".equals(rs.getString("XFPYCOMMON")) ? false
						: true);
				detail.setOsusume1("0".equals(rs.getString("osusume1")) ? false
						: true);
				detail.setOsusume2("0".equals(rs.getString("osusume2")) ? false
						: true);
				detail.setOsusume3("0".equals(rs.getString("osusume3")) ? false
						: true);
				detail.setNouki("0".equals(rs.getString("nouki")) ? false
						: true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return detailList;
	}

	protected void init() throws Exception {
		setTitle("V120201:ÎÄ°¸±à¼­:  " + a120201Common.getShumokuname(shumokuid));
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f120201
	 */
	public F120201 getF120201() {
		return f120201;
	}

	/**
	 * @param f120201
	 *            the f120201 to set
	 */
	public void setF120201(F120201 f120201) {
		this.f120201 = f120201;
	}

	/**
	 * @return the detail
	 */
	public F120201Detail getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(F120201Detail detail) {
		this.detail = detail;
	}

	/**
	 * @return the shumokuid
	 */
	public String getShumokuid() {
		return shumokuid;
	}

	/**
	 * @param shumokuid
	 *            the shumokuid to set
	 */
	public void setShumokuid(String shumokuid) {
		this.shumokuid = shumokuid;
	}

}
