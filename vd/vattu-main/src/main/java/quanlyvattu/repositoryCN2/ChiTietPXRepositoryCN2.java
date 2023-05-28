package quanlyvattu.repositoryCN2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quanlyvattu.entity.CTPXEntity;





public interface ChiTietPXRepositoryCN2 extends JpaRepository<CTPXEntity, String> {

	@Query("select ctPhieuxuat from CTPXEntity ctPhieuxuat where ctPhieuxuat.phieuXuat.maPX = ?1")
	List<CTPXEntity> findByMaSoPX(String maSoDDH);
	@Query (value="exec sp_CapNhatSoLuongVatTu 'XUAT',?1,?2", nativeQuery=true)
	void cnvt(String maVT, int soLuong);
}
