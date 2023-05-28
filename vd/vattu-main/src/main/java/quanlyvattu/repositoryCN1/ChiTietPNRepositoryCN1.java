package quanlyvattu.repositoryCN1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quanlyvattu.entity.CTPNEntity;






public interface ChiTietPNRepositoryCN1 extends JpaRepository<CTPNEntity, String> {

	@Query("select ctPhieunhap from CTPNEntity ctPhieunhap where ctPhieunhap.phieuNhap.maPN = ?1")
	List<CTPNEntity> findByMaSoPN(String maSoDDH);
	
	@Query (value="exec sp_CapNhatSoLuongVatTu 'NHAP',?1,?2", nativeQuery=true)
	void cnvt(String maVT, int soLuong);

}
