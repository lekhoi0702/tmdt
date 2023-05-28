package quanlyvattu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CTSLHHXNModel {
	private String thangNam;
	
	public String getThangNam() {
		return thangNam;
	}

	public void setThangNam(String thangNam) {
		this.thangNam = thangNam;
	}

	public String getTenVT() {
		return tenVT;
	}

	public void setTenVT(String tenVT) {
		this.tenVT = tenVT;
	}

	public int getTongSoLuong() {
		return tongSoLuong;
	}

	public void setTongSoLuong(int tongSoLuong) {
		this.tongSoLuong = tongSoLuong;
	}


	public float getTriGia() {
		return triGia;
	}

	public void setTriGia(float triGia) {
		this.triGia = triGia;
	}


	private String tenVT;
	
	private int tongSoLuong;
	
	private float triGia;

	
	

	
	
	
}
