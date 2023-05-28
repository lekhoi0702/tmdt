package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.ChiTietHDEntity;

public interface ChiTietHDRepository extends JpaRepository<ChiTietHDEntity, Long> {
	List<ChiTietHDEntity> findByHoaDonId(Long maHD);
	ChiTietHDEntity findByHoaDonIdAndThucDonId(Long maHD,String maThucDon);
	 default long getIdChiTietHD(Long maHD,String maThucDon) {
		return findByHoaDonIdAndThucDonId(maHD,maThucDon).getId();
	}
}
