package batch.bean;

import java.io.Serializable;

public class StockBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String commodity_id = null;
	String detail_no = null;
	String detail_name_yoko = null;
	String detail_name_shitaga = "";
	int stock_jp = 0;
	int stock_jp_kano = -1;
	int stock_unsochu = 0;
	int stock_unsochu_kano = -1;
	int stock_sh = 0;
	int stock_sh_kano = 0;
	int stock_nyukachu = 0;
	int stock_nyukachu_kano = -1;
	String jinhuoshang = null;
	boolean nyukafukaFlg = false;

	public String getJinhuoshang() {
		return jinhuoshang;
	}

	public void setJinhuoshang(String jinhuoshang) {
		this.jinhuoshang = jinhuoshang;
	}

	public String getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}

	public String getDetail_no() {
		return detail_no;
	}

	public void setDetail_no(String detail_no) {
		this.detail_no = detail_no;
	}

	public String getDetail_name_yoko() {
		return detail_name_yoko;
	}

	public void setDetail_name_yoko(String detail_name_yoko) {
		this.detail_name_yoko = detail_name_yoko;
	}

	public String getDetail_name_shitaga() {
		return detail_name_shitaga;
	}

	public void setDetail_name_shitaga(String detail_name_shitaga) {
		this.detail_name_shitaga = detail_name_shitaga;
	}

	public int getStock_jp() {
		return stock_jp;
	}

	public void setStock_jp(int stock_jp) {
		this.stock_jp = stock_jp;
	}

	public int getStock_jp_kano() {
		return stock_jp_kano;
	}

	public void setStock_jp_kano(int stock_jp_kano) {
		this.stock_jp_kano = stock_jp_kano;
	}

	public int getStock_unsochu() {
		return stock_unsochu;
	}

	public void setStock_unsochu(int stock_unsochu) {
		this.stock_unsochu = stock_unsochu;
	}

	public int getStock_unsochu_kano() {
		return stock_unsochu_kano;
	}

	public void setStock_unsochu_kano(int stock_unsochu_kano) {
		this.stock_unsochu_kano = stock_unsochu_kano;
	}

	public int getStock_sh() {
		return stock_sh;
	}

	public void setStock_sh(int stock_sh) {
		this.stock_sh = stock_sh;
	}

	public int getStock_sh_kano() {
		return stock_sh_kano;
	}

	public void setStock_sh_kano(int stock_sh_kano) {
		this.stock_sh_kano = stock_sh_kano;
	}

	public int getStock_nyukachu() {
		return stock_nyukachu;
	}

	public void setStock_nyukachu(int stock_nyukachu) {
		this.stock_nyukachu = stock_nyukachu;
	}

	public int getStock_nyukachu_kano() {
		return stock_nyukachu_kano;
	}

	public void setStock_nyukachu_kano(int stock_nyukachu_kano) {
		this.stock_nyukachu_kano = stock_nyukachu_kano;
	}

	public boolean isNyukafukaFlg() {
		return nyukafukaFlg;
	}

	public void setNyukafukaFlg(boolean nyukafukaFlg) {
		this.nyukafukaFlg = nyukafukaFlg;
	}

}
