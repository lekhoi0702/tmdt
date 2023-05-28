package quanlyvattu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import quanlyvattu.dao.INhanVienDAO;
import quanlyvattu.entity.NhanVienEntity;
import quanlyvattu.mapper.NhanVienLoginMapper;
import quanlyvattu.model.NhanVienLoginModel;

@Repository
public class NhanVienDAO extends AbstractDAO<NhanVienLoginModel> implements INhanVienDAO{

	public NhanVienLoginModel login(String userName,String passWord){
		String sql = "EXEC	sp_DangNhap ?";
		List<NhanVienLoginModel> nv =queryPM(userName,passWord,sql,new NhanVienLoginMapper(),userName);
		System.out.println(nv);
		return nv==null? null:nv.get(0);
	}



}
