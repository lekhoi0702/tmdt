package quanlyvattu.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HDNVModel {
	private String thangNam;
	
	@Temporal(TemporalType.DATE)
	private Date ngay;

	private String maPhieu;

	private String tenVT;

	private String tenKho;

	private int soLuong;

	private float donGia;

	private float triGia;
	
	public String getThangNam() {
		return thangNam;
	}

	public void setThangNam(String thangNam) {
		this.thangNam = thangNam;
	}

	

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public String getTenVT() {
		return tenVT;
	}

	public void setTenVT(String tenVT) {
		this.tenVT = tenVT;
	}

	public String getTenKho() {
		return tenKho;
	}

	public void setTenKho(String tenKho) {
		this.tenKho = tenKho;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public float getDonGia() {
		return donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public float getTriGia() {
		return triGia;
	}

	public void setTriGia(float triGia) {
		this.triGia = triGia;
	}

	
}