package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.CTPNEntity;





public interface CTPNRepository extends JpaRepository<CTPNEntity, Long>{
	List<CTPNEntity> findByPhieuNhapId(Long maPN);

}
