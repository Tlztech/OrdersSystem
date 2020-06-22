package com.rakuten.common.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.bean.ShohinBean;
import com.rakuten.common.bean.ShohinInfoBean;
import com.rakuten.common.bean.YahooShohinBean;
import com.rakuten.common.bean.YahooShohinCsvBean;
import com.rakuten.common.bean.YahooShohinQuatityCsvBean;
import com.rakuten.util.Utility;

public class RakutenToYahoo {

	public List<ShohinBean> getRakutenShohin(String itemCsvPath,
			String itemCatCsvPath, String selectCsvPath) throws Exception {
		File itemCsv = new File(itemCsvPath);
		File itemCatCsv = new File(itemCatCsvPath);
		File selectCsv = new File(selectCsvPath);
		List<ShohinBean> shohinList = Utility.getShohinFromCsv(itemCsv,
				itemCatCsv, selectCsv);
		return shohinList;
	}

	public List<YahooShohinCsvBean> getYahooShohinCsvBeanList(String path)
			throws Exception {
		List<YahooShohinCsvBean> yahooShohinCsvBeanList = new ArrayList<YahooShohinCsvBean>();
		File yahooShohinCsvFile = new File(path);
		List<String[]> csvList = Utility.readCsvFile(yahooShohinCsvFile, true);
		for (String[] shohin : csvList) {
			YahooShohinCsvBean yahooShohinCsvBean = new YahooShohinCsvBean();
			yahooShohinCsvBeanList.add(yahooShohinCsvBean);
			int i = 0;

			yahooShohinCsvBean.setPath(shohin[i++]);
			yahooShohinCsvBean.setName(shohin[i++]);
			yahooShohinCsvBean.setCode(shohin[i++]);
			yahooShohinCsvBean.setSub_code(shohin[i++]);
			yahooShohinCsvBean.setOriginal_price(shohin[i++]);
			yahooShohinCsvBean.setPrice(shohin[i++]);
			yahooShohinCsvBean.setSale_price(shohin[i++]);
			yahooShohinCsvBean.setOptions(shohin[i++]);
			yahooShohinCsvBean.setHeadline(shohin[i++]);
			yahooShohinCsvBean.setCaption(shohin[i++]);
			yahooShohinCsvBean.setAbstract_y(shohin[i++]);
			yahooShohinCsvBean.setExplanation(shohin[i++]);
			yahooShohinCsvBean.setAdditional1(shohin[i++]);
			yahooShohinCsvBean.setAdditional2(shohin[i++]);
			yahooShohinCsvBean.setAdditional3(shohin[i++]);
			yahooShohinCsvBean.setRelevant_links(shohin[i++]);
			yahooShohinCsvBean.setShip_weight(shohin[i++]);
			yahooShohinCsvBean.setTaxable(shohin[i++]);
			yahooShohinCsvBean.setRelease_date(shohin[i++]);
			yahooShohinCsvBean.setTemporary_point_term(shohin[i++]);
			yahooShohinCsvBean.setPoint_code(shohin[i++]);
			yahooShohinCsvBean.setMeta_key(shohin[i++]);
			yahooShohinCsvBean.setMeta_desc(shohin[i++]);
			yahooShohinCsvBean.setTemplate(shohin[i++]);
			yahooShohinCsvBean.setSale_period_start(shohin[i++]);
			yahooShohinCsvBean.setSale_period_end(shohin[i++]);
			yahooShohinCsvBean.setSale_limit(shohin[i++]);
			yahooShohinCsvBean.setSp_code(shohin[i++]);
			yahooShohinCsvBean.setBrand_code(shohin[i++]);
			yahooShohinCsvBean.setPerson_code(shohin[i++]);
			yahooShohinCsvBean.setYahoo_product_code(shohin[i++]);
			yahooShohinCsvBean.setProduct_code(shohin[i++]);
			yahooShohinCsvBean.setJan(shohin[i++]);
			yahooShohinCsvBean.setIsbn(shohin[i++]);
			yahooShohinCsvBean.setDelivery(shohin[i++]);
			yahooShohinCsvBean.setAstk_code(shohin[i++]);
			yahooShohinCsvBean.setCondition(shohin[i++]);
			yahooShohinCsvBean.setTaojapan(shohin[i++]);
			yahooShohinCsvBean.setProduct_category(shohin[i++]);
			yahooShohinCsvBean.setSpec1(shohin[i++]);
			yahooShohinCsvBean.setSpec2(shohin[i++]);
			yahooShohinCsvBean.setSpec3(shohin[i++]);
			yahooShohinCsvBean.setSpec4(shohin[i++]);
			yahooShohinCsvBean.setSpec5(shohin[i++]);
			yahooShohinCsvBean.setDisplay(shohin[i++]);
			yahooShohinCsvBean.setSort(shohin[i++]);
			if (i + 1 == csvList.size()) {
				yahooShohinCsvBean.setSp_additional(shohin[i++]);
			}
		}
		return yahooShohinCsvBeanList;
	}

