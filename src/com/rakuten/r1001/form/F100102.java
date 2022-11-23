package com.rakuten.r1001.form;

import java.io.Serializable;
import java.util.List;

import com.rakuten.common.bean.HenkinListBean;

public class F100102 implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ShohinList> shohinList = null;
	private String sonotatesuryo = "0";
	private String sonotawaribikigaku = "0";
	private String hasoyoteibi = "0";
	private String chumonsts1 = null;
	private String chumonsts2 = null;
	private String chumonsts3 = null;
	private String chumonsts4 = null;
	private String chumonsts5 = null;
	private String chumonsts6 = null;
	private String dokonFlg = null;
	private String okurisakuchuiFlg = null;
	private String mobileMailFlg = null;
	private String asurakuFlg = null;
	private String juchubango = null;
	private String chumonnichiji = null;
	private String dingdandaoruri = null;
	private String chumonshanamae = null;
	private String chumonshameruadoresu = null;
	private String chumonshatanjoubi = null;
	private String chumonshajusho = null;
	private String chumonshadenwabango = null;
	private String oshiharaihoho = null;
	private String haisohoho = null;
	private String hassobi = null;
	private String otodokebishitei = null;
	private String otodokejikantai1 = null;
	private String otodokejikantai2 = null;
	private String otodokejikantai3 = null;
	private String biko = null;
	private String bikorakuten = null;
	private String okyakusamahenomeseji = null;
	private String sofusakijoho = null;
	private String onimotsudenpyobango = null;
	private String haisokaisha = null;
	private String noshi = null;
	private String gokeishouhin = null;
	private String gokeizei = null;
	private String gokeisouryou = null;
	private String gokeidaibikiryou = null;
	private String pointriyou = null;
	private String sonotariyogaku = null;
	private String seikyukingaku = null;
	private String resultcount = null;
	private String tenpobetsu = null;
	private String futuhasokanoFlg = "1";
	private String hasouzumiFlg = "0";
	private String toiawasebango_futsuhasou = null;
	private String haisokaisha_futsuhasou = null;
	private String hasonichiji_hasozumi = null;
	private String haisohoho_hasozumi = null;
	private String haisokaisha_hasozumi = null;
	private String toiawasebango_hasozumi = null;
	private String haisohoho_futsuhasou = null;
	private String bunnohasoukaFlg = "0";
	private String toiawasebango_bunnou = null;
	private String haisohoho_bunnou = null;
	private String haisokaisha_bunnou = null;
	private String bunnotorikeshikaFlg = "1";
	private String tuikahasokaFlg = "1";
	private String tuikaariFlg = "0";

	private String[] henpinriyuTenpo = null;
	private String henpinriyuTenpoSonota = null;
	private String[] henpinriyuOkyaku = null;
	private String henpinriyuOkyakuSonota = null;
	private String henpinsoryofutanshokihasou = null;
	private String henpinsoryofutanuketori = null;
	private String henpinhenkinhituyoFlg = null;
	private String henpinhenkinkigaku = null;
	private String henpinbiko = null;

	private String henpinariFlg = "0";
	private String henkinariFlg = "0";

	private String shoribango_henpinhaso = null;
	private String toiawasebango_henpinhaso = null;
	private String haisohoho_henpinhaso = null;
	private String haisokaisha_henpinhaso = null;

	private String shoribango_henpinshusei = null;
	private String[] henpinriyuTenpo_henpinshusei = null;
	private String henpinriyuTenpoSonota_henpinshusei = null;
	private String[] henpinriyuOkyaku_henpinshusei = null;
	private String henpinriyuOkyakuSonota_henpinshusei = null;
	private String henpinsoryofutanshokihasou_henpinshusei = null;
	private String henpinsoryofutanuketori_henpinshusei = null;
	private String henpinhenkinhituyoFlg_henpinshusei = null;
	private String henpinhenkinkigaku_henpinshusei = null;
	private String henpinbiko_henpinshusei = null;

	private String[] tuikariyu = null;
	private String tuikasoryofutan = null;
	private String tuikariyuSonota = null;
	private String tuikabiko = null;
	private String jsonArr = null;

	private String tuikashoribango_tuikashusei = null;
	private String tuikariyu_tuikashusei = null;
	private String tuikariyusonota_tuikashusei = null;
	private String hasosoryofutan_tuikashusei = null;
	private String biko_tuikashusei = null;

	private String tuikashoribango_shuseihaso = null;
	private String jsonArr_moto = null;

	private String toiawasebango_tuikahaso = null;
	private String haisohoho_tuikahaso = null;
	private String haisokaisha_tuikahaso = null;

	private String henkinkinkaku = null;
	private String henkinbiko = null;
	private String shoribango_henkin = null;

	private List<HasomachiList> hasomachiList = null;
	private List<HasozumiList> hasozumiList = null;
	private List<SoushinList> soshinList = null;
	private List<BunnohasomachiList> bunnohasomachiList = null;
	private List<BunnohasozumiList> bunnohasozumiList = null;
	private List<BunnouhasokaList> bunnouhasokaList = null;
	private List<HenpinList> henpinkaList = null;
	private List<HenpinList> henpinshuseikaShohinList = null;

	private List<HenpinSeteiZumiList> henpinList = null;
	private List<HenpinSeteiZumiList> henpinListzumi = null;
	private List<HenkinListBean> henkinList = null;
	private List<HenpinshuseikaList> henpinshuseikaList = null;

	private List<TuikaList> tuikaList = null;
	private List<DokonList> dokonList = null;

	private String site = null;
	private String oyaFlg = null;

	private String jsonArr_dokon = null;
	private String jsonArr_moto_dokon = null;
	private String juchubango_dokon = null;

	private String nyukafukaBangoArr = null;

	private String hasoyakusokubi = null;
	private String hasoshahe = null;
	
	private String tuikatantosya = null;
	private String tantosya_tuikashusei = null;

	public String getTantosya_tuikashusei() {
		return tantosya_tuikashusei;
	}

	public void setTantosya_tuikashusei(String tantosya_tuikashusei) {
		this.tantosya_tuikashusei = tantosya_tuikashusei;
	}

	public String getTuikatantosya() {
		return tuikatantosya;
	}

	public void setTuikatantosya(String tuikatantosya) {
		this.tuikatantosya = tuikatantosya;
	}

	/**
	 * @return the sonotatesuryo
	 */
	public String getSonotatesuryo() {
		return sonotatesuryo;
	}

	/**
	 * @param sonotatesuryo
	 *            the sonotatesuryo to set
	 */
	public void setSonotatesuryo(String sonotatesuryo) {
		this.sonotatesuryo = sonotatesuryo;
	}

	/**
	 * @return the sonotawaribikigaku
	 */
	public String getSonotawaribikigaku() {
		return sonotawaribikigaku;
	}

	/**
	 * @param sonotawaribikigaku
	 *            the sonotawaribikigaku to set
	 */
	public void setSonotawaribikigaku(String sonotawaribikigaku) {
		this.sonotawaribikigaku = sonotawaribikigaku;
	}

	/**
	 * @return the jsonArr_dokon
	 */
	public String getJsonArr_dokon() {
		return jsonArr_dokon;
	}

	/**
	 * @param jsonArr_dokon
	 *            the jsonArr_dokon to set
	 */
	public void setJsonArr_dokon(String jsonArr_dokon) {
		this.jsonArr_dokon = jsonArr_dokon;
	}

	/**
	 * @return the jsonArr_moto_dokon
	 */
	public String getJsonArr_moto_dokon() {
		return jsonArr_moto_dokon;
	}

	/**
	 * @param jsonArr_moto_dokon
	 *            the jsonArr_moto_dokon to set
	 */
	public void setJsonArr_moto_dokon(String jsonArr_moto_dokon) {
		this.jsonArr_moto_dokon = jsonArr_moto_dokon;
	}

	/**
	 * @return the henkinkinkaku
	 */
	public String getHenkinkinkaku() {
		return henkinkinkaku;
	}

	/**
	 * @param henkinkinkaku
	 *            the henkinkinkaku to set
	 */
	public void setHenkinkinkaku(String henkinkinkaku) {
		this.henkinkinkaku = henkinkinkaku;
	}

	/**
	 * @return the henkinbiko
	 */
	public String getHenkinbiko() {
		return henkinbiko;
	}

	/**
	 * @param henkinbiko
	 *            the henkinbiko to set
	 */
	public void setHenkinbiko(String henkinbiko) {
		this.henkinbiko = henkinbiko;
	}

	/**
	 * @return the toiawasebango_tuikahaso
	 */
	public String getToiawasebango_tuikahaso() {
		return toiawasebango_tuikahaso;
	}

	/**
	 * @param toiawasebango_tuikahaso
	 *            the toiawasebango_tuikahaso to set
	 */
	public void setToiawasebango_tuikahaso(String toiawasebango_tuikahaso) {
		this.toiawasebango_tuikahaso = toiawasebango_tuikahaso;
	}

	/**
	 * @return the haisohoho_tuikahaso
	 */
	public String getHaisohoho_tuikahaso() {
		return haisohoho_tuikahaso;
	}

	/**
	 * @param haisohoho_tuikahaso
	 *            the haisohoho_tuikahaso to set
	 */
	public void setHaisohoho_tuikahaso(String haisohoho_tuikahaso) {
		this.haisohoho_tuikahaso = haisohoho_tuikahaso;
	}

	/**
	 * @return the haisokaisha_tuikahaso
	 */
	public String getHaisokaisha_tuikahaso() {
		return haisokaisha_tuikahaso;
	}

	/**
	 * @param haisokaisha_tuikahaso
	 *            the haisokaisha_tuikahaso to set
	 */
	public void setHaisokaisha_tuikahaso(String haisokaisha_tuikahaso) {
		this.haisokaisha_tuikahaso = haisokaisha_tuikahaso;
	}

	/**
	 * @return the jsonArr_moto
	 */
	public String getJsonArr_moto() {
		return jsonArr_moto;
	}

	/**
	 * @param jsonArr_moto
	 *            the jsonArr_moto to set
	 */
	public void setJsonArr_moto(String jsonArr_moto) {
		this.jsonArr_moto = jsonArr_moto;
	}

	/**
	 * @return the tuikashoribango_tuikashusei
	 */
	public String getTuikashoribango_tuikashusei() {
		return tuikashoribango_tuikashusei;
	}

	/**
	 * @param tuikashoribango_tuikashusei
	 *            the tuikashoribango_tuikashusei to set
	 */
	public void setTuikashoribango_tuikashusei(
			String tuikashoribango_tuikashusei) {
		this.tuikashoribango_tuikashusei = tuikashoribango_tuikashusei;
	}

	/**
	 * @return the tuikariyu_tuikashusei
	 */
	public String getTuikariyu_tuikashusei() {
		return tuikariyu_tuikashusei;
	}

	/**
	 * @param tuikariyu_tuikashusei
	 *            the tuikariyu_tuikashusei to set
	 */
	public void setTuikariyu_tuikashusei(String tuikariyu_tuikashusei) {
		this.tuikariyu_tuikashusei = tuikariyu_tuikashusei;
	}

	/**
	 * @return the tuikariyusonota_tuikashusei
	 */
	public String getTuikariyusonota_tuikashusei() {
		return tuikariyusonota_tuikashusei;
	}

	/**
	 * @param tuikariyusonota_tuikashusei
	 *            the tuikariyusonota_tuikashusei to set
	 */
	public void setTuikariyusonota_tuikashusei(
			String tuikariyusonota_tuikashusei) {
		this.tuikariyusonota_tuikashusei = tuikariyusonota_tuikashusei;
	}

	/**
	 * @return the hasosoryofutan_tuikashusei
	 */
	public String getHasosoryofutan_tuikashusei() {
		return hasosoryofutan_tuikashusei;
	}

	/**
	 * @param hasosoryofutan_tuikashusei
	 *            the hasosoryofutan_tuikashusei to set
	 */
	public void setHasosoryofutan_tuikashusei(String hasosoryofutan_tuikashusei) {
		this.hasosoryofutan_tuikashusei = hasosoryofutan_tuikashusei;
	}

	/**
	 * @return the biko_tuikashusei
	 */
	public String getBiko_tuikashusei() {
		return biko_tuikashusei;
	}

	/**
	 * @param biko_tuikashusei
	 *            the biko_tuikashusei to set
	 */
	public void setBiko_tuikashusei(String biko_tuikashusei) {
		this.biko_tuikashusei = biko_tuikashusei;
	}

	/**
	 * @return the tuikaList
	 */
	public List<TuikaList> getTuikaList() {
		return tuikaList;
	}

	/**
	 * @param tuikaList
	 *            the tuikaList to set
	 */
	public void setTuikaList(List<TuikaList> tuikaList) {
		this.tuikaList = tuikaList;
	}

	/**
	 * @return the tuikariyu
	 */
	public String[] getTuikariyu() {
		return tuikariyu;
	}

	/**
	 * @param tuikariyu
	 *            the tuikariyu to set
	 */
	public void setTuikariyu(String[] tuikariyu) {
		this.tuikariyu = tuikariyu;
	}

	/**
	 * @return the tuikasoryofutan
	 */
	public String getTuikasoryofutan() {
		return tuikasoryofutan;
	}

	/**
	 * @param tuikasoryofutan
	 *            the tuikasoryofutan to set
	 */
	public void setTuikasoryofutan(String tuikasoryofutan) {
		this.tuikasoryofutan = tuikasoryofutan;
	}

	/**
	 * @return the tuikariyuSonota
	 */
	public String getTuikariyuSonota() {
		return tuikariyuSonota;
	}

	/**
	 * @param tuikariyuSonota
	 *            the tuikariyuSonota to set
	 */
	public void setTuikariyuSonota(String tuikariyuSonota) {
		this.tuikariyuSonota = tuikariyuSonota;
	}

	/**
	 * @return the tuikabiko
	 */
	public String getTuikabiko() {
		return tuikabiko;
	}

	/**
	 * @param tuikabiko
	 *            the tuikabiko to set
	 */
	public void setTuikabiko(String tuikabiko) {
		this.tuikabiko = tuikabiko;
	}

	/**
	 * @return the jsonArr
	 */
	public String getJsonArr() {
		return jsonArr;
	}

	/**
	 * @param jsonArr
	 *            the jsonArr to set
	 */
	public void setJsonArr(String jsonArr) {
		this.jsonArr = jsonArr;
	}

	/**
	 * @return the shoribango_henpinhaso
	 */
	public String getShoribango_henpinhaso() {
		return shoribango_henpinhaso;
	}

	/**
	 * @param shoribango_henpinhaso
	 *            the shoribango_henpinhaso to set
	 */
	public void setShoribango_henpinhaso(String shoribango_henpinhaso) {
		this.shoribango_henpinhaso = shoribango_henpinhaso;
	}

	/**
	 * @return the toiawasebango_henpinhaso
	 */
	public String getToiawasebango_henpinhaso() {
		return toiawasebango_henpinhaso;
	}

	/**
	 * @param toiawasebango_henpinhaso
	 *            the toiawasebango_henpinhaso to set
	 */
	public void setToiawasebango_henpinhaso(String toiawasebango_henpinhaso) {
		this.toiawasebango_henpinhaso = toiawasebango_henpinhaso;
	}

	/**
	 * @return the haisohoho_henpinhaso
	 */
	public String getHaisohoho_henpinhaso() {
		return haisohoho_henpinhaso;
	}

	/**
	 * @param haisohoho_henpinhaso
	 *            the haisohoho_henpinhaso to set
	 */
	public void setHaisohoho_henpinhaso(String haisohoho_henpinhaso) {
		this.haisohoho_henpinhaso = haisohoho_henpinhaso;
	}

	/**
	 * @return the haisokaisha_henpinhaso
	 */
	public String getHaisokaisha_henpinhaso() {
		return haisokaisha_henpinhaso;
	}

	/**
	 * @param haisokaisha_henpinhaso
	 *            the haisokaisha_henpinhaso to set
	 */
	public void setHaisokaisha_henpinhaso(String haisokaisha_henpinhaso) {
		this.haisokaisha_henpinhaso = haisokaisha_henpinhaso;
	}

	/**
	 * @return the shoribango_henpinshusei
	 */
	public String getShoribango_henpinshusei() {
		return shoribango_henpinshusei;
	}

	/**
	 * @param shoribango_henpinshusei
	 *            the shoribango_henpinshusei to set
	 */
	public void setShoribango_henpinshusei(String shoribango_henpinshusei) {
		this.shoribango_henpinshusei = shoribango_henpinshusei;
	}

	/**
	 * @return the henpinriyuTenpoSonota_henpinshusei
	 */
	public String getHenpinriyuTenpoSonota_henpinshusei() {
		return henpinriyuTenpoSonota_henpinshusei;
	}

	/**
	 * @param henpinriyuTenpoSonota_henpinshusei
	 *            the henpinriyuTenpoSonota_henpinshusei to set
	 */
	public void setHenpinriyuTenpoSonota_henpinshusei(
			String henpinriyuTenpoSonota_henpinshusei) {
		this.henpinriyuTenpoSonota_henpinshusei = henpinriyuTenpoSonota_henpinshusei;
	}

	/**
	 * @return the henpinriyuTenpo_henpinshusei
	 */
	public String[] getHenpinriyuTenpo_henpinshusei() {
		return henpinriyuTenpo_henpinshusei;
	}

	/**
	 * @param henpinriyuTenpo_henpinshusei
	 *            the henpinriyuTenpo_henpinshusei to set
	 */
	public void setHenpinriyuTenpo_henpinshusei(
			String[] henpinriyuTenpo_henpinshusei) {
		this.henpinriyuTenpo_henpinshusei = henpinriyuTenpo_henpinshusei;
	}

	/**
	 * @return the henpinriyuOkyaku_henpinshusei
	 */
	public String[] getHenpinriyuOkyaku_henpinshusei() {
		return henpinriyuOkyaku_henpinshusei;
	}

	/**
	 * @param henpinriyuOkyaku_henpinshusei
	 *            the henpinriyuOkyaku_henpinshusei to set
	 */
	public void setHenpinriyuOkyaku_henpinshusei(
			String[] henpinriyuOkyaku_henpinshusei) {
		this.henpinriyuOkyaku_henpinshusei = henpinriyuOkyaku_henpinshusei;
	}

	/**
	 * @return the henpinriyuOkyakuSonota_henpinshusei
	 */
	public String getHenpinriyuOkyakuSonota_henpinshusei() {
		return henpinriyuOkyakuSonota_henpinshusei;
	}

	/**
	 * @param henpinriyuOkyakuSonota_henpinshusei
	 *            the henpinriyuOkyakuSonota_henpinshusei to set
	 */
	public void setHenpinriyuOkyakuSonota_henpinshusei(
			String henpinriyuOkyakuSonota_henpinshusei) {
		this.henpinriyuOkyakuSonota_henpinshusei = henpinriyuOkyakuSonota_henpinshusei;
	}

	/**
	 * @return the henpinsoryofutanshokihasou_henpinshusei
	 */
	public String getHenpinsoryofutanshokihasou_henpinshusei() {
		return henpinsoryofutanshokihasou_henpinshusei;
	}

	/**
	 * @param henpinsoryofutanshokihasou_henpinshusei
	 *            the henpinsoryofutanshokihasou_henpinshusei to set
	 */
	public void setHenpinsoryofutanshokihasou_henpinshusei(
			String henpinsoryofutanshokihasou_henpinshusei) {
		this.henpinsoryofutanshokihasou_henpinshusei = henpinsoryofutanshokihasou_henpinshusei;
	}

	/**
	 * @return the henpinsoryofutanuketori_henpinshusei
	 */
	public String getHenpinsoryofutanuketori_henpinshusei() {
		return henpinsoryofutanuketori_henpinshusei;
	}

	/**
	 * @param henpinsoryofutanuketori_henpinshusei
	 *            the henpinsoryofutanuketori_henpinshusei to set
	 */
	public void setHenpinsoryofutanuketori_henpinshusei(
			String henpinsoryofutanuketori_henpinshusei) {
		this.henpinsoryofutanuketori_henpinshusei = henpinsoryofutanuketori_henpinshusei;
	}

	/**
	 * @return the henpinhenkinhituyoFlg_henpinshusei
	 */
	public String getHenpinhenkinhituyoFlg_henpinshusei() {
		return henpinhenkinhituyoFlg_henpinshusei;
	}

	/**
	 * @param henpinhenkinhituyoFlg_henpinshusei
	 *            the henpinhenkinhituyoFlg_henpinshusei to set
	 */
	public void setHenpinhenkinhituyoFlg_henpinshusei(
			String henpinhenkinhituyoFlg_henpinshusei) {
		this.henpinhenkinhituyoFlg_henpinshusei = henpinhenkinhituyoFlg_henpinshusei;
	}

	/**
	 * @return the henpinhenkinkigaku_henpinshusei
	 */
	public String getHenpinhenkinkigaku_henpinshusei() {
		return henpinhenkinkigaku_henpinshusei;
	}

	/**
	 * @param henpinhenkinkigaku_henpinshusei
	 *            the henpinhenkinkigaku_henpinshusei to set
	 */
	public void setHenpinhenkinkigaku_henpinshusei(
			String henpinhenkinkigaku_henpinshusei) {
		this.henpinhenkinkigaku_henpinshusei = henpinhenkinkigaku_henpinshusei;
	}

	/**
	 * @return the henpinbiko_henpinshusei
	 */
	public String getHenpinbiko_henpinshusei() {
		return henpinbiko_henpinshusei;
	}

	/**
	 * @param henpinbiko_henpinshusei
	 *            the henpinbiko_henpinshusei to set
	 */
	public void setHenpinbiko_henpinshusei(String henpinbiko_henpinshusei) {
		this.henpinbiko_henpinshusei = henpinbiko_henpinshusei;
	}

	/**
	 * @return the henpinariFlg
	 */
	public String getHenpinariFlg() {
		return henpinariFlg;
	}

	/**
	 * @param henpinariFlg
	 *            the henpinariFlg to set
	 */
	public void setHenpinariFlg(String henpinariFlg) {
		this.henpinariFlg = henpinariFlg;
	}

	/**
	 * @return the toiawasebango_bunnou
	 */
	public String getToiawasebango_bunnou() {
		return toiawasebango_bunnou;
	}

	/**
	 * @param toiawasebango_bunnou
	 *            the toiawasebango_bunnou to set
	 */
	public void setToiawasebango_bunnou(String toiawasebango_bunnou) {
		this.toiawasebango_bunnou = toiawasebango_bunnou;
	}

	/**
	 * @return the haisohoho_bunnou
	 */
	public String getHaisohoho_bunnou() {
		return haisohoho_bunnou;
	}

	/**
	 * @param haisohoho_bunnou
	 *            the haisohoho_bunnou to set
	 */
	public void setHaisohoho_bunnou(String haisohoho_bunnou) {
		this.haisohoho_bunnou = haisohoho_bunnou;
	}

	/**
	 * @return the haisokaisha_bunnou
	 */
	public String getHaisokaisha_bunnou() {
		return haisokaisha_bunnou;
	}

	/**
	 * @param haisokaisha_bunnou
	 *            the haisokaisha_bunnou to set
	 */
	public void setHaisokaisha_bunnou(String haisokaisha_bunnou) {
		this.haisokaisha_bunnou = haisokaisha_bunnou;
	}

	/**
	 * @return the hasonichiji_hasozumi
	 */
	public String getHasonichiji_hasozumi() {
		return hasonichiji_hasozumi;
	}

	/**
	 * @param hasonichiji_hasozumi
	 *            the hasonichiji_hasozumi to set
	 */
	public void setHasonichiji_hasozumi(String hasonichiji_hasozumi) {
		this.hasonichiji_hasozumi = hasonichiji_hasozumi;
	}

	/**
	 * @return the haisohoho_hasozumi
	 */
	public String getHaisohoho_hasozumi() {
		return haisohoho_hasozumi;
	}

	/**
	 * @param haisohoho_hasozumi
	 *            the haisohoho_hasozumi to set
	 */
	public void setHaisohoho_hasozumi(String haisohoho_hasozumi) {
		this.haisohoho_hasozumi = haisohoho_hasozumi;
	}

	/**
	 * @return the haisokaisha_hasozumi
	 */
	public String getHaisokaisha_hasozumi() {
		return haisokaisha_hasozumi;
	}

	/**
	 * @param haisokaisha_hasozumi
	 *            the haisokaisha_hasozumi to set
	 */
	public void setHaisokaisha_hasozumi(String haisokaisha_hasozumi) {
		this.haisokaisha_hasozumi = haisokaisha_hasozumi;
	}

	/**
	 * @return the toiawasebango_hasozumi
	 */
	public String getToiawasebango_hasozumi() {
		return toiawasebango_hasozumi;
	}

	/**
	 * @param toiawasebango_hasozumi
	 *            the toiawasebango_hasozumi to set
	 */
	public void setToiawasebango_hasozumi(String toiawasebango_hasozumi) {
		this.toiawasebango_hasozumi = toiawasebango_hasozumi;
	}

	/**
	 * @return the hasouzumiFlg
	 */
	public String getHasouzumiFlg() {
		return hasouzumiFlg;
	}

	/**
	 * @param hasouzumiFlg
	 *            the hasouzumiFlg to set
	 */
	public void setHasouzumiFlg(String hasouzumiFlg) {
		this.hasouzumiFlg = hasouzumiFlg;
	}

	/**
	 * @return the gokeishouhin
	 */
	public String getGokeishouhin() {
		return gokeishouhin;
	}

	/**
	 * @param gokeishouhin
	 *            the gokeishouhin to set
	 */
	public void setGokeishouhin(String gokeishouhin) {
		this.gokeishouhin = gokeishouhin;
	}

	/**
	 * @return the gokeizei
	 */
	public String getGokeizei() {
		return gokeizei;
	}

	/**
	 * @param gokeizei
	 *            the gokeizei to set
	 */
	public void setGokeizei(String gokeizei) {
		this.gokeizei = gokeizei;
	}

	/**
	 * @return the gokeisouryou
	 */
	public String getGokeisouryou() {
		return gokeisouryou;
	}

	/**
	 * @param gokeisouryou
	 *            the gokeisouryou to set
	 */
	public void setGokeisouryou(String gokeisouryou) {
		this.gokeisouryou = gokeisouryou;
	}

	/**
	 * @return the gokeidaibikiryou
	 */
	public String getGokeidaibikiryou() {
		return gokeidaibikiryou;
	}

	/**
	 * @param gokeidaibikiryou
	 *            the gokeidaibikiryou to set
	 */
	public void setGokeidaibikiryou(String gokeidaibikiryou) {
		this.gokeidaibikiryou = gokeidaibikiryou;
	}

	/**
	 * @return the pointriyou
	 */
	public String getPointriyou() {
		return pointriyou;
	}

	/**
	 * @param pointriyou
	 *            the pointriyou to set
	 */
	public void setPointriyou(String pointriyou) {
		this.pointriyou = pointriyou;
	}

	/**
	 * @return the seikyukingaku
	 */
	public String getSeikyukingaku() {
		return seikyukingaku;
	}

	/**
	 * @param seikyukingaku
	 *            the seikyukingaku to set
	 */
	public void setSeikyukingaku(String seikyukingaku) {
		this.seikyukingaku = seikyukingaku;
	}

	/**
	 * @return the sofusakijoho
	 */
	public String getSofusakijoho() {
		return sofusakijoho;
	}

	/**
	 * @param sofusakijoho
	 *            the sofusakijoho to set
	 */
	public void setSofusakijoho(String sofusakijoho) {
		this.sofusakijoho = sofusakijoho;
	}

	/**
	 * @return the onimotsudenpyobango
	 */
	public String getOnimotsudenpyobango() {
		return onimotsudenpyobango;
	}

	/**
	 * @param onimotsudenpyobango
	 *            the onimotsudenpyobango to set
	 */
	public void setOnimotsudenpyobango(String onimotsudenpyobango) {
		this.onimotsudenpyobango = onimotsudenpyobango;
	}

	/**
	 * @return the haisokaisha
	 */
	public String getHaisokaisha() {
		return haisokaisha;
	}

	/**
	 * @param haisokaisha
	 *            the haisokaisha to set
	 */
	public void setHaisokaisha(String haisokaisha) {
		this.haisokaisha = haisokaisha;
	}

	/**
	 * @return the noshi
	 */
	public String getNoshi() {
		return noshi;
	}

	/**
	 * @param noshi
	 *            the noshi to set
	 */
	public void setNoshi(String noshi) {
		this.noshi = noshi;
	}

	/**
	 * @return the shohinList
	 */
	public List<ShohinList> getShohinList() {
		return shohinList;
	}

	/**
	 * @param shohinList
	 *            the shohinList to set
	 */
	public void setShohinList(List<ShohinList> shohinList) {
		this.shohinList = shohinList;
	}

	/**
	 * @return the chumonsts1
	 */
	public String getChumonsts1() {
		return chumonsts1;
	}

	/**
	 * @param chumonsts1
	 *            the chumonsts1 to set
	 */
	public void setChumonsts1(String chumonsts1) {
		this.chumonsts1 = chumonsts1;
	}

	/**
	 * @return the chumonsts2
	 */
	public String getChumonsts2() {
		return chumonsts2;
	}

	/**
	 * @param chumonsts2
	 *            the chumonsts2 to set
	 */
	public void setChumonsts2(String chumonsts2) {
		this.chumonsts2 = chumonsts2;
	}

	/**
	 * @return the chumonsts3
	 */
	public String getChumonsts3() {
		return chumonsts3;
	}

	/**
	 * @param chumonsts3
	 *            the chumonsts3 to set
	 */
	public void setChumonsts3(String chumonsts3) {
		this.chumonsts3 = chumonsts3;
	}

	/**
	 * @return the chumonsts4
	 */
	public String getChumonsts4() {
		return chumonsts4;
	}

	/**
	 * @param chumonsts4
	 *            the chumonsts4 to set
	 */
	public void setChumonsts4(String chumonsts4) {
		this.chumonsts4 = chumonsts4;
	}

	/**
	 * @return the dokonFlg
	 */
	public String getDokonFlg() {
		return dokonFlg;
	}

	/**
	 * @param dokonFlg
	 *            the dokonFlg to set
	 */
	public void setDokonFlg(String dokonFlg) {
		this.dokonFlg = dokonFlg;
	}

	/**
	 * @return the okurisakuchuiFlg
	 */
	public String getOkurisakuchuiFlg() {
		return okurisakuchuiFlg;
	}

	/**
	 * @param okurisakuchuiFlg
	 *            the okurisakuchuiFlg to set
	 */
	public void setOkurisakuchuiFlg(String okurisakuchuiFlg) {
		this.okurisakuchuiFlg = okurisakuchuiFlg;
	}

	/**
	 * @return the mobileMailFlg
	 */
	public String getMobileMailFlg() {
		return mobileMailFlg;
	}

	/**
	 * @param mobileMailFlg
	 *            the mobileMailFlg to set
	 */
	public void setMobileMailFlg(String mobileMailFlg) {
		this.mobileMailFlg = mobileMailFlg;
	}

	/**
	 * @return the asurakuFlg
	 */
	public String getAsurakuFlg() {
		return asurakuFlg;
	}

	/**
	 * @param asurakuFlg
	 *            the asurakuFlg to set
	 */
	public void setAsurakuFlg(String asurakuFlg) {
		this.asurakuFlg = asurakuFlg;
	}

	/**
	 * @return the juchubango
	 */
	public String getJuchubango() {
		return juchubango;
	}

	/**
	 * @param juchubango
	 *            the juchubango to set
	 */
	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
	}

	/**
	 * @return the chumonnichiji
	 */
	public String getChumonnichiji() {
		return chumonnichiji;
	}

	/**
	 * @param chumonnichiji
	 *            the chumonnichiji to set
	 */
	public void setChumonnichiji(String chumonnichiji) {
		this.chumonnichiji = chumonnichiji;
	}

	/**
	 * @return the chumonshanamae
	 */
	public String getChumonshanamae() {
		return chumonshanamae;
	}

	/**
	 * @param chumonshanamae
	 *            the chumonshanamae to set
	 */
	public void setChumonshanamae(String chumonshanamae) {
		this.chumonshanamae = chumonshanamae;
	}

	/**
	 * @return the chumonshameruadoresu
	 */
	public String getChumonshameruadoresu() {
		return chumonshameruadoresu;
	}

	/**
	 * @param chumonshameruadoresu
	 *            the chumonshameruadoresu to set
	 */
	public void setChumonshameruadoresu(String chumonshameruadoresu) {
		this.chumonshameruadoresu = chumonshameruadoresu;
	}

	/**
	 * @return the chumonshatanjoubi
	 */
	public String getChumonshatanjoubi() {
		return chumonshatanjoubi;
	}

	/**
	 * @param chumonshatanjoubi
	 *            the chumonshatanjoubi to set
	 */
	public void setChumonshatanjoubi(String chumonshatanjoubi) {
		this.chumonshatanjoubi = chumonshatanjoubi;
	}

	/**
	 * @return the chumonshadenwabango
	 */
	public String getChumonshadenwabango() {
		return chumonshadenwabango;
	}

	/**
	 * @param chumonshadenwabango
	 *            the chumonshadenwabango to set
	 */
	public void setChumonshadenwabango(String chumonshadenwabango) {
		this.chumonshadenwabango = chumonshadenwabango;
	}

	/**
	 * @return the oshiharaihoho
	 */
	public String getOshiharaihoho() {
		return oshiharaihoho;
	}

	/**
	 * @param oshiharaihoho
	 *            the oshiharaihoho to set
	 */
	public void setOshiharaihoho(String oshiharaihoho) {
		this.oshiharaihoho = oshiharaihoho;
	}

	/**
	 * @return the haisohoho
	 */
	public String getHaisohoho() {
		return haisohoho;
	}

	/**
	 * @param haisohoho
	 *            the haisohoho to set
	 */
	public void setHaisohoho(String haisohoho) {
		this.haisohoho = haisohoho;
	}

	/**
	 * @return the hassobi
	 */
	public String getHassobi() {
		return hassobi;
	}

	/**
	 * @param hassobi
	 *            the hassobi to set
	 */
	public void setHassobi(String hassobi) {
		this.hassobi = hassobi;
	}

	/**
	 * @return the otodokebishitei
	 */
	public String getOtodokebishitei() {
		return otodokebishitei;
	}

	/**
	 * @param otodokebishitei
	 *            the otodokebishitei to set
	 */
	public void setOtodokebishitei(String otodokebishitei) {
		this.otodokebishitei = otodokebishitei;
	}

	/**
	 * @return the otodokejikantai1
	 */
	public String getOtodokejikantai1() {
		return otodokejikantai1;
	}

	/**
	 * @param otodokejikantai1
	 *            the otodokejikantai1 to set
	 */
	public void setOtodokejikantai1(String otodokejikantai1) {
		this.otodokejikantai1 = otodokejikantai1;
	}

	/**
	 * @return the otodokejikantai2
	 */
	public String getOtodokejikantai2() {
		return otodokejikantai2;
	}

	/**
	 * @param otodokejikantai2
	 *            the otodokejikantai2 to set
	 */
	public void setOtodokejikantai2(String otodokejikantai2) {
		this.otodokejikantai2 = otodokejikantai2;
	}

	/**
	 * @return the otodokejikantai3
	 */
	public String getOtodokejikantai3() {
		return otodokejikantai3;
	}

	/**
	 * @param otodokejikantai3
	 *            the otodokejikantai3 to set
	 */
	public void setOtodokejikantai3(String otodokejikantai3) {
		this.otodokejikantai3 = otodokejikantai3;
	}

	/**
	 * @return the biko
	 */
	public String getBiko() {
		return biko;
	}

	/**
	 * @param biko
	 *            the biko to set
	 */
	public void setBiko(String biko) {
		this.biko = biko;
	}

	/**
	 * @return the bikorakuten
	 */
	public String getBikorakuten() {
		return bikorakuten;
	}

	/**
	 * @param bikorakuten
	 *            the bikorakuten to set
	 */
	public void setBikorakuten(String bikorakuten) {
		this.bikorakuten = bikorakuten;
	}

	/**
	 * @return the okyakusamahenomeseji
	 */
	public String getOkyakusamahenomeseji() {
		return okyakusamahenomeseji;
	}

	/**
	 * @param okyakusamahenomeseji
	 *            the okyakusamahenomeseji to set
	 */
	public void setOkyakusamahenomeseji(String okyakusamahenomeseji) {
		this.okyakusamahenomeseji = okyakusamahenomeseji;
	}

	public String getChumonshajusho() {
		return chumonshajusho;
	}

	public void setChumonshajusho(String chumonshajusho) {
		this.chumonshajusho = chumonshajusho;
	}

	public String getResultcount() {
		return resultcount;
	}

	public void setResultcount(String resultcount) {
		this.resultcount = resultcount;
	}

	public String getTenpobetsu() {
		return tenpobetsu;
	}

	public void setTenpobetsu(String tenpobetsu) {
		this.tenpobetsu = tenpobetsu;
	}

	public String getChumonsts5() {
		return chumonsts5;
	}

	public void setChumonsts5(String chumonsts5) {
		this.chumonsts5 = chumonsts5;
	}

	public String getChumonsts6() {
		return chumonsts6;
	}

	public void setChumonsts6(String chumonsts6) {
		this.chumonsts6 = chumonsts6;
	}

	public List<HasomachiList> getHasomachiList() {
		return hasomachiList;
	}

	public void setHasomachiList(List<HasomachiList> hasomachiList) {
		this.hasomachiList = hasomachiList;
	}

	public String getFutuhasokanoFlg() {
		return futuhasokanoFlg;
	}

	public void setFutuhasokanoFlg(String futuhasokanoFlg) {
		this.futuhasokanoFlg = futuhasokanoFlg;
	}

	public List<HasozumiList> getHasozumiList() {
		return hasozumiList;
	}

	public void setHasozumiList(List<HasozumiList> hasozumiList) {
		this.hasozumiList = hasozumiList;
	}

	public String getToiawasebango_futsuhasou() {
		return toiawasebango_futsuhasou;
	}

	public void setToiawasebango_futsuhasou(String toiawasebango_futsuhasou) {
		this.toiawasebango_futsuhasou = toiawasebango_futsuhasou;
	}

	public String getHaisokaisha_futsuhasou() {
		return haisokaisha_futsuhasou;
	}

	public void setHaisokaisha_futsuhasou(String haisokaisha_futsuhasou) {
		this.haisokaisha_futsuhasou = haisokaisha_futsuhasou;
	}

	public String getHaisohoho_futsuhasou() {
		return haisohoho_futsuhasou;
	}

	public void setHaisohoho_futsuhasou(String haisohoho_futsuhasou) {
		this.haisohoho_futsuhasou = haisohoho_futsuhasou;
	}

	public List<SoushinList> getSoshinList() {
		return soshinList;
	}

	public void setSoshinList(List<SoushinList> soshinList) {
		this.soshinList = soshinList;
	}

	public List<BunnohasomachiList> getBunnohasomachiList() {
		return bunnohasomachiList;
	}

	public void setBunnohasomachiList(
			List<BunnohasomachiList> bunnohasomachiList) {
		this.bunnohasomachiList = bunnohasomachiList;
	}

	public List<BunnohasozumiList> getBunnohasozumiList() {
		return bunnohasozumiList;
	}

	public void setBunnohasozumiList(List<BunnohasozumiList> bunnohasozumiList) {
		this.bunnohasozumiList = bunnohasozumiList;
	}

	public List<BunnouhasokaList> getBunnouhasokaList() {
		return bunnouhasokaList;
	}

	public void setBunnouhasokaList(List<BunnouhasokaList> bunnouhasokaList) {
		this.bunnouhasokaList = bunnouhasokaList;
	}

	public String getBunnohasoukaFlg() {
		return bunnohasoukaFlg;
	}

	public void setBunnohasoukaFlg(String bunnohasoukaFlg) {
		this.bunnohasoukaFlg = bunnohasoukaFlg;
	}

	public String getBunnotorikeshikaFlg() {
		return bunnotorikeshikaFlg;
	}

	public void setBunnotorikeshikaFlg(String bunnotorikeshikaFlg) {
		this.bunnotorikeshikaFlg = bunnotorikeshikaFlg;
	}

	/**
	 * @return the henpinriyuTenpo
	 */
	public String[] getHenpinriyuTenpo() {
		return henpinriyuTenpo;
	}

	/**
	 * @param henpinriyuTenpo
	 *            the henpinriyuTenpo to set
	 */
	public void setHenpinriyuTenpo(String[] henpinriyuTenpo) {
		this.henpinriyuTenpo = henpinriyuTenpo;
	}

	/**
	 * @return the henpinriyuTenpoSonota
	 */
	public String getHenpinriyuTenpoSonota() {
		return henpinriyuTenpoSonota;
	}

	/**
	 * @param henpinriyuTenpoSonota
	 *            the henpinriyuTenpoSonota to set
	 */
	public void setHenpinriyuTenpoSonota(String henpinriyuTenpoSonota) {
		this.henpinriyuTenpoSonota = henpinriyuTenpoSonota;
	}

	/**
	 * @return the henpinriyuOkyaku
	 */
	public String[] getHenpinriyuOkyaku() {
		return henpinriyuOkyaku;
	}

	/**
	 * @param henpinriyuOkyaku
	 *            the henpinriyuOkyaku to set
	 */
	public void setHenpinriyuOkyaku(String[] henpinriyuOkyaku) {
		this.henpinriyuOkyaku = henpinriyuOkyaku;
	}

	/**
	 * @return the henpinriyuOkyakuSonota
	 */
	public String getHenpinriyuOkyakuSonota() {
		return henpinriyuOkyakuSonota;
	}

	/**
	 * @param henpinriyuOkyakuSonota
	 *            the henpinriyuOkyakuSonota to set
	 */
	public void setHenpinriyuOkyakuSonota(String henpinriyuOkyakuSonota) {
		this.henpinriyuOkyakuSonota = henpinriyuOkyakuSonota;
	}

	/**
	 * @return the henpinsoryofutanshokihasou
	 */
	public String getHenpinsoryofutanshokihasou() {
		return henpinsoryofutanshokihasou;
	}

	/**
	 * @param henpinsoryofutanshokihasou
	 *            the henpinsoryofutanshokihasou to set
	 */
	public void setHenpinsoryofutanshokihasou(String henpinsoryofutanshokihasou) {
		this.henpinsoryofutanshokihasou = henpinsoryofutanshokihasou;
	}

	/**
	 * @return the henpinsoryofutanuketori
	 */
	public String getHenpinsoryofutanuketori() {
		return henpinsoryofutanuketori;
	}

	/**
	 * @param henpinsoryofutanuketori
	 *            the henpinsoryofutanuketori to set
	 */
	public void setHenpinsoryofutanuketori(String henpinsoryofutanuketori) {
		this.henpinsoryofutanuketori = henpinsoryofutanuketori;
	}

	/**
	 * @return the henpinhenkinhituyoFlg
	 */
	public String getHenpinhenkinhituyoFlg() {
		return henpinhenkinhituyoFlg;
	}

	/**
	 * @param henpinhenkinhituyoFlg
	 *            the henpinhenkinhituyoFlg to set
	 */
	public void setHenpinhenkinhituyoFlg(String henpinhenkinhituyoFlg) {
		this.henpinhenkinhituyoFlg = henpinhenkinhituyoFlg;
	}

	/**
	 * @return the henpinhenkinkigaku
	 */
	public String getHenpinhenkinkigaku() {
		return henpinhenkinkigaku;
	}

	/**
	 * @param henpinhenkinkigaku
	 *            the henpinhenkinkigaku to set
	 */
	public void setHenpinhenkinkigaku(String henpinhenkinkigaku) {
		this.henpinhenkinkigaku = henpinhenkinkigaku;
	}

	/**
	 * @return the henpinbiko
	 */
	public String getHenpinbiko() {
		return henpinbiko;
	}

	/**
	 * @param henpinbiko
	 *            the henpinbiko to set
	 */
	public void setHenpinbiko(String henpinbiko) {
		this.henpinbiko = henpinbiko;
	}

	/**
	 * @return the henpinkaList
	 */
	public List<HenpinList> getHenpinkaList() {
		return henpinkaList;
	}

	/**
	 * @param henpinkaList
	 *            the henpinkaList to set
	 */
	public void setHenpinkaList(List<HenpinList> henpinkaList) {
		this.henpinkaList = henpinkaList;
	}

	public List<HenpinSeteiZumiList> getHenpinList() {
		return henpinList;
	}

	public void setHenpinList(List<HenpinSeteiZumiList> henpinList) {
		this.henpinList = henpinList;
	}

	public List<HenkinListBean> getHenkinList() {
		return henkinList;
	}

	public void setHenkinList(List<HenkinListBean> henkinList) {
		this.henkinList = henkinList;
	}

	public String getHenkinariFlg() {
		return henkinariFlg;
	}

	public void setHenkinariFlg(String henkinariFlg) {
		this.henkinariFlg = henkinariFlg;
	}

	public List<HenpinList> getHenpinshuseikaShohinList() {
		return henpinshuseikaShohinList;
	}

	public void setHenpinshuseikaShohinList(
			List<HenpinList> henpinshuseikaShohinList) {
		this.henpinshuseikaShohinList = henpinshuseikaShohinList;
	}

	public List<HenpinshuseikaList> getHenpinshuseikaList() {
		return henpinshuseikaList;
	}

	public void setHenpinshuseikaList(
			List<HenpinshuseikaList> henpinshuseikaList) {
		this.henpinshuseikaList = henpinshuseikaList;
	}

	public List<HenpinSeteiZumiList> getHenpinListzumi() {
		return henpinListzumi;
	}

	public void setHenpinListzumi(List<HenpinSeteiZumiList> henpinListzumi) {
		this.henpinListzumi = henpinListzumi;
	}

	public String getTuikahasokaFlg() {
		return tuikahasokaFlg;
	}

	public void setTuikahasokaFlg(String tuikahasokaFlg) {
		this.tuikahasokaFlg = tuikahasokaFlg;
	}

	public String getTuikaariFlg() {
		return tuikaariFlg;
	}

	public void setTuikaariFlg(String tuikaariFlg) {
		this.tuikaariFlg = tuikaariFlg;
	}

	public String getTuikashoribango_shuseihaso() {
		return tuikashoribango_shuseihaso;
	}

	public void setTuikashoribango_shuseihaso(String tuikashoribango_shuseihaso) {
		this.tuikashoribango_shuseihaso = tuikashoribango_shuseihaso;
	}

	public String getShoribango_henkin() {
		return shoribango_henkin;
	}

	public void setShoribango_henkin(String shoribango_henkin) {
		this.shoribango_henkin = shoribango_henkin;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public List<DokonList> getDokonList() {
		return dokonList;
	}

	public void setDokonList(List<DokonList> dokonList) {
		this.dokonList = dokonList;
	}

	public String getOyaFlg() {
		return oyaFlg;
	}

	public void setOyaFlg(String oyaFlg) {
		this.oyaFlg = oyaFlg;
	}

	public String getJuchubango_dokon() {
		return juchubango_dokon;
	}

	public void setJuchubango_dokon(String juchubango_dokon) {
		this.juchubango_dokon = juchubango_dokon;
	}

	public String getNyukafukaBangoArr() {
		return nyukafukaBangoArr;
	}

	public void setNyukafukaBangoArr(String nyukafukaBangoArr) {
		this.nyukafukaBangoArr = nyukafukaBangoArr;
	}

	public String getHasoyoteibi() {
		return hasoyoteibi;
	}

	public void setHasoyoteibi(String hasoyoteibi) {
		this.hasoyoteibi = hasoyoteibi;
	}

	public String getHasoyakusokubi() {
		return hasoyakusokubi;
	}

	public void setHasoyakusokubi(String hasoyakusokubi) {
		this.hasoyakusokubi = hasoyakusokubi;
	}

	public String getHasoshahe() {
		return hasoshahe;
	}

	public void setHasoshahe(String hasoshahe) {
		this.hasoshahe = hasoshahe;
	}

	public String getSonotariyogaku() {
		return sonotariyogaku;
	}

	public void setSonotariyogaku(String sonotariyogaku) {
		this.sonotariyogaku = sonotariyogaku;
	}

	public String getDingdandaoruri() {
		return dingdandaoruri;
	}

	public void setDingdandaoruri(String dingdandaoruri) {
		this.dingdandaoruri = dingdandaoruri;
	}
}
