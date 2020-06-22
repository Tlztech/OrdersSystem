package com.rakuten.r1202.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.ShohinList;
import com.rakuten.util.Utility;

public class A12020108Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();

	protected void exec() throws Exception {
		boolean isSuccess = a120201Common.saveDetail(detail,
				f120201.getShumokuid());
		if (isSuccess) {
			f120201.setSuccessFlg("1");
			detail.setHozonFlg("1");
			String shohinbango_moto = detail.getShohinbango_moto();
			String shohinbango = detail.getShohinbango();
			List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");

			if (Utility.isEmptyString(shohinbango_moto)) {
				if (detailList == null) {
					detailList = new ArrayList<F120201Detail>();
				}
				detailList.add(detail);
			} else {
				for (int i = 0; i < detailList.size(); i++) {
					if (detailList.get(i).getShohinbango()
							.equals(shohinbango_moto)) {
						detailList.set(i, detail);
					}
				}
			}
			f120201.setShohinbango_selected(shohinbango);
			detail.setShohinbango_moto(shohinbango);

			List<ShohinList> shohinList = a120201Common.getshohinList(
					detailList, f120201);
			f120201.setShohinList(shohinList);

			setSessionAttribute("f120201DetailList", detailList);

		} else {
			f120201.setSuccessFlg("2");
		}

	}

	protected void init() throws Exception {
		setTitle("V120201:文案编辑:  "
				+ a120201Common.getShumokuname(f120201.getShumokuid()));
	}

	protected void isValidated() throws Exception {
		String shohinbango_moto = detail.getShohinbango_moto();
		String shohinbango = detail.getShohinbango();
		if (Utility.isEmptyString(shohinbango_moto)
				|| (!Utility.isEmptyString(shohinbango_moto) && !shohinbango_moto
						.equals(shohinbango))) {
			if (a120201Common.isShohinExist(shohinbango)) {
				addError(null, "商品已存在，不能重复添加");
				return;
			}
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
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
