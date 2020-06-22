package shohinmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import batch.bean.StockBean;

public class YahooStockBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<StockBean> stockBeanList = new ArrayList<StockBean>();
	private String shohinbango = null;

	public List<StockBean> getStockBeanList() {
		return stockBeanList;
	}

	public void setStockBeanList(List<StockBean> stockBeanList) {
		this.stockBeanList = stockBeanList;
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

}
