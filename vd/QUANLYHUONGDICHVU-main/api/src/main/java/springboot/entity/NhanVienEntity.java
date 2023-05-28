package springboot.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NHANVIEN")
public class NhanVienEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MANV")
	private Long maNV;

	@Column(name = "HOTEN")
	private String hoTen;

	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	private Date ngaySinh;

	@Column(name = "GIOITINH")
	private Boolean gioiTinh;

	@Column(name = "LUONG")
	private Integer luong;

	@Column(name = "SDT")
	private String sdt;

	@Column(name = "CMND")
	private String cmnd;

	@Column(name = "DIACHI")
	private String diaChi;

	@Column(name = "NGAYVAOLAM")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	private Date ngayVaoLam;

	@Column(name = "DANGHI")
	private Boolean daNghi;

	@OneToMany(mappedBy = "nvThucHien", fetch = FetchType.LAZY)
	private Collection<HoaDonEntity> hoadon;

	@OneToMany(mappedBy = "nvTao", fetch = FetchType.LAZY)
	private Collection<ChiPhiEntity> chiPhi;

	@OneToMany(mappedBy = "usernv", fetch = FetchType.LAZY)
	private Collection<UserTBEntity> userTB;

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

	public NhanVienEntity(Long maNV, String hoTen, Date ngaySinh, Boolean gioiTinh, Integer luong, String sdt,
			String cmnd, String diaChi, Date ngayVaoLam, Boolean daNghi, Collection<HoaDonEntity> hoadon,
			Collection<ChiPhiEntity> chiPhi, Collection<UserTBEntity> userTB) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.luong = luong;
		this.sdt = sdt;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.ngayVaoLam = ngayVaoLam;
		this.daNghi = daNghi;
		this.hoadon = hoadon;

		this.chiPhi = chiPhi;
		this.userTB = userTB;
	}

	public NhanVienEntity() {
		super();
	}
}
