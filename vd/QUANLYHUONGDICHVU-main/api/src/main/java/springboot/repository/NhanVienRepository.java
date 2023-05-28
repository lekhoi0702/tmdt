package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.NhanVienEntity;





public interface NhanVienRepository extends JpaRepository<NhanVienEntity, Long>{
	@Query(
			  value = "SELECT * FROM NHANVIEN item WHERE DANGHI = 0", 
			  nativeQuery = true)
	List<NhanVienEntity> findAllActive();

}
