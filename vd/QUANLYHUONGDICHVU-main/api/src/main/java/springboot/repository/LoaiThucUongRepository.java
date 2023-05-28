package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.LoaiThucUongEntity;





public interface LoaiThucUongRepository extends JpaRepository<LoaiThucUongEntity, String>{

	List<LoaiThucUongEntity> findByTenLoai(String tenLoai);
}
