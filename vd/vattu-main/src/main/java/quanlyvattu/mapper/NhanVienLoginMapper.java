package quanlyvattu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import quanlyvattu.model.NhanVienLoginModel;

public class NhanVienLoginMapper implements RowMapper<NhanVienLoginModel>{
	@Override
	public NhanVienLoginModel mapRow(ResultSet resultSet) {
		try {
			NhanVienLoginModel nhanVien = new NhanVienLoginModel();
			nhanVien.setMaNV(resultSet.getInt("MANV"));
			nhanVien.setHoTen(resultSet.getString("HOTEN"));
			nhanVien.setTenNhom(resultSet.getString("TENNHOM"));
			return nhanVien;
		} catch (SQLException e) {
			return null;
		}	
	}
}
