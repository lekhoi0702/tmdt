package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.DDHEntity;

public interface DDHRepository extends JpaRepository<DDHEntity, Long> {
	List<DDHEntity> findByOrderByIdDesc();

	@Query(value = "SELECT COUNT(*) FROM DDHEntity WHERE " + "YEAR(ngayThucHien) = ?1", nativeQuery = false)
	Integer soDDHNam(Integer nam);

	@Query(value = "SELECT COUNT(*) FROM DDHEntity WHERE "
			+ "MONTH(ngayThucHien) = ?1 AND YEAR(ngayThucHien) = ?2", nativeQuery = false)
	Integer soDDHThang(Integer thang, Integer nam);

	@Query(value = "SELECT COUNT(*) FROM DDHEntity WHERE "
			+ "DAY(ngayThucHien) = ?1 AND MONTH(ngayThucHien) = ?2 AND YEAR(ngayThucHien) = ?3", nativeQuery = false)
	Integer soDDHNgay(Integer ngay, Integer thang, Integer nam);

	@Query(value = "SELECT sum(ctddh.tongTien) FROM DDHEntity ddh INNER JOIN ddh.chiTietDDH ctddh WHERE "
			+ "ddh.tinhTrang >= 4 AND " + "YEAR(ddh.ngayThucHien) = ?1", nativeQuery = false)
	Integer doanhThuNam(Integer nam);

	@Query(value = "SELECT sum(ctddh.tongTien) FROM DDHEntity ddh INNER JOIN ddh.chiTietDDH ctddh WHERE "
			+ "ddh.tinhTrang >= 4 AND "

			+ "MONTH(ddh.ngayThucHien) = ?1 AND YEAR(ddh.ngayThucHien) = ?2", nativeQuery = false)
	Integer doanhThuThang(Integer thang, Integer nam);

	@Query(value = "SELECT sum(ctddh.tongTien) FROM DDHEntity ddh INNER JOIN ddh.chiTietDDH ctddh WHERE "
			+ "ddh.tinhTrang >= 4 AND "

			+ "DAY(ddh.ngayThucHien) = ?1 AND MONTH(ddh.ngayThucHien) = ?2 AND YEAR(ddh.ngayThucHien) = ?3", nativeQuery = false)
	Integer doanhThuNgay(Integer ngay, Integer thang, Integer nam);

	@Query(value = "FROM DDHEntity ddh WHERE " + "YEAR(ddh.ngayThucHien) = ?1", nativeQuery = false)
	List<DDHEntity> getDDHNam(Integer nam);

	@Query(value = "FROM DDHEntity ddh WHERE "
			+ "MONTH(ddh.ngayThucHien) = ?1 AND YEAR(ddh.ngayThucHien) = ?2", nativeQuery = false)
	List<DDHEntity> getDDHThang(Integer thang, Integer nam);

	@Query(value = "FROM DDHEntity ddh WHERE "
			+ "DAY(ddh.ngayThucHien) = ?1 AND MONTH(ddh.ngayThucHien) = ?2 AND YEAR(ddh.ngayThucHien) = ?3", nativeQuery = false)
	List<DDHEntity> getDDHNgay(Integer ngay, Integer thang, Integer nam);

//	@Query(value = "SELECT sum(tongTien) FROM ChiTietHDEntity where hoaDon.id =  ?1", nativeQuery = false)
//	Integer getTongTienHD(Long id);
}
