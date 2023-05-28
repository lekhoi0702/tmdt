package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springboot.entity.UserTBEntity;





public interface UserTBRepository extends JpaRepository<UserTBEntity, String>{
	@Query(
			  value = "SELECT * from USERTB WHERE Status= 1", 
			  nativeQuery = true)
	List<UserTBEntity> findAllActive();
	UserTBEntity findByUserNameAndPasswd(String userName, String passwd);
}

