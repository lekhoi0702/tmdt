package spring.dto;

public class LoaiThucUongDTO {
	private Long id1;
	private String id;
	private String tenLoai;
	private String donVi;
	public LoaiThucUongDTO(Long id1, String id, String tenLoai, String donVi) {
		super();
		this.id1 = id1;
		this.id = id;
		this.tenLoai = tenLoai;
		this.donVi = donVi;
	}
	public LoaiThucUongDTO() {}
	public Long getid1() {
		return id1;
	}
	public void setid1(Long id1) {
		this.id1 = id1;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	
	
}
