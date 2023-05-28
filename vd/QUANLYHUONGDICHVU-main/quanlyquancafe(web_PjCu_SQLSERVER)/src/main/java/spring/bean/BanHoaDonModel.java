package spring.bean;

import java.util.ArrayList;
import java.util.List;

import spring.entity.ChiTietHDEntity;
import spring.entity.HoaDonEntity;
import spring.entity.LoaiThucUongEntity;
import spring.entity.ThucDonEntity;

public class BanHoaDonModel {
	public BanHoaDonModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BanHoaDonModel(long idBan) {
		super();
		this.idBan = idBan;
	}
	private int trangThaiCu=0;
	private long idBan=0;
	private List<ChiTietHDEntity> cthds= new ArrayList<ChiTietHDEntity>();
	private HoaDonEntity hoaDon;

	public List<ChiTietHDEntity> getCthds() {
		return cthds;
	}
	public void setCthds(List<ChiTietHDEntity> cthds) {
		this.cthds = cthds;
	}
	
	public long getIdBan() {
		return idBan;
	}
	public void setIdBan(long idBan) {
		this.idBan = idBan;
	}
	public HoaDonEntity getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDonEntity hoaDon) {
		this.hoaDon = hoaDon;
	}

	public void xuat() {
		for (ChiTietHDEntity i:cthds)
			System.out.println(i.getThucDon());
	}
	public int getTrangThaiCu() {
		return trangThaiCu;
	}
	public void setTrangThaiCu(int trangThaiCu) {
		this.trangThaiCu = trangThaiCu;
	}


}
