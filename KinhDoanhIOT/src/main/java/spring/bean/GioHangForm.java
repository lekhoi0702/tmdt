package spring.bean;

import java.util.List;

import spring.dto.GioHangDTO;

public class GioHangForm {
	private List<GioHangDTO> gioHangs ;

	public List<GioHangDTO> getGioHangs () {
		return gioHangs ;
	}

	public void setGioHangs (List<GioHangDTO> gioHangs ) {
		this.gioHangs  = gioHangs ;
	}
}
