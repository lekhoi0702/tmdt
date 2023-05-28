package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.NhanEntity;





public interface NhanRepository extends JpaRepository<NhanEntity, String>{

}
