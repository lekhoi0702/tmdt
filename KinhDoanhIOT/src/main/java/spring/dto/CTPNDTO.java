package spring.dto;

//@Entity
//@Table(name = "CTDDH")
public class CTPNDTO {

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "ID")
	private Long id;

	// @Column(name = "SOLUONG")
	private Integer soLuong;

	// @ManyToOne
	// @JoinColumn(name = "SANPHAM")
	private Long sanPham;

	// @ManyToOne
	// @JoinColumn(name = "MAPN")
	private Long phieuNhap;
	
	private String tenSP;


	// @Column(name = "DONGIA")
	private Integer donGia;
	
	private PhieuNhapDTO pn;

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Long getSanPham() {
		return sanPham;
	}

	public void setSanPham(Long sanPham) {
		this.sanPham = sanPham;
	}

	public Long getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(Long phieuNhap) {
		this.phieuNhap = phieuNhap;
	}

	public Integer getDonGia() {
		return donGia;
	}

	public void setDonGia(Integer donGia) {
		this.donGia = donGia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PhieuNhapDTO getPn() {
		return pn;
	}

	public void setPn(PhieuNhapDTO pn) {
		this.pn = pn;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

}
