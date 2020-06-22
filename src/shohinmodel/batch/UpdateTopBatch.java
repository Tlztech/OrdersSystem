package shohinmodel.batch;

import java.util.Calendar;
import java.util.TimerTask;

import page.pagetools.Top;

public class UpdateTopBatch extends TimerTask {
	/**
	 * 　ReceiveEmail类测试
	 */

	@Override
	public void run() {
		try {
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			if (4 == hour) {
				Top top = new Top();
				top.basicUpdate("trend777");
				top.basicUpdate("coverforefront");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
