package springboot.dto;

import java.sql.Timestamp;
import java.util.Date;

import springboot.entity.ChiPhiEntity;

public class ChiPhiDTO {
	private Long id;
	private String tenChiPhi;
	private Date ngayNhap;
	private int soLuong;
	private int giaDonVi;
	private String loai;
	private String donVi;
	private String nhaCungCap;
	private String ghiChu;
	private Long nvTao;
	
	public ChiPhiDTO(Long id, String tenChiPhi, Timestamp ngayNhap, int soLuong, int giaDonVi, String loai,
			String donVi, String nhaCungCap, String ghiChu, Long nvTao) {
		super();
		this.id = id;
		this.tenChiPhi = tenChiPhi;
		this.ngayNhap = ngayNhap;
		this.soLuong = soLuong;
		this.giaDonVi = giaDonVi;
		this.loai = loai;
		this.donVi = donVi;
		this.nhaCungCap = nhaCungCap;
		this.ghiChu = ghiChu;
		this.nvTao = nvTao;
	}
	public ChiPhiDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiPhiDTO(ChiPhiEntity item) {
		this.setId(item.getId());
		this.setDonVi(item.getDonVi());
		this.setGhiChu(item.getGhiChu());
		this.setGiaDonVi(item.getGiaMoiDV());
		this.setLoai(item.getLoai());
		this.setNgayNhap(item.getNgayNhap());
		this.setNhaCungCap(item.getNhaCungCap());
		this.setNvTao(item.getNvTao().getMaNV());
		this.setSoLuong(item.getSoLuong());
		this.setTenChiPhi(item.getTenChiPhi());
	}
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
	public void setNgayNhap(Date date) {
		this.ngayNhap = date;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getGiaDonVi() {
		return giaDonVi;
	}
	public void setGiaDonVi(int giaDonVi) {
		this.giaDonVi = giaDonVi;
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
	public Long getNvTao() {
		return nvTao;
	}
	public void setNvTao(Long nvTao) {
		this.nvTao = nvTao;
	}



	

	
}
