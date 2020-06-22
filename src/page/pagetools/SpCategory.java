package page.pagetools;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

import shohinmodel.bean.Category;
import shohinmodel.bean.CategoryList;
import shohinmodel.bean.Item;
import shohinmodel.common.Shohincommon;

public class SpCategory {

	public void basicUpdate() throws Exception {

		Shohincommon common = new Shohincommon();

		CategoryList categoryCover = common.getCategoryList(common.getCategoryListXml("coverforefront"));

		int[] coverMainCateId = common.getMainCateIdCover();

		List<Category> categoryListCover = categoryCover.getCategory();

		List<String[]> categoryHtmlListCover = new ArrayList<String[]>();

		List<Item> commonItemsCover = common.getCommonRankList("coverforefront");

		List<String[]> shohinxmlListCover = common.getShohinXmlList("coverforefront");

		for (int cateId : coverMainCateId) {
			List<Integer> cateidlist = common.getDownCateId(cateId, categoryCover, "coverforefront");
			List<Item> mainitems = common.getMainRankList("coverforefront", cateidlist, shohinxmlListCover);
			for (int cateidmain : cateidlist) {
				categoryHtmlListCover.add(new String[] { String.valueOf(cateidmain),
						common.getCategoryHtml(mainitems, commonItemsCover, "coverforefront") });
			}
		}

		List<Integer> cateIdListCover = new ArrayList<Integer>();

		for (Category cate : categoryListCover) {
			getAllcateId(cate, cateIdListCover);
		}

		for (int cateid : cateIdListCover) {
			boolean ariFlg = false;
			for (String[] cateinfo : categoryHtmlListCover) {
				if (cateid == Integer.valueOf(cateinfo[0])) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				categoryHtmlListCover.add(new String[] { String.valueOf(cateid),
						common.getCategoryHtml(null, commonItemsCover, "coverforefront") });
			}
		}
		String updatexml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<request>" + "<categoryUpdateRequest>"
				+ "<categoryId>&{categoryId}</categoryId><category><categoryContent>"
				+ "<smartPhoneText><![CDATA[&{smartPhoneText}]]></smartPhoneText>"
				+ "</categoryContent></category></categoryUpdateRequest>" + "</request>");

		for (String[] info : categoryHtmlListCover) {
if(Integer.valueOf(info[0])>500){
			common.updateCategory("coverforefront",
					updatexml.replace("&{categoryId}", info[0]).replace("&{smartPhoneText}", info[1]));
		}}

	}

	private void getAllcateId(Category cate, List<Integer> cateIdList) {
		if (cate != null) {
			cateIdList.add(cate.getCategoryId());
			if (cate.getChildCategories() != null) {
				List<Category> categoryList = cate.getChildCategories().getCategoryList();
				if (!Utility.isEmptyList(categoryList)) {
					for (Category ccate : categoryList) {
						getAllcateId(ccate, cateIdList);
					}
				}
			}
		}

	}
}
