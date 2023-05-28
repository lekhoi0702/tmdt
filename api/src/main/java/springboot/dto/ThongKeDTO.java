package springboot.dto;

import java.util.List;

public class ThongKeDTO {
	private int soDDH;
	private int doanhThu;
	private int chiPhi;
	private int loiNhuan;
	private List<DDHDTO> DDHs;
	private List<PhieuNhapDTO> phieuNhaps;

	
	public int getSoDDH() {
		return soDDH;
	}
	public void setSoDDH(int soDDH) {
		this.soDDH = soDDH;
	}
	public int getDoanhThu() {
		return doanhThu;
	}
	public List<DDHDTO> getDDHs() {
		return DDHs;
	}
	public void setDDHs(List<DDHDTO> hoaDons) {
		DDHs = hoaDons;
	}
	public List<PhieuNhapDTO> getPhieuNhaps() {
		return phieuNhaps;
	}
	public void setPhieuNhaps(List<PhieuNhapDTO> phieuNhaps) {
		this.phieuNhaps = phieuNhaps;
	}
	public void setDoanhThu(int doanhThu) {
		this.doanhThu = doanhThu;
	}
	public int getPhieuNhap() {
		return chiPhi;
	}
	public void setPhieuNhap(int chiPhi) {
		this.chiPhi = chiPhi;
	}
	public int getLoiNhuan() {
		return loiNhuan;
	}
	public void setLoiNhuan(int loiNhuan) {
		this.loiNhuan = loiNhuan;
	}

}
