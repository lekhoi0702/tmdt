package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.ChiPhiEntity;





public interface ChiPhiRepository extends JpaRepository<ChiPhiEntity, Long>{
	@Query(value = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE "
			+ "YEAR(ngayNhap) = ?1", nativeQuery = false)
	int tongChiPhiNam(Integer nam);
	
	@Query(value = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE "
			+ "MONTH(ngayNhap) = ?1 AND YEAR(ngayNhap) = ?2", nativeQuery = false)
	
	int tongChiPhiThang(Integer thang, Integer nam);
	
	@Query(value = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE "
			+ "DAY(ngayNhap) = ?1 AND MONTH(ngayNhap) = ?2 AND YEAR(ngayNhap) = ?3", nativeQuery = false)
	int tongChiPhiNgay(Integer ngay, Integer thang, Integer nam);
	
	
	
	@Query(value = "FROM ChiPhiEntity WHERE "
			+ "YEAR(ngayNhap) = ?1 "
			+ "ORDER BY id DESC, ngayNhap DESC", nativeQuery = false)
	List<ChiPhiEntity> getChiPhiNam(Integer nam);
	
	@Query(value = "FROM ChiPhiEntity WHERE "
			+ "MONTH(ngayNhap) = ?1 AND YEAR(ngayNhap) = ?2 "
			+ "ORDER BY id DESC, ngayNhap DESC", nativeQuery = false)
	List<ChiPhiEntity> getChiPhiThang(Integer thang, Integer nam);
	
	@Query(value = "FROM ChiPhiEntity WHERE "
			+ "DAY(ngayNhap) = ?1 AND MONTH(ngayNhap) = ?2 AND YEAR(ngayNhap) = ?3 "
			+ "ORDER BY id DESC, ngayNhap DESC", nativeQuery = false)
	List<ChiPhiEntity> getChiPhiNgay(Integer ngay, Integer thang, Integer nam);
	
}
