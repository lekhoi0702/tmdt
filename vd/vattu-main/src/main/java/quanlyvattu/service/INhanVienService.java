package quanlyvattu.service;

import java.util.List;

import quanlyvattu.model.NhanVienLoginModel;


public interface INhanVienService {

	List<NhanVienLoginModel> findAll();
	NhanVienLoginModel findOne(long id);
}
