package spring.dto;

public class ChucVuDTO {
	private long id;
	private String tenChucVu;
	public ChucVuDTO(int id, String tenChucVu) {
		super();
		this.id = id;
		this.tenChucVu = tenChucVu;
	}
	public ChucVuDTO() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTenChucVu() {
		return tenChucVu;
	}
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}
	
	
}
