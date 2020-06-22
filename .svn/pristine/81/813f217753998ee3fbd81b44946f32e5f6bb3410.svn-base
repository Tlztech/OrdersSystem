package shohinmodel;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

import shohinmodel.bean.Category;
import shohinmodel.bean.CategoryList;
import shohinmodel.bean.Item;
import shohinmodel.common.Shohincommon;

public class CopyOftest {

	public static void main(String[] args) throws Exception {

		Shohincommon common = new Shohincommon();
//		CategoryList categoryTrend = common.getCategoryList(common
//				.getCategoryListXml("trend777"));
		CategoryList categoryCover = common.getCategoryList(common
				.getCategoryListXml("coverforefront"));

//		int[] trendMainCateId = common.getMainCateIdTrend();
		int[] coverMainCateId = common.getMainCateIdCover();

//		List<Category> categoryListTrend = categoryTrend.getCategory();
		List<Category> categoryListCover = categoryCover.getCategory();

//		List<String[]> categoryHtmlListTrend = new ArrayList<String[]>();
		List<String[]> categoryHtmlListCover = new ArrayList<String[]>();

//		List<Item> commonItemsTrend = common.getCommonRankList("trend777");
		List<Item> commonItemsCover = common
				.getCommonRankList("coverforefront");
//		List<String[]> shohinxmlListTrend = common.getShohinXmlList("trend777");
		List<String[]> shohinxmlListCover = common
				.getShohinXmlList("coverforefront");
//		for (int cateId : trendMainCateId) {
//			List<Integer> cateidlist = common.getDownCateId(cateId,
//					categoryTrend, "trend777");
//			List<Item> mainitems = common.getMainRankList("trend777",
//					cateidlist, shohinxmlListTrend);
//			for (int cateidmain : cateidlist) {
//				categoryHtmlListTrend.add(new String[] {
//						String.valueOf(cateidmain),
//						common.getCategoryHtml(mainitems, commonItemsTrend,
//								"trend777") });
//			}
//		}

		for (int cateId : coverMainCateId) {
			List<Integer> cateidlist = common.getDownCateId(cateId,
					categoryCover, "coverforefront");
			List<Item> mainitems = common.getMainRankList("coverforefront",
					cateidlist, shohinxmlListCover);
			for (int cateidmain : cateidlist) {
				categoryHtmlListCover.add(new String[] {
						String.valueOf(cateidmain),
						common.getCategoryHtml(mainitems, commonItemsCover,
								"coverforefront") });
			}
		}

//		List<Integer> cateIdListTrend = new ArrayList<Integer>();
		List<Integer> cateIdListCover = new ArrayList<Integer>();
//		for (Category cate : categoryListTrend) {
//			getAllcateId(cate, cateIdListTrend);
//		}
		for (Category cate : categoryListCover) {
			getAllcateId(cate, cateIdListCover);
		}

//		for (int cateid : cateIdListTrend) {
//			boolean ariFlg = false;
//			for (String[] cateinfo : categoryHtmlListTrend) {
//				if (cateid == Integer.valueOf(cateinfo[0])) {
//					ariFlg = true;
//				}
//			}
//			if (!ariFlg) {
//				categoryHtmlListTrend.add(new String[] {
//						String.valueOf(cateid),
//						common.getCategoryHtml(null, commonItemsTrend,
//								"trend777") });
//			}
//		}
		for (int cateid : cateIdListCover) {
			boolean ariFlg = false;
			for (String[] cateinfo : categoryHtmlListCover) {
				if (cateid == Integer.valueOf(cateinfo[0])) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				categoryHtmlListCover.add(new String[] {
						String.valueOf(cateid),
						common.getCategoryHtml(null, commonItemsCover,
								"coverforefront") });
			}
		}
		String updatexml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<request>"
				+ "<categoryUpdateRequest>"
				+ "<categoryId>&{categoryId}</categoryId><category><categoryContent>"
				+ "<smartPhoneText><![CDATA[&{smartPhoneText}]]></smartPhoneText>"
				+ "</categoryContent></category></categoryUpdateRequest>"
				+ "</request>");
		// for (String[] info : categoryHtmlListTrend) {
		// common.updateCategory(
		// "trend777",
		// updatexml.replace("&{categoryId}", info[0]).replace(
		// "&{smartPhoneText}", info[1]));
		// }
		for (String[] info : categoryHtmlListCover) {
			common.updateCategory(
					"coverforefront",
					updatexml.replace("&{categoryId}", info[0]).replace(
							"&{smartPhoneText}", info[1]));
		}

	}

	private static void getAllcateId(Category cate, List<Integer> cateIdList) {
		if (cate != null) {
			cateIdList.add(cate.getCategoryId());
			if (cate.getChildCategories() != null) {
				List<Category> categoryList = cate.getChildCategories()
						.getCategoryList();
				if (!Utility.isEmptyList(categoryList)) {
					for (Category ccate : categoryList) {
						getAllcateId(ccate, cateIdList);
					}
				}
			}
		}

	}
}
