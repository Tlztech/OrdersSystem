package com.rakuten.r0602.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class CopyOfCompare {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String[] replaceList = new String[] { "salesjpj200", "salesjpj201",
				"salesjpj202", "salesjpj203", "salesjpj204", "salesjpj205",
				"salesjpj206", "salesjpj207", "salesjpj208", "salesjpj209",
				"salesjpj210", "salesjpj212", "salesjpj071", "salesjpj074",
				"salesjpj076", "salesjpj077", "salesjpj079", "salesjpj083",
				"salesjpj081", "salesjpj080", "salesjpj082", "salesjpj084",
				"salesjpj065", "salesjpj066", "salesjpj067", "salesjpj068",
				"salesjpj069", "salesjpj070", "salesjpj072", "salesjpj073",
				"salesjpj075", "salesjpj078" };

		File file = new File("C:\\Users\\Matsuda\\Desktop\\14");
		String[] fileList = file.list();
		for (String str : fileList) {
			for (String moto : replaceList) {
				if (("sale" + str).equals(moto + ".jpg")) {
					File file2 = new File("C:\\Users\\Matsuda\\Desktop\\14\\"
							+ str);
					if (file2.exists()) {
						file2.renameTo(new File(
								"C:\\Users\\Matsuda\\Desktop\\14\\" + "sale"
										+ str));
					}
				}
			}

		}

	}
}
