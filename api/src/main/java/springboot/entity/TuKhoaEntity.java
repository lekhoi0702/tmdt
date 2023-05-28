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
@Table(name = "TUKHOA")
public class TuKhoaEntity {
	@Id
	@Column(name="TENTUKHOA")
	private String tenTuKhoa;
	
	@OneToMany(mappedBy = "tuKhoa", fetch = FetchType.LAZY)
	private Collection<TuKhoa_NhanEntity> chiTietTuKhoa= new ArrayList<TuKhoa_NhanEntity>();

	@Column(name="NGAYTAO")
	private Date ngayTao;
	
	@Column(name="NVTAO")
	private Long nvTao;
	
	@Column(name="NVSUA")
	private Long nvSua;
	
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

	
	
	
	
	
	



	public String getTenTuKhoa() {
		return tenTuKhoa;
	}



	public void setTenTuKhoa(String tenTuKhoa) {
		this.tenTuKhoa = tenTuKhoa;
	}




	public Collection<TuKhoa_NhanEntity> getChiTietTuKhoa() {
		return chiTietTuKhoa;
	}



	public void setChiTietTuKhoa(Collection<TuKhoa_NhanEntity> chiTietTuKhoa) {
		this.chiTietTuKhoa = chiTietTuKhoa;
	}



	public TuKhoaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


}
