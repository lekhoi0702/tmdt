package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.KhachHangEntity;





public interface KhachHangRepository extends JpaRepository<KhachHangEntity, Long>{
	@Query(
			  value = "SELECT * FROM KHACHHANG item WHERE TRANGTHAI = 1", 
			  nativeQuery = true)
	List<KhachHangEntity> findAllActive();

}
