package com.quancafehighland.dao.impl;

import java.util.List;

import com.quancafehighland.dao.INhanVienDAO;
import com.quancafehighland.mapper.NhanVienMapper;
import com.quancafehighland.model.NhanVienModel;
import com.quancafehighland.model.UserModel;

public class NhanVienDAO extends AbstractDAO<NhanVienModel> implements INhanVienDAO{
	public List<NhanVienModel> findAll(){
		String sql = "SELECT * FROM dbo.NHANVIEN";
		return query(sql,new NhanVienMapper());
	}

	public NhanVienModel findOne(long id) {
		String sql = "SELECT * FROM dbo.NHANVIEN NV WHERE MANV=?";
		List<NhanVienModel> nv= query(sql,new NhanVienMapper(),id);
		return nv.isEmpty()?null:nv.get(0);
	}
}
