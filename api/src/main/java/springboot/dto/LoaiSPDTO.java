package springboot.dto;

public class LoaiSPDTO {
	public LoaiSPDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private String tenLoai;
	public LoaiSPDTO(Long id, String tenLoai, int giaDat) {
		super();
		this.id = id;
		this.tenLoai = tenLoai;
	}
	public Long getid() {
		return id;
	}
	public void setid(Long id) {
		this.id = id;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	
}
