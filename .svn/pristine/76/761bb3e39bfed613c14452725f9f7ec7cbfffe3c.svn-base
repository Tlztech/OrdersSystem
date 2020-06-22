package com.rakuten.r0001.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.ap.CommonAp;
import com.rakuten.r0001.bean.CheckLoginInput;
import com.rakuten.r0001.bean.CheckLoginOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class CheckLoginAp extends CommonAp {

	public CheckLoginOutput execute(CheckLoginInput input) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		CheckLoginOutput output = null;
		try {
			conn = JdbcConnection.getConnection();
			String userName = input.getUserName();
			String password = input.getPassword();
			String sql = SqlUtility.getSql("SQLR0001001");
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}
			output = new CheckLoginOutput();
			if (count > 0) {
				output.setSuccessFlg(true);
			}
			// commit
			conn.commit();
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
