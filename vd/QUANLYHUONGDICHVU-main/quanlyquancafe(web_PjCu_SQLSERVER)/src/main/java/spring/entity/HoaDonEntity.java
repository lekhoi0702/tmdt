package spring.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HOADON")
public class HoaDonEntity {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NGAYTHUCHIEN")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date ngayThucHien;

	@ManyToOne
	@JoinColumn(name = "BAN")
	private BanEntity ban;

	@OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
	private Collection<ChiTietHDEntity> chiTietHD;


	@ManyToOne
	@JoinColumn(name = "NVTHUCHIEN")
	private NhanVienEntity hdnv;
	

	@Column(name = "TINHTRANG")
	private Integer tinhTrang;

	public Integer getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNgayThucHien() {
		return ngayThucHien;
	}

	public void setNgayThucHien(Date ngayThucHien) {
		this.ngayThucHien = ngayThucHien;
	}

	public BanEntity getBan() {
		return ban;
	}

	public void setBan(BanEntity ban) {
		this.ban = ban;
	}

	public Collection<ChiTietHDEntity> getChiTietHD() {
		return chiTietHD;
	}

	public void setChiTietHD(Collection<ChiTietHDEntity> chiTietHD) {
		this.chiTietHD = chiTietHD;
	}

	public NhanVienEntity getHdnv() {
		return hdnv;
	}

	public void setHdnv(NhanVienEntity hdnv) {
		this.hdnv = hdnv;
	}


}
