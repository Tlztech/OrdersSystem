package com.rakuten.r0901.action;

import java.util.TimerTask;

import com.rakuten.r0901.action.A09010105Action;

public class GetbankInfo extends TimerTask {

	/**
	 * 　ReceiveEmail类测试
	 */
	public void excute() throws Exception {
		A09010105Action action = new A09010105Action();
		action.setLogKey("12345");
		action.exec();

	}

	@Override
	public void run() {
		try {
			excute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
