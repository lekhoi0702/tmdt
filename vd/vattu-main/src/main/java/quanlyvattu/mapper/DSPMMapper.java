package quanlyvattu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import quanlyvattu.model.DSPMModel;
import quanlyvattu.model.NhanVienLoginModel;

public class DSPMMapper implements RowMapper<DSPMModel>{
	@Override
	public DSPMModel mapRow(ResultSet resultSet) {
		try {
			DSPMModel DSPM = new DSPMModel();
		
			DSPM.setTenCN(resultSet.getString("TENCN"));
			DSPM.setTenServer(resultSet.getString("TENSERVER"));
		
			return DSPM;
		} catch (SQLException e) {
			return null;
		}	
	}
}
