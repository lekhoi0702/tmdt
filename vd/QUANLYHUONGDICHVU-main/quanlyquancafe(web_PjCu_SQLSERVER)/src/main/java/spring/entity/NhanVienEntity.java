package spring.entity;


import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="NHANVIEN")
public class NhanVienEntity {
	@Id
	@GeneratedValue
	@Column(name="MANV")
	private Long maNV;
	
	@Column(name="HOTEN")
	private String hoTen;
	
	@Column(name="NGAYSINH")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MMM-yyyy")
	private Date ngaySinh;
	
	@Column(name="GIOITINH")
	private Boolean gioiTinh;
	
	@Column(name="LUONG")
	private Integer luong;
	
	@Column(name="SDT")
	private String sdt;
	
	@Column(name="CMND")
	private String cmnd;
	
	@Column(name="DIACHI")
	private String diaChi;
	
	@Column(name="NGAYVAOLAM")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MMM-yyyy")
	private Date ngayVaoLam;
	
	@Column(name="DANGHI")
	private Boolean daNghi;
	
	@OneToMany(mappedBy="hdnv", fetch = FetchType.EAGER)
	private Collection<HoaDonEntity> hoadon;
	
	@OneToMany(mappedBy="dbnv", fetch = FetchType.EAGER)
	private Collection<DatBanEntity> datBan;
	
	@OneToMany(mappedBy="cpnv", fetch = FetchType.EAGER)
	private Collection<ChiPhiEntity> chiPhi;
	
	@OneToMany(mappedBy="usernv", fetch = FetchType.EAGER)
	private Collection<UserTBEntity> userTB;
	
	
	public NhanVienEntity() {
		super();
	}

	public Long getMaNV() {
		return maNV;
	}

	public void setMaNV(Long maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Integer getLuong() {
		return luong;
	}

	public void setLuong(Integer luong) {
		this.luong = luong;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

	public Boolean getDaNghi() {
		return daNghi;
	}

	public void setDaNghi(Boolean daNghi) {
		this.daNghi = daNghi;
	}

	public Collection<HoaDonEntity> getHoadon() {
		return hoadon;
	}

	public void setHoadon(Collection<HoaDonEntity> hoadon) {
		this.hoadon = hoadon;
	}

	public Collection<DatBanEntity> getDatBan() {
		return datBan;
	}

	public void setDatBan(Collection<DatBanEntity> datBan) {
		this.datBan = datBan;
	}

	public Collection<ChiPhiEntity> getChiPhi() {
		return chiPhi;
	}

	public void setChiPhi(Collection<ChiPhiEntity> chiPhi) {
		this.chiPhi = chiPhi;
	}

	public Collection<UserTBEntity> getUserTB() {
		return userTB;
	}

	public void setUserTB(Collection<UserTBEntity> userTB) {
		this.userTB = userTB;
	}
	
	
}
