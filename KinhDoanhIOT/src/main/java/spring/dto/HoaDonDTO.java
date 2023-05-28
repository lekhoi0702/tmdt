package spring.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDTO {
	private Long id;
	private Date ngayThucHien;
	private Long nvThucHien;
	private Long ban;
	private List<ChiTietHDDTO> cthds=new ArrayList<ChiTietHDDTO>();
	private int tongTien;



	public HoaDonDTO() {}



	public Long getNvThucHien() {
		return nvThucHien;
	}
	public void setNvThucHien(Long nvThuchien) {
		this.nvThucHien = nvThuchien;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBan() {
		return ban;
	}
	public void setBan(Long ban) {
		this.ban = ban;
	}



	public Date getNgayThucHien() {
		return ngayThucHien;
	}



	public void setNgayThucHien(Date ngayThucHien) {
		this.ngayThucHien = ngayThucHien;
	}



	public List<ChiTietHDDTO> getCthds() {
		return cthds;
	}



	public void setCthds(List<ChiTietHDDTO> cthds) {
		this.cthds = cthds;
	}



	public int getTongTien() {
		return tongTien;
	}



	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	
	
}
