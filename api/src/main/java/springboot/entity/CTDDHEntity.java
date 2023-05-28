package springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "CTDDH")
public class CTDDHEntity {

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
	@JoinColumn(name = "MADDH")
	private DDHEntity ddh;
	
	@Column(name = "TONGTIEN")
	private Integer tongTien;
	
	@Column(name = "DIEM")
	private Integer diem=0;
	
	@Column(name = "DANHGIA")
	@Type(type="text")

	private String danhGia;

	public Integer getDiem() {
		return diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	public String getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(String danhGia) {
		this.danhGia = danhGia;
	}

	public SanPhamEntity getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPhamEntity sanPham) {
		this.sanPham = sanPham;
	}

	public DDHEntity getDdh() {
		return ddh;
	}

	public void setDdh(DDHEntity ddh) {
		this.ddh = ddh;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Integer getTongTien() {
		return tongTien;
	}

	public void setTongTien(Integer tongTien) {
		this.tongTien = tongTien;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
