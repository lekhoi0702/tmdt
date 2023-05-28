package quanlyvattu.dao;

import java.util.List;

import quanlyvattu.model.NhanVienLoginModel;

public interface INhanVienDAO {
	public NhanVienLoginModel login(String userName,String passWord);

}
