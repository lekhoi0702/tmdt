package quanlyvattu.repositoryCN1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quanlyvattu.entity.CTDDHEntity;

import quanlyvattu.entity.DatHangEntity;

public interface ChiTietDDHRepositoryCN1 extends JpaRepository<CTDDHEntity, String> {

	@Query("select ctdatHang from CTDDHEntity ctdatHang where ctdatHang.datHang.maSoDDH = ?1")
	List<CTDDHEntity> findByMaSoDDH(String maSoDDH);

}
