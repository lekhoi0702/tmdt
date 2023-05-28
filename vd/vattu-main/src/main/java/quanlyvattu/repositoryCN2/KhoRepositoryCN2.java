package quanlyvattu.repositoryCN2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quanlyvattu.entity.ChiNhanhEntity;
import quanlyvattu.entity.KhoEntity;
import quanlyvattu.entity.NhanVienEntity;



public interface KhoRepositoryCN2 extends JpaRepository<KhoEntity, String> {
	@Query (value="exec sp_TraCuu_KiemTraMaKho ?1", nativeQuery=true)
	int ckK(String MAKHO);
}
