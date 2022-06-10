package com.rakuten.r1601.action;

import java.io.File;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.Utility;

public class A16010114Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F160101 f160101 = null;
	private File inputPath = null;
	private OrderCommon orderCommon = new OrderCommon();
	
	@Override
	protected void init() throws Exception {
		setTitle("V160101:小工具");

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		List<String[]> shoriList = Utility.readCsvFileJpn(inputPath, false);
		try {
			
			for(int i=0; i<shoriList.size(); i++){
				orderCommon.setStatus("1", "6", shoriList.get(i)[0]);
            }
			
			addError(null, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			addError(null, "操作失敗");
		} finally {
			
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub
		if (null == inputPath) {
			addError(null, "订单编号CSVファイル選択が必要");
		}
	}
	
	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

	public File getInputPath() {
		return inputPath;
	}

	public void setInputPath(File inputPath) {
		this.inputPath = inputPath;
	}
	
}
