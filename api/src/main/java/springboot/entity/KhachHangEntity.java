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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "KHACHHANG")
public class KhachHangEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAKH")
	private Long maKH;

	@Column(name = "HOTEN")
	private String hoTen;

	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	private Date ngaySinh;

	@Column(name = "GIOITINH")
	private Boolean gioiTinh;


	@Column(name = "SDT")
	private String sdt;


	@Column(name = "DIACHI")
	private String diaChi;



	@Column(name = "TRANGTHAI")
	private int trangThai;

	@OneToMany(mappedBy = "khThucHien", fetch = FetchType.LAZY)
	private Collection<DDHEntity> ddhs;
	
	@OneToOne(mappedBy = "userkh")
	private UserKHEntity userkh;

	@OneToMany(mappedBy = "khachHang", fetch = FetchType.LAZY)
	private Collection<GioHangEntity> gioHangs;	
	
	public Collection<DDHEntity> getDdhs() {
		return ddhs;
	}

	public void setDdhs(Collection<DDHEntity> ddhs) {
		this.ddhs = ddhs;
	}

	public UserKHEntity getUserkh() {
		return userkh;
	}

	public void setUserkh(UserKHEntity userkh) {
		this.userkh = userkh;
	}


	

	public Long getMaKH() {
		return maKH;
	}

	public void setMaKH(Long maNV) {
		this.maKH = maNV;
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


	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}



	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}



	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int i) {
		this.trangThai = i;
	}



	public UserKHEntity getUser() {
		return userkh;
	}

	public void setUser(UserKHEntity userkh) {
		this.userkh = userkh;
	}


	public KhachHangEntity() {
		super();
	}
}
