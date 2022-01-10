package com.rakuten.common.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class Ponpareshusei {

	public static void main(String[] args) throws Exception {
		List<String[]> dataList = Utility.readCsvFileJpn(new File("d:\\format_item - 副本.csv"), false);
		List<String[]> shoriList = new ArrayList<String[]>();
		for (String[] data : dataList) {
			String spdata = data[2];
			spdata = spdata.replace("<table align=\"center\">", "<table width=\"100%\" align=\"center\">");
			spdata = spdata.replace("<td><img ", "<td><img width=\"98%\" ");
			spdata = spdata.replace("<table width=\"600px\"", "<table width=\"100%\"");
			spdata = spdata.replace("<tr height=\"35px\">", "<tr>");
			spdata = spdata.replace("<td width=\"120px\"", "<td ");
			spdata = spdata.replace("<table width=\"550px\"", "<table width=\"100%\"");
			spdata = spdata.replace("<td width=\"50px\"", "<td ");
			spdata = spdata.replace("<td width=\"93px\"", "<td ");
			spdata = spdata.replace("<td><img width=\"650px\"", "<td><img width=\"98%\" ");
			spdata = spdata.replace(" width=\"650\"></img></td>", "></img></td>");
			spdata = spdata.replace("<td><img width=\"98%\" width=\"650px\" ", "<td><img width=\"98%\" ");
			spdata = spdata.replace("<table width=\"650px\" style=", "<table width=\"100%\" style=");
			spdata = spdata.replace("<td width=\"20%\" style", "<td style");
			spdata = spdata.replace(
					"<table style=\"font-size:12px\" width=\"550px\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">",
					"<table style=\"font-size:12px\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
			spdata = spdata.replace("<table width=\"780px\">", "<table width=\"100%\">");
			spdata = spdata.replace("<table width=\"650px\" align=\"center\">",
					"<table width=\"100%\" align=\"center\">");
			spdata = spdata.replace(".STYLE3 {font-family: \"MS PGothic\"; font-size: 14px; }", "");
			data[2] = spdata;
			shoriList.add(data);
		}
		Utility.writeCsvFile(shoriList, "d:\\ponpareshusei.csv");
	}

}
