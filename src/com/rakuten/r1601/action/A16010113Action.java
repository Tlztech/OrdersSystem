package com.rakuten.r1601.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A16010113Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F160101 f160101 = null;
	private File inputPath = null;
	
	@Override
	protected void init() throws Exception {
		setTitle("V160101:小工具");

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		List<String[]> shoriList = Utility.readCsvFile(inputPath, false);
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";
		String condition = "";
		try {
			
			for(int i=0; i<shoriList.size(); i++){
				condition+="'"+shoriList.get(i)[0]+"'";
                if(shoriList.size()-1 != i){
                	condition+=",";
                }
            }
			
			conn = JdbcConnection.getConnection();
			sql = "delete from kaisha_size_tbl where juchubango in (" + condition + ")";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
			addError(null, "操作終わった");
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

	public File getInputPath() {
		return inputPath;
	}

	public void setInputPath(File inputPath) {
		this.inputPath = inputPath;
	}
	
}
