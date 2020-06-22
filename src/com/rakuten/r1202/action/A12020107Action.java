package com.rakuten.r1202.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.ShohinList;
import com.rakuten.util.Utility;

public class A12020107Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();

	protected void exec() throws Exception {
		List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");
		for (F120201Detail detailInfo : detailList) {
			boolean isSuccess = a120201Common.saveDetail(detailInfo,
					f120201.getShumokuid());
			if (isSuccess) {
				f120201.setSuccessFlg("1");
				detailInfo.setHozonFlg("1");
			}
		}
		List<ShohinList> shohinList = a120201Common.getshohinList(detailList,
				f120201);
		f120201.setShohinList(shohinList);
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
		List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");
		for (F120201Detail detailInfo : detailList) {
			if (detailInfo.getShohinbango().equals(detail.getShohinbango())) {
				detailInfo = detail;
			}

			String shohinmei = detail.getShohinmei_jp();
			String pccopy = detail.getPckyachikopi();
			String mbcopy = detail.getMobairukyachikopi();
			if (!Utility.isEmptyString(shohinmei)) {
				if (Utility.getLength(shohinmei) > 255) {
					addError(null, detail.getShohinbango() + "商品名(日文)长度超过");
				}
			}
			if (!Utility.isEmptyString(pccopy)) {
				if (Utility.getLength(pccopy) > 174) {
					addError(null, detail.getShohinbango() + "PC用キャッチコピー长度超过");
				}
			}
			if (!Utility.isEmptyString(mbcopy)) {
				if (Utility.getLength(mbcopy) > 60) {
					addError(null, detail.getShohinbango() + "モバイル用キャッチコピー长度超过");
				}
			}

		}

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
