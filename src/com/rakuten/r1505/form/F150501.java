package com.rakuten.r1505.form;

import java.io.Serializable;
import java.util.List;

public class F150501 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String createDay = null;
	private String startDay = null;
	private String endDay = null;
	private String hiseikyusha = null;
	private String seikyukingaku = null;
	private String seikyusha1 ="株式会社野口産業";
	private String seikyusha2 = "170-0012";
	private String seikyusha3 = "東京都豊島区巣鴨1-14-12";
	private String seikyusha4 = "03-3943-4488";
	private String seikyusha5 = "03-3943-5088";
	private String seikyusha6 = "三浦龍治";
	private String merubintanka = "180";
	private String merubinkosu = null;
	private String merubinkingaku = null;
	private String takyubintanka = "540";
	private String takyubinkosu = null;
	private String takyubinkingaku = null;
	private String tesuryotanka = null;
	private String tesuryokosu = null;
	private String tesuryokingaku = null;
	private String daibikitanka = null;
	private String daibikikingaku = null;
	private String daibikikosu = null;
	private String himoku1 = null;
	private String tanka1 = null;
	private String kosu1 = null;
	private String kingaku1 = null;
	private String himoku2 = null;
	private String tanka2 = null;
	private String kosu2 = null;
	private String kingaku2 = null;
	private String himoku3 = null;
	private String tanka3 = null;
	private String kosu3 = null;
	private String kingaku3 = null;
	private String himoku4 = null;
	private String tanka4 = null;
	private String kosu4 = null;
	private String kingaku4 = null;
	private List<MeisaiList> meisaiList = null;
	private String kokei = null;
	private String gokei = null;
	private String hozozumiFlg = null;
	private String jishu = "0.7";
	private boolean tesuryoChk = false;
	private boolean daibikiChk = false;

	public boolean isTesuryoChk() {
		return tesuryoChk;
	}

	public void setTesuryoChk(boolean tesuryoChk) {
		this.tesuryoChk = tesuryoChk;
	}

	public boolean isDaibikiChk() {
		return daibikiChk;
	}

	public void setDaibikiChk(boolean daibikiChk) {
		this.daibikiChk = daibikiChk;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getHiseikyusha() {
		return hiseikyusha;
	}

	public void setHiseikyusha(String hiseikyusha) {
		this.hiseikyusha = hiseikyusha;
	}

	public String getSeikyukingaku() {
		return seikyukingaku;
	}

	public void setSeikyukingaku(String seikyukingaku) {
		this.seikyukingaku = seikyukingaku;
	}

	public String getSeikyusha1() {
		return seikyusha1;
	}

	public void setSeikyusha1(String seikyusha1) {
		this.seikyusha1 = seikyusha1;
	}

	public String getSeikyusha2() {
		return seikyusha2;
	}

	public void setSeikyusha2(String seikyusha2) {
		this.seikyusha2 = seikyusha2;
	}

	public String getSeikyusha3() {
		return seikyusha3;
	}

	public void setSeikyusha3(String seikyusha3) {
		this.seikyusha3 = seikyusha3;
	}

	public String getSeikyusha4() {
		return seikyusha4;
	}

	public void setSeikyusha4(String seikyusha4) {
		this.seikyusha4 = seikyusha4;
	}

	public String getSeikyusha5() {
		return seikyusha5;
	}

	public void setSeikyusha5(String seikyusha5) {
		this.seikyusha5 = seikyusha5;
	}

	public String getSeikyusha6() {
		return seikyusha6;
	}

	public void setSeikyusha6(String seikyusha6) {
		this.seikyusha6 = seikyusha6;
	}

	public String getMerubintanka() {
		return merubintanka;
	}

	public void setMerubintanka(String merubintanka) {
		this.merubintanka = merubintanka;
	}

	public String getMerubinkosu() {
		return merubinkosu;
	}

	public void setMerubinkosu(String merubinkosu) {
		this.merubinkosu = merubinkosu;
	}

	public String getMerubinkingaku() {
		return merubinkingaku;
	}

	public void setMerubinkingaku(String merubinkingaku) {
		this.merubinkingaku = merubinkingaku;
	}

	public String getTakyubintanka() {
		return takyubintanka;
	}

	public void setTakyubintanka(String takyubintanka) {
		this.takyubintanka = takyubintanka;
	}

	public String getTakyubinkosu() {
		return takyubinkosu;
	}

	public void setTakyubinkosu(String takyubinkosu) {
		this.takyubinkosu = takyubinkosu;
	}

	public String getTakyubinkingaku() {
		return takyubinkingaku;
	}

	public void setTakyubinkingaku(String takyubinkingaku) {
		this.takyubinkingaku = takyubinkingaku;
	}

	public String getTesuryotanka() {
		return tesuryotanka;
	}

	public void setTesuryotanka(String tesuryotanka) {
		this.tesuryotanka = tesuryotanka;
	}

	public String getTesuryokosu() {
		return tesuryokosu;
	}

	public void setTesuryokosu(String tesuryokosu) {
		this.tesuryokosu = tesuryokosu;
	}

	public String getTesuryokingaku() {
		return tesuryokingaku;
	}

	public void setTesuryokingaku(String tesuryokingaku) {
		this.tesuryokingaku = tesuryokingaku;
	}

	public String getDaibikitanka() {
		return daibikitanka;
	}

	public void setDaibikitanka(String daibikitanka) {
		this.daibikitanka = daibikitanka;
	}

	public String getDaibikikingaku() {
		return daibikikingaku;
	}

	public void setDaibikikingaku(String daibikikingaku) {
		this.daibikikingaku = daibikikingaku;
	}

	public String getDaibikikosu() {
		return daibikikosu;
	}

	public void setDaibikikosu(String daibikikosu) {
		this.daibikikosu = daibikikosu;
	}

	public String getHimoku1() {
		return himoku1;
	}

	public void setHimoku1(String himoku1) {
		this.himoku1 = himoku1;
	}

	public String getTanka1() {
		return tanka1;
	}

	public void setTanka1(String tanka1) {
		this.tanka1 = tanka1;
	}

	public String getKosu1() {
		return kosu1;
	}

	public void setKosu1(String kosu1) {
		this.kosu1 = kosu1;
	}

	public String getKingaku1() {
		return kingaku1;
	}

	public void setKingaku1(String kingaku1) {
		this.kingaku1 = kingaku1;
	}

	public String getHimoku2() {
		return himoku2;
	}

	public void setHimoku2(String himoku2) {
		this.himoku2 = himoku2;
	}

	public String getTanka2() {
		return tanka2;
	}

	public void setTanka2(String tanka2) {
		this.tanka2 = tanka2;
	}

	public String getKosu2() {
		return kosu2;
	}

	public void setKosu2(String kosu2) {
		this.kosu2 = kosu2;
	}

	public String getKingaku2() {
		return kingaku2;
	}

	public void setKingaku2(String kingaku2) {
		this.kingaku2 = kingaku2;
	}

	public String getHimoku3() {
		return himoku3;
	}

	public void setHimoku3(String himoku3) {
		this.himoku3 = himoku3;
	}

	public String getTanka3() {
		return tanka3;
	}

	public void setTanka3(String tanka3) {
		this.tanka3 = tanka3;
	}

	public String getKosu3() {
		return kosu3;
	}

	public void setKosu3(String kosu3) {
		this.kosu3 = kosu3;
	}

	public String getKingaku3() {
		return kingaku3;
	}

	public void setKingaku3(String kingaku3) {
		this.kingaku3 = kingaku3;
	}

	public String getHimoku4() {
		return himoku4;
	}

	public void setHimoku4(String himoku4) {
		this.himoku4 = himoku4;
	}

	public String getTanka4() {
		return tanka4;
	}

	public void setTanka4(String tanka4) {
		this.tanka4 = tanka4;
	}

	public String getKosu4() {
		return kosu4;
	}

	public void setKosu4(String kosu4) {
		this.kosu4 = kosu4;
	}

	public String getKingaku4() {
		return kingaku4;
	}

	public void setKingaku4(String kingaku4) {
		this.kingaku4 = kingaku4;
	}

	public List<MeisaiList> getMeisaiList() {
		return meisaiList;
	}

	public void setMeisaiList(List<MeisaiList> meisaiList) {
		this.meisaiList = meisaiList;
	}

	public String getKokei() {
		return kokei;
	}

	public void setKokei(String kokei) {
		this.kokei = kokei;
	}

	public String getGokei() {
		return gokei;
	}

	public void setGokei(String gokei) {
		this.gokei = gokei;
	}

	public String getHozozumiFlg() {
		return hozozumiFlg;
	}

	public void setHozozumiFlg(String hozozumiFlg) {
		this.hozozumiFlg = hozozumiFlg;
	}

	public String getJishu() {
		return jishu;
	}

	public void setJishu(String jishu) {
		this.jishu = jishu;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}

}
