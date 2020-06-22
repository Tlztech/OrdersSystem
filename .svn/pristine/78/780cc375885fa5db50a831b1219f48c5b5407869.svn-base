package com.rakuten.r1701.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1701.form.AlbumList;
import com.rakuten.r1701.form.F170101;
import com.rakuten.util.JdbcConnection;

public class A17010101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F170101 f170101 = null;

	protected void exec() throws Exception {
		f170101 = new F170101();
		List<AlbumList> albumList = new ArrayList<AlbumList>();
		f170101.setAlbumList(albumList);

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
		setTitle("V170101:商品关键字分类");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F170101 getF170101() {
		return f170101;
	}

	public void setF170101(F170101 f170101) {
		this.f170101 = f170101;
	}

}
