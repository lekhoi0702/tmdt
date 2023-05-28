package springboot.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LOAISP")
public class LoaiSPEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="TENLOAI")
	private String tenLoai;
	
	@OneToMany(mappedBy = "loaiSP", fetch = FetchType.LAZY)
	private Collection<SanPhamEntity> user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public Collection<SanPhamEntity> getUser() {
		return user;
	}

	public void setUser(Collection<SanPhamEntity> user) {
		this.user = user;
	}

	
	
}
