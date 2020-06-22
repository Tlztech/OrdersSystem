package com.rakuten.r1201.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1201.form.AlbumList;
import com.rakuten.r1201.form.F120101;
import com.rakuten.util.JdbcConnection;

public class A12010101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F120101 f120101 = null;

	protected void exec() throws Exception {
		f120101 = new F120101();
		List<AlbumList> albumList = new ArrayList<AlbumList>();
		f120101.setAlbumList(albumList);

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM TBL00022 WHERE 1=1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				AlbumList album = new AlbumList();
				albumList.add(album);

				album.setShumokuId(rs.getString("SHUMOKUID"));
				album.setName(rs.getString("SHUMOKUMEI"));

				sql = "SELECT COUNT(*) COUNT FROM TBL00023 WHERE SHUMOKUID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, rs.getString("SHUMOKUID"));
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					album.setKosu(rs2.getString("COUNT"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V120101:商品专辑列表");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F120101 getF120101() {
		return f120101;
	}

	public void setF120101(F120101 f120101) {
		this.f120101 = f120101;
	}
}
