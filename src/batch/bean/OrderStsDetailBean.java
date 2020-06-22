package batch.bean;

import java.io.Serializable;

public class OrderStsDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String juchubango;

	private String shohinbango;

	private String ordersts;
	private String kosu;

	public String getJuchubango() {
		return juchubango;
	}

	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public String getOrdersts() {
		return ordersts;
	}

	public void setOrdersts(String ordersts) {
		this.ordersts = ordersts;
	}

	public String getKosu() {
		return kosu;
	}

	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

}
