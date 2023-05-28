package quanlyvattu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import quanlyvattu.model.THNXModel;



public class THNXMapper implements RowMapper<THNXModel>{
	@Override
	public THNXModel mapRow(ResultSet resultSet) {
		try {
			THNXModel thnx = new THNXModel();
			thnx.setNgay(resultSet.getDate("NGAY"));
			thnx.setNhap(resultSet.getFloat("NHAP"));
			thnx.setTyLeNhap(resultSet.getFloat("TYLENHAP"));
			thnx.setXuat(resultSet.getFloat("XUAT"));
			thnx.setTiLeXuat(resultSet.getFloat("TYLEXUAT"));
			return thnx;
		} catch (SQLException e) {
			return null;
		}	
	}
}
