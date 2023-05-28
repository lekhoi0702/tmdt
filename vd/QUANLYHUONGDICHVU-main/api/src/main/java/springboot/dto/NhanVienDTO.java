package springboot.dto;

import java.util.Date;

public class NhanVienDTO {
	private Long maNV;
	private String hoTen;
	private Date ngaySinh;
	private boolean gioiTinh;
	private int luong;
	private String sdt;
	private String cmnd;
	private String diaChi;
	private Date ngayVaoLam;
	private boolean daNghi;
	
	public NhanVienDTO(Long maNV, String hoTen, Date ngaySinh, boolean gioiTinh, int luong, String sdt, String cmnd,
			String diaChi, Date ngayVaoLam, boolean daNghi) {
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
	}
	public NhanVienDTO() {}
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
	public boolean getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public int getLuong() {
		return luong;
	}
	public void setLuong(int luong) {
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
	public boolean getDaNghi() {
		return daNghi;
	}
	public void setDaNghi(boolean daNghi) {
		this.daNghi = daNghi;
	}
	
	
	
	
}
