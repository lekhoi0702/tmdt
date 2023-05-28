package quanlyvattu.repositoryCN2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import quanlyvattu.entity.DatHangEntity;
import quanlyvattu.entity.NhanVienEntity;



public interface NhanVienRepositoryCN2 extends JpaRepository<NhanVienEntity, Integer> {
	@Query(value  = "exec sp_TraCuu_TimMaNV", nativeQuery = true)
	int TimMaNV();
	
	
	@Query("select nv from NhanVienEntity nv where nv.trangThaiXoa = 0")
	List<NhanVienEntity> findAllNV();
	
	@Query(value  =
		
			"EXEC	[dbo].[sp_TaoTaiKhoan]\n" + 
			"		@LGNAME = ?1,\n" + 
			"		@PASS = ?2,\n" + 
			"		@USERNAME = ?3,\n" + 
			"		@ROLE = ?4\n"   ,  nativeQuery = true)
	void taoLogin(String lgName, String pass, String userName, String role);
}
