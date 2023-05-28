package springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TUKHOA_NHAN")
public class TuKhoa_NhanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "TENTUKHOA")
	private TuKhoaEntity tuKhoa;
	
	@ManyToOne
	@JoinColumn(name = "TENNHAN")
	private NhanEntity nhan;

	
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



	public NhanEntity getNhan() {
		return nhan;
	}


	public void setNhan(NhanEntity nhan) {
		this.nhan = nhan;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public TuKhoaEntity getTuKhoa() {
		return tuKhoa;
	}


	public void setTuKhoa(TuKhoaEntity tuKhoa) {
		this.tuKhoa = tuKhoa;
	}




	public 	TuKhoa_NhanEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


}
