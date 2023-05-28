package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.ChucVuEntity;





public interface DatBanRepository extends JpaRepository<ChucVuEntity, Long>{

}
