package quanlyvattu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import quanlyvattu.model.CTSLHHXNModel;
import quanlyvattu.model.DDHnoPNModel;
import quanlyvattu.model.NhanVienLoginModel;

public class CTSLHHXNMapper implements RowMapper<CTSLHHXNModel>{
	@Override
	public CTSLHHXNModel mapRow(ResultSet resultSet) {
		try {
			CTSLHHXNModel ctxn = new CTSLHHXNModel();
			ctxn.setThangNam(resultSet.getString("THANGNAM"));
			ctxn.setTenVT(resultSet.getString("TENVT"));
			ctxn.setTongSoLuong(resultSet.getInt("TONGSOLUONG"));
			ctxn.setTriGia(resultSet.getFloat("TRIGIA"));
			return ctxn;
		} catch (SQLException e) {
			return null;
		}	
	}
}