	public void writeYahooCsv(List<YahooShohinCsvBean> yahooShohinCsvBeanList,
			String path) throws Exception {
		List<String[]> csvList = new ArrayList<String[]>();
		String[] title = new String[] { "path", "name", "code", "sub-code",
				"original-price", "price", "sale-price", "options", "headline",
				"caption", "abstract", "explanation", "additional1",
				"additional2", "additional3", "relevant-links", "ship-weight",
				"taxable", "release-date", "temporary-point-term",
				"point-code", "meta-key", "meta-desc", "template",
				"sale-period-start", "sale-period-end", "sale-limit",
				"sp-code", "brand-code", "person-code", "yahoo-product-code",
				"product-code", "jan", "isbn", "delivery", "astk-code",
				"condition", "taojapan", "product-category", "spec1", "spec2",
				"spec3", "spec4", "spec5", "display", "sort", "sp-additional" };
		csvList.add(title);
		for (YahooShohinCsvBean yahooShohinCsvBean : yahooShohinCsvBeanList) {
			String[] shohin = new String[47];
			csvList.add(shohin);
			int i = 0;
			shohin[i++] = yahooShohinCsvBean.getPath();
			shohin[i++] = yahooShohinCsvBean.getName();
			shohin[i++] = yahooShohinCsvBean.getCode();
			shohin[i++] = yahooShohinCsvBean.getSub_code();
			shohin[i++] = yahooShohinCsvBean.getOriginal_price();
			shohin[i++] = yahooShohinCsvBean.getPrice();
			shohin[i++] = yahooShohinCsvBean.getSale_price();
			shohin[i++] = yahooShohinCsvBean.getOptions();
			shohin[i++] = yahooShohinCsvBean.getHeadline();
			shohin[i++] = yahooShohinCsvBean.getCaption();
			shohin[i++] = yahooShohinCsvBean.getAbstract_y();
			shohin[i++] = yahooShohinCsvBean.getExplanation();
			shohin[i++] = yahooShohinCsvBean.getAdditional1();
			shohin[i++] = yahooShohinCsvBean.getAdditional2();
			shohin[i++] = yahooShohinCsvBean.getAdditional3();
			shohin[i++] = yahooShohinCsvBean.getRelevant_links();
			shohin[i++] = yahooShohinCsvBean.getShip_weight();
			shohin[i++] = yahooShohinCsvBean.getTaxable();
			shohin[i++] = yahooShohinCsvBean.getRelease_date();
			shohin[i++] = yahooShohinCsvBean.getTemporary_point_term();
			shohin[i++] = yahooShohinCsvBean.getPoint_code();
			shohin[i++] = yahooShohinCsvBean.getMeta_key();
			shohin[i++] = yahooShohinCsvBean.getMeta_desc();
			shohin[i++] = yahooShohinCsvBean.getTemplate();
			shohin[i++] = yahooShohinCsvBean.getSale_period_start();
			shohin[i++] = yahooShohinCsvBean.getSale_period_end();
			shohin[i++] = yahooShohinCsvBean.getSale_limit();
			shohin[i++] = yahooShohinCsvBean.getSp_code();
			shohin[i++] = yahooShohinCsvBean.getBrand_code();
			shohin[i++] = yahooShohinCsvBean.getPerson_code();
			shohin[i++] = yahooShohinCsvBean.getYahoo_product_code();
			shohin[i++] = yahooShohinCsvBean.getProduct_code();
			shohin[i++] = yahooShohinCsvBean.getJan();
			shohin[i++] = yahooShohinCsvBean.getIsbn();
			shohin[i++] = yahooShohinCsvBean.getDelivery();
			shohin[i++] = yahooShohinCsvBean.getAstk_code();
			shohin[i++] = yahooShohinCsvBean.getCondition();
			shohin[i++] = yahooShohinCsvBean.getTaojapan();
			shohin[i++] = yahooShohinCsvBean.getProduct_category();
			shohin[i++] = yahooShohinCsvBean.getSpec1();
			shohin[i++] = yahooShohinCsvBean.getSpec2();
			shohin[i++] = yahooShohinCsvBean.getSpec3();
			shohin[i++] = yahooShohinCsvBean.getSpec4();
			shohin[i++] = yahooShohinCsvBean.getSpec5();
			shohin[i++] = yahooShohinCsvBean.getDisplay();
			shohin[i++] = yahooShohinCsvBean.getSort();
			shohin[i++] = yahooShohinCsvBean.getSp_additional();
		}
		Utility.writeCsvFile(csvList, path);
	}

