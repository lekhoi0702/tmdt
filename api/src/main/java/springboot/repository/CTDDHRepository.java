package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.CTDDHEntity;





public interface CTDDHRepository extends JpaRepository<CTDDHEntity, Long>{
	@Query(value = "FROM CTDDHEntity ctddh WHERE "
			+ "ctddh.ddh.tinhTrang = 4 AND " + "ctddh.sanPham.id = ?1 AND "+"ctddh.diem > 0", nativeQuery = false)
	List<CTDDHEntity> findReview(Long maSp);
}
