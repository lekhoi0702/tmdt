package springboot.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NHAN")
public class NhanEntity {
	@Id
	@Column(name="TENNHAN")
	private String tenNhan;
	
	@OneToMany(mappedBy = "nhan", fetch = FetchType.LAZY)
	private Collection<TuKhoa_NhanEntity> chiTietNhan= new ArrayList<TuKhoa_NhanEntity>();
	
	@OneToMany(mappedBy = "nhan", fetch = FetchType.LAZY)
	private Collection<Nhan_SanPhamEntity> chiTietSp= new ArrayList<Nhan_SanPhamEntity>();
	
	
	@Column(name="NGAYTAO")
	private Date ngayTao;
	
	@Column(name="NVTAO")
	private Long nvTao;
	
	@Column(name="NVSUA")
	private Long nvSua;

	public Collection<Nhan_SanPhamEntity> getChiTietSp() {
		return chiTietSp;
	}



	public void setChiTietSp(Collection<Nhan_SanPhamEntity> chiTietSp) {
		this.chiTietSp = chiTietSp;
	}




	public String getTenNhan() {
		return tenNhan;
	}



	
	public Date getNgayTao() {
		return ngayTao;
	}



	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}



	public Long getNvTao() {
		return nvTao;
	}



	public void setNvTao(Long nvTao) {
		this.nvTao = nvTao;
	}



	public Long getNvSua() {
		return nvSua;
	}



	public void setNvSua(Long nvSua) {
		this.nvSua = nvSua;
	}

	public void setTenNhan(String tenNhan) {
		this.tenNhan = tenNhan;
	}




	public Collection<TuKhoa_NhanEntity> getChiTietNhan() {
		return chiTietNhan;
	}



	public void setChiTietNhan(Collection<TuKhoa_NhanEntity> chiTietNhan) {
		this.chiTietNhan = chiTietNhan;
	}







	public NhanEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


}
