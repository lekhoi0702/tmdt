package quanlyvattu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import quanlyvattu.model.DDHnoPNModel;
import quanlyvattu.model.NhanVienLoginModel;

public class DDHnoPNMapper implements RowMapper<DDHnoPNModel>{
	@Override
	public DDHnoPNModel mapRow(ResultSet resultSet) {
		try {
			DDHnoPNModel ddh = new DDHnoPNModel();
			ddh.setMaSoDDH(resultSet.getString("MasoDDH"));
			ddh.setNgay(resultSet.getDate("Ngay"));
			ddh.setNhaCC(resultSet.getString("NhaCC"));
			ddh.setHoTen(resultSet.getString("HOTEN"));
			ddh.setTenVT(resultSet.getString("TENVT"));
			ddh.setSoLuong(resultSet.getInt("SOLUONG"));
			ddh.setDonGia(resultSet.getInt("DONGIA"));
			return ddh;
		} catch (SQLException e) {
			return null;
		}	
	}
}
