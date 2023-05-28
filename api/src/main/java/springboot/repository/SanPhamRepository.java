package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.SanPhamEntity;





public interface SanPhamRepository extends JpaRepository<SanPhamEntity, Long>{
	@Query(
			  value = "SELECT * FROM SANPHAM item WHERE TRANGTHAI = 1", 
			  nativeQuery = true)
	List<SanPhamEntity> findAllActive();

}
