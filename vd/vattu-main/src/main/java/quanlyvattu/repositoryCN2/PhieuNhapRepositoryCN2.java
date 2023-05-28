package quanlyvattu.repositoryCN2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quanlyvattu.entity.PhieuNhapEntity;


public interface PhieuNhapRepositoryCN2 extends JpaRepository<PhieuNhapEntity, String> {
	@Query("select phieuNhap from PhieuNhapEntity phieuNhap where phieuNhap.datHang.maSoDDH = ?1")
	List<PhieuNhapEntity> findByDDH(String maSoDDH);
	@Query (value="exec sp_KiemTraMaPhieuNhap ?1", nativeQuery=true)
	int ckPN(String MAPN);
	
	
}
