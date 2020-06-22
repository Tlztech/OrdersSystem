package com.rakuten.r1501.bean;

import java.io.Serializable;

public class AlipayCsvBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 账务流水号
	private String zhangwuliushuihao = null;
	// 业务流水号
	private String yewuliushuihao = null;
	// 商户订单号
	private String shanghudingdanhao = null;
	// 商品名称
	private String shangpinmingcheng = null;
	// 发生时间
	private String fashengshijian = null;
	// 对方账号
	private String duifangzhanghao = null;
	// 收入金额（+元）
	private String shourujine = null;
	// 支出金额（-元）
	private String zhichujine = null;
	// 账户余额（元）
	private String zhanghuyue = null;
	// 交易渠道
	private String jiaoyiqudao = null;
	// 业务类型
	private String yewuleixing = null;
	// 备注
	private String beizhu = null;

	public String getZhangwuliushuihao() {
		return zhangwuliushuihao;
	}

	public void setZhangwuliushuihao(String zhangwuliushuihao) {
		this.zhangwuliushuihao = zhangwuliushuihao;
	}

	public String getYewuliushuihao() {
		return yewuliushuihao;
	}

	public void setYewuliushuihao(String yewuliushuihao) {
		this.yewuliushuihao = yewuliushuihao;
	}

	public String getShanghudingdanhao() {
		return shanghudingdanhao;
	}

	public void setShanghudingdanhao(String shanghudingdanhao) {
		this.shanghudingdanhao = shanghudingdanhao;
	}

	public String getShangpinmingcheng() {
		return shangpinmingcheng;
	}

	public void setShangpinmingcheng(String shangpinmingcheng) {
		this.shangpinmingcheng = shangpinmingcheng;
	}

	public String getFashengshijian() {
		return fashengshijian;
	}

	public void setFashengshijian(String fashengshijian) {
		this.fashengshijian = fashengshijian;
	}

	public String getDuifangzhanghao() {
		return duifangzhanghao;
	}

	public void setDuifangzhanghao(String duifangzhanghao) {
		this.duifangzhanghao = duifangzhanghao;
	}

	public String getShourujine() {
		return shourujine;
	}

	public void setShourujine(String shourujine) {
		this.shourujine = shourujine;
	}

	public String getZhichujine() {
		return zhichujine;
	}

	public void setZhichujine(String zhichujine) {
		this.zhichujine = zhichujine;
	}

	public String getZhanghuyue() {
		return zhanghuyue;
	}

	public void setZhanghuyue(String zhanghuyue) {
		this.zhanghuyue = zhanghuyue;
	}

	public String getJiaoyiqudao() {
		return jiaoyiqudao;
	}

	public void setJiaoyiqudao(String jiaoyiqudao) {
		this.jiaoyiqudao = jiaoyiqudao;
	}

	public String getYewuleixing() {
		return yewuleixing;
	}

	public void setYewuleixing(String yewuleixing) {
		this.yewuleixing = yewuleixing;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

}
