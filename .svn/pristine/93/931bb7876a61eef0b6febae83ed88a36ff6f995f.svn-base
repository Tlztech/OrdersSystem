package com.rakuten.r1202.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.Plxg;
import com.rakuten.r1202.form.ShohinList;
import com.rakuten.util.Utility;

public class A12020104Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();
	Plxg plxg = null;

	protected void exec() throws Exception {
		List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");
		String col = plxg.getInput_selectedItem();
		List<F120201Detail> shuseiList = new ArrayList<F120201Detail>();
		String[] selectedShoinArr = f120201.getShohinbango_checked().split("&");
		for (String shohinbango : selectedShoinArr) {
			for (F120201Detail detail : detailList) {
				if (shohinbango.equals(detail.getShohinbango())) {
					shuseiList.add(detail);
				}
			}
		}
		String input_addAtFirst = plxg.getInput_addAtFirst();
		String input_addAtEnd = plxg.getInput_addAtEnd();
		String input_replaceFrom = plxg.getInput_replaceFrom();
		String input_replaceTo = plxg.getInput_replaceTo();
		String input_strDelete = plxg.getInput_strDelete();
		String input_resetStr = plxg.getInput_resetStr();

		String type = plxg.getRadio_typeSelect();
		String tempStr = "";
		String str = null;
		if ("plxg.shohinbango_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinbango();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinbango(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinmei_cn_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinmei_cn();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinmei_cn(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shiireurl_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShiireurl();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShiireurl(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shiirekakaku_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShiirekakaku();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShiirekakaku(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.kategori1_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getKategori1();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setKategori1(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.kategori2_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getKategori2();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setKategori2(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.kategori3_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getKategori3();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setKategori3(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.kategori4_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getKategori4();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setKategori4(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.yokomei_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getYokomei();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setYokomei(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shitagamei_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShitagamei();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShitagamei(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.sozainaiyo_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getSozainaiyo();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setSozainaiyo(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosaimeisho1_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosaimeisho1();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosaimeisho1(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosainaiyo1_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosainaiyo1();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosainaiyo1(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosaimeisho2_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosaimeisho2();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosaimeisho2(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosainaiyo2_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosainaiyo2();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosainaiyo2(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosaimeisho3_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosaimeisho3();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosaimeisho3(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosainaiyo3_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosainaiyo3();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosainaiyo3(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosaimeisho4_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosaimeisho4();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosaimeisho4(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosainaiyo4_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosainaiyo4();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosainaiyo4(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosaimeisho5_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosaimeisho5();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosaimeisho5(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosainaiyo5_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosainaiyo5();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosainaiyo5(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosaimeisho6_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosaimeisho6();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosaimeisho6(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinshosainaiyo6_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinshosainaiyo6();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinshosainaiyo6(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohinmei_jp_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getShohinmei_jp();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setShohinmei_jp(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.pckyachikopi_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getPckyachikopi();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setPckyachikopi(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.mobairukyachikopi_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getMobairukyachikopi();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setMobairukyachikopi(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.direkutoriid_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getDirekutoriid();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setDirekutoriid(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.taguid_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getTaguid();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setTaguid(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.hanbaikakaku_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getHanbaikakaku();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setHanbaikakaku(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.hyojikakaku_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getHyojikakaku();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setHyojikakaku(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.picdir_str".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				str = detail.getPicdir();// 要修改
				if (Utility.isEmptyString(str)) {
					str = "";
				}
				if ("0".equals(type)) {
					tempStr = input_addAtFirst + str + input_addAtEnd;
				} else if ("1".equals(type)) {
					tempStr = str.replace(input_replaceFrom, input_replaceTo);
				} else if ("2".equals(type)) {
					tempStr = str.replace(input_strDelete, "");
				} else if ("3".equals(type)) {
					tempStr = input_resetStr;
				}
				detail.setPicdir(tempStr);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.merubin_sel".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setMerubin(plxg.getSel_merubintaio());// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.soryobetsu_sel".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setSoryobetsu(plxg.getSel_soryosentaku());// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.shohizeibetsu_sel".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setShohizeibetsu(plxg.getSel_shohizeisentaku());// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.merubinsoryomuryokyanpen_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setMerubinsoryomuryokyanpen("0".equals(plxg
						.getChk_xpjxfby()) ? false : true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.hogofirumu_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setHogofirumu("0".equals(plxg.getChk_xpjssjtmfcs()) ? false
						: true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.sizepic_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setSizepic("0".equals(plxg.getChk_nzccsyt()) ? false
						: true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.pycommonpic_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setPycommonpic("0".equals(plxg.getChk_pygtt()) ? false
						: true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.xfpycommonpic_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setXfpycommonpic("0".equals(plxg.getChk_xfpygtt()) ? false
						: true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.osusume1_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setOsusume1("0".equals(plxg.getChk_osusume1()) ? false
						: true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.osusume2_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setOsusume2("0".equals(plxg.getChk_osusume2()) ? false
						: true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.osusume3_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setOsusume3("0".equals(plxg.getChk_osusume3()) ? false
						: true);// 要修改
				detail.setHozonFlg("0");
			}
		} else if ("plxg.nouki_chk".equals(col)) {
			for (F120201Detail detail : shuseiList) {
				detail.setNouki("0".equals(plxg.getChk_nouki()) ? false : true);// 要修改
				detail.setHozonFlg("0");
			}
		}
		a120201Common.honykuAndSet(detailList);

		List<ShohinList> shohinList = a120201Common.getshohinList(detailList,
				f120201);
		f120201.setShohinList(shohinList);
		if (!Utility.isEmptyString(f120201.getShohinbango_selected())) {
			for (F120201Detail detailinfo : detailList) {
				if (detail.getShohinbango().equals(
						f120201.getShohinbango_selected())) {
					detail = detailinfo;
				}
			}
		}
		setSessionAttribute("f120201DetailList", detailList);
	}

	protected void init() throws Exception {
		setTitle("V120201:文案编辑:  "
				+ a120201Common.getShumokuname(f120201.getShumokuid()));
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the plxg
	 */
	public Plxg getPlxg() {
		return plxg;
	}

	/**
	 * @param plxg
	 *            the plxg to set
	 */
	public void setPlxg(Plxg plxg) {
		this.plxg = plxg;
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

}
