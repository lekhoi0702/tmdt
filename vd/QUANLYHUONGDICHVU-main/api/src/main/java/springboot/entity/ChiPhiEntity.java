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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="CHIPHI")
public class ChiPhiEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="ID")
	private Long id;
	
	@Column(name="TENCHIPHI")
	private String tenChiPhi;
	
	@Column(name="NGAYNHAP")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss.SS")
	private Date ngayNhap;
	
	@Column(name="SOLUONG")
	private Integer soLuong;
	
	@Column(name="GIAMOIDONVI")
	private Integer giaMoiDV;
	
	@Column(name="LOAI")
	private String loai;
	
	@Column(name="DONVI")
	private String donVi;
	
	@Column(name="NHACUNGCAP")
	private String nhaCungCap;
	
	@Column(name="GHICHU")
	private String ghiChu;
	
	
	@ManyToOne
	@JoinColumn(name="NVTAO")
	private NhanVienEntity nvTao;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTenChiPhi() {
		return tenChiPhi;
	}


	public void setTenChiPhi(String tenChiPhi) {
		this.tenChiPhi = tenChiPhi;
	}


	public Date getNgayNhap() {
		return ngayNhap;
	}


	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}


	public Integer getSoLuong() {
		return soLuong;
	}


	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}


	public Integer getGiaMoiDV() {
		return giaMoiDV;
	}


	public void setGiaMoiDV(Integer giaMoiDV) {
		this.giaMoiDV = giaMoiDV;
	}


	public String getLoai() {
		return loai;
	}


	public void setLoai(String loai) {
		this.loai = loai;
	}


	public String getDonVi() {
		return donVi;
	}


	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}


	public String getNhaCungCap() {
		return nhaCungCap;
	}


	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	public NhanVienEntity getNvTao() {
		return nvTao;
	}


	public void setNvTao(NhanVienEntity nvTao) {
		this.nvTao = nvTao;
	}


	public ChiPhiEntity(Long id, String tenChiPhi, Date ngayNhap, Integer soLuong, Integer giaMoiDV, String loai,
			String donVi, String nhaCungCap, String ghiChu, NhanVienEntity nvTao) {
		super();
		this.id = id;
		this.tenChiPhi = tenChiPhi;
		this.ngayNhap = ngayNhap;
		this.soLuong = soLuong;
		this.giaMoiDV = giaMoiDV;
		this.loai = loai;
		this.donVi = donVi;
		this.nhaCungCap = nhaCungCap;
		this.ghiChu = ghiChu;
		this.nvTao = nvTao;
	}


	public ChiPhiEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
