package shohin.bean;

import java.io.Serializable;
import java.util.Date;

public class Point implements Serializable {

	private static final long serialVersionUID = 1L;
	// ポイント変倍率
	private int pointRate;
	// ポイント変倍適応期間（開始）
	private Date pointRateStart;
	// ポイント変倍適応期間（終了）
	private Date pointRateEnd;

	public int getPointRate() {
		return pointRate;
	}

	public void setPointRate(int pointRate) {
		this.pointRate = pointRate;
	}

	public Date getPointRateStart() {
		return pointRateStart;
	}

	public void setPointRateStart(Date pointRateStart) {
		this.pointRateStart = pointRateStart;
	}

	public Date getPointRateEnd() {
		return pointRateEnd;
	}

	public void setPointRateEnd(Date pointRateEnd) {
		this.pointRateEnd = pointRateEnd;
	}

}
