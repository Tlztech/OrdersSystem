package com.rakuten.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlUtility {
	public static String getSql(String sqlId) throws IOException {
		InputStream in = SqlUtility.class.getClassLoader().getResourceAsStream(
				"sql.properties");
		Properties p = new Properties();
		p.load(in);
		String sql = (String) p.get(sqlId);
		return sql;
	}

}
