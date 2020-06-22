package shohinmodel.batch;

import java.util.Calendar;
import java.util.TimerTask;

import page.pagetools.Left2;

public class UpdateLeft2Batch extends TimerTask {
	/**
	 * 　ReceiveEmail类测试
	 */

	@Override
	public void run() {
		try {
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			if (3 == hour) {
				Left2 left2 = new Left2();
				left2.basicUpdate("coverforefront", new String[] {"冬"});
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
