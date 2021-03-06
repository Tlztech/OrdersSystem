package com.rakuten.p0101.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.p0101.bean.GetCommodityCategoryByFatherIdInput;
import com.rakuten.p0101.bean.GetCommodityCategoryOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class GetCommodityCategoryByFatherIdAp {

	public List<GetCommodityCategoryOutput> execute(
			GetCommodityCategoryByFatherIdInput input) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		GetCommodityCategoryOutput output = null;
		List<GetCommodityCategoryOutput> outputList = new ArrayList<GetCommodityCategoryOutput>();
		try {
			conn = JdbcConnection.getConnection();
			String fatherID = input.getID();
			String sql = SqlUtility.getSql("SQLR0001002");
			ps = conn.prepareStatement(sql);
			ps.setString(1, fatherID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				output = new GetCommodityCategoryOutput();
				outputList.add(output);
				output.setCategoryId(rs.getString("CATEGORY_ID"));
				output.setCategoryName(rs.getString("CATEGORY_NAME"));
				output.setFatherCategoryId(rs.getString("FATHER_CATEGORY_ID"));
				output.setSozaiChn(rs.getString("SOZAI_CHN"));
				output.setSozaiJpn(rs.getString("SOZAI_JPN"));
				output.setNameJpn(rs.getString("NAME_JPN"));
				output.setKakaku(rs.getString("KAKAKU"));

			}

			// commit
			conn.commit();
			return outputList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
