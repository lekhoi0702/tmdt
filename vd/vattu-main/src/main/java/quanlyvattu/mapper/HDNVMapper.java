package quanlyvattu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import quanlyvattu.model.CTSLHHXNModel;
import quanlyvattu.model.DDHnoPNModel;
import quanlyvattu.model.HDNVModel;
import quanlyvattu.model.NhanVienLoginModel;

public class HDNVMapper implements RowMapper<HDNVModel>{
	@Override
	public HDNVModel mapRow(ResultSet resultSet) {
		try {
			HDNVModel hdnv = new HDNVModel();
			hdnv.setThangNam(resultSet.getString("THANGNAM"));
			hdnv.setNgay(resultSet.getDate("NGAY"));
			hdnv.setMaPhieu(resultSet.getString("MAPHIEU"));
			hdnv.setTenVT(resultSet.getString("TENVT"));
			hdnv.setTenKho(resultSet.getString("TENKHO"));
			hdnv.setSoLuong(resultSet.getInt("SOLUONG"));
			hdnv.setDonGia(resultSet.getFloat("DONGIA"));
			hdnv.setTriGia(resultSet.getFloat("TRIGIA"));
			return hdnv;
		} catch (SQLException e) {
			return null;
		}	
	}
}
