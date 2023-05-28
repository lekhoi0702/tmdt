package spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="TENNL")
	private String tenNL;
	
	@Column(name="NGAYNHAP")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss.SS")
	private Date ngayNhap;
	
	@Column(name="SOLUONG")
	private Integer soLuong;
	
	@Column(name="GIAMOIDV")
	private Integer giaMoiDV;
	
	@Column(name="LOAI")
	private String loai;
	
	@Column(name="DV")
	private String dv;
	
	@Column(name="NHACUNGCAP")
	private String nhaCungCap;
	
	@Column(name="GHICHU")
	private String ghiChu;
	
	
	@ManyToOne
	@JoinColumn(name="NVTAO")
	private NhanVienEntity cpnv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenNL() {
		return tenNL;
	}

	public void setTenNL(String tenNL) {
		this.tenNL = tenNL;
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

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
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


	public NhanVienEntity getCpnv() {
		return cpnv;
	}

	public void setCpnv(NhanVienEntity cpnv) {
		this.cpnv = cpnv;
	}

	public ChiPhiEntity() {
		super();
	}
	
	
	
}