	public YahooShohinBean convertRakutenToYahooUpdate(
			List<ShohinBean> rakutenShohinList,
			List<YahooShohinCsvBean> yahooShohinCsvBeanList,
			List<YahooShohinQuatityCsvBean> yahooShohinQuatityCsvBeanList) {

		for (YahooShohinCsvBean yahooShohin : yahooShohinCsvBeanList) {
			String shohinbango = yahooShohin.getCode();
			for (ShohinBean rakutenShohin : rakutenShohinList) {
				if (shohinbango.equals(rakutenShohin.getShohinInfoBean()
						.getShouhinkanribango())) {
					ShohinInfoBean rakutenShohinInfo = rakutenShohin
							.getShohinInfoBean();
					yahooShohin.setSp_additional(rakutenShohinInfo
							.getMobairuyoushouhinsetumeibun());
					String pcsetumeibun = rakutenShohinInfo
							.getPcyouhanbaisetumeibun();
					if (pcsetumeibun.indexOf("<table") > 0) {
						pcsetumeibun = pcsetumeibun.substring(
								pcsetumeibun.indexOf("<table")).replace(
								"<br/>", "");
					}

					String picUrl = "";
					List<String> picList = new ArrayList<String>();
					String pcsetumeibuntemp = pcsetumeibun;
					String baseUrl = "http://shopping.c.yimg.jp/lib/123mart/";

					while (pcsetumeibuntemp.contains(".jpg")) {
						pcsetumeibuntemp = pcsetumeibuntemp
								.substring(pcsetumeibuntemp.indexOf("http"));
						picUrl = pcsetumeibuntemp.substring(0,
								pcsetumeibuntemp.indexOf(".jpg") + 4);
						pcsetumeibuntemp = pcsetumeibuntemp
								.substring(pcsetumeibuntemp.indexOf(".jpg") + 4);
						picList.add(picUrl);
					}

					for (String pic : picList) {
						String kachoshi = pic.substring(pic.lastIndexOf("."));
						String picName = pic.substring(0, pic.lastIndexOf("."));
						picName = picName.substring(pic.lastIndexOf("/") + 1);
						if ("common2".equals(picName)
								|| "model_common1".equals(picName)
								|| "review".equals(picName)
								|| "size-1".equals(picName)
								|| "xfpy_common".equals(picName)) {
							pcsetumeibun = pcsetumeibun.replace(pic, baseUrl
									+ picName + kachoshi);
						} else {
							pcsetumeibun = pcsetumeibun.replace(pic, baseUrl
									+ shohinbango + "_" + picName + kachoshi);
						}
					}
					yahooShohin.setAdditional1(pcsetumeibun);

				}
			}
			yahooShohin.setOptions(yahooShohin.getOptions().replace(
					"\n\nレビュー記入お願い はい、書きます！", ""));
			if (yahooShohin.getAdditional1().indexOf("<table") > 0) {
				yahooShohin
						.setAdditional1(yahooShohin.getAdditional1().substring(
								yahooShohin.getAdditional1().indexOf("<table")));
			}

			yahooShohin.setOriginal_price("");

		}

		YahooShohinBean yahooShohinBean = new YahooShohinBean();
		yahooShohinBean.setYahooShohinCsvBeanList(yahooShohinCsvBeanList);
		yahooShohinBean
				.setYahooShohinQuatityCsvBeanList(yahooShohinQuatityCsvBeanList);
		return yahooShohinBean;

	}
}
