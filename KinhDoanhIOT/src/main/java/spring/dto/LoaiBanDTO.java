package spring.dto;

public class LoaiBanDTO {
	public LoaiBanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private String tenLoai;
	private int giaDat;
	public LoaiBanDTO(Long id, String tenLoai, int giaDat) {
		super();
		this.id = id;
		this.tenLoai = tenLoai;
		this.giaDat = giaDat;
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
	public int getGiaDat() {
		return giaDat;
	}
	public void setGiaDat(int giaDat) {
		this.giaDat = giaDat;
	}
	
	
}
