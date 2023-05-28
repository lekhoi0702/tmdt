package springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHITIETHD")
public class ChiTietHDEntity {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "SOLUONG")
	private Integer soLuong;

	@ManyToOne
	@JoinColumn(name = "MASP")
	private ThucDonEntity thucDon;

	@ManyToOne
	@JoinColumn(name = "MAHD")
	private HoaDonEntity hoaDon;

	@Column(name = "TONGTIEN")
	private Integer tongTien;

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public ThucDonEntity getThucDon() {
		return thucDon;
	}

	public void setThucDon(ThucDonEntity thucDon) {
		this.thucDon = thucDon;
	}

	public HoaDonEntity getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDonEntity hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Integer getTongTien() {
		return tongTien;
	}

	public void setTongTien(Integer tongTien) {
		this.tongTien = tongTien;
	}

}
