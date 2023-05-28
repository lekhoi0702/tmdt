package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.ThucDonEntity;





public interface ThucDonRepository extends JpaRepository<ThucDonEntity, String>{

}
