package springboot.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LOAITHUCUONG")
public class LoaiThucUongEntity {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="TENLOAI")
	private String tenLoai;
	@Column(name="DONVI")
	private String donVi;
	@OneToMany(mappedBy="loaiThucUong", fetch=FetchType.LAZY)
	private Collection<ThucDonEntity> thucDon;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public Collection<ThucDonEntity> getThucDon() {
		return thucDon;
	}
	public void setThucDon(Collection<ThucDonEntity> thucDon) {
		this.thucDon = thucDon;
	}
	public LoaiThucUongEntity(String id, String tenLoai, String donVi, Collection<ThucDonEntity> thucDon) {
		super();
		this.id = id;
		this.tenLoai = tenLoai;
		this.donVi = donVi;
		this.thucDon = thucDon;
	}
	public LoaiThucUongEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
