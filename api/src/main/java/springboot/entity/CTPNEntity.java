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
@Table(name = "CTPN")
public class CTPNEntity {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "SOLUONG")
	private Integer soLuong;

	@ManyToOne
	@JoinColumn(name = "SANPHAM")
	private SanPhamEntity sanPham;

	@ManyToOne
	@JoinColumn(name = "MAPN")
	private PhieuNhapEntity phieuNhap;

	@Column(name = "DONGIA")
	private Integer donGia;

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	

	public SanPhamEntity getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPhamEntity sanPham) {
		this.sanPham = sanPham;
	}

	public PhieuNhapEntity getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(PhieuNhapEntity phieuNhap) {
		this.phieuNhap = phieuNhap;
	}

	public Integer getDonGia() {
		return donGia;
	}

	public void setDonGia(Integer donGia) {
		this.donGia = donGia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
