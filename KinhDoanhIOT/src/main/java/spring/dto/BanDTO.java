package spring.dto;

public class BanDTO {
	private int soGhe;
	private Long id;
	private Long loaiBan;
	private String tenLoai;
	private int tinhTrang;

	public BanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BanDTO(int soGhe, Long id, Long loai, int tinhTrang) {
		super();
		this.soGhe = soGhe;
		this.setId(id);
		this.setLoaiBan(loai);
		this.tinhTrang = tinhTrang;
	}
	public Integer getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}


	public int getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public Long getLoaiBan() {
		return loaiBan;
	}
	public void setLoaiBan(Long loaiBan) {
		this.loaiBan = loaiBan;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}


	
	
}
