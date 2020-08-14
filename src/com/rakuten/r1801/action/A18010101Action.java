package com.rakuten.r1801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1801.form.AlbumList;
import com.rakuten.r1801.form.F180101;
import com.rakuten.util.JdbcConnection;

public class A18010101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F180101 f180101 = null;

	protected void exec() throws Exception {
		f180101 = new F180101();
		List<AlbumList> albumList = new ArrayList<AlbumList>();
//		f180101.setAlbumList(albumList);

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM TBL00032 WHERE 1=1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				AlbumList album = new AlbumList();
				albumList.add(album);

				album.setShumokuId(rs.getString("SHUMOKUID"));
				album.setName(rs.getString("SHUMOKUMEI"));

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
		setTitle("V180101:店铺管理");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F180101 getF180101() {
		return f180101;
	}

	public void setF180101(F180101 f180101) {
		this.f180101 = f180101;
	}

}
