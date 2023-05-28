package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.UserKHEntity;





public interface UserKHRepository extends JpaRepository<UserKHEntity, String>{
	@Query(
			  value = "SELECT * from USERKH user where STATUS = 1", 
			  nativeQuery = true)
	List<UserKHEntity> findAllActive();
	UserKHEntity findByUserNameAndPasswdAndStatus(String userName, String passwd,int status);
	UserKHEntity findByMaXacThucAndStatus(String maXacNhan,int status);

}

