package quanlyvattu.model;

import java.sql.Date;

public class THNXModel {
	private Date ngay;
	private float nhap;

	private float tyLeNhap;

	private float xuat;

	private float tiLeXuat;

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public float getNhap() {
		return nhap;
	}

	public void setNhap(float nhap) {
		this.nhap = nhap;
	}

	public float getTyLeNhap() {
		return tyLeNhap;
	}

	public void setTyLeNhap(float tyLeNhap) {
		this.tyLeNhap = tyLeNhap;
	}

	public float getXuat() {
		return xuat;
	}

	public void setXuat(float xuat) {
		this.xuat = xuat;
	}

	public float getTiLeXuat() {
		return tiLeXuat;
	}

	public void setTiLeXuat(float tiLeXuat) {
		this.tiLeXuat = tiLeXuat;
	}

}