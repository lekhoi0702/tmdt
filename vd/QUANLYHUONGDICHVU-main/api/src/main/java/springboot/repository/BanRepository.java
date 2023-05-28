package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.BanEntity;

public interface BanRepository extends JpaRepository<BanEntity, Long> {
	@Query(value = "SELECT * FROM BAN  WHERE NOT TINHTRANG= -1", nativeQuery = true)
	List<BanEntity> findAllActive();
}
