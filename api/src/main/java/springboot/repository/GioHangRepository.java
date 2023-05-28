package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.GioHangEntity;





public interface GioHangRepository extends JpaRepository<GioHangEntity, Long>{
	List<GioHangEntity> findByKhachHangMaKH(Long maKH);
}
