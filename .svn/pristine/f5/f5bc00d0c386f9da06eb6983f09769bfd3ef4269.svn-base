package com.rakuten.r1202.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.ShohinList;
import com.rakuten.util.Utility;

public class A12020113Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();

	protected void exec() throws Exception {
		String shohinbango_selected = f120201.getShohinbango_selected();

		String shohinbango_moto = detail.getShohinbango_moto();
		List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");
		if (!Utility.isEmptyString(shohinbango_moto)) {

			if ("0".equals(detail.getDetailDelFlg())) {
				for (int i = 0; i < detailList.size(); i++) {
					if (detailList.get(i).getShohinbango()
							.equals(shohinbango_moto)) {
						detailList.set(i, detail);
					}
				}
				setSessionAttribute("f120201DetailList", detailList);
			}

			List<ShohinList> shohinList = a120201Common.getshohinList(
					detailList, f120201);
			f120201.setShohinList(shohinList);

		}

		for (F120201Detail detailInfo : detailList) {
			if (detailInfo.getShohinbango().equals(shohinbango_selected)) {
				detail = detailInfo;
				break;
			}
		}

	}

	protected void init() throws Exception {
		setTitle("V120201:ÎÄ°¸±à¼­:  "
				+ a120201Common.getShumokuname(f120201.getShumokuid()));
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

}
