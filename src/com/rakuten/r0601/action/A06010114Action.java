package com.rakuten.r0601.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.DeleteWayVBillAp;
import com.rakuten.r0601.form.F060101;

public class A06010114Action extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	F060101 f060101 = null;

	public F060101 getF060101() {
		return f060101;
	}

	public void setF060101(F060101 f060101) {
		this.f060101 = f060101;
	}

	@Override
	protected void exec() throws Exception {
		String deliverDay = f060101.getDeliverDay();
		DeleteWayVBillAp deleteWayVBillAp = new DeleteWayVBillAp();
		int result = deleteWayVBillAp.execute(deliverDay.replace("-", ""));
		f060101.setQueryResult(result);
	}

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
