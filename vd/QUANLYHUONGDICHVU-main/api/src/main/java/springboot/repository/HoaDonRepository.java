package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.HoaDonEntity;





public interface HoaDonRepository extends JpaRepository<HoaDonEntity, Long>{
	List<HoaDonEntity> findByOrderByIdDesc();
	
	@Query(value = "SELECT COUNT(*) FROM HoaDonEntity WHERE "
			+ "YEAR(ngayThucHien) = ?1", nativeQuery = false)
	int soHoaDonNam(Integer nam);
	@Query(value = "SELECT COUNT(*) FROM HoaDonEntity WHERE "
			+ "MONTH(ngayThucHien) = ?1 AND YEAR(ngayThucHien) = ?2", nativeQuery = false)
	int soHoaDonThang(Integer thang, Integer nam);
	@Query(value = "SELECT COUNT(*) FROM HoaDonEntity WHERE "
			+ "DAY(ngayThucHien) = ?1 AND MONTH(ngayThucHien) = ?2 AND YEAR(ngayThucHien) = ?3", nativeQuery = false)
	int soHoaDonNgay(Integer ngay, Integer thang, Integer nam);
	
	
	
	@Query(value = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE "
			+ "YEAR(hd.ngayThucHien) = ?1", nativeQuery = false)
	int doanhThuNam(Integer nam);
	
	@Query(value = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE "
			+ "MONTH(hd.ngayThucHien) = ?1 AND YEAR(hd.ngayThucHien) = ?2", nativeQuery = false)
	int doanhThuThang(Integer thang, Integer nam);
	
	@Query(value = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE "
			+ "DAY(hd.ngayThucHien) = ?1 AND MONTH(hd.ngayThucHien) = ?2 AND YEAR(hd.ngayThucHien) = ?3"
			, nativeQuery = false)	
	int doanhThuNgay(Integer ngay, Integer thang, Integer nam);


	
	@Query(value = "FROM HoaDonEntity hd WHERE "
			+ "YEAR(hd.ngayThucHien) = ?1", nativeQuery = false)
	List<HoaDonEntity> getHoaDonNam(Integer nam);
	
	@Query(value = "FROM HoaDonEntity hd WHERE "
			+ "MONTH(hd.ngayThucHien) = ?1 AND YEAR(hd.ngayThucHien) = ?2", nativeQuery = false)
	List<HoaDonEntity> getHoaDonThang(Integer thang, Integer nam);
	
	@Query(value = "FROM HoaDonEntity hd WHERE "
			+ "DAY(hd.ngayThucHien) = ?1 AND MONTH(hd.ngayThucHien) = ?2 AND YEAR(hd.ngayThucHien) = ?3"
			, nativeQuery = false)	
	List<HoaDonEntity> getHoaDonNgay(Integer ngay, Integer thang, Integer nam);

	@Query(value = "SELECT sum(tongTien) FROM ChiTietHDEntity where hoaDon.id =  ?1"
			, nativeQuery = false)	
	int getTongTienHD(Long id);

}
