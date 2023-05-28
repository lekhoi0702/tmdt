package springboot.dto;

import java.util.List;

import springboot.entity.ChiPhiEntity;
import springboot.entity.HoaDonEntity;

public class ThongKeDTO {
	private int soHoaDon;
	private int doanhThu;
	private int chiPhi;
	private int loiNhuan;
	private List<HoaDonDTO> HoaDons;
	private List<ChiPhiDTO> chiPhis;

	
	public int getSoHoaDon() {
		return soHoaDon;
	}
	public void setSoHoaDon(int soHoaDon) {
		this.soHoaDon = soHoaDon;
	}
	public int getDoanhThu() {
		return doanhThu;
	}
	public List<HoaDonDTO> getHoaDons() {
		return HoaDons;
	}
	public void setHoaDons(List<HoaDonDTO> hoaDons) {
		HoaDons = hoaDons;
	}
	public List<ChiPhiDTO> getChiPhis() {
		return chiPhis;
	}
	public void setChiPhis(List<ChiPhiDTO> chiPhis) {
		this.chiPhis = chiPhis;
	}
	public void setDoanhThu(int doanhThu) {
		this.doanhThu = doanhThu;
	}
	public int getChiPhi() {
		return chiPhi;
	}
	public void setChiPhi(int chiPhi) {
		this.chiPhi = chiPhi;
	}
	public int getLoiNhuan() {
		return loiNhuan;
	}
	public void setLoiNhuan(int loiNhuan) {
		this.loiNhuan = loiNhuan;
	}

}
