package spring.dto;

public class LoaiSPDTO {

	public LoaiSPDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private String tenLoai;

	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
