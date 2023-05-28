package springboot.dto;

import java.util.Date;

public class LogDTO {
	private Date ngayTao;
	
	private Long nvTao;
	
	private Long nvSua;
	
	public Date getNgayTao() {
		return ngayTao;
	}



	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}



	public Long getNvTao() {
		return nvTao;
	}



	public void setNvTao(Long nvTao) {
		this.nvTao = nvTao;
	}



	public Long getNvSua() {
		return nvSua;
	}



	public void setNvSua(Long nvSua) {
		this.nvSua = nvSua;
	}
	
	
}
