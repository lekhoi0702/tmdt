package spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

class ChiTietHDPK implements Serializable {
	private ThucDonEntity thucDon;
	private HoaDonEntity hoaDon;
	public ChiTietHDPK(ThucDonEntity thucDon, HoaDonEntity hoaDon) {
		super();
		this.thucDon = thucDon;
		this.hoaDon = hoaDon;
	}
	public ChiTietHDPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}


@Entity
@IdClass(ChiTietHDPK.class)
@Table(name="CHITIETHD")
public class ChiTietHDEntity {

	@Column(name="SOLUONG")
	private Integer soLuong;
	
	@Id
	@ManyToOne
	@JoinColumn(name="MASP")
	private ThucDonEntity thucDon;
	
	@Id
	@ManyToOne
	@JoinColumn(name="MAHD")
	private HoaDonEntity hoaDon;

	@Column(name="TONGTIEN")
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
