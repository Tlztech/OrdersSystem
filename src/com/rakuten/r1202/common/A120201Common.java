package com.rakuten.r1202.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.bean.ShohinInfoBean;
import com.rakuten.common.bean.ShohinkategoriBean;
import com.rakuten.common.bean.ShohinsentakushiBean;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.PicList;
import com.rakuten.r1202.form.ShohinList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A120201Common {

	public List<ShohinList> getshohinList(List<F120201Detail> detailList, F120201 f120201) {
		List<ShohinList> shohinList = new ArrayList<ShohinList>();
		String keyword = f120201.getKeyword();
		String kihon_kensaku = f120201.getKihon_kensaku();
		String kyachikopi_kensaku = f120201.getKyachikopi_kensaku();
		String kakaku_kensaku = f120201.getKakaku_kensaku();
		String pic_kensaku = f120201.getPic_kensaku();
		String sonota_kensaku = f120201.getSonota_kensaku();
		String honyaku_kensaku = f120201.getHonyaku_kensaku();
		String hozon_kensaku = f120201.getHozon_kensaku();
		String del_kensaku = f120201.getDel_kensaku();
		ShohinList shohin = null;
		for (F120201Detail detail : detailList) {
			shohin = new ShohinList();
			shohinList.add(shohin);

			shohin.setShohinbango(detail.getShohinbango());
			if ("1".equals(detail.getHozonFlg())) {
				shohin.setHozonFlg("1");
			} else {
				shohin.setHozonFlg("0");
			}
			shohin.setDelFlg(detail.getDetailDelFlg());

			// 基本情报
			String shohinbango = detail.getShohinbango();
			String shohinmei_cn = detail.getShohinmei_cn();
			String shiireurl = detail.getShiireurl();
			String shiirekakaku = detail.getShiirekakaku();
			String merubin = detail.getMerubin();
			String kategori1 = detail.getKategori1();
			String yokomei_a = detail.getYokomei();
			String shitagamei_a = detail.getShitagamei();
			String sozainaiyo = detail.getSozainaiyo();
			String yokonaiyo1_a = detail.getYokonaiyo1();
			String shitaganaiyo1_a = detail.getShitaganaiyo1();

			if (!Utility.isEmptyString(shohinbango) && !Utility.isEmptyString(shohinmei_cn)
					&& !Utility.isEmptyString(shiireurl) && !Utility.isEmptyString(shiirekakaku)
					&& !Utility.isEmptyString(merubin) && !Utility.isEmptyString(kategori1)
					&& !Utility.isEmptyString(yokomei_a) && !Utility.isEmptyString(sozainaiyo)
					&& !Utility.isEmptyString(yokonaiyo1_a)) {
				shohin.setKihonnyuryoku("1");
			}

			if (!Utility.isEmptyString(shitagamei_a)) {
				if (Utility.isEmptyString(shitaganaiyo1_a)) {
					shohin.setKihonnyuryoku("0");
				}
			}

			// 关键字
			String shohinmei_jp = detail.getShohinmei_jp();
			String pckyachikopi = detail.getPckyachikopi();
			String mobairukyachikopi = detail.getMobairukyachikopi();
			String direkutoriid = detail.getDirekutoriid();

			if (!Utility.isEmptyString(shohinmei_jp) && !Utility.isEmptyString(pckyachikopi)
					&& !Utility.isEmptyString(mobairukyachikopi) && !Utility.isEmptyString(direkutoriid)) {
				shohin.setKyachikopinyuryoku("1");
			}

			// 价格
			String hanbaikakaku = detail.getHanbaikakaku();
			if (!Utility.isEmptyString(hanbaikakaku)) {
				shohin.setKakakunyuryoku("1");
			}

			// 翻译
			String yokomei = detail.getYokomei();
			String shitagamei = detail.getShitagamei();
			String shohinshosaimeisho1 = detail.getShohinshosaimeisho1();
			String shohinshosainaiyo1 = detail.getShohinshosainaiyo1();
			String shohinshosaimeisho2 = detail.getShohinshosaimeisho2();
			String shohinshosainaiyo2 = detail.getShohinshosainaiyo2();
			String shohinshosaimeisho3 = detail.getShohinshosaimeisho3();
			String shohinshosainaiyo3 = detail.getShohinshosainaiyo3();
			String shohinshosaimeisho4 = detail.getShohinshosaimeisho4();
			String shohinshosainaiyo4 = detail.getShohinshosainaiyo4();
			String shohinshosaimeisho5 = detail.getShohinshosaimeisho5();
			String shohinshosainaiyo5 = detail.getShohinshosainaiyo5();
			String shohinshosaimeisho6 = detail.getShohinshosaimeisho6();
			String shohinshosainaiyo6 = detail.getShohinshosainaiyo6();
			String yokonaiyo1 = detail.getYokonaiyo1();
			String yokonaiyo2 = detail.getYokonaiyo2();
			String yokonaiyo3 = detail.getYokonaiyo3();
			String yokonaiyo4 = detail.getYokonaiyo4();
			String yokonaiyo5 = detail.getYokonaiyo5();
			String yokonaiyo6 = detail.getYokonaiyo6();
			String yokonaiyo7 = detail.getYokonaiyo7();
			String yokonaiyo8 = detail.getYokonaiyo8();
			String yokonaiyo9 = detail.getYokonaiyo9();
			String yokonaiyo10 = detail.getYokonaiyo10();
			String yokonaiyo11 = detail.getYokonaiyo11();
			String yokonaiyo12 = detail.getYokonaiyo12();
			String yokonaiyo13 = detail.getYokonaiyo13();
			String yokonaiyo14 = detail.getYokonaiyo14();
			String yokonaiyo15 = detail.getYokonaiyo15();
			String yokonaiyo16 = detail.getYokonaiyo16();
			String yokonaiyo17 = detail.getYokonaiyo17();
			String yokonaiyo18 = detail.getYokonaiyo18();
			String yokonaiyo19 = detail.getYokonaiyo19();
			String yokonaiyo20 = detail.getYokonaiyo20();
			String shitaganaiyo1 = detail.getShitaganaiyo1();
			String shitaganaiyo2 = detail.getShitaganaiyo2();
			String shitaganaiyo3 = detail.getShitaganaiyo3();
			String shitaganaiyo4 = detail.getShitaganaiyo4();
			String shitaganaiyo5 = detail.getShitaganaiyo5();
			String shitaganaiyo6 = detail.getShitaganaiyo6();
			String shitaganaiyo7 = detail.getShitaganaiyo7();
			String shitaganaiyo8 = detail.getShitaganaiyo8();
			String shitaganaiyo9 = detail.getShitaganaiyo9();
			String shitaganaiyo10 = detail.getShitaganaiyo10();
			String shitaganaiyo11 = detail.getShitaganaiyo11();
			String shitaganaiyo12 = detail.getShitaganaiyo12();
			String shitaganaiyo13 = detail.getShitaganaiyo13();
			String shitaganaiyo14 = detail.getShitaganaiyo14();
			String shitaganaiyo15 = detail.getShitaganaiyo15();
			String shitaganaiyo16 = detail.getShitaganaiyo16();
			String shitaganaiyo17 = detail.getShitaganaiyo17();
			String shitaganaiyo18 = detail.getShitaganaiyo18();
			String shitaganaiyo19 = detail.getShitaganaiyo19();
			String shitaganaiyo20 = detail.getShitaganaiyo20();
			String sizeyoko1 = detail.getSizeyoko1();
			String sizeyoko2 = detail.getSizeyoko2();
			String sizeyoko3 = detail.getSizeyoko3();
			String sizeyoko4 = detail.getSizeyoko4();
			String sizeyoko5 = detail.getSizeyoko5();
			String sizeyoko6 = detail.getSizeyoko6();
			String sizeyoko7 = detail.getSizeyoko7();
			String sizeyoko8 = detail.getSizeyoko8();
			String sizeyoko9 = detail.getSizeyoko9();
			String sizeyoko10 = detail.getSizeyoko10();
			String sizeshitaga1 = detail.getSizeshitaga1();
			String sizeshitaga2 = detail.getSizeshitaga2();
			String sizeshitaga3 = detail.getSizeshitaga3();
			String sizeshitaga4 = detail.getSizeshitaga4();
			String sizeshitaga5 = detail.getSizeshitaga5();
			String sizeshitaga6 = detail.getSizeshitaga6();
			String sizeshitaga7 = detail.getSizeshitaga7();
			String sizeshitaga8 = detail.getSizeshitaga8();
			String sizeshitaga9 = detail.getSizeshitaga9();
			String sizeshitaga10 = detail.getSizeshitaga10();

			String[] honyakuhituyoList = new String[] { yokomei, shitagamei, shohinshosaimeisho1, shohinshosainaiyo1,
					shohinshosaimeisho2, shohinshosainaiyo2, shohinshosaimeisho3, shohinshosainaiyo3,
					shohinshosaimeisho4, shohinshosainaiyo4, shohinshosaimeisho5, shohinshosainaiyo5,
					shohinshosaimeisho6, shohinshosainaiyo6, yokonaiyo1, yokonaiyo2, yokonaiyo3, yokonaiyo4, yokonaiyo5,
					yokonaiyo6, yokonaiyo7, yokonaiyo8, yokonaiyo9, yokonaiyo10, yokonaiyo11, yokonaiyo12, yokonaiyo13,
					yokonaiyo14, yokonaiyo15, yokonaiyo16, yokonaiyo17, yokonaiyo18, yokonaiyo19, yokonaiyo20,
					shitaganaiyo1, shitaganaiyo2, shitaganaiyo3, shitaganaiyo4, shitaganaiyo5, shitaganaiyo6,
					shitaganaiyo7, shitaganaiyo8, shitaganaiyo9, shitaganaiyo10, shitaganaiyo11, shitaganaiyo12,
					shitaganaiyo13, shitaganaiyo14, shitaganaiyo15, shitaganaiyo16, shitaganaiyo17, shitaganaiyo18,
					shitaganaiyo19, shitaganaiyo20, sizeyoko1, sizeyoko2, sizeyoko3, sizeyoko4, sizeyoko5, sizeyoko6,
					sizeyoko7, sizeyoko8, sizeyoko9, sizeyoko10, sizeshitaga1, sizeshitaga2, sizeshitaga3, sizeshitaga4,
					sizeshitaga5, sizeshitaga6, sizeshitaga7, sizeshitaga8, sizeshitaga9, sizeshitaga10 };

			String yokomei_jp = detail.getYokomei_jp();
			String shitagamei_jp = detail.getShitagamei_jp();
			String shohinshosaimeisho1_jp = detail.getShohinshosaimeisho1_jp();
			String shohinshosainaiyo1_jp = detail.getShohinshosainaiyo1_jp();
			String shohinshosaimeisho2_jp = detail.getShohinshosaimeisho2_jp();
			String shohinshosainaiyo2_jp = detail.getShohinshosainaiyo2_jp();
			String shohinshosaimeisho3_jp = detail.getShohinshosaimeisho3_jp();
			String shohinshosainaiyo3_jp = detail.getShohinshosainaiyo3_jp();
			String shohinshosaimeisho4_jp = detail.getShohinshosaimeisho4_jp();
			String shohinshosainaiyo4_jp = detail.getShohinshosainaiyo4_jp();
			String shohinshosaimeisho5_jp = detail.getShohinshosaimeisho5_jp();
			String shohinshosainaiyo5_jp = detail.getShohinshosainaiyo5_jp();
			String shohinshosaimeisho6_jp = detail.getShohinshosaimeisho6_jp();
			String shohinshosainaiyo6_jp = detail.getShohinshosainaiyo6_jp();
			String yokonaiyo1_jp = detail.getYokonaiyo1_jp();
			String yokonaiyo2_jp = detail.getYokonaiyo2_jp();
			String yokonaiyo3_jp = detail.getYokonaiyo3_jp();
			String yokonaiyo4_jp = detail.getYokonaiyo4_jp();
			String yokonaiyo5_jp = detail.getYokonaiyo5_jp();
			String yokonaiyo6_jp = detail.getYokonaiyo6_jp();
			String yokonaiyo7_jp = detail.getYokonaiyo7_jp();
			String yokonaiyo8_jp = detail.getYokonaiyo8_jp();
			String yokonaiyo9_jp = detail.getYokonaiyo9_jp();
			String yokonaiyo10_jp = detail.getYokonaiyo10_jp();
			String yokonaiyo11_jp = detail.getYokonaiyo11_jp();
			String yokonaiyo12_jp = detail.getYokonaiyo12_jp();
			String yokonaiyo13_jp = detail.getYokonaiyo13_jp();
			String yokonaiyo14_jp = detail.getYokonaiyo14_jp();
			String yokonaiyo15_jp = detail.getYokonaiyo15_jp();
			String yokonaiyo16_jp = detail.getYokonaiyo16_jp();
			String yokonaiyo17_jp = detail.getYokonaiyo17_jp();
			String yokonaiyo18_jp = detail.getYokonaiyo18_jp();
			String yokonaiyo19_jp = detail.getYokonaiyo19_jp();
			String yokonaiyo20_jp = detail.getYokonaiyo20_jp();
			String shitaganaiyo1_jp = detail.getShitaganaiyo1_jp();
			String shitaganaiyo2_jp = detail.getShitaganaiyo2_jp();
			String shitaganaiyo3_jp = detail.getShitaganaiyo3_jp();
			String shitaganaiyo4_jp = detail.getShitaganaiyo4_jp();
			String shitaganaiyo5_jp = detail.getShitaganaiyo5_jp();
			String shitaganaiyo6_jp = detail.getShitaganaiyo6_jp();
			String shitaganaiyo7_jp = detail.getShitaganaiyo7_jp();
			String shitaganaiyo8_jp = detail.getShitaganaiyo8_jp();
			String shitaganaiyo9_jp = detail.getShitaganaiyo9_jp();
			String shitaganaiyo10_jp = detail.getShitaganaiyo10_jp();
			String shitaganaiyo11_jp = detail.getShitaganaiyo11_jp();
			String shitaganaiyo12_jp = detail.getShitaganaiyo12_jp();
			String shitaganaiyo13_jp = detail.getShitaganaiyo13_jp();
			String shitaganaiyo14_jp = detail.getShitaganaiyo14_jp();
			String shitaganaiyo15_jp = detail.getShitaganaiyo15_jp();
			String shitaganaiyo16_jp = detail.getShitaganaiyo16_jp();
			String shitaganaiyo17_jp = detail.getShitaganaiyo17_jp();
			String shitaganaiyo18_jp = detail.getShitaganaiyo18_jp();
			String shitaganaiyo19_jp = detail.getShitaganaiyo19_jp();
			String shitaganaiyo20_jp = detail.getShitaganaiyo20_jp();
			String sizeyoko1_jp = detail.getSizeyoko1_jp();
			String sizeyoko2_jp = detail.getSizeyoko2_jp();
			String sizeyoko3_jp = detail.getSizeyoko3_jp();
			String sizeyoko4_jp = detail.getSizeyoko4_jp();
			String sizeyoko5_jp = detail.getSizeyoko5_jp();
			String sizeyoko6_jp = detail.getSizeyoko6_jp();
			String sizeyoko7_jp = detail.getSizeyoko7_jp();
			String sizeyoko8_jp = detail.getSizeyoko8_jp();
			String sizeyoko9_jp = detail.getSizeyoko9_jp();
			String sizeyoko10_jp = detail.getSizeyoko10_jp();
			String sizeshitaga1_jp = detail.getSizeshitaga1_jp();
			String sizeshitaga2_jp = detail.getSizeshitaga2_jp();
			String sizeshitaga3_jp = detail.getSizeshitaga3_jp();
			String sizeshitaga4_jp = detail.getSizeshitaga4_jp();
			String sizeshitaga5_jp = detail.getSizeshitaga5_jp();
			String sizeshitaga6_jp = detail.getSizeshitaga6_jp();
			String sizeshitaga7_jp = detail.getSizeshitaga7_jp();
			String sizeshitaga8_jp = detail.getSizeshitaga8_jp();
			String sizeshitaga9_jp = detail.getSizeshitaga9_jp();
			String sizeshitaga10_jp = detail.getSizeshitaga10_jp();

			String[] honyakuList = new String[] { yokomei_jp, shitagamei_jp, shohinshosaimeisho1_jp,
					shohinshosainaiyo1_jp, shohinshosaimeisho2_jp, shohinshosainaiyo2_jp, shohinshosaimeisho3_jp,
					shohinshosainaiyo3_jp, shohinshosaimeisho4_jp, shohinshosainaiyo4_jp, shohinshosaimeisho5_jp,
					shohinshosainaiyo5_jp, shohinshosaimeisho6_jp, shohinshosainaiyo6_jp, yokonaiyo1_jp, yokonaiyo2_jp,
					yokonaiyo3_jp, yokonaiyo4_jp, yokonaiyo5_jp, yokonaiyo6_jp, yokonaiyo7_jp, yokonaiyo8_jp,
					yokonaiyo9_jp, yokonaiyo10_jp, yokonaiyo11_jp, yokonaiyo12_jp, yokonaiyo13_jp, yokonaiyo14_jp,
					yokonaiyo15_jp, yokonaiyo16_jp, yokonaiyo17_jp, yokonaiyo18_jp, yokonaiyo19_jp, yokonaiyo20_jp,
					shitaganaiyo1_jp, shitaganaiyo2_jp, shitaganaiyo3_jp, shitaganaiyo4_jp, shitaganaiyo5_jp,
					shitaganaiyo6_jp, shitaganaiyo7_jp, shitaganaiyo8_jp, shitaganaiyo9_jp, shitaganaiyo10_jp,
					shitaganaiyo11_jp, shitaganaiyo12_jp, shitaganaiyo13_jp, shitaganaiyo14_jp, shitaganaiyo15_jp,
					shitaganaiyo16_jp, shitaganaiyo17_jp, shitaganaiyo18_jp, shitaganaiyo19_jp, shitaganaiyo20_jp,
					sizeyoko1_jp, sizeyoko2_jp, sizeyoko3_jp, sizeyoko4_jp, sizeyoko5_jp, sizeyoko6_jp, sizeyoko7_jp,
					sizeyoko8_jp, sizeyoko9_jp, sizeyoko10_jp, sizeshitaga1_jp, sizeshitaga2_jp, sizeshitaga3_jp,
					sizeshitaga4_jp, sizeshitaga5_jp, sizeshitaga6_jp, sizeshitaga7_jp, sizeshitaga8_jp,
					sizeshitaga9_jp, sizeshitaga10_jp };

			for (int i = 0; i < honyakuhituyoList.length; i++) {
				if (!Utility.isEmptyString(honyakuhituyoList[i])) {
					if (Utility.isEmptyString(honyakuList[i])) {
						shohin.setHonyakunyuflg("0");
						break;
					}
				}
			}

			String sozai = detail.getSozainaiyo();
			String sozaiJp = detail.getSozainaiyo_jp();
			if (!Utility.isEmptyString(sozai)) {
				if (Utility.isEmptyString(sozaiJp)) {
					shohin.setHonyakunyuflg("0");
				} else {
					String[] sozaiArr = sozai.split(",");
					String[] sozaiJpArr = sozaiJp.split(",", sozaiArr.length);
					for (String jp : sozaiJpArr) {
						if (Utility.isEmptyString(jp)) {
							shohin.setHonyakunyuflg("0");
							break;
						}
					}
				}
			}

			// 其他
			String picdir = detail.getPicdir();
			if (!Utility.isEmptyString(picdir)) {
				shohin.setSonotanyuryoku("1");
			}

			if (!Utility.isEmptyList(detail.getPicList())) {
				shohin.setPicnyuryoku("1");
				int count = 0;
				for (PicList pic : detail.getPicList()) {
					if (pic.isChecked()) {
						count++;
					}
				}
				if (count < 3) {
					shohin.setSonotanyuryoku("0");
				}
			}

			shohin.setShowFlg("1");
			if (!Utility.isEmptyString(keyword)) {
				if (!keyword.equals(shohin.getShohinbango())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(kihon_kensaku)) {
				if (!kihon_kensaku.equals(shohin.getKihonnyuryoku())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(kyachikopi_kensaku)) {
				if (!kyachikopi_kensaku.equals(shohin.getKyachikopinyuryoku())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(kakaku_kensaku)) {
				if (!kakaku_kensaku.equals(shohin.getKakakunyuryoku())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(pic_kensaku)) {
				if (!pic_kensaku.equals(shohin.getPicnyuryoku())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(sonota_kensaku)) {
				if (!sonota_kensaku.equals(shohin.getSonotanyuryoku())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(honyaku_kensaku)) {
				if (!honyaku_kensaku.equals(shohin.getHonyakunyuflg())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(hozon_kensaku)) {
				if (!hozon_kensaku.equals(shohin.getHozonFlg())) {
					shohin.setShowFlg("0");
				}
			}
			if (!Utility.isEmptyString(del_kensaku)) {
				if (!del_kensaku.equals(shohin.getDelFlg())) {
					shohin.setShowFlg("0");
				}

			}
		}

		return shohinList;
	}

	public boolean saveDetail(F120201Detail detail, String shumokuId) throws Exception {
		boolean saveFlg = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			boolean existflg = false;
			if (!Utility.isEmptyString(detail.getShohinbango_moto())) {
				existflg = isShohinExist(detail.getShohinbango_moto());
			}
			if (!existflg) {
				sql = "INSERT INTO rakuten.tbl00023" + "(SHUMOKUID," + "SHURUI," + "SHOHINBANGO," + "SHOINMEICN,"
						+ "SHIIREURL," + "SHIIREKAKAKU," + "MERUBIN," + "SHOHINMEI," + "PCYOKYACHI,"
						+ "MOBAIRUYOKYACHI," + "HANBAIKAKAKU," + "HYOJIKAKAKU," + "SORYOBETSU," + "ZEIBETSU,"
						+ "DIREKUTORIID," + "TAGUID," + "KATEGORI1," + "KATEGORI2," + "KATEGORI3," + "KATEGORI4,"
						+ "YOKOKOMOKUMEI," + "SHITAGAKOMOKUMEI," + "SOZAINAIYO," + "SHOHINSHOSAI1MEISHO,"
						+ "SHOHINSHOSAI1NAIYO," + "SHOHINSHOSAI2MEISHO," + "SHOHINSHOSAI2NAIYO,"
						+ "SHOHINSHOSAI3MEISHO," + "SHOHINSHOSAI3NAIYO," + "SHOHINSHOSAI4MEISHO,"
						+ "SHOHINSHOSAI4NAIYO," + "SHOHINSHOSAI5MEISHO," + "SHOHINSHOSAI5NAIYO,"
						+ "SHOHINSHOSAI6MEISHO," + "SHOHINSHOSAI6NAIYO," + "YOKONAIYO1," + "YOKONAIYO2," + "YOKONAIYO3,"
						+ "YOKONAIYO4," + "YOKONAIYO5," + "YOKONAIYO6," + "YOKONAIYO7," + "YOKONAIYO8," + "YOKONAIYO9,"
						+ "YOKONAIYO10," + "YOKONAIYO11," + "YOKONAIYO12," + "YOKONAIYO13," + "YOKONAIYO14,"
						+ "YOKONAIYO15," + "YOKONAIYO16," + "YOKONAIYO17," + "YOKONAIYO18," + "YOKONAIYO19,"
						+ "YOKONAIYO20," + "YOKOSUBID1," + "YOKOSUBID2," + "YOKOSUBID3," + "YOKOSUBID4," + "YOKOSUBID5,"
						+ "YOKOSUBID6," + "YOKOSUBID7," + "YOKOSUBID8," + "YOKOSUBID9," + "YOKOSUBID10,"
						+ "YOKOSUBID11," + "YOKOSUBID12," + "YOKOSUBID13," + "YOKOSUBID14," + "YOKOSUBID15,"
						+ "YOKOSUBID16," + "YOKOSUBID17," + "YOKOSUBID18," + "YOKOSUBID19," + "YOKOSUBID20,"
						+ "SHITAGANAIYO1," + "SHITAGANAIYO2," + "SHITAGANAIYO3," + "SHITAGANAIYO4," + "SHITAGANAIYO5,"
						+ "SHITAGANAIYO6," + "SHITAGANAIYO7," + "SHITAGANAIYO8," + "SHITAGANAIYO9," + "SHITAGANAIYO10,"
						+ "SHITAGANAIYO11," + "SHITAGANAIYO12," + "SHITAGANAIYO13," + "SHITAGANAIYO14,"
						+ "SHITAGANAIYO15," + "SHITAGANAIYO16," + "SHITAGANAIYO17," + "SHITAGANAIYO18,"
						+ "SHITAGANAIYO19," + "SHITAGANAIYO20," + "SHITAGASUBID1," + "SHITAGASUBID2," + "SHITAGASUBID3,"
						+ "SHITAGASUBID4," + "SHITAGASUBID5," + "SHITAGASUBID6," + "SHITAGASUBID7," + "SHITAGASUBID8,"
						+ "SHITAGASUBID9," + "SHITAGASUBID10," + "SHITAGASUBID11," + "SHITAGASUBID12,"
						+ "SHITAGASUBID13," + "SHITAGASUBID14," + "SHITAGASUBID15," + "SHITAGASUBID16,"
						+ "SHITAGASUBID17," + "SHITAGASUBID18," + "SHITAGASUBID19," + "SHITAGASUBID20,"
						+ "SIZEYOKOLIST," + "SIZESHITAGALIST," + "SIZELIST," + "PICDIR," + "PICLIST," + "PICSELECTLIST,"
						+ "MERUBINSORYOMURYOKYNPEN," + "HOGOFIRUMU," + "SIZEPIC," + "PYCOMMON," + "XFPYCOMMON,"
						+ "osusume1," + "osusume2," + "osusume3," + "nouki," + "CREATE_TIME," + "CREATE_USER,"
						+ "UPDATE_TIME," + "UPDATE_USER)" + "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			} else {
				sql = "UPDATE tbl00023 SET SHURUI = ?," + "SHOHINBANGO = ?," + "SHOINMEICN = ?," + "SHIIREURL = ?,"
						+ "SHIIREKAKAKU = ?," + "MERUBIN = ?," + "SHOHINMEI = ?," + "PCYOKYACHI = ?,"
						+ "MOBAIRUYOKYACHI = ?," + "HANBAIKAKAKU = ?," + "HYOJIKAKAKU = ?," + "SORYOBETSU = ?,"
						+ "ZEIBETSU = ?," + "DIREKUTORIID = ?," + "TAGUID = ?," + "KATEGORI1 = ?," + "KATEGORI2 = ?,"
						+ "KATEGORI3 = ?," + "KATEGORI4 = ?," + "YOKOKOMOKUMEI = ?," + "SHITAGAKOMOKUMEI = ?,"
						+ "SOZAINAIYO = ?," + "SHOHINSHOSAI1MEISHO = ?," + "SHOHINSHOSAI1NAIYO = ?,"
						+ "SHOHINSHOSAI2MEISHO = ?," + "SHOHINSHOSAI2NAIYO = ?," + "SHOHINSHOSAI3MEISHO = ?,"
						+ "SHOHINSHOSAI3NAIYO = ?," + "SHOHINSHOSAI4MEISHO = ?," + "SHOHINSHOSAI4NAIYO = ?,"
						+ "SHOHINSHOSAI5MEISHO = ?," + "SHOHINSHOSAI5NAIYO = ?," + "SHOHINSHOSAI6MEISHO = ?,"
						+ "SHOHINSHOSAI6NAIYO = ?," + "YOKONAIYO1 = ?," + "YOKONAIYO2 = ?," + "YOKONAIYO3 = ?,"
						+ "YOKONAIYO4 = ?," + "YOKONAIYO5 = ?," + "YOKONAIYO6 = ?," + "YOKONAIYO7 = ?,"
						+ "YOKONAIYO8 = ?," + "YOKONAIYO9 = ?," + "YOKONAIYO10 = ?," + "YOKONAIYO11 = ?,"
						+ "YOKONAIYO12 = ?," + "YOKONAIYO13 = ?," + "YOKONAIYO14 = ?," + "YOKONAIYO15 = ?,"
						+ "YOKONAIYO16 = ?," + "YOKONAIYO17 = ?," + "YOKONAIYO18 = ?," + "YOKONAIYO19 = ?,"
						+ "YOKONAIYO20 = ?," + "YOKOSUBID1 = ?," + "YOKOSUBID2 = ?," + "YOKOSUBID3 = ?,"
						+ "YOKOSUBID4 = ?," + "YOKOSUBID5 = ?," + "YOKOSUBID6 = ?," + "YOKOSUBID7 = ?,"
						+ "YOKOSUBID8 = ?," + "YOKOSUBID9 = ?," + "YOKOSUBID10 = ?," + "YOKOSUBID11 = ?,"
						+ "YOKOSUBID12 = ?," + "YOKOSUBID13 = ?," + "YOKOSUBID14 = ?," + "YOKOSUBID15 = ?,"
						+ "YOKOSUBID16 = ?," + "YOKOSUBID17 = ?," + "YOKOSUBID18 = ?," + "YOKOSUBID19 = ?,"
						+ "YOKOSUBID20 = ?," + "SHITAGANAIYO1 = ?," + "SHITAGANAIYO2 = ?," + "SHITAGANAIYO3 = ?,"
						+ "SHITAGANAIYO4 = ?," + "SHITAGANAIYO5 = ?," + "SHITAGANAIYO6 = ?," + "SHITAGANAIYO7 = ?,"
						+ "SHITAGANAIYO8 = ?," + "SHITAGANAIYO9 = ?," + "SHITAGANAIYO10 = ?," + "SHITAGANAIYO11 = ?,"
						+ "SHITAGANAIYO12 = ?," + "SHITAGANAIYO13 = ?," + "SHITAGANAIYO14 = ?," + "SHITAGANAIYO15 = ?,"
						+ "SHITAGANAIYO16 = ?," + "SHITAGANAIYO17 = ?," + "SHITAGANAIYO18 = ?," + "SHITAGANAIYO19 = ?,"
						+ "SHITAGANAIYO20 = ?," + "SHITAGASUBID1 = ?," + "SHITAGASUBID2 = ?," + "SHITAGASUBID3 = ?,"
						+ "SHITAGASUBID4 = ?," + "SHITAGASUBID5 = ?," + "SHITAGASUBID6 = ?," + "SHITAGASUBID7 = ?,"
						+ "SHITAGASUBID8 = ?," + "SHITAGASUBID9 = ?," + "SHITAGASUBID10 = ?," + "SHITAGASUBID11 = ?,"
						+ "SHITAGASUBID12 = ?," + "SHITAGASUBID13 = ?," + "SHITAGASUBID14 = ?," + "SHITAGASUBID15 = ?,"
						+ "SHITAGASUBID16 = ?," + "SHITAGASUBID17 = ?," + "SHITAGASUBID18 = ?," + "SHITAGASUBID19 = ?,"
						+ "SHITAGASUBID20 = ?," + "SIZEYOKOLIST = ?," + "SIZESHITAGALIST = ?," + "SIZELIST = ?,"
						+ "PICDIR= ?," + "PICLIST= ?," + "PICSELECTLIST= ?," + "MERUBINSORYOMURYOKYNPEN = ?,"
						+ "HOGOFIRUMU = ?," + "SIZEPIC = ?," + "PYCOMMON = ?," + "XFPYCOMMON = ?," + "osusume1 = ?,"
						+ "osusume2 = ?," + "osusume3 = ?," + "nouki = ?," + "UPDATE_TIME = ?," + "UPDATE_USER = ?"
						+ "WHERE SHUMOKUID = ? AND SHOHINBANGO = ? ";
			}
			ps = conn.prepareStatement(sql);

			int j = 0;

			if (!existflg) {
				// SHUMOKUID,
				ps.setString(++j, shumokuId);
			}
			// SHURUI,
			// 普通
			ps.setString(++j, "0");

			// SHOHINBANGO,
			ps.setString(++j, detail.getShohinbango());
			// SHOINMEICN,
			ps.setString(++j, detail.getShohinmei_cn());
			// SHIIREURL,
			ps.setString(++j, detail.getShiireurl());
			// SHIIREKAKAKU,
			ps.setString(++j, detail.getShiirekakaku());
			// SHIIREKAKAKU,
			ps.setString(++j, detail.getMerubin());
			// SHOHINMEI,
			ps.setString(++j, detail.getShohinmei_jp());
			// PCYOKYACHI,
			ps.setString(++j, detail.getPckyachikopi());
			// MOBAIRUYOKYACHI,
			ps.setString(++j, detail.getMobairukyachikopi());
			// HANBAIKAKAKU,
			ps.setString(++j, detail.getHanbaikakaku());
			// HYOJIKAKAKU,
			ps.setString(++j, detail.getHyojikakaku());
			// SORYOBETSU,
			ps.setString(++j, detail.getSoryobetsu());
			// ZEIBETSU,
			ps.setString(++j, detail.getShohizeibetsu());
			// DIREKUTORIID,
			ps.setString(++j, detail.getDirekutoriid());
			// TAGUID,
			ps.setString(++j, detail.getTaguid());
			// KATEGORI1,
			ps.setString(++j, detail.getKategori1());
			// KATEGORI2,
			ps.setString(++j, detail.getKategori2());
			// KATEGORI3,
			ps.setString(++j, detail.getKategori3());
			// KATEGORI4,
			ps.setString(++j, detail.getKategori4());
			// YOKOKOMOKUMEI,
			ps.setString(++j, detail.getYokomei());
			// SHITAGAKOMOKUMEI,
			ps.setString(++j, detail.getShitagamei());
			ps.setString(++j, detail.getSozainaiyo());
			// SHOHINSHOSAI1MEISHO,
			ps.setString(++j, detail.getShohinshosaimeisho1());
			// SHOHINSHOSAI1NAIYO,
			ps.setString(++j, detail.getShohinshosainaiyo1());
			// SHOHINSHOSAI2MEISHO,
			ps.setString(++j, detail.getShohinshosaimeisho2());
			// SHOHINSHOSAI2NAIYO,
			ps.setString(++j, detail.getShohinshosainaiyo2());
			// SHOHINSHOSAI3MEISHO,
			ps.setString(++j, detail.getShohinshosaimeisho3());
			// SHOHINSHOSAI3NAIYO,
			ps.setString(++j, detail.getShohinshosainaiyo3());
			// SHOHINSHOSAI4MEISHO,
			ps.setString(++j, detail.getShohinshosaimeisho4());
			// SHOHINSHOSAI4NAIYO,
			ps.setString(++j, detail.getShohinshosainaiyo4());
			// SHOHINSHOSAI5MEISHO,
			ps.setString(++j, detail.getShohinshosaimeisho5());
			// SHOHINSHOSAI5NAIYO,
			ps.setString(++j, detail.getShohinshosainaiyo5());
			// SHOHINSHOSAI6MEISHO,
			ps.setString(++j, detail.getShohinshosaimeisho6());
			// SHOHINSHOSAI6NAIYO,
			ps.setString(++j, detail.getShohinshosainaiyo6());
			// YOKONAIYO1,
			ps.setString(++j, detail.getYokonaiyo1());
			// YOKONAIYO2,
			ps.setString(++j, detail.getYokonaiyo2());
			// YOKONAIYO3,
			ps.setString(++j, detail.getYokonaiyo3());
			// YOKONAIYO4,
			ps.setString(++j, detail.getYokonaiyo4());
			// YOKONAIYO5,
			ps.setString(++j, detail.getYokonaiyo5());
			// YOKONAIYO6,
			ps.setString(++j, detail.getYokonaiyo6());
			// YOKONAIYO7,
			ps.setString(++j, detail.getYokonaiyo7());
			// YOKONAIYO8,
			ps.setString(++j, detail.getYokonaiyo8());
			// YOKONAIYO9,
			ps.setString(++j, detail.getYokonaiyo9());
			// YOKONAIYO10,
			ps.setString(++j, detail.getYokonaiyo10());
			// YOKONAIYO11,
			ps.setString(++j, detail.getYokonaiyo11());
			// YOKONAIYO12,
			ps.setString(++j, detail.getYokonaiyo12());
			// YOKONAIYO13,
			ps.setString(++j, detail.getYokonaiyo13());
			// YOKONAIYO14,
			ps.setString(++j, detail.getYokonaiyo14());
			// YOKONAIYO15,
			ps.setString(++j, detail.getYokonaiyo15());
			// YOKONAIYO16,
			ps.setString(++j, detail.getYokonaiyo16());
			// YOKONAIYO17,
			ps.setString(++j, detail.getYokonaiyo17());
			// YOKONAIYO18,
			ps.setString(++j, detail.getYokonaiyo18());
			// YOKONAIYO19,
			ps.setString(++j, detail.getYokonaiyo19());
			// YOKONAIYO20,
			ps.setString(++j, detail.getYokonaiyo20());
			// YOKOSUBID1,
			ps.setString(++j, detail.getYokonaiyo1_subid());
			// YokonaiyoSUBID2,
			ps.setString(++j, detail.getYokonaiyo2_subid());
			// YokonaiyoSUBID3,
			ps.setString(++j, detail.getYokonaiyo3_subid());
			// YokonaiyoSUBID4,
			ps.setString(++j, detail.getYokonaiyo4_subid());
			// YokonaiyoSUBID5,
			ps.setString(++j, detail.getYokonaiyo5_subid());
			// YokonaiyoSUBID6,
			ps.setString(++j, detail.getYokonaiyo6_subid());
			// YokonaiyoSUBID7,
			ps.setString(++j, detail.getYokonaiyo7_subid());
			// YokonaiyoSUBID8,
			ps.setString(++j, detail.getYokonaiyo8_subid());
			// YokonaiyoSUBID9,
			ps.setString(++j, detail.getYokonaiyo9_subid());
			// YokonaiyoSUBID10,
			ps.setString(++j, detail.getYokonaiyo10_subid());
			// YokonaiyoSUBID11,
			ps.setString(++j, detail.getYokonaiyo11_subid());
			// YokonaiyoSUBID12,
			ps.setString(++j, detail.getYokonaiyo12_subid());
			// YokonaiyoSUBID13,
			ps.setString(++j, detail.getYokonaiyo13_subid());
			// YokonaiyoSUBID14,
			ps.setString(++j, detail.getYokonaiyo14_subid());
			// YokonaiyoSUBID15,
			ps.setString(++j, detail.getYokonaiyo15_subid());
			// YokonaiyoSUBID16,
			ps.setString(++j, detail.getYokonaiyo16_subid());
			// YokonaiyoSUBID17,
			ps.setString(++j, detail.getYokonaiyo17_subid());
			// YokonaiyoSUBID18,
			ps.setString(++j, detail.getYokonaiyo18_subid());
			// YokonaiyoSUBID19,
			ps.setString(++j, detail.getYokonaiyo19_subid());
			// YokonaiyoSUBID20,
			ps.setString(++j, detail.getYokonaiyo20_subid());
			// SHITAGANAIYO1,
			ps.setString(++j, detail.getShitaganaiyo1());
			// SHITAGANAIYO2,
			ps.setString(++j, detail.getShitaganaiyo2());
			// SHITAGANAIYO3,
			ps.setString(++j, detail.getShitaganaiyo3());
			// SHITAGANAIYO4,
			ps.setString(++j, detail.getShitaganaiyo4());
			// SHITAGANAIYO5,
			ps.setString(++j, detail.getShitaganaiyo5());
			// SHITAGANAIYO6,
			ps.setString(++j, detail.getShitaganaiyo6());
			// SHITAGANAIYO7,
			ps.setString(++j, detail.getShitaganaiyo7());
			// SHITAGANAIYO8,
			ps.setString(++j, detail.getShitaganaiyo8());
			// SHITAGANAIYO9,
			ps.setString(++j, detail.getShitaganaiyo9());
			// SHITAGANAIYO10,
			ps.setString(++j, detail.getShitaganaiyo10());
			// SHITAGANAIYO11,
			ps.setString(++j, detail.getShitaganaiyo11());
			// SHITAGANAIYO12,
			ps.setString(++j, detail.getShitaganaiyo12());
			// SHITAGANAIYO13,
			ps.setString(++j, detail.getShitaganaiyo13());
			// SHITAGANAIYO14,
			ps.setString(++j, detail.getShitaganaiyo14());
			// SHITAGANAIYO15,
			ps.setString(++j, detail.getShitaganaiyo15());
			// SHITAGANAIYO16,
			ps.setString(++j, detail.getShitaganaiyo16());
			// SHITAGANAIYO17,
			ps.setString(++j, detail.getShitaganaiyo17());
			// SHITAGANAIYO18,
			ps.setString(++j, detail.getShitaganaiyo18());
			// SHITAGANAIYO19,
			ps.setString(++j, detail.getShitaganaiyo19());
			// SHITAGANAIYO20,
			ps.setString(++j, detail.getShitaganaiyo20());
			// SHITAGASUBID1,
			ps.setString(++j, detail.getShitaganaiyo1_subid());
			// ShitaganaiyoSUBID2,
			ps.setString(++j, detail.getShitaganaiyo2_subid());
			// ShitaganaiyoSUBID3,
			ps.setString(++j, detail.getShitaganaiyo3_subid());
			// ShitaganaiyoSUBID4,
			ps.setString(++j, detail.getShitaganaiyo4_subid());
			// ShitaganaiyoSUBID5,
			ps.setString(++j, detail.getShitaganaiyo5_subid());
			// ShitaganaiyoSUBID6,
			ps.setString(++j, detail.getShitaganaiyo6_subid());
			// ShitaganaiyoSUBID7,
			ps.setString(++j, detail.getShitaganaiyo7_subid());
			// ShitaganaiyoSUBID8,
			ps.setString(++j, detail.getShitaganaiyo8_subid());
			// ShitaganaiyoSUBID9,
			ps.setString(++j, detail.getShitaganaiyo9_subid());
			// ShitaganaiyoSUBID10,
			ps.setString(++j, detail.getShitaganaiyo10_subid());
			// ShitaganaiyoSUBID11,
			ps.setString(++j, detail.getShitaganaiyo11_subid());
			// ShitaganaiyoSUBID12,
			ps.setString(++j, detail.getShitaganaiyo12_subid());
			// ShitaganaiyoSUBID13,
			ps.setString(++j, detail.getShitaganaiyo13_subid());
			// ShitaganaiyoSUBID14,
			ps.setString(++j, detail.getShitaganaiyo14_subid());
			// ShitaganaiyoSUBID15,
			ps.setString(++j, detail.getShitaganaiyo15_subid());
			// ShitaganaiyoSUBID16,
			ps.setString(++j, detail.getShitaganaiyo16_subid());
			// ShitaganaiyoSUBID17,
			ps.setString(++j, detail.getShitaganaiyo17_subid());
			// ShitaganaiyoSUBID18,
			ps.setString(++j, detail.getShitaganaiyo18_subid());
			// ShitaganaiyoSUBID19,
			ps.setString(++j, detail.getShitaganaiyo19_subid());
			// ShitaganaiyoSUBID20,
			ps.setString(++j, detail.getShitaganaiyo20_subid());
			// SIZEYOKOLIST,
			ps.setString(++j,
					detail.getSizeyoko1() + "&" + detail.getSizeyoko2() + "&" + detail.getSizeyoko3() + "&"
							+ detail.getSizeyoko4() + "&" + detail.getSizeyoko5() + "&" + detail.getSizeyoko6() + "&"
							+ detail.getSizeyoko7() + "&" + detail.getSizeyoko8() + "&" + detail.getSizeyoko9() + "&"
							+ detail.getSizeyoko10());
			// SIZESHITAGALIST,
			ps.setString(++j,
					detail.getSizeshitaga1() + "&" + detail.getSizeshitaga2() + "&" + detail.getSizeshitaga3() + "&"
							+ detail.getSizeshitaga4() + "&" + detail.getSizeshitaga5() + "&" + detail.getSizeshitaga6()
							+ "&" + detail.getSizeshitaga7() + "&" + detail.getSizeshitaga8() + "&"
							+ detail.getSizeshitaga9() + "&" + detail.getSizeshitaga10());
			// SIZELIST
			ps.setString(++j,
					detail.getSize11() + "&" + detail.getSize12() + "&" + detail.getSize13() + "&" + detail.getSize14()
							+ "&" + detail.getSize15() + "&" + detail.getSize16() + "&" + detail.getSize17() + "&"
							+ detail.getSize18() + "&" + detail.getSize19() + "&" + detail.getSize110() + "&"
							+ detail.getSize21() + "&" + detail.getSize22() + "&" + detail.getSize23() + "&"
							+ detail.getSize24() + "&" + detail.getSize25() + "&" + detail.getSize26() + "&"
							+ detail.getSize27() + "&" + detail.getSize28() + "&" + detail.getSize29() + "&"
							+ detail.getSize210() + "&" + detail.getSize31() + "&" + detail.getSize32() + "&"
							+ detail.getSize33() + "&" + detail.getSize34() + "&" + detail.getSize35() + "&"
							+ detail.getSize36() + "&" + detail.getSize37() + "&" + detail.getSize38() + "&"
							+ detail.getSize39() + "&" + detail.getSize310() + "&" + detail.getSize41() + "&"
							+ detail.getSize42() + "&" + detail.getSize43() + "&" + detail.getSize44() + "&"
							+ detail.getSize45() + "&" + detail.getSize46() + "&" + detail.getSize47() + "&"
							+ detail.getSize48() + "&" + detail.getSize49() + "&" + detail.getSize410() + "&"
							+ detail.getSize51() + "&" + detail.getSize52() + "&" + detail.getSize53() + "&"
							+ detail.getSize54() + "&" + detail.getSize55() + "&" + detail.getSize56() + "&"
							+ detail.getSize57() + "&" + detail.getSize58() + "&" + detail.getSize59() + "&"
							+ detail.getSize510() + "&" + detail.getSize61() + "&" + detail.getSize62() + "&"
							+ detail.getSize63() + "&" + detail.getSize64() + "&" + detail.getSize65() + "&"
							+ detail.getSize66() + "&" + detail.getSize67() + "&" + detail.getSize68() + "&"
							+ detail.getSize69() + "&" + detail.getSize610() + "&" + detail.getSize71() + "&"
							+ detail.getSize72() + "&" + detail.getSize73() + "&" + detail.getSize74() + "&"
							+ detail.getSize75() + "&" + detail.getSize76() + "&" + detail.getSize77() + "&"
							+ detail.getSize78() + "&" + detail.getSize79() + "&" + detail.getSize710() + "&"
							+ detail.getSize81() + "&" + detail.getSize82() + "&" + detail.getSize83() + "&"
							+ detail.getSize84() + "&" + detail.getSize85() + "&" + detail.getSize86() + "&"
							+ detail.getSize87() + "&" + detail.getSize88() + "&" + detail.getSize89() + "&"
							+ detail.getSize810() + "&" + detail.getSize91() + "&" + detail.getSize92() + "&"
							+ detail.getSize93() + "&" + detail.getSize94() + "&" + detail.getSize95() + "&"
							+ detail.getSize96() + "&" + detail.getSize97() + "&" + detail.getSize98() + "&"
							+ detail.getSize99() + "&" + detail.getSize910() + "&" + detail.getSize101() + "&"
							+ detail.getSize102() + "&" + detail.getSize103() + "&" + detail.getSize104() + "&"
							+ detail.getSize105() + "&" + detail.getSize106() + "&" + detail.getSize107() + "&"
							+ detail.getSize108() + "&" + detail.getSize109() + "&" + detail.getSize1010());

			ps.setString(++j, detail.getPicdir());
			String picarr = "";
			String checkarr = "";
			if (!Utility.isEmptyList(detail.getPicList())) {
				for (int i = 0; i < detail.getPicList().size(); i++) {
					PicList pic = detail.getPicList().get(i);
					boolean check = pic.isChecked();
					if ("".equals(picarr)) {
						picarr = pic.getPicurl();
					} else {
						picarr = picarr + "&" + pic.getPicurl();
					}
					if (check) {
						if ("".equals(checkarr)) {
							checkarr = String.valueOf(i);
						} else {
							checkarr = checkarr + "&" + String.valueOf(i);
						}
					}
				}
			}
			ps.setString(++j, picarr);
			ps.setString(++j, checkarr);

			ps.setString(++j, detail.isMerubinsoryomuryokyanpen() ? "1" : "0");
			ps.setString(++j, detail.isHogofirumu() ? "1" : "0");
			ps.setString(++j, detail.isSizepic() ? "1" : "0");
			ps.setString(++j, detail.isPycommonpic() ? "1" : "0");
			ps.setString(++j, detail.isXfpycommonpic() ? "1" : "0");
			ps.setString(++j, detail.isOsusume1() ? "1" : "0");
			ps.setString(++j, detail.isOsusume2() ? "1" : "0");
			ps.setString(++j, detail.isOsusume3() ? "1" : "0");
			ps.setString(++j, detail.isNouki() ? "1" : "0");

			if (!existflg) {
				ps.setString(++j, Utility.getDateTime());
				ps.setString(++j, Utility.getUser());
			}
			ps.setString(++j, Utility.getDateTime());
			ps.setString(++j, Utility.getUser());
			if (existflg) {
				// SHUMOKUID,
				ps.setString(++j, shumokuId);
				// SHOHINBANGO,
				ps.setString(++j, detail.getShohinbango_moto());
			}
			ps.execute();
			conn.commit();
			saveFlg = true;

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			saveFlg = false;
		} finally {
			conn.close();
		}
		return saveFlg;

	}

	public List<String> isKihonnyuryoku(F120201Detail detail) {
		List<String> msgList = new ArrayList<String>();
		return msgList;
	}

	public boolean isShohinExist(String shohinbango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Long count = 0L;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT COUNT(*) COUNT FROM tbl00023 WHERE SHOHINBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shohinbango);
			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getLong("COUNT");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return count > 0L ? true : false;
	}

	public String getShumokuname(String shumokuid) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String shumokuname = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM tbl00022 WHERE SHUMOKUID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokuid);
			rs = ps.executeQuery();

			while (rs.next()) {
				shumokuname = rs.getString("SHUMOKUMEI");

			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return shumokuname;
	}

	public void honykuAndSet(List<F120201Detail> detailList) throws Exception {
		Connection conn = JdbcConnection.getConnection();
		try {
			for (int i = 0; i < detailList.size(); i++) {
				F120201Detail detail = detailList.get(i);
				// 详细信息（名称）
				String shohinshosaimeisho1 = detail.getShohinshosaimeisho1();
				String shohinshosaimeisho2 = detail.getShohinshosaimeisho2();
				String shohinshosaimeisho3 = detail.getShohinshosaimeisho3();
				String shohinshosaimeisho4 = detail.getShohinshosaimeisho4();
				String shohinshosaimeisho5 = detail.getShohinshosaimeisho5();
				String shohinshosaimeisho6 = detail.getShohinshosaimeisho6();

				detail.setShohinshosaimeisho1_jp(getJpstr(conn, shohinshosaimeisho1));
				detail.setShohinshosaimeisho2_jp(getJpstr(conn, shohinshosaimeisho2));
				detail.setShohinshosaimeisho3_jp(getJpstr(conn, shohinshosaimeisho3));
				detail.setShohinshosaimeisho4_jp(getJpstr(conn, shohinshosaimeisho4));
				detail.setShohinshosaimeisho5_jp(getJpstr(conn, shohinshosaimeisho5));
				detail.setShohinshosaimeisho6_jp(getJpstr(conn, shohinshosaimeisho6));

				// 详细信息（内容）
				String shohinshosainaiyo1 = detail.getShohinshosainaiyo1();
				String shohinshosainaiyo2 = detail.getShohinshosainaiyo2();
				String shohinshosainaiyo3 = detail.getShohinshosainaiyo3();
				String shohinshosainaiyo4 = detail.getShohinshosainaiyo4();
				String shohinshosainaiyo5 = detail.getShohinshosainaiyo5();
				String shohinshosainaiyo6 = detail.getShohinshosainaiyo6();

				detail.setShohinshosainaiyo1_jp(getJpstr(conn, shohinshosainaiyo1));
				detail.setShohinshosainaiyo2_jp(getJpstr(conn, shohinshosainaiyo2));
				detail.setShohinshosainaiyo3_jp(getJpstr(conn, shohinshosainaiyo3));
				detail.setShohinshosainaiyo4_jp(getJpstr(conn, shohinshosainaiyo4));
				detail.setShohinshosainaiyo5_jp(getJpstr(conn, shohinshosainaiyo5));
				detail.setShohinshosainaiyo6_jp(getJpstr(conn, shohinshosainaiyo6));

				// 横纵轴（名称）
				String yokomei = detail.getYokomei();
				String shitagamei = detail.getShitagamei();

				detail.setYokomei_jp(getJpstr(conn, yokomei));
				detail.setShitagamei_jp(getJpstr(conn, shitagamei));

				// 横纵轴（内容）
				String yokonaiyo1 = detail.getYokonaiyo1();
				String yokonaiyo2 = detail.getYokonaiyo2();
				String yokonaiyo3 = detail.getYokonaiyo3();
				String yokonaiyo4 = detail.getYokonaiyo4();
				String yokonaiyo5 = detail.getYokonaiyo5();
				String yokonaiyo6 = detail.getYokonaiyo6();
				String yokonaiyo7 = detail.getYokonaiyo7();
				String yokonaiyo8 = detail.getYokonaiyo8();
				String yokonaiyo9 = detail.getYokonaiyo9();
				String yokonaiyo10 = detail.getYokonaiyo10();
				String yokonaiyo11 = detail.getYokonaiyo11();
				String yokonaiyo12 = detail.getYokonaiyo12();
				String yokonaiyo13 = detail.getYokonaiyo13();
				String yokonaiyo14 = detail.getYokonaiyo14();
				String yokonaiyo15 = detail.getYokonaiyo15();
				String yokonaiyo16 = detail.getYokonaiyo16();
				String yokonaiyo17 = detail.getYokonaiyo17();
				String yokonaiyo18 = detail.getYokonaiyo18();
				String yokonaiyo19 = detail.getYokonaiyo19();
				String yokonaiyo20 = detail.getYokonaiyo20();
				String shitaganaiyo1 = detail.getShitaganaiyo1();
				String shitaganaiyo2 = detail.getShitaganaiyo2();
				String shitaganaiyo3 = detail.getShitaganaiyo3();
				String shitaganaiyo4 = detail.getShitaganaiyo4();
				String shitaganaiyo5 = detail.getShitaganaiyo5();
				String shitaganaiyo6 = detail.getShitaganaiyo6();
				String shitaganaiyo7 = detail.getShitaganaiyo7();
				String shitaganaiyo8 = detail.getShitaganaiyo8();
				String shitaganaiyo9 = detail.getShitaganaiyo9();
				String shitaganaiyo10 = detail.getShitaganaiyo10();
				String shitaganaiyo11 = detail.getShitaganaiyo11();
				String shitaganaiyo12 = detail.getShitaganaiyo12();
				String shitaganaiyo13 = detail.getShitaganaiyo13();
				String shitaganaiyo14 = detail.getShitaganaiyo14();
				String shitaganaiyo15 = detail.getShitaganaiyo15();
				String shitaganaiyo16 = detail.getShitaganaiyo16();
				String shitaganaiyo17 = detail.getShitaganaiyo17();
				String shitaganaiyo18 = detail.getShitaganaiyo18();
				String shitaganaiyo19 = detail.getShitaganaiyo19();
				String shitaganaiyo20 = detail.getShitaganaiyo20();

				String[] yokoArr1 = getJpstrAndSubid(conn, yokonaiyo1).split("&", 2);
				String[] yokoArr2 = getJpstrAndSubid(conn, yokonaiyo2).split("&", 2);
				String[] yokoArr3 = getJpstrAndSubid(conn, yokonaiyo3).split("&", 2);
				String[] yokoArr4 = getJpstrAndSubid(conn, yokonaiyo4).split("&", 2);
				String[] yokoArr5 = getJpstrAndSubid(conn, yokonaiyo5).split("&", 2);
				String[] yokoArr6 = getJpstrAndSubid(conn, yokonaiyo6).split("&", 2);
				String[] yokoArr7 = getJpstrAndSubid(conn, yokonaiyo7).split("&", 2);
				String[] yokoArr8 = getJpstrAndSubid(conn, yokonaiyo8).split("&", 2);
				String[] yokoArr9 = getJpstrAndSubid(conn, yokonaiyo9).split("&", 2);
				String[] yokoArr10 = getJpstrAndSubid(conn, yokonaiyo10).split("&", 2);
				String[] yokoArr11 = getJpstrAndSubid(conn, yokonaiyo11).split("&", 2);
				String[] yokoArr12 = getJpstrAndSubid(conn, yokonaiyo12).split("&", 2);
				String[] yokoArr13 = getJpstrAndSubid(conn, yokonaiyo13).split("&", 2);
				String[] yokoArr14 = getJpstrAndSubid(conn, yokonaiyo14).split("&", 2);
				String[] yokoArr15 = getJpstrAndSubid(conn, yokonaiyo15).split("&", 2);
				String[] yokoArr16 = getJpstrAndSubid(conn, yokonaiyo16).split("&", 2);
				String[] yokoArr17 = getJpstrAndSubid(conn, yokonaiyo17).split("&", 2);
				String[] yokoArr18 = getJpstrAndSubid(conn, yokonaiyo18).split("&", 2);
				String[] yokoArr19 = getJpstrAndSubid(conn, yokonaiyo19).split("&", 2);
				String[] yokoArr20 = getJpstrAndSubid(conn, yokonaiyo20).split("&", 2);

				String[] shitagaArr1 = getJpstrAndSubid(conn, shitaganaiyo1).split("&", 2);
				String[] shitagaArr2 = getJpstrAndSubid(conn, shitaganaiyo2).split("&", 2);
				String[] shitagaArr3 = getJpstrAndSubid(conn, shitaganaiyo3).split("&", 2);
				String[] shitagaArr4 = getJpstrAndSubid(conn, shitaganaiyo4).split("&", 2);
				String[] shitagaArr5 = getJpstrAndSubid(conn, shitaganaiyo5).split("&", 2);
				String[] shitagaArr6 = getJpstrAndSubid(conn, shitaganaiyo6).split("&", 2);
				String[] shitagaArr7 = getJpstrAndSubid(conn, shitaganaiyo7).split("&", 2);
				String[] shitagaArr8 = getJpstrAndSubid(conn, shitaganaiyo8).split("&", 2);
				String[] shitagaArr9 = getJpstrAndSubid(conn, shitaganaiyo9).split("&", 2);
				String[] shitagaArr10 = getJpstrAndSubid(conn, shitaganaiyo10).split("&", 2);
				String[] shitagaArr11 = getJpstrAndSubid(conn, shitaganaiyo11).split("&", 2);
				String[] shitagaArr12 = getJpstrAndSubid(conn, shitaganaiyo12).split("&", 2);
				String[] shitagaArr13 = getJpstrAndSubid(conn, shitaganaiyo13).split("&", 2);
				String[] shitagaArr14 = getJpstrAndSubid(conn, shitaganaiyo14).split("&", 2);
				String[] shitagaArr15 = getJpstrAndSubid(conn, shitaganaiyo15).split("&", 2);
				String[] shitagaArr16 = getJpstrAndSubid(conn, shitaganaiyo16).split("&", 2);
				String[] shitagaArr17 = getJpstrAndSubid(conn, shitaganaiyo17).split("&", 2);
				String[] shitagaArr18 = getJpstrAndSubid(conn, shitaganaiyo18).split("&", 2);
				String[] shitagaArr19 = getJpstrAndSubid(conn, shitaganaiyo19).split("&", 2);
				String[] shitagaArr20 = getJpstrAndSubid(conn, shitaganaiyo20).split("&", 2);

				detail.setYokonaiyo1_jp(yokoArr1[0]);
				detail.setYokonaiyo2_jp(yokoArr2[0]);
				detail.setYokonaiyo3_jp(yokoArr3[0]);
				detail.setYokonaiyo4_jp(yokoArr4[0]);
				detail.setYokonaiyo5_jp(yokoArr5[0]);
				detail.setYokonaiyo6_jp(yokoArr6[0]);
				detail.setYokonaiyo7_jp(yokoArr7[0]);
				detail.setYokonaiyo8_jp(yokoArr8[0]);
				detail.setYokonaiyo9_jp(yokoArr9[0]);
				detail.setYokonaiyo10_jp(yokoArr10[0]);
				detail.setYokonaiyo11_jp(yokoArr11[0]);
				detail.setYokonaiyo12_jp(yokoArr12[0]);
				detail.setYokonaiyo13_jp(yokoArr13[0]);
				detail.setYokonaiyo14_jp(yokoArr14[0]);
				detail.setYokonaiyo15_jp(yokoArr15[0]);
				detail.setYokonaiyo16_jp(yokoArr16[0]);
				detail.setYokonaiyo17_jp(yokoArr17[0]);
				detail.setYokonaiyo18_jp(yokoArr18[0]);
				detail.setYokonaiyo19_jp(yokoArr19[0]);
				detail.setYokonaiyo20_jp(yokoArr20[0]);

				detail.setYokonaiyo1_subid(yokoArr1[1]);
				detail.setYokonaiyo2_subid(yokoArr2[1]);
				detail.setYokonaiyo3_subid(yokoArr3[1]);
				detail.setYokonaiyo4_subid(yokoArr4[1]);
				detail.setYokonaiyo5_subid(yokoArr5[1]);
				detail.setYokonaiyo6_subid(yokoArr6[1]);
				detail.setYokonaiyo7_subid(yokoArr7[1]);
				detail.setYokonaiyo8_subid(yokoArr8[1]);
				detail.setYokonaiyo9_subid(yokoArr9[1]);
				detail.setYokonaiyo10_subid(yokoArr10[1]);
				detail.setYokonaiyo11_subid(yokoArr11[1]);
				detail.setYokonaiyo12_subid(yokoArr12[1]);
				detail.setYokonaiyo13_subid(yokoArr13[1]);
				detail.setYokonaiyo14_subid(yokoArr14[1]);
				detail.setYokonaiyo15_subid(yokoArr15[1]);
				detail.setYokonaiyo16_subid(yokoArr16[1]);
				detail.setYokonaiyo17_subid(yokoArr17[1]);
				detail.setYokonaiyo18_subid(yokoArr18[1]);
				detail.setYokonaiyo19_subid(yokoArr19[1]);
				detail.setYokonaiyo20_subid(yokoArr20[1]);

				detail.setShitaganaiyo1_jp(shitagaArr1[0]);
				detail.setShitaganaiyo2_jp(shitagaArr2[0]);
				detail.setShitaganaiyo3_jp(shitagaArr3[0]);
				detail.setShitaganaiyo4_jp(shitagaArr4[0]);
				detail.setShitaganaiyo5_jp(shitagaArr5[0]);
				detail.setShitaganaiyo6_jp(shitagaArr6[0]);
				detail.setShitaganaiyo7_jp(shitagaArr7[0]);
				detail.setShitaganaiyo8_jp(shitagaArr8[0]);
				detail.setShitaganaiyo9_jp(shitagaArr9[0]);
				detail.setShitaganaiyo10_jp(shitagaArr10[0]);
				detail.setShitaganaiyo11_jp(shitagaArr11[0]);
				detail.setShitaganaiyo12_jp(shitagaArr12[0]);
				detail.setShitaganaiyo13_jp(shitagaArr13[0]);
				detail.setShitaganaiyo14_jp(shitagaArr14[0]);
				detail.setShitaganaiyo15_jp(shitagaArr15[0]);
				detail.setShitaganaiyo16_jp(shitagaArr16[0]);
				detail.setShitaganaiyo17_jp(shitagaArr17[0]);
				detail.setShitaganaiyo18_jp(shitagaArr18[0]);
				detail.setShitaganaiyo19_jp(shitagaArr19[0]);
				detail.setShitaganaiyo20_jp(shitagaArr20[0]);

				detail.setShitaganaiyo1_subid(shitagaArr1[1]);
				detail.setShitaganaiyo2_subid(shitagaArr2[1]);
				detail.setShitaganaiyo3_subid(shitagaArr3[1]);
				detail.setShitaganaiyo4_subid(shitagaArr4[1]);
				detail.setShitaganaiyo5_subid(shitagaArr5[1]);
				detail.setShitaganaiyo6_subid(shitagaArr6[1]);
				detail.setShitaganaiyo7_subid(shitagaArr7[1]);
				detail.setShitaganaiyo8_subid(shitagaArr8[1]);
				detail.setShitaganaiyo9_subid(shitagaArr9[1]);
				detail.setShitaganaiyo10_subid(shitagaArr10[1]);
				detail.setShitaganaiyo11_subid(shitagaArr11[1]);
				detail.setShitaganaiyo12_subid(shitagaArr12[1]);
				detail.setShitaganaiyo13_subid(shitagaArr13[1]);
				detail.setShitaganaiyo14_subid(shitagaArr14[1]);
				detail.setShitaganaiyo15_subid(shitagaArr15[1]);
				detail.setShitaganaiyo16_subid(shitagaArr16[1]);
				detail.setShitaganaiyo17_subid(shitagaArr17[1]);
				detail.setShitaganaiyo18_subid(shitagaArr18[1]);
				detail.setShitaganaiyo19_subid(shitagaArr19[1]);
				detail.setShitaganaiyo20_subid(shitagaArr20[1]);

				// 尺码
				String sizeyoko1 = detail.getSizeyoko1();
				String sizeyoko2 = detail.getSizeyoko2();
				String sizeyoko3 = detail.getSizeyoko3();
				String sizeyoko4 = detail.getSizeyoko4();
				String sizeyoko5 = detail.getSizeyoko5();
				String sizeyoko6 = detail.getSizeyoko6();
				String sizeyoko7 = detail.getSizeyoko7();
				String sizeyoko8 = detail.getSizeyoko8();
				String sizeyoko9 = detail.getSizeyoko9();
				String sizeyoko10 = detail.getSizeyoko10();
				String sizeshitaga1 = detail.getSizeshitaga1();
				String sizeshitaga2 = detail.getSizeshitaga2();
				String sizeshitaga3 = detail.getSizeshitaga3();
				String sizeshitaga4 = detail.getSizeshitaga4();
				String sizeshitaga5 = detail.getSizeshitaga5();
				String sizeshitaga6 = detail.getSizeshitaga6();
				String sizeshitaga7 = detail.getSizeshitaga7();
				String sizeshitaga8 = detail.getSizeshitaga8();
				String sizeshitaga9 = detail.getSizeshitaga9();
				String sizeshitaga10 = detail.getSizeshitaga10();

				detail.setSizeyoko1_jp(getJpstr(conn, sizeyoko1));
				detail.setSizeyoko2_jp(getJpstr(conn, sizeyoko2));
				detail.setSizeyoko3_jp(getJpstr(conn, sizeyoko3));
				detail.setSizeyoko4_jp(getJpstr(conn, sizeyoko4));
				detail.setSizeyoko5_jp(getJpstr(conn, sizeyoko5));
				detail.setSizeyoko6_jp(getJpstr(conn, sizeyoko6));
				detail.setSizeyoko7_jp(getJpstr(conn, sizeyoko7));
				detail.setSizeyoko8_jp(getJpstr(conn, sizeyoko8));
				detail.setSizeyoko9_jp(getJpstr(conn, sizeyoko9));
				detail.setSizeyoko10_jp(getJpstr(conn, sizeyoko10));
				detail.setSizeshitaga1_jp(getJpstr(conn, sizeshitaga1));
				detail.setSizeshitaga2_jp(getJpstr(conn, sizeshitaga2));
				detail.setSizeshitaga3_jp(getJpstr(conn, sizeshitaga3));
				detail.setSizeshitaga4_jp(getJpstr(conn, sizeshitaga4));
				detail.setSizeshitaga5_jp(getJpstr(conn, sizeshitaga5));
				detail.setSizeshitaga6_jp(getJpstr(conn, sizeshitaga6));
				detail.setSizeshitaga7_jp(getJpstr(conn, sizeshitaga7));
				detail.setSizeshitaga8_jp(getJpstr(conn, sizeshitaga8));
				detail.setSizeshitaga9_jp(getJpstr(conn, sizeshitaga9));
				detail.setSizeshitaga10_jp(getJpstr(conn, sizeshitaga10));

				String sozai = detail.getSozainaiyo();
				if (sozai.contains(",")) {
					String[] sozaiList = sozai.split(",");
					String sozaiJp = "";
					for (String sozainaiyo : sozaiList) {
						sozaiJp += getJpstr(conn, sozainaiyo) + ",";
					}
					sozaiJp = sozaiJp.substring(0, sozaiJp.length() - 1);
					detail.setSozainaiyo_jp(sozaiJp);
				} else {
					detail.setSozainaiyo_jp(getJpstr(conn, sozai));
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

	public void getHonyakuList(List<F120201Detail> detailList, List<String[]> sozaiList,
			List<String[]> shosaimeishoList, List<String[]> shosainaiyoList, List<String[]> axismeiList,
			List<String[]> axisnaiyoList, List<String[]> sizeList) throws Exception {
		Connection conn = JdbcConnection.getConnection();
		try {
			for (F120201Detail detail : detailList) {
				if ("0".equals(detail.getDetailDelFlg())) {
					if (!Utility.isEmptyString(detail.getSozainaiyo())) {
						String[] sozainaiyoList = detail.getSozainaiyo().split(",");
						if (!Utility.isEmptyString(detail.getSozainaiyo_jp())) {
							String[] sozainaiyoJpList = detail.getSozainaiyo_jp().split(",", sozainaiyoList.length);
							for (int i = 0; i < sozainaiyoList.length; i++) {
								if (Utility.isEmptyString(sozainaiyoJpList[i])) {
									sozaiList.addAll(
											honyaku(conn, new String[] { sozainaiyoList[i] }, detail.getShohinbango()));
								}
							}
						} else {
							for (String sozai : sozainaiyoList) {
								sozaiList.add(new String[] { sozai, detail.getShohinbango() });
							}
						}
					}
					// 详细信息（名称）
					String shohinshosaimeisho1 = detail.getShohinshosaimeisho1();
					String shohinshosaimeisho2 = detail.getShohinshosaimeisho2();
					String shohinshosaimeisho3 = detail.getShohinshosaimeisho3();
					String shohinshosaimeisho4 = detail.getShohinshosaimeisho4();
					String shohinshosaimeisho5 = detail.getShohinshosaimeisho5();
					String shohinshosaimeisho6 = detail.getShohinshosaimeisho6();
					shosaimeishoList.addAll(honyaku(conn,
							new String[] { shohinshosaimeisho1, shohinshosaimeisho2, shohinshosaimeisho3,
									shohinshosaimeisho4, shohinshosaimeisho5, shohinshosaimeisho6 },
							detail.getShohinbango()));

					// 详细信息（内容）
					String shohinshosainaiyo1 = detail.getShohinshosainaiyo1();
					String shohinshosainaiyo2 = detail.getShohinshosainaiyo2();
					String shohinshosainaiyo3 = detail.getShohinshosainaiyo3();
					String shohinshosainaiyo4 = detail.getShohinshosainaiyo4();
					String shohinshosainaiyo5 = detail.getShohinshosainaiyo5();
					String shohinshosainaiyo6 = detail.getShohinshosainaiyo6();
					shosainaiyoList.addAll(honyaku(conn,
							new String[] { shohinshosainaiyo1, shohinshosainaiyo2, shohinshosainaiyo3,
									shohinshosainaiyo4, shohinshosainaiyo5, shohinshosainaiyo6 },
							detail.getShohinbango()));

					// 横纵轴（名称）
					String yokomei = detail.getYokomei();
					String shitagamei = detail.getShitagamei();
					axismeiList.addAll(honyaku(conn, new String[] { yokomei, shitagamei }, detail.getShohinbango()));

					// 横纵轴（内容）
					String yokonaiyo1 = detail.getYokonaiyo1();
					String yokonaiyo2 = detail.getYokonaiyo2();
					String yokonaiyo3 = detail.getYokonaiyo3();
					String yokonaiyo4 = detail.getYokonaiyo4();
					String yokonaiyo5 = detail.getYokonaiyo5();
					String yokonaiyo6 = detail.getYokonaiyo6();
					String yokonaiyo7 = detail.getYokonaiyo7();
					String yokonaiyo8 = detail.getYokonaiyo8();
					String yokonaiyo9 = detail.getYokonaiyo9();
					String yokonaiyo10 = detail.getYokonaiyo10();
					String yokonaiyo11 = detail.getYokonaiyo11();
					String yokonaiyo12 = detail.getYokonaiyo12();
					String yokonaiyo13 = detail.getYokonaiyo13();
					String yokonaiyo14 = detail.getYokonaiyo14();
					String yokonaiyo15 = detail.getYokonaiyo15();
					String yokonaiyo16 = detail.getYokonaiyo16();
					String yokonaiyo17 = detail.getYokonaiyo17();
					String yokonaiyo18 = detail.getYokonaiyo18();
					String yokonaiyo19 = detail.getYokonaiyo19();
					String yokonaiyo20 = detail.getYokonaiyo20();
					String shitaganaiyo1 = detail.getShitaganaiyo1();
					String shitaganaiyo2 = detail.getShitaganaiyo2();
					String shitaganaiyo3 = detail.getShitaganaiyo3();
					String shitaganaiyo4 = detail.getShitaganaiyo4();
					String shitaganaiyo5 = detail.getShitaganaiyo5();
					String shitaganaiyo6 = detail.getShitaganaiyo6();
					String shitaganaiyo7 = detail.getShitaganaiyo7();
					String shitaganaiyo8 = detail.getShitaganaiyo8();
					String shitaganaiyo9 = detail.getShitaganaiyo9();
					String shitaganaiyo10 = detail.getShitaganaiyo10();
					String shitaganaiyo11 = detail.getShitaganaiyo11();
					String shitaganaiyo12 = detail.getShitaganaiyo12();
					String shitaganaiyo13 = detail.getShitaganaiyo13();
					String shitaganaiyo14 = detail.getShitaganaiyo14();
					String shitaganaiyo15 = detail.getShitaganaiyo15();
					String shitaganaiyo16 = detail.getShitaganaiyo16();
					String shitaganaiyo17 = detail.getShitaganaiyo17();
					String shitaganaiyo18 = detail.getShitaganaiyo18();
					String shitaganaiyo19 = detail.getShitaganaiyo19();
					String shitaganaiyo20 = detail.getShitaganaiyo20();

					axisnaiyoList.addAll(honyaku(conn, new String[] { yokonaiyo1, yokonaiyo2, yokonaiyo3, yokonaiyo4,
							yokonaiyo5, yokonaiyo6, yokonaiyo7, yokonaiyo8, yokonaiyo9, yokonaiyo10, yokonaiyo11,
							yokonaiyo12, yokonaiyo13, yokonaiyo14, yokonaiyo15, yokonaiyo16, yokonaiyo17, yokonaiyo18,
							yokonaiyo19, yokonaiyo20, shitaganaiyo1, shitaganaiyo2, shitaganaiyo3, shitaganaiyo4,
							shitaganaiyo5, shitaganaiyo6, shitaganaiyo7, shitaganaiyo8, shitaganaiyo9, shitaganaiyo10,
							shitaganaiyo11, shitaganaiyo12, shitaganaiyo13, shitaganaiyo14, shitaganaiyo15,
							shitaganaiyo16, shitaganaiyo17, shitaganaiyo18, shitaganaiyo19, shitaganaiyo20, },
							detail.getShohinbango()));

					// 尺码
					String sizeyoko1 = detail.getSizeyoko1();
					String sizeyoko2 = detail.getSizeyoko2();
					String sizeyoko3 = detail.getSizeyoko3();
					String sizeyoko4 = detail.getSizeyoko4();
					String sizeyoko5 = detail.getSizeyoko5();
					String sizeyoko6 = detail.getSizeyoko6();
					String sizeyoko7 = detail.getSizeyoko7();
					String sizeyoko8 = detail.getSizeyoko8();
					String sizeyoko9 = detail.getSizeyoko9();
					String sizeyoko10 = detail.getSizeyoko10();
					String sizeshitaga1 = detail.getSizeyoko1();
					String sizeshitaga2 = detail.getSizeyoko2();
					String sizeshitaga3 = detail.getSizeyoko3();
					String sizeshitaga4 = detail.getSizeyoko4();
					String sizeshitaga5 = detail.getSizeyoko5();
					String sizeshitaga6 = detail.getSizeyoko6();
					String sizeshitaga7 = detail.getSizeyoko7();
					String sizeshitaga8 = detail.getSizeyoko8();
					String sizeshitaga9 = detail.getSizeyoko9();
					String sizeshitaga10 = detail.getSizeyoko10();

					sizeList.addAll(honyaku(conn,
							new String[] { sizeyoko1, sizeyoko2, sizeyoko3, sizeyoko4, sizeyoko5, sizeyoko6, sizeyoko7,
									sizeyoko8, sizeyoko9, sizeyoko10, sizeshitaga1, sizeshitaga2, sizeshitaga3,
									sizeshitaga4, sizeshitaga5, sizeshitaga6, sizeshitaga7, sizeshitaga8, sizeshitaga9,
									sizeshitaga10 },
							detail.getShohinbango()));
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

	public List<String[]> honyaku(Connection conn, String[] strArr, String shohinbango) throws Exception {
		List<String[]> resultArr = new ArrayList<String[]>();
		for (String cnstr : strArr) {
			if (!Utility.isEmptyString(cnstr)) {
				String jpstr = getJpstr(conn, cnstr);
				if (Utility.isEmptyString(jpstr)) {
					resultArr.add(new String[] { cnstr, shohinbango });
				}
			}
		}
		return resultArr;
	}

	public String getJpstr(Connection conn, String cnstr) throws Exception {

		String jpstr = "";

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		sql = "SELECT * FROM dic_tbl where c_name = ?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, cnstr);
		rs = ps.executeQuery();
		while (rs.next()) {
			jpstr = rs.getString("j_name");
		}

		return jpstr;
	}

	public String getJpstrAndSubid(Connection conn, String cnstr) throws Exception {
		String result = "";

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = null;

		sql = "SELECT * FROM dic_tbl where c_name = ? and id= '102'";

		String jpstr = "";
		String subid = "";
		ps = conn.prepareStatement(sql);
		ps.setString(1, cnstr);
		rs = ps.executeQuery();
		while (rs.next()) {
			jpstr = rs.getString("j_name");
			subid = rs.getString("s_coid");
			result = jpstr + "&" + subid;
		}
		if (Utility.isEmptyString(result)) {
			result = "&";
		}

		return result;
	}

	public List<ShohinsentakushiBean> converSelToSelBean(List<F120201Detail> detailList) {
		List<ShohinsentakushiBean> resultList = new ArrayList<>();

		for (F120201Detail detail : detailList) {
			String kontororukaramu = "u";
			String shohinkanribango = "";
			String sentakutaipu = "i";
			String selectcheckboxyoukomokumei = "";
			String selectcheckboxyousentakushi = "";
			String komokusentakushibetuzaikoyouyokojikusentakushi = "";
			String komokusentakushibetuzaikoyoyokojikusentakushishibango = "";
			String komokusentakushizaikoyoutatejikusentakushi = "";
			String komokusentakushibetuzaikoyotatejikusentakushishibango = "";
			String komokusentakushibetuzaikototoriyosekanohyouji = "";
			String komokusentakushibetuzaikoyozaikosu = "999";
			String zaikonodoshifuragu = "0";
			String zaikokiretokinochumonuketuke = "0";
			String zaikoaritokinoukikanribango = "0";
			String zaikokiretokinoukikanribango = "0";

			String[] yokoList = yokoListToArr(detail);
			String[] shitagaList = shitagaListToArr(detail);
			String[] yokoSubidList = yokoSubidListToArr(detail);
			String[] shitagaSubidList = shitagaSubidListToArr(detail);

			for (int i = 0; i < yokoList.length; i++) {
				if (Utility.isEmptyString(yokoList[i])) {
					break;
				}
				shohinkanribango = detail.getShohinbango();

				komokusentakushibetuzaikoyouyokojikusentakushi = yokoList[i];
				komokusentakushibetuzaikoyoyokojikusentakushishibango = yokoSubidList[i];
				if (!Utility.isEmptyString(shitagaList[0])) {
					for (int j = 0; j < shitagaList.length; j++) {
						if (Utility.isEmptyString(shitagaList[j])) {
							break;
						}
						komokusentakushizaikoyoutatejikusentakushi = shitagaList[j];
						komokusentakushibetuzaikoyotatejikusentakushishibango = shitagaSubidList[j];
						ShohinsentakushiBean sel = new ShohinsentakushiBean();
						resultList.add(sel);

						// コントロールカラム
						sel.setKontororukaramu(kontororukaramu);
						// 商品管理番号（商品URL）
						sel.setShohinkanribango(shohinkanribango);
						// 選択肢タイプ
						sel.setSentakutaipu(sentakutaipu);
						// Select/Checkbox用項目名
						sel.setSelectcheckboxyoukomokumei(selectcheckboxyoukomokumei);
						// Select/Checkbox用選択肢
						sel.setSelectcheckboxyousentakushi(selectcheckboxyousentakushi);
						// 項目選択肢別在庫用横軸選択肢
						sel.setKomokusentakushibetuzaikoyouyokojikusentakushi(
								komokusentakushibetuzaikoyouyokojikusentakushi);
						// 項目選択肢別在庫用横軸選択肢子番号
						sel.setKomokusentakushibetuzaikoyoyokojikusentakushishibango(
								komokusentakushibetuzaikoyoyokojikusentakushishibango);
						// 項目選択肢別在庫用縦軸選択肢
						sel.setKomokusentakushizaikoyoutatejikusentakushi(komokusentakushizaikoyoutatejikusentakushi);
						// 項目選択肢別在庫用縦軸選択肢子番号
						sel.setKomokusentakushibetuzaikoyotatejikusentakushishibango(
								komokusentakushibetuzaikoyotatejikusentakushishibango);
						// 項目選択肢別在庫用取り寄せ可能表示
						sel.setKomokusentakushibetuzaikototoriyosekanohyouji(
								komokusentakushibetuzaikototoriyosekanohyouji);
						// 項目選択肢別在庫用在庫数
						sel.setKomokusentakushibetuzaikoyozaikosu(komokusentakushibetuzaikoyozaikosu);
						// 在庫戻しフラグ
						sel.setZaikonodoshifuragu(zaikonodoshifuragu);
						// 在庫切れ時の注文受付
						sel.setZaikokiretokinochumonuketuke(zaikokiretokinochumonuketuke);
						// 在庫あり時納期管理番号
						sel.setZaikoaritokinoukikanribango(zaikoaritokinoukikanribango);
						// 在庫切れ時納期管理番号
						sel.setZaikokiretokinoukikanribango(zaikokiretokinoukikanribango);
					}
				} else {
					ShohinsentakushiBean sel = new ShohinsentakushiBean();
					resultList.add(sel);

					// コントロールカラム
					sel.setKontororukaramu(kontororukaramu);
					// 商品管理番号（商品URL）
					sel.setShohinkanribango(shohinkanribango);
					// 選択肢タイプ
					sel.setSentakutaipu(sentakutaipu);
					// Select/Checkbox用項目名
					sel.setSelectcheckboxyoukomokumei(selectcheckboxyoukomokumei);
					// Select/Checkbox用選択肢
					sel.setSelectcheckboxyousentakushi(selectcheckboxyousentakushi);
					// 項目選択肢別在庫用横軸選択肢
					sel.setKomokusentakushibetuzaikoyouyokojikusentakushi(
							komokusentakushibetuzaikoyouyokojikusentakushi);
					// 項目選択肢別在庫用横軸選択肢子番号
					sel.setKomokusentakushibetuzaikoyoyokojikusentakushishibango(
							komokusentakushibetuzaikoyoyokojikusentakushishibango);
					// 項目選択肢別在庫用縦軸選択肢
					sel.setKomokusentakushizaikoyoutatejikusentakushi(komokusentakushizaikoyoutatejikusentakushi);
					// 項目選択肢別在庫用縦軸選択肢子番号
					sel.setKomokusentakushibetuzaikoyotatejikusentakushishibango(
							komokusentakushibetuzaikoyotatejikusentakushishibango);
					// 項目選択肢別在庫用取り寄せ可能表示
					sel.setKomokusentakushibetuzaikototoriyosekanohyouji(komokusentakushibetuzaikototoriyosekanohyouji);
					// 項目選択肢別在庫用在庫数
					sel.setKomokusentakushibetuzaikoyozaikosu(komokusentakushibetuzaikoyozaikosu);
					// 在庫戻しフラグ
					sel.setZaikonodoshifuragu(zaikonodoshifuragu);
					// 在庫切れ時の注文受付
					sel.setZaikokiretokinochumonuketuke(zaikokiretokinochumonuketuke);
					// 在庫あり時納期管理番号
					sel.setZaikoaritokinoukikanribango(zaikoaritokinoukikanribango);
					// 在庫切れ時納期管理番号
					sel.setZaikokiretokinoukikanribango(zaikokiretokinoukikanribango);
				}
			}

		}
		for (F120201Detail detail : detailList) {
			if (detail.isHogofirumu()) {
				ShohinsentakushiBean sel1 = new ShohinsentakushiBean();
				ShohinsentakushiBean sel2 = new ShohinsentakushiBean();
				resultList.add(sel1);
				resultList.add(sel2);

				// コントロールカラム
				sel1.setKontororukaramu("u");
				// 商品管理番号（商品URL）
				sel1.setShohinkanribango(detail.getShohinbango());
				// 選択肢タイプ
				sel1.setSentakutaipu("s");
				// Select/Checkbox用項目名
				sel1.setSelectcheckboxyoukomokumei("保護フィルムとイヤホンジャック");
				// Select/Checkbox用選択肢
				sel1.setSelectcheckboxyousentakushi("いいえ、いりません");
				// 項目選択肢別在庫用横軸選択肢
				sel1.setKomokusentakushibetuzaikoyouyokojikusentakushi("");
				// 項目選択肢別在庫用横軸選択肢子番号
				sel1.setKomokusentakushibetuzaikoyoyokojikusentakushishibango("");
				// 項目選択肢別在庫用縦軸選択肢
				sel1.setKomokusentakushizaikoyoutatejikusentakushi("");
				// 項目選択肢別在庫用縦軸選択肢子番号
				sel1.setKomokusentakushibetuzaikoyotatejikusentakushishibango("");
				// 項目選択肢別在庫用取り寄せ可能表示
				sel1.setKomokusentakushibetuzaikototoriyosekanohyouji("");
				// 項目選択肢別在庫用在庫数
				sel1.setKomokusentakushibetuzaikoyozaikosu("");
				// 在庫戻しフラグ
				sel1.setZaikonodoshifuragu("");
				// 在庫切れ時の注文受付
				sel1.setZaikokiretokinochumonuketuke("");
				// 在庫あり時納期管理番号
				sel1.setZaikoaritokinoukikanribango("");
				// 在庫切れ時納期管理番号
				sel1.setZaikokiretokinoukikanribango("");

				// コントロールカラム
				sel2.setKontororukaramu("u");
				// 商品管理番号（商品URL）
				sel2.setShohinkanribango(detail.getShohinbango());
				// 選択肢タイプ
				sel2.setSentakutaipu("s");
				// Select/Checkbox用項目名
				sel2.setSelectcheckboxyoukomokumei("保護フィルムとイヤホンジャック");
				// Select/Checkbox用選択肢
				sel2.setSelectcheckboxyousentakushi("レビュー書いて、GET");
				// 項目選択肢別在庫用横軸選択肢
				sel2.setKomokusentakushibetuzaikoyouyokojikusentakushi("");
				// 項目選択肢別在庫用横軸選択肢子番号
				sel2.setKomokusentakushibetuzaikoyoyokojikusentakushishibango("");
				// 項目選択肢別在庫用縦軸選択肢
				sel2.setKomokusentakushizaikoyoutatejikusentakushi("");
				// 項目選択肢別在庫用縦軸選択肢子番号
				sel2.setKomokusentakushibetuzaikoyotatejikusentakushishibango("");
				// 項目選択肢別在庫用取り寄せ可能表示
				sel2.setKomokusentakushibetuzaikototoriyosekanohyouji("");
				// 項目選択肢別在庫用在庫数
				sel2.setKomokusentakushibetuzaikoyozaikosu("");
				// 在庫戻しフラグ
				sel2.setZaikonodoshifuragu("");
				// 在庫切れ時の注文受付
				sel2.setZaikokiretokinochumonuketuke("");
				// 在庫あり時納期管理番号
				sel2.setZaikoaritokinoukikanribango("");
				// 在庫切れ時納期管理番号
				sel2.setZaikokiretokinoukikanribango("");
			}
			// if (detail.isMerubinsoryomuryokyanpen()) {
//			if (detail.getShohinmei_jp().contains("【メール便送料無料】")) {
//				ShohinsentakushiBean sel2 = new ShohinsentakushiBean();
//
//				resultList.add(sel2);
//
//				// // コントロールカラム
//				// sel1.setKontororukaramu("u");
//				// // 商品管理番号（商品URL）
//				// sel1.setShohinkanribango(detail.getShohinbango());
//				// // 選択肢タイプ
//				// sel1.setSentakutaipu("s");
//				// // Select/Checkbox用項目名
//				// sel1.setSelectcheckboxyoukomokumei("レビューキャンペーン");
//				// // Select/Checkbox用選択肢
//				// sel1.setSelectcheckboxyousentakushi("いいえ、不参加。");
//				// // 項目選択肢別在庫用横軸選択肢
//				// sel1.setKomokusentakushibetuzaikoyouyokojikusentakushi("");
//				// // 項目選択肢別在庫用横軸選択肢子番号
//				// sel1.setKomokusentakushibetuzaikoyoyokojikusentakushishibango("");
//				// // 項目選択肢別在庫用縦軸選択肢
//				// sel1.setKomokusentakushizaikoyoutatejikusentakushi("");
//				// // 項目選択肢別在庫用縦軸選択肢子番号
//				// sel1.setKomokusentakushibetuzaikoyotatejikusentakushishibango("");
//				// // 項目選択肢別在庫用取り寄せ可能表示
//				// sel1.setKomokusentakushibetuzaikototoriyosekanohyouji("");
//				// // 項目選択肢別在庫用在庫数
//				// sel1.setKomokusentakushibetuzaikoyozaikosu("");
//				// // 在庫戻しフラグ
//				// sel1.setZaikonodoshifuragu("");
//				// // 在庫切れ時の注文受付
//				// sel1.setZaikokiretokinochumonuketuke("");
//				// // 在庫あり時納期管理番号
//				// sel1.setZaikoaritokinoukikanribango("");
//				// // 在庫切れ時納期管理番号
//				// sel1.setZaikokiretokinoukikanribango("");
//
//				// コントロールカラム
//				sel2.setKontororukaramu("u");
//				// 商品管理番号（商品URL）
//				sel2.setShohinkanribango(detail.getShohinbango());
//				// 選択肢タイプ
//				sel2.setSentakutaipu("s");
//				// Select/Checkbox用項目名
//				sel2.setSelectcheckboxyoukomokumei("代金引換の場合は送料無料対象外");
//				// Select/Checkbox用選択肢
//				sel2.setSelectcheckboxyousentakushi("了承済み");
//				// 項目選択肢別在庫用横軸選択肢
//				sel2.setKomokusentakushibetuzaikoyouyokojikusentakushi("");
//				// 項目選択肢別在庫用横軸選択肢子番号
//				sel2.setKomokusentakushibetuzaikoyoyokojikusentakushishibango("");
//				// 項目選択肢別在庫用縦軸選択肢
//				sel2.setKomokusentakushizaikoyoutatejikusentakushi("");
//				// 項目選択肢別在庫用縦軸選択肢子番号
//				sel2.setKomokusentakushibetuzaikoyotatejikusentakushishibango("");
//				// 項目選択肢別在庫用取り寄せ可能表示
//				sel2.setKomokusentakushibetuzaikototoriyosekanohyouji("");
//				// 項目選択肢別在庫用在庫数
//				sel2.setKomokusentakushibetuzaikoyozaikosu("");
//				// 在庫戻しフラグ
//				sel2.setZaikonodoshifuragu("");
//				// 在庫切れ時の注文受付
//				sel2.setZaikokiretokinochumonuketuke("");
//				// 在庫あり時納期管理番号
//				sel2.setZaikoaritokinoukikanribango("");
//				// 在庫切れ時納期管理番号
//				sel2.setZaikokiretokinoukikanribango("");
//			}

			if ("0".equals(detail.getMerubin())) {
				ShohinsentakushiBean sel1 = new ShohinsentakushiBean();

				resultList.add(sel1);

				// コントロールカラム
				sel1.setKontororukaramu("u");
				// 商品管理番号（商品URL）
				sel1.setShohinkanribango(detail.getShohinbango());
				// 選択肢タイプ
				sel1.setSentakutaipu("s");
				// Select/Checkbox用項目名
				sel1.setSelectcheckboxyoukomokumei("メール便対応不可");
				// Select/Checkbox用選択肢
				sel1.setSelectcheckboxyousentakushi("宅急便を選択してください");
				// 項目選択肢別在庫用横軸選択肢
				sel1.setKomokusentakushibetuzaikoyouyokojikusentakushi("");
				// 項目選択肢別在庫用横軸選択肢子番号
				sel1.setKomokusentakushibetuzaikoyoyokojikusentakushishibango("");
				// 項目選択肢別在庫用縦軸選択肢
				sel1.setKomokusentakushizaikoyoutatejikusentakushi("");
				// 項目選択肢別在庫用縦軸選択肢子番号
				sel1.setKomokusentakushibetuzaikoyotatejikusentakushishibango("");
				// 項目選択肢別在庫用取り寄せ可能表示
				sel1.setKomokusentakushibetuzaikototoriyosekanohyouji("");
				// 項目選択肢別在庫用在庫数
				sel1.setKomokusentakushibetuzaikoyozaikosu("");
				// 在庫戻しフラグ
				sel1.setZaikonodoshifuragu("");
				// 在庫切れ時の注文受付
				sel1.setZaikokiretokinochumonuketuke("");
				// 在庫あり時納期管理番号
				sel1.setZaikoaritokinoukikanribango("");
				// 在庫切れ時納期管理番号
				sel1.setZaikokiretokinoukikanribango("");

			}
			ShohinsentakushiBean sel1 = new ShohinsentakushiBean();

			resultList.add(sel1);

			// コントロールカラム
			sel1.setKontororukaramu("u");
			// 商品管理番号（商品URL）
			sel1.setShohinkanribango(detail.getShohinbango());
			// 選択肢タイプ
			sel1.setSentakutaipu("s");
			// Select/Checkbox用項目名
			sel1.setSelectcheckboxyoukomokumei("ご注文前に必ず納期をご確認お願いします");
			// Select/Checkbox用選択肢
			sel1.setSelectcheckboxyousentakushi("了承済み");
			// 項目選択肢別在庫用横軸選択肢
			sel1.setKomokusentakushibetuzaikoyouyokojikusentakushi("");
			// 項目選択肢別在庫用横軸選択肢子番号
			sel1.setKomokusentakushibetuzaikoyoyokojikusentakushishibango("");
			// 項目選択肢別在庫用縦軸選択肢
			sel1.setKomokusentakushizaikoyoutatejikusentakushi("");
			// 項目選択肢別在庫用縦軸選択肢子番号
			sel1.setKomokusentakushibetuzaikoyotatejikusentakushishibango("");
			// 項目選択肢別在庫用取り寄せ可能表示
			sel1.setKomokusentakushibetuzaikototoriyosekanohyouji("");
			// 項目選択肢別在庫用在庫数
			sel1.setKomokusentakushibetuzaikoyozaikosu("");
			// 在庫戻しフラグ
			sel1.setZaikonodoshifuragu("");
			// 在庫切れ時の注文受付
			sel1.setZaikokiretokinochumonuketuke("");
			// 在庫あり時納期管理番号
			sel1.setZaikoaritokinoukikanribango("");
			// 在庫切れ時納期管理番号
			sel1.setZaikokiretokinoukikanribango("");
		}
		return resultList;
	}

	public List<ShohinInfoBean> converDetailToShohinBean(List<F120201Detail> detailList, String shop) {
		List<ShohinInfoBean> resultList = new ArrayList<>();
		for (F120201Detail detail : detailList) {
			ShohinInfoBean shohin = new ShohinInfoBean();
			resultList.add(shohin);

			// コントロールカラム
			shohin.setKontororukaramu("n");
			// 商品管理番号（商品URL）
			shohin.setShouhinkanribango(detail.getShohinbango());
			// 商品番号
			shohin.setShouhinbango(detail.getShohinbango());
			// 全商品ディレクトリID
			shohin.setZenshohindirekutoriId(detail.getDirekutoriid());
			// タグID
			shohin.setTaguId(detail.getTaguid());
			// PC用キャッチコピー
			shohin.setPcyokyachikopi(detail.getPckyachikopi());
			// モバイル用キャッチコピー
			shohin.setMobairuyokyachikopi(detail.getMobairukyachikopi());
			// 商品名
			shohin.setShouhinmei(detail.getShohinmei_jp());
			// 販売価格
			shohin.setHanbaikakaku(detail.getHanbaikakaku());
			// 表示価格
			shohin.setHyojikakaku(detail.getHyojikakaku());
			// 消費税
			shohin.setShouhizei(detail.getShohizeibetsu());
			// 送料
			shohin.setSouryou(detail.getSoryobetsu());
			// 個別送料
			shohin.setKobetusouryou("");
			// 送料区分1
			shohin.setSouryoukubun1("0");
			// 送料区分2
			shohin.setSouryoukubun2("0");
			// 代引料
			shohin.setDaibikiryou("0");
			// 倉庫指定
			shohin.setSokoshitei("");
			// 商品情報レイアウト
			shohin.setShouhinjouhoureiaouto("1");
			// 注文ボタン
			shohin.setChumonbotan("1");
			// 資料請求ボタン
			shohin.setShiryosekyubotan("0");
			// 商品問い合わせボタン
			shohin.setShouhintoiawasebotan("1");
			// 再入荷お知らせボタン
			shohin.setSainyukaoshirasebotan("0");
			// モバイル表示
			shohin.setMobairuhyoji("1");
			// のし対応
			shohin.setNoshitaiou("0");
			// PC用商品説明文
			String pcyspsmw = "";
			if (detail.isOsusume1()) {
				pcyspsmw += "<iframe frameborder=\"0\" scrolling=\"no\" src=\"https://www.rakuten.ne.jp/gold/" + shop
						+ "/osusume1.html\" width=\"370px\" height=\"820px\"></iframe><br/>";
			}
			if (detail.isOsusume2()) {
				pcyspsmw += "<iframe frameborder=\"0\" scrolling=\"no\" src=\"https://www.rakuten.ne.jp/gold/" + shop
						+ "/osusume2.html\" width=\"370px\" height=\"820px\"></iframe><br/>";
			}
			if (detail.isOsusume3()) {
				pcyspsmw += "<iframe frameborder=\"0\" scrolling=\"no\" src=\"https://www.rakuten.ne.jp/gold/" + shop
						+ "/osusume3.html\" width=\"370px\" height=\"820px\"></iframe><br/>";
			}
			shohin.setPcyoushouhinsetumeibun(pcyspsmw);
			// 商品詳細１（名称）
			String spxxms1 = detail.getShohinshosaimeisho1_jp();

			// 商品詳細１（内容）
			String spxxny1 = detail.getShohinshosainaiyo1_jp();

			// 商品詳細２（名称）
			String spxxms2 = detail.getShohinshosaimeisho2_jp();

			// 商品詳細２（内容）
			String spxxny2 = detail.getShohinshosainaiyo2_jp();

			// 商品詳細３（名称）
			String spxxms3 = detail.getShohinshosaimeisho3_jp();

			// 商品詳細３（内容）
			String spxxny3 = detail.getShohinshosainaiyo3_jp();

			// 商品詳細４（名称）
			String spxxms4 = detail.getShohinshosaimeisho4_jp();

			// 商品詳細４（内容）
			String spxxny4 = detail.getShohinshosainaiyo4_jp();

			// 商品詳細５（名称）
			String spxxms5 = detail.getShohinshosaimeisho5_jp();

			// 商品詳細５（内容）
			String spxxny5 = detail.getShohinshosainaiyo5_jp();

			// 商品詳細６（名称）
			String spxxms6 = detail.getShohinshosaimeisho6_jp();

			// 商品詳細６（内容）
			String spxxny6 = detail.getShohinshosainaiyo6_jp();

			List<String> yokoList = sizeyokoListToArr(detail);
			List<String> shitagaList = sizeshitagaListToArr(detail);
			List<List<String>> naiyoList = naiyoListToArr(detail, yokoList.size(), shitagaList.size());
			List<String> picUrlList = getPicList(detail, "" + shop + "", "楽天");
			List<String> checkedList = new ArrayList<String>();

			List<PicList> picList = detail.getPicList();
			if(!Utility.isEmptyList(picList)) {
			for (int i = 0; i < picList.size(); i++) {
				if (picList.get(i).isChecked()) {
					checkedList.add(picUrlList.get(i));
				}
			}}

			// モバイル用商品説明文
			String mobairusetumeibun = getMobairuSetumei(detail, yokoList, shitagaList, naiyoList);
			shohin.setMobairuyoushouhinsetumeibun(mobairusetumeibun);

			// スマートフォン用商品説明文
			String sphkyachikopy = getSmhsetumsei(detail, yokoList, shitagaList, naiyoList, picList, picUrlList,
					spxxms1, spxxny1, spxxms2, spxxms3, spxxny2, spxxny3, spxxms4, spxxny4, spxxms5, spxxny5, spxxms6,
					spxxny6);
			shohin.setSumatofonyoushouhinsetumeibun(sphkyachikopy);

			// PC用販売説明文
			String pcyouhanbaisetumeibun = getPCsetumei(detail, yokoList, shitagaList, naiyoList, picList, picUrlList,
					spxxms1, spxxny1, spxxms2, spxxms3, spxxny2, spxxny3, spxxms4, spxxny4, spxxms5, spxxny5, spxxms6,
					spxxny6, shop);
			shohin.setPcyouhanbaisetumeibun(pcyouhanbaisetumeibun);

			// 商品画像URL
			String shouhingazoUrl = "";
			for (String str : checkedList) {
				if (Utility.isEmptyString(shouhingazoUrl)) {
					shouhingazoUrl = str;
				} else {
					shouhingazoUrl = shouhingazoUrl + " " + str;
				}
			}
			shohin.setShouhingazoUrl(shouhingazoUrl);
			// 商品画像名（ALT）
			shohin.setShouhingazomeiAlt(
					detail.getShohinmei_jp() + " " + detail.getShohinmei_jp() + " " + detail.getShohinmei_jp());
			// 動画
			shohin.setDouga("");
			// 販売期間指定
			shohin.setHanbaikikanshitei("");
			// 注文受付数
			shohin.setChumonnuketukesu("-1");
			// 在庫タイプ
			shohin.setZaikotaipu("2");
			// 在庫数
			shohin.setZaikusu("");
			// 在庫数表示
			shohin.setZaikosuhyouji("");
			// 項目選択肢別在庫用横軸項目名
			shohin.setKomokusentakushibetuzaikoyouyokojikukoumokumei(detail.getYokomei_jp());
			// 項目選択肢別在庫用縦軸項目名
			shohin.setKomokusentakushibetuzaikoyoutatejikukomokumei(detail.getShitagamei_jp());
			// 項目選択肢別在庫用残り表示閾値
			shohin.setKoumokusentakushibetuzaikoyounokorihyoujiikichi("0");
			// RAC番号
			shohin.setRacbango("");
			// サーチ非表示
			shohin.setSachihihyoji("0");
			// 闇市パスワード
			shohin.setYamiichipasuwado("");
			// カタログID
			shohin.setKataroguId("");
			// 在庫戻しフラグ
			shohin.setZaikonodoshifuragu("");
			// 在庫切れ時の注文受付
			shohin.setZaikokiretokinochumonnuketuke("");
			// 在庫あり時納期管理番号
			shohin.setZaikoaritokinoukikanribango("");
			// 在庫切れ時納期管理番号
			shohin.setZaikokiretokinoukikanribango("");
			// 予約商品発売日
			shohin.setYoyakushouhinhanbaibi("");
			// ポイント変倍率
			shohin.setPointohenbairitu("");
			// ポイント変倍率適用期間
			shohin.setPointohenbaitekiyoukikan("");

			// ヘッダー・フッター・レフトナビ
			shohin.setHeddafuttarefutonabi("「自動選択」テンプレートを利用");
			// 表示項目の並び順
			shohin.setHyoujikomokunonarabijun("「自動選択」テンプレートを利用");
			// 共通説明文（小）
			shohin.setKyotusetumeibunsho("「自動選択」テンプレートを利用");
			// 目玉商品
			shohin.setMedamashouhin("「自動選択」テンプレートを利用");
			// 共通説明文（大）
			shohin.setKyoutusetumeibundai("「自動選択」テンプレートを利用");

			// レビュー本文表示
			shohin.setRebyuhonbunhyouji("2");
			// あす楽配送管理番号
			shohin.setArurakuhaisoukanribango("0");
			// 海外配送管理番号
			shohin.setKaigaihaisoukanribango("");
			// サイズ表リンク
			shohin.setSaizuhyourinku("0");
			// 医薬品説明文
			shohin.setYypsmw("");
			// 医薬品注意事項
			shohin.setYypzysx("");
			// 二重価格文言管理番号
			shohin.setNichokakakubungankanribango("-1");
		}

		return resultList;
	}

	private String getPCsetumei(F120201Detail detail, List<String> yokoList, List<String> shitagaList,
			List<List<String>> naiyoList, List<PicList> picList, List<String> picUrlList, String spxxms1,
			String spxxny1, String spxxms2, String spxxms3, String spxxny2, String spxxny3, String spxxms4,
			String spxxny4, String spxxms5, String spxxny5, String spxxms6, String spxxny6, String shop) {

		String tenpobetu = "";

		StringBuffer sb2 = new StringBuffer();
		sb2.append(
				"<style type=\"text/css\"><!--.STYLE1 {font-family: \"MS PGothic\"}--></style><table align=\"center\">");

		for (int j = 0; j < picUrlList.size(); j++) {
			sb2.append("<tr><td><img src=\"" + picUrlList.get(j) + "\"");
			sb2.append(" width=\"650\"></img></td></tr>");
		}
		if (detail.isXfpycommonpic()) {
			sb2.append("<tr><td><img src=\"https://image.rakuten.co.jp/" + tenpobetu + "/cabinet/");
			sb2.append("nzyy/yy_size.jpg\"");
			sb2.append(" width=\"650\"></img></td></tr>");
			;
		}

		sb2.append(
				"<table width=\"600px\" align=\"center\" style=\"font-size:12px\" cellpadding=\"0\" cellspacing=\"0\">");
		sb2.append("<tr height=\"35px\">");
		sb2.append(
				"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;background-color:#B2B2B2\">商品名</td>");
		sb2.append(
				"<td style=\"border-top:1px solid #000;border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
		sb2.append(detail.getShohinmei_jp());

		if (!Utility.isEmptyString(detail.getYokomei_jp())) {

			String yokonaiyo = "";

			String[] yokoarr = yokoListToArr(detail);
			for (String str : yokoarr) {
				if (!Utility.isEmptyString(str)) {
					if (Utility.isEmptyString(yokonaiyo)) {
						yokonaiyo = str;
					} else {
						yokonaiyo += "," + str;
					}
				}
			}

			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(detail.getYokomei_jp());
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(replaceStr(yokonaiyo));
			sb2.append("</td></tr>");
		}
		if (!Utility.isEmptyString(detail.getShitagamei_jp())) {

			String shitaganaiyo = "";

			String[] shitagaarr = shitagaListToArr(detail);
			for (String str : shitagaarr) {
				if (!Utility.isEmptyString(str)) {
					if (Utility.isEmptyString(shitaganaiyo)) {
						shitaganaiyo = str;
					} else {
						shitaganaiyo = shitaganaiyo + "," + str;
					}
				}
			}
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(detail.getShitagamei_jp());
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(replaceStr(shitaganaiyo));
			sb2.append("</td></tr>");
		}
		if (!Utility.isEmptyString(detail.getSozainaiyo_jp())) {
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append("素材");
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(replaceStr(detail.getSozainaiyo_jp()));
			sb2.append("</td></tr>");
		}

		if (spxxms1 != null && !"".equals(spxxms1)) {
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(spxxms1);
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(spxxny1.replaceAll("\n", "<br>"));
			sb2.append("</td></tr>");
		}

		if (spxxms2 != null && !"".equals(spxxms2)) {
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(spxxms2);
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(spxxny2.replaceAll("\n", "<br>"));
			sb2.append("</td></tr>");
		}

		if (spxxms3 != null && !"".equals(spxxms3)) {
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(spxxms3);
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(spxxny3.replaceAll("\n", "<br>"));
			sb2.append("</td></tr>");
		}

		if (spxxms4 != null && !"".equals(spxxms4)) {
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(spxxms4);
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(spxxny4.replaceAll("\n", "<br>"));
			sb2.append("</td></tr>");
		}

		if (spxxms5 != null && !"".equals(spxxms5)) {
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(spxxms5);
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(spxxny5.replaceAll("\n", "<br>"));
			sb2.append("</td></tr>");
		}
		if (spxxms6 != null && !"".equals(spxxms6)) {
			sb2.append("<tr height=\"35px\">");
			sb2.append(
					"<td width=\"120px\" align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">");
			sb2.append(spxxms6);
			sb2.append("</td>");
			sb2.append("<td style=\"border-bottom:1px solid #000;border-right:1px solid #000;padding-left:5px\">");
			sb2.append(spxxny6.replaceAll("\n", "<br>"));
			sb2.append("</td></tr>");
		}
		sb2.append("</table>");
		if (yokoList.size() > 0) {
			sb2.append("<br>");
			sb2.append(
					"<table width=\"550px\" align=\"center\" style=\"font-size:12px\" cellpadding=\"0\" cellspacing=\"0\">");
			sb2.append("<tr height=\"35px\">");
			sb2.append("<td width=\"50px\" style=\"border:1px solid #000;\">&nbsp;</td>");
		}
		for (int k = 0; k < yokoList.size(); k++) {
			sb2.append(
					"<td width=\"93px\" align=\"center\" style=\"border-top:1px solid #000;border-bottom:1px solid #000;border-right:1px solid #000;background-color:#B2B2B2\">");
			sb2.append(yokoList.get(k));
			sb2.append("</td>");
		}
		if (yokoList.size() > 0) {
			sb2.append("</tr>");
		}
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
		if (detail.isPycommonpic() || detail.isXfpycommonpic()) {
			sb2.append("<tr><td><img src=\"https://image.rakuten.co.jp/" + tenpobetu + "/cabinet/");
			sb2.append("123mart/nzpy/common2.jpg\"");
			sb2.append(" width=\"650\"></img></td></tr>");
		}
		if (detail.isSizepic()) {
			// 衣服尺寸
			sb2.append("<table><tr><td><img width=\"650px\" src=\"https://www.rakuten.ne.jp/gold/" + tenpobetu
					+ "/images/commodity/size-1.jpg\"></td></tr></table>");
		}
		if (detail.isHogofirumu()) {
			sb2.append("<table><tr><td><img width=\"650px\" src=\"https://www.rakuten.ne.jp/gold/" + tenpobetu
					+ "/images/review.jpg\"></td></tr></table>");
		}

		if (detail.isNouki()) {
			sb2.append("<table align=\"center\"><tr><td><img width=\"650px\" src=\"https://image.rakuten.co.jp/" + shop
					+ "/cabinet/others/nouki.jpg\"></td></tr></table>");
		}
		return sb2.toString();
	}

	private String[] yokoListToArr(F120201Detail detail) {
		String[] resultList = new String[] { detail.getYokonaiyo1_jp(), detail.getYokonaiyo2_jp(),
				detail.getYokonaiyo3_jp(), detail.getYokonaiyo4_jp(), detail.getYokonaiyo5_jp(),
				detail.getYokonaiyo6_jp(), detail.getYokonaiyo7_jp(), detail.getYokonaiyo8_jp(),
				detail.getYokonaiyo9_jp(), detail.getYokonaiyo10_jp(), detail.getYokonaiyo11_jp(),
				detail.getYokonaiyo12_jp(), detail.getYokonaiyo13_jp(), detail.getYokonaiyo14_jp(),
				detail.getYokonaiyo15_jp(), detail.getYokonaiyo16_jp(), detail.getYokonaiyo17_jp(),
				detail.getYokonaiyo18_jp(), detail.getYokonaiyo19_jp(), detail.getYokonaiyo20_jp() };
		return resultList;
	}

	private String[] yokoSubidListToArr(F120201Detail detail) {
		String[] resultList = new String[] { detail.getYokonaiyo1_subid(), detail.getYokonaiyo2_subid(),
				detail.getYokonaiyo3_subid(), detail.getYokonaiyo4_subid(), detail.getYokonaiyo5_subid(),
				detail.getYokonaiyo6_subid(), detail.getYokonaiyo7_subid(), detail.getYokonaiyo8_subid(),
				detail.getYokonaiyo9_subid(), detail.getYokonaiyo10_subid(), detail.getYokonaiyo11_subid(),
				detail.getYokonaiyo12_subid(), detail.getYokonaiyo13_subid(), detail.getYokonaiyo14_subid(),
				detail.getYokonaiyo15_subid(), detail.getYokonaiyo16_subid(), detail.getYokonaiyo17_subid(),
				detail.getYokonaiyo18_subid(), detail.getYokonaiyo19_subid(), detail.getYokonaiyo20_subid() };
		return resultList;
	}

	private String[] shitagaListToArr(F120201Detail detail) {
		String[] resultList = new String[] { detail.getShitaganaiyo1_jp(), detail.getShitaganaiyo2_jp(),
				detail.getShitaganaiyo3_jp(), detail.getShitaganaiyo4_jp(), detail.getShitaganaiyo5_jp(),
				detail.getShitaganaiyo6_jp(), detail.getShitaganaiyo7_jp(), detail.getShitaganaiyo8_jp(),
				detail.getShitaganaiyo9_jp(), detail.getShitaganaiyo10_jp(), detail.getShitaganaiyo11_jp(),
				detail.getShitaganaiyo12_jp(), detail.getShitaganaiyo13_jp(), detail.getShitaganaiyo14_jp(),
				detail.getShitaganaiyo15_jp(), detail.getShitaganaiyo16_jp(), detail.getShitaganaiyo17_jp(),
				detail.getShitaganaiyo18_jp(), detail.getShitaganaiyo19_jp(), detail.getShitaganaiyo20_jp() };
		return resultList;
	}

	private String[] shitagaSubidListToArr(F120201Detail detail) {
		String[] resultList = new String[] { detail.getShitaganaiyo1_subid(), detail.getShitaganaiyo2_subid(),
				detail.getShitaganaiyo3_subid(), detail.getShitaganaiyo4_subid(), detail.getShitaganaiyo5_subid(),
				detail.getShitaganaiyo6_subid(), detail.getShitaganaiyo7_subid(), detail.getShitaganaiyo8_subid(),
				detail.getShitaganaiyo9_subid(), detail.getShitaganaiyo10_subid(), detail.getShitaganaiyo11_subid(),
				detail.getShitaganaiyo12_subid(), detail.getShitaganaiyo13_subid(), detail.getShitaganaiyo14_subid(),
				detail.getShitaganaiyo15_subid(), detail.getShitaganaiyo16_subid(), detail.getShitaganaiyo17_subid(),
				detail.getShitaganaiyo18_subid(), detail.getShitaganaiyo19_subid(), detail.getShitaganaiyo20_subid() };
		return resultList;
	}

	private List<String> sizeyokoListToArr(F120201Detail detail) {
		List<String> resultList = new ArrayList<String>();
		if (!Utility.isEmptyString(detail.getSizeyoko1_jp())) {
			resultList.add(detail.getSizeyoko1_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko2_jp())) {
			resultList.add(detail.getSizeyoko2_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko3_jp())) {
			resultList.add(detail.getSizeyoko3_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko4_jp())) {
			resultList.add(detail.getSizeyoko4_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko5_jp())) {
			resultList.add(detail.getSizeyoko5_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko6_jp())) {
			resultList.add(detail.getSizeyoko6_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko7_jp())) {
			resultList.add(detail.getSizeyoko7_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko8_jp())) {
			resultList.add(detail.getSizeyoko8_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko9_jp())) {
			resultList.add(detail.getSizeyoko9_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeyoko10_jp())) {
			resultList.add(detail.getSizeyoko10_jp());
		}
		return resultList;
	}

	private List<String> sizeshitagaListToArr(F120201Detail detail) {
		List<String> resultList = new ArrayList<String>();
		if (!Utility.isEmptyString(detail.getSizeshitaga1_jp())) {
			resultList.add(detail.getSizeshitaga1_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga2_jp())) {
			resultList.add(detail.getSizeshitaga2_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga3_jp())) {
			resultList.add(detail.getSizeshitaga3_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga4_jp())) {
			resultList.add(detail.getSizeshitaga4_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga5_jp())) {
			resultList.add(detail.getSizeshitaga5_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga6_jp())) {
			resultList.add(detail.getSizeshitaga6_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga7_jp())) {
			resultList.add(detail.getSizeshitaga7_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga8_jp())) {
			resultList.add(detail.getSizeshitaga8_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga9_jp())) {
			resultList.add(detail.getSizeshitaga9_jp());
		}
		if (!Utility.isEmptyString(detail.getSizeshitaga10_jp())) {
			resultList.add(detail.getSizeshitaga10_jp());
		}
		return resultList;
	}

	private List<List<String>> naiyoListToArr(F120201Detail detail, int yokoCount, int shitagaCount) {
		List<List<String>> resultList = new ArrayList<List<String>>();
		List<String> List1 = new ArrayList<>();
		List<String> List2 = new ArrayList<>();
		List<String> List3 = new ArrayList<>();
		List<String> List4 = new ArrayList<>();
		List<String> List5 = new ArrayList<>();
		List<String> List6 = new ArrayList<>();
		List<String> List7 = new ArrayList<>();
		List<String> List8 = new ArrayList<>();
		List<String> List9 = new ArrayList<>();
		List<String> List10 = new ArrayList<>();

		String[] arr1 = new String[] { detail.getSize11(), detail.getSize12(), detail.getSize13(), detail.getSize14(),
				detail.getSize15(), detail.getSize16(), detail.getSize17(), detail.getSize18(), detail.getSize19(),
				detail.getSize110() };
		String[] arr2 = new String[] { detail.getSize21(), detail.getSize22(), detail.getSize23(), detail.getSize24(),
				detail.getSize25(), detail.getSize26(), detail.getSize27(), detail.getSize28(), detail.getSize29(),
				detail.getSize210() };
		String[] arr3 = new String[] { detail.getSize31(), detail.getSize32(), detail.getSize33(), detail.getSize34(),
				detail.getSize35(), detail.getSize36(), detail.getSize37(), detail.getSize38(), detail.getSize39(),
				detail.getSize310() };
		String[] arr4 = new String[] { detail.getSize41(), detail.getSize42(), detail.getSize43(), detail.getSize44(),
				detail.getSize45(), detail.getSize46(), detail.getSize47(), detail.getSize48(), detail.getSize49(),
				detail.getSize410() };
		String[] arr5 = new String[] { detail.getSize51(), detail.getSize52(), detail.getSize53(), detail.getSize54(),
				detail.getSize55(), detail.getSize56(), detail.getSize57(), detail.getSize58(), detail.getSize59(),
				detail.getSize510() };
		String[] arr6 = new String[] { detail.getSize61(), detail.getSize62(), detail.getSize63(), detail.getSize64(),
				detail.getSize65(), detail.getSize66(), detail.getSize67(), detail.getSize68(), detail.getSize69(),
				detail.getSize610() };
		String[] arr7 = new String[] { detail.getSize71(), detail.getSize72(), detail.getSize73(), detail.getSize74(),
				detail.getSize75(), detail.getSize76(), detail.getSize77(), detail.getSize78(), detail.getSize79(),
				detail.getSize710() };
		String[] arr8 = new String[] { detail.getSize81(), detail.getSize82(), detail.getSize83(), detail.getSize84(),
				detail.getSize85(), detail.getSize86(), detail.getSize87(), detail.getSize88(), detail.getSize89(),
				detail.getSize810() };
		String[] arr9 = new String[] { detail.getSize91(), detail.getSize92(), detail.getSize93(), detail.getSize94(),
				detail.getSize95(), detail.getSize96(), detail.getSize97(), detail.getSize98(), detail.getSize99(),
				detail.getSize910() };
		String[] arr10 = new String[] { detail.getSize101(), detail.getSize102(), detail.getSize103(),
				detail.getSize104(), detail.getSize105(), detail.getSize106(), detail.getSize107(), detail.getSize108(),
				detail.getSize109(), detail.getSize1010() };

		for (int i = 0; i < yokoCount; i++) {
			List1.add(arr1[i]);
			List2.add(arr2[i]);
			List3.add(arr3[i]);
			List4.add(arr4[i]);
			List5.add(arr5[i]);
			List6.add(arr6[i]);
			List7.add(arr7[i]);
			List8.add(arr8[i]);
			List9.add(arr9[i]);
			List10.add(arr10[i]);
		}

		if (1 <= shitagaCount) {
			resultList.add(List1);
		}
		if (2 <= shitagaCount) {
			resultList.add(List2);
		}
		if (3 <= shitagaCount) {
			resultList.add(List3);
		}
		if (4 <= shitagaCount) {
			resultList.add(List4);
		}
		if (5 <= shitagaCount) {
			resultList.add(List5);
		}
		if (6 <= shitagaCount) {
			resultList.add(List6);
		}
		if (7 <= shitagaCount) {
			resultList.add(List7);
		}
		if (8 <= shitagaCount) {
			resultList.add(List8);
		}
		if (9 <= shitagaCount) {
			resultList.add(List9);
		}
		if (10 <= shitagaCount) {
			resultList.add(List10);
		}

		return resultList;
	}

	private String getMobairuSetumei(F120201Detail detail, List<String> yokoList, List<String> shitagaList,
			List<List<String>> naiyoList) {
		StringBuffer mobairusetumeibun = new StringBuffer();
		mobairusetumeibun.append("商品名<br>");
		mobairusetumeibun.append(detail.getShohinmei_jp() + "<br>");
		if (!Utility.isEmptyString(detail.getYokomei_jp())) {
			mobairusetumeibun.append(detail.getYokomei_jp() + "<br>");
			String yokonaiyo = "";

			String[] yokoarr = yokoListToArr(detail);
			for (String str : yokoarr) {
				if (!Utility.isEmptyString(str)) {
					if (Utility.isEmptyString(yokonaiyo)) {
						yokonaiyo = str;
					} else {
						yokonaiyo += "," + str;
					}
				}
			}

			mobairusetumeibun.append(replaceStr(yokonaiyo) + "<br><br>");
		}
		if (!Utility.isEmptyString(detail.getShitagamei_jp())) {
			mobairusetumeibun.append(detail.getShitagamei_jp() + "<br>");
			String shitaganaiyo = "";

			String[] shitagaarr = shitagaListToArr(detail);
			for (String str : shitagaarr) {
				if (!Utility.isEmptyString(str)) {
					if (Utility.isEmptyString(shitaganaiyo)) {
						shitaganaiyo = str;
					} else {
						shitaganaiyo = shitaganaiyo + "," + str;
					}
				}
			}

			mobairusetumeibun.append(replaceStr(shitaganaiyo) + "<br><br>");
		}
		if (!Utility.isEmptyString(detail.getSozainaiyo_jp())) {
			mobairusetumeibun.append("素材" + "<br>");
			mobairusetumeibun.append(replaceStr(detail.getSozainaiyo_jp()) + "<br><br>");
		}

		if (yokoList.size() > 0) {
			mobairusetumeibun.append("サイズ:<br>");
			mobairusetumeibun.append("&nbsp;&nbsp;");
			for (int k = 0; k < yokoList.size(); k++) {
				mobairusetumeibun.append(yokoList.get(k));
				mobairusetumeibun.append("&nbsp;");
			}
			mobairusetumeibun.append("<br>");

			for (int k = 0; k < shitagaList.size(); k++) {
				mobairusetumeibun.append(shitagaList.get(k));
				mobairusetumeibun.append("&nbsp;");
				for (int j = 0; j < yokoList.size(); j++) {
					mobairusetumeibun.append(naiyoList.get(k).get(j));
					mobairusetumeibun.append("&nbsp;");
				}
				mobairusetumeibun.append("<br>");
			}
		}
		return mobairusetumeibun.toString();
	}

	private String getSmhsetumsei(F120201Detail detail, List<String> yokoList, List<String> shitagaList,
			List<List<String>> naiyoList, List<PicList> picList, List<String> picUrlList, String spxxms1,
			String spxxny1, Object spxxms2, Object spxxms3, String spxxny2, String spxxny3, Object spxxms4,
			String spxxny4, Object spxxms5, String spxxny5, String spxxms6, String spxxny6) {
		StringBuffer sphkyachikopy = new StringBuffer();
		List<String> pic = new ArrayList<String>();
if(!Utility.isEmptyList(picList)) {
		for (int i = 0; i < picList.size(); i++) {
			if (picList.get(i).isChecked()) {
				pic.add(picUrlList.get(i));
			}
		}
		sphkyachikopy.append("<table><tr><td><img width=\"310\" src=\"" + pic.get(0)
				+ "\"/></td></tr><tr><td><img width=\"310\" src=\"" + pic.get(1)
				+ "\"/></td></tr><tr><td><img width=\"310\" src=\"" + pic.get(2) + "\"/></td></tr></table>");
		sphkyachikopy.append("<br>商品名<br>");
		sphkyachikopy.append(detail.getShohinmei_jp() + "<br>");}
		if (!Utility.isEmptyString(detail.getYokomei_jp())) {
			sphkyachikopy.append(detail.getYokomei_jp() + "<br>");
			String yokonaiyo = "";

			String[] yokoarr = yokoListToArr(detail);
			for (String str : yokoarr) {
				if (!Utility.isEmptyString(str)) {
					if (Utility.isEmptyString(yokonaiyo)) {
						yokonaiyo = str;
					} else {
						yokonaiyo += "," + str;
					}
				}
			}

			sphkyachikopy.append(replaceStr(yokonaiyo) + "<br><br>");
		}
		if (!Utility.isEmptyString(detail.getShitagamei_jp())) {
			sphkyachikopy.append(detail.getShitagamei_jp() + "<br>");
			String shitaganaiyo = "";

			String[] shitagaarr = shitagaListToArr(detail);
			for (String str : shitagaarr) {
				if (!Utility.isEmptyString(str)) {
					if (Utility.isEmptyString(shitaganaiyo)) {
						shitaganaiyo = str;
					} else {
						shitaganaiyo = shitaganaiyo + "," + str;
					}
				}
			}

			sphkyachikopy.append(replaceStr(shitaganaiyo) + "<br><br>");
		}
		if (!Utility.isEmptyString(detail.getSozainaiyo_jp())) {
			sphkyachikopy.append("素材" + "<br>");
			sphkyachikopy.append(replaceStr(detail.getSozainaiyo_jp()) + "<br><br>");
		}

		if (spxxms1 != null && !"".equals(spxxms1)) {
			sphkyachikopy.append(spxxms1 + "<br>");
			sphkyachikopy.append(replaceStr(spxxny1) + "<br><br>");
		}
		if (spxxms2 != null && !"".equals(spxxms2)) {
			sphkyachikopy.append(spxxms2 + "<br>");
			sphkyachikopy.append(replaceStr(spxxny2) + "<br><br>");
		}
		if (spxxms3 != null && !"".equals(spxxms3)) {
			sphkyachikopy.append(spxxms3 + "<br>");
			sphkyachikopy.append(replaceStr(spxxny3) + "<br><br>");
		}
		if (spxxms4 != null && !"".equals(spxxms4)) {
			sphkyachikopy.append(spxxms4 + "<br>");
			sphkyachikopy.append(replaceStr(spxxny4) + "<br><br>");
		}
		if (spxxms5 != null && !"".equals(spxxms5)) {
			sphkyachikopy.append(spxxms5 + "<br>");
			sphkyachikopy.append(replaceStr(spxxny5) + "<br><br>");
		}
		if (spxxms6 != null && !"".equals(spxxms6)) {
			sphkyachikopy.append(spxxms6 + "<br>");
			sphkyachikopy.append(replaceStr(spxxny6) + "<br><br>");
		}

		if (yokoList.size() > 0) {
			sphkyachikopy.append("サイズ:<br>");
			sphkyachikopy.append("<table border=\"1\" width=\"320px\">");
			sphkyachikopy.append("<tr>");
			sphkyachikopy.append("<td align=\"center\" width=\"15%\">&nbsp;</td>");
		}
		for (int k = 0; k < yokoList.size(); k++) {
			sphkyachikopy.append("<td align=\"center\">");
			sphkyachikopy.append(yokoList.get(k));
			sphkyachikopy.append("</td>");
		}
		if (yokoList.size() > 0) {
			sphkyachikopy.append("</tr>");
		}

		for (int k = 0; k < shitagaList.size(); k++) {
			sphkyachikopy.append("<tr>");
			sphkyachikopy.append("<td align=\"center\" width=\"15%\">");
			sphkyachikopy.append(shitagaList.get(k));
			sphkyachikopy.append("</td>");
			for (int j = 0; j < yokoList.size(); j++) {
				sphkyachikopy.append("<td align=\"center\">");
				sphkyachikopy.append(naiyoList.get(k).get(j));
				sphkyachikopy.append("</td>");
			}
			sphkyachikopy.append("</tr>");
		}
		if (yokoList.size() > 0) {
			sphkyachikopy.append("</table>");
		}
		return sphkyachikopy.toString();
	}

	private List<String> getPicList(F120201Detail detail, String shop, String site) {
		List<String> resultList = new ArrayList<>();
		List<PicList> picLists = detail.getPicList();
		if (!Utility.isEmptyList(picLists)) {
			for (PicList pic : picLists) {
				String picurl = pic.getPicurl();
				picurl = picurl.substring(picurl.lastIndexOf("/"));
				picurl = "https://image.rakuten.co.jp/" + shop + "/cabinet/" + detail.getPicdir() + picurl;
				resultList.add(picurl);
			}
		}
		return resultList;

	}

	private String replaceStr(String str) {
		return str.replaceAll("\n", "<br>");
	}

	public List<ShohinkategoriBean> converCatToCatBean(List<F120201Detail> detailList) throws Exception {
		List<ShohinkategoriBean> resultList = new ArrayList<ShohinkategoriBean>();
		Connection conn = JdbcConnection.getConnection();
		String sql = "SELECT path1,path2,path3,path4,path5,path6,path7,path8,path9,path10 from  directid_tbl where directid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {

			for (F120201Detail detail : detailList) {

				if (!Utility.isEmptyString(detail.getKategori1())) {
					ShohinkategoriBean cat = new ShohinkategoriBean();
					resultList.add(cat);

					// コントロールカラム
					cat.setKontororukaramu("u");
					// 商品管理番号（商品URL）
					cat.setShohinkanribango(detail.getShohinbango());
					// 商品名
					cat.setShohinmei(detail.getShohinmei_jp());
					// 表示先カテゴリ
					cat.setHuojisakikategori(detail.getKategori1());
					// 優先度
					cat.setYusendo("2");
					// URL
					cat.setUrl("");
					// 1ページ複数形式
					cat.setIchipejifukusukeishiki("");
				}
				if (!Utility.isEmptyString(detail.getKategori2())) {
					ShohinkategoriBean cat = new ShohinkategoriBean();
					resultList.add(cat);

					// コントロールカラム
					cat.setKontororukaramu("u");
					// 商品管理番号（商品URL）
					cat.setShohinkanribango(detail.getShohinbango());
					// 商品名
					cat.setShohinmei(detail.getShohinmei_jp());
					// 表示先カテゴリ
					cat.setHuojisakikategori(detail.getKategori2());
					// 優先度
					cat.setYusendo("2");
					// URL
					cat.setUrl("");
					// 1ページ複数形式
					cat.setIchipejifukusukeishiki("");
				}
				if (!Utility.isEmptyString(detail.getKategori3())) {
					ShohinkategoriBean cat = new ShohinkategoriBean();
					resultList.add(cat);

					// コントロールカラム
					cat.setKontororukaramu("u");
					// 商品管理番号（商品URL）
					cat.setShohinkanribango(detail.getShohinbango());
					// 商品名
					cat.setShohinmei(detail.getShohinmei_jp());
					// 表示先カテゴリ
					cat.setHuojisakikategori(detail.getKategori3());
					// 優先度
					cat.setYusendo("2");
					// URL
					cat.setUrl("");
					// 1ページ複数形式
					cat.setIchipejifukusukeishiki("");
				}
				if (!Utility.isEmptyString(detail.getKategori4())) {
					ShohinkategoriBean cat = new ShohinkategoriBean();
					resultList.add(cat);

					// コントロールカラム
					cat.setKontororukaramu("u");
					// 商品管理番号（商品URL）
					cat.setShohinkanribango(detail.getShohinbango());
					// 商品名
					cat.setShohinmei(detail.getShohinmei_jp());
					// 表示先カテゴリ
					cat.setHuojisakikategori(detail.getKategori4());
					// 優先度
					cat.setYusendo("2");
					// URL
					cat.setUrl("");
					// 1ページ複数形式
					cat.setIchipejifukusukeishiki("");
				}

				String directid = detail.getDirekutoriid();

				ps.setString(1, directid);
				rs = ps.executeQuery();
				String path = "";
				while (rs.next()) {
					if (!Utility.isEmptyString(rs.getString("path1")) && !rs.getString("path1").contains("その他")) {
						path = rs.getString("path1");
					}
					if (!Utility.isEmptyString(rs.getString("path2")) && !rs.getString("path2").contains("その他")) {
						path = path + "\\" + rs.getString("path2");
					}
					if (!Utility.isEmptyString(rs.getString("path3")) && !rs.getString("path3").contains("その他")) {
						path = path + "\\" + rs.getString("path3");
					}
					if (!Utility.isEmptyString(rs.getString("path4")) && !rs.getString("path4").contains("その他")) {
						path = path + "\\" + rs.getString("path4");
					}
					if (!Utility.isEmptyString(rs.getString("path5")) && !rs.getString("path5").contains("その他")) {
						path = path + "\\" + rs.getString("path5");
					}
					if (!Utility.isEmptyString(rs.getString("path6")) && !rs.getString("path6").contains("その他")) {
						path = path + "\\" + rs.getString("path6");
					}
					if (!Utility.isEmptyString(rs.getString("path7")) && !rs.getString("path7").contains("その他")) {
						path = path + "\\" + rs.getString("path7");
					}
					if (!Utility.isEmptyString(rs.getString("path8")) && !rs.getString("path8").contains("その他")) {
						path = path + "\\" + rs.getString("path8");
					}
					if (!Utility.isEmptyString(rs.getString("path9")) && !rs.getString("path9").contains("その他")) {
						path = path + "\\" + rs.getString("path9");
					}
					if (!Utility.isEmptyString(rs.getString("path10")) && !rs.getString("path10").contains("その他")) {
						path = path + "\\" + rs.getString("path10");
					}

				}

				ShohinkategoriBean cat = new ShohinkategoriBean();
				resultList.add(cat);

				// コントロールカラム
				cat.setKontororukaramu("u");
				// 商品管理番号（商品URL）
				cat.setShohinkanribango(detail.getShohinbango());
				// 商品名
				cat.setShohinmei(detail.getShohinmei_jp());
				// 表示先カテゴリ
				cat.setHuojisakikategori(path);
				// 優先度
				cat.setYusendo("2");
				// URL
				cat.setUrl("");
				// 1ページ複数形式
				cat.setIchipejifukusukeishiki("");

			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return resultList;
	}
}
