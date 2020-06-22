package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class SetWeight2 {
	public static void main(String[] args) throws Exception {
		List<String[]> datalist = Utility.readCsvFile(new File("d:\\yahooNouki.csv"), false);

		List<String[]> shoriList = Utility.readCsvFile(new File("d:\\ddpeggggg.csv"), true);
		for (String[] data : datalist) {
			for (String[] shori : shoriList) {
				if (data[2].equals(shori[0])) {
					data[13] = shori[1];
				}
			}
		}
		Utility.writeCsvFile(datalist, "D:\\ddpeggggfafsg.csv");

	}

}
