package shohinmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import shohinmodel.bean.Item;
import shohinmodel.common.EventCommon;
import shohinmodel.common.Shohincommon;

import com.rakuten.util.Utility;

public class Testtttt {

	public static void main(String[] args) throws Exception {
		EventCommon common = new EventCommon();
		List<String> shohinbangoList_temp = common.getNoSaleShohinList(30);
		List<String> shohinbangoList = new ArrayList<String>();
		for (String shohinbango : shohinbangoList_temp) {
			String bango = Utility.getCommodityId(shohinbango);
			boolean ariFlg = false;
			for (String go : shohinbangoList) {
				if (go.equals(bango)) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				shohinbangoList.add(bango);
			}
		}

		List<String> exceptList1 = new ArrayList<String>();
		exceptList1.add("0101");
		exceptList1.add("sjpj150");
		exceptList1.add("nzfd");
		exceptList1.add("kcwt003");
		exceptList1.add("kcwt002");
		exceptList1.add("kcwt001");
		exceptList1.add("kcwb002");
		exceptList1.add("kcwb001");
		exceptList1.add("kcsp001");
		exceptList1.add("kcpc001");
		exceptList1.add("kcdq001");
		exceptList1.add("10086");

		List<String> exceptList2 = common.getExceptList2();

		List<String> shoriList = new ArrayList<String>();

		for (String shohinbango : shohinbangoList) {
			boolean ariFlg = false;
			for (String except1 : exceptList1) {
				if (except1.equals(shohinbango)) {
					ariFlg = true;
				}
			}
			for (String except2 : exceptList2) {
				if (except2.equals(shohinbango)) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				shoriList.add(shohinbango);
			}
		}
		// List<String> todayListTrend = common.getTodayList("trend777");
		List<String> todayListCover = common.getTodayList("coverforefront");
		// List<String> tommorowListTrend = common.getTommorowList("trend777");
		List<String> tommorowListCover = common
				.getTommorowList("coverforefront");
		Collections.shuffle(shoriList);

		// List<Item> itemListTrendToday = new ArrayList<Item>();
		List<Item> itemListCoverToday = new ArrayList<Item>();
		// List<Item> itemListTrendTomorrow = new ArrayList<Item>();
		List<Item> itemListCoverTomorrow = new ArrayList<Item>();

		Shohincommon shohincommon = new Shohincommon();
		// List<String[]> shohinXmlTrendList = shohincommon
		// .getShohinXmlList("trend777");
		List<String[]> shohinXmlCoverList = shohincommon
				.getShohinXmlList("coverforefront");

		// int i = 0;
		// if (Utility.isEmptyList(todayListTrend)) {
		// for (i = 0; i < shoriList.size(); i++) {
		// String shohinbango = shoriList.get(i);
		// for (String[] shohinXmlTrend : shohinXmlTrendList) {
		// if (shohinbango.equals(shohinXmlTrend[0])) {
		// itemListTrendToday.add(shohincommon
		// .getItemBean(shohinXmlTrend[1]));
		// }
		// }
		// if (itemListTrendToday.size() == 15) {
		// break;
		// }
		// }
		// } else {
		// for (String shohinbango : todayListTrend) {
		// for (String[] shohinXmlTrend : shohinXmlTrendList) {
		// if (shohinbango.equals(shohinXmlTrend[0])) {
		// itemListTrendToday.add(shohincommon
		// .getItemBean(shohinXmlTrend[1]));
		// }
		// }
		// }
		// }
		int j = 0;
		if (Utility.isEmptyList(todayListCover)) {
			for (j = 0; j < shoriList.size(); j++) {
				String shohinbango = shoriList.get(j);
				for (String[] shohinXmlCover : shohinXmlCoverList) {
					if (shohinbango.equals(shohinXmlCover[0])) {
						itemListCoverToday.add(shohincommon
								.getItemBean(shohinXmlCover[1]));
					}
				}
				if (itemListCoverToday.size() == 15) {
					break;
				}
			}
		} else {
			for (String shohinbango : todayListCover) {
				for (String[] shohinXmlCover : shohinXmlCoverList) {
					if (shohinbango.equals(shohinXmlCover[0])) {
						itemListCoverToday.add(shohincommon
								.getItemBean(shohinXmlCover[1]));
					}
				}
			}
		}
		// if (Utility.isEmptyList(tommorowListTrend)) {
		// for (i = j + 1; i < shoriList.size(); i++) {
		// String shohinbango = shoriList.get(i);
		// for (String[] shohinXmlTrend : shohinXmlTrendList) {
		// if (shohinbango.equals(shohinXmlTrend[0])) {
		// itemListTrendTomorrow.add(shohincommon
		// .getItemBean(shohinXmlTrend[1]));
		// }
		// }
		// if (itemListTrendTomorrow.size() == 15) {
		// break;
		// }
		// }
		// } else {
		// for (String shohinbango : tommorowListTrend) {
		// for (String[] shohinXmlTrend : shohinXmlTrendList) {
		// if (shohinbango.equals(shohinXmlTrend[0])) {
		// itemListTrendTomorrow.add(shohincommon
		// .getItemBean(shohinXmlTrend[1]));
		// }
		// }
		// }
		// }
		int i = 0;
		if (Utility.isEmptyList(tommorowListCover)) {
			for (i = j + 1; i < shoriList.size(); i++) {
				String shohinbango = shoriList.get(i);
				for (String[] shohinXmlTrend : shohinXmlCoverList) {
					if (shohinbango.equals(shohinXmlTrend[0])) {
						itemListCoverTomorrow.add(shohincommon
								.getItemBean(shohinXmlTrend[1]));
					}
				}
				if (itemListCoverTomorrow.size() == 15) {
					break;
				}
			}
		} else {
			for (String shohinbango : tommorowListCover) {
				for (String[] shohinXmlTrend : shohinXmlCoverList) {
					if (shohinbango.equals(shohinXmlTrend[0])) {
						itemListCoverTomorrow.add(shohincommon
								.getItemBean(shohinXmlTrend[1]));
					}
				}
			}
		}

		// for (Item item : itemListTrendToday) {
		// System.out.print(item.getItemUrl() + " ");
		// }
		System.out.println();
		for (Item item : itemListCoverToday) {
			System.out.print(item.getItemUrl() + " ");
		}
		// System.out.println();
		// for (Item item : itemListTrendTomorrow) {
		// System.out.print(item.getItemUrl() + " ");
		// }
		System.out.println();
		for (Item item : itemListCoverTomorrow) {
			System.out.print(item.getItemUrl() + " ");
		}
		System.out.println();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();

		String startDay1 = sdf.format(date.getTime());
		String startDataTimeToday = (sdf2.format(date.getTime()) + "T00:00:00+09:00");

		date.add(Calendar.DATE, 1);
		String endDataTimeToday = (sdf2.format(date.getTime()) + "T00:00:00+09:00");
		String startDataTimeTommorrow = (sdf2.format(date.getTime()) + "T00:00:00+09:00");
		String startDay2 = sdf.format(date.getTime());

		date.add(Calendar.DATE, 1);
		String endDataTimeTommorrow = (sdf2.format(date.getTime()) + "T00:00:00+09:00");

		// List<String> yesterdayTrend =
		// common.getYesterdayItemList("trend777");
		List<String> yesterdayCover = common
				.getYesterdayItemList("coverforefront");
		// if (!Utility.isEmptyList(yesterdayTrend)) {
		// for (String shohinbango : yesterdayTrend) {
		// try {
		// common.updateEventItem("trend777", shohinbango, 0, "", "");
		// } catch (NullPointerException e) {
		// continue;
		// }
		// }
		// common.setStock("trend777", yesterdayTrend, true);
		// }
		if (!Utility.isEmptyList(yesterdayCover)) {
			for (String shohinbango : yesterdayCover) {
				try {
					common.updateEventItem("coverforefront", shohinbango, 0,
							"", "");
				} catch (NullPointerException e) {
					continue;
				}
			}
			common.setStock("coverforefront", yesterdayCover, true);
		}

		// common.deleteTesterdayEventItem("trend777");
		common.deleteTesterdayEventItem("coverforefront");

		// if (!Utility.isEmptyList(itemListTrendToday)) {
		// common.addEventItemToDB(itemListTrendToday, startDay1, "trend777");
		// List<String> shoribangoList = new ArrayList<String>();
		// for (Item item : itemListTrendToday) {
		// try {
		// common.updateEventItem("trend777", item.getItemUrl(), 1,
		// startDataTimeToday, endDataTimeToday);
		// shoribangoList.add(item.getItemUrl());
		// } catch (NullPointerException e) {
		// continue;
		// }
		// }
		// common.setStock("trend777", shoribangoList, false);
		// }
		if (!Utility.isEmptyList(itemListCoverToday)) {
			common.addEventItemToDB(itemListCoverToday, startDay1,
					"coverforefront");
			List<String> shoribangoList = new ArrayList<String>();
			for (Item item : itemListCoverToday) {
				try {
					common.updateEventItem("coverforefront", item.getItemUrl(),
							1, startDataTimeToday, endDataTimeToday);
					shoribangoList.add(item.getItemUrl());
				} catch (NullPointerException e) {
					continue;
				}
			}
			common.setStock("coverforefront", shoribangoList, false);
		}
		// if (!Utility.isEmptyList(itemListTrendTomorrow)) {
		//
		// common.addEventItemToDB(itemListTrendTomorrow, startDay2,
		// "trend777");
		// List<String> shoribangoList = new ArrayList<String>();
		// for (Item item : itemListTrendTomorrow) {
		// try {
		// common.updateEventItem("trend777", item.getItemUrl(), 1,
		// startDataTimeTommorrow, endDataTimeTommorrow);
		// shoribangoList.add(item.getItemUrl());
		// } catch (NullPointerException e) {
		// continue;
		// }
		// }
		//
		// common.setStock("trend777", shoribangoList, false);
		// }
		if (!Utility.isEmptyList(itemListCoverTomorrow)) {
			common.addEventItemToDB(itemListCoverTomorrow, startDay2,
					"coverforefront");
			List<String> shoribangoList = new ArrayList<String>();
			for (Item item : itemListCoverTomorrow) {
				try {
					common.updateEventItem("coverforefront", item.getItemUrl(),
							1, startDataTimeTommorrow, endDataTimeTommorrow);
					shoribangoList.add(item.getItemUrl());
				} catch (NullPointerException e) {
					continue;
				}
			}
			common.setStock("coverforefront", shoribangoList, false);
		}

	}
}
