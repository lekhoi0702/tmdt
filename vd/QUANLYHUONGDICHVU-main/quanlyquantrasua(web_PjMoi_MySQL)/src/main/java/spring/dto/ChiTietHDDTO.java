package spring.dto;

public class ChiTietHDDTO {
	private Long id;

	private Long maHD;
	private String maSP;
	private int soLuong;
	private int tongTien;
	//private ThucDonDTO thucDon;

	public Long getMaHD() {
		return maHD;
	}
	public void setMaHD(Long maHD) {
		this.maHD = maHD;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public ThucDonDTO getThucDon() {
//		return thucDon;
//	}
//	public void setThucDon(ThucDonDTO thucDon) {
//		this.thucDon = thucDon;
//	}
	

}
