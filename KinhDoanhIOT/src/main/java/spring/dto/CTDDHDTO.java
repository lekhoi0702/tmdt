package spring.dto;

//@Entity
//@Table(name = "CTDDH")
public class CTDDHDTO {

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "ID")
	private Long id;

	// @Column(name = "SOLUONG")
	private Integer soLuong;

	// @ManyToOne
	// @JoinColumn(name = "SANPHAM")
	private Long sanPham;

	private String tenSP;

	// @ManyToOne
	// @JoinColumn(name = "MADDH")
	private Long ddh;
	private Integer diem=0;

	private String danhGia;

	private String userName;

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDiem() {
		return diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	public String getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(String danhGia) {
		this.danhGia = danhGia;
	}

	public Long getSanPham() {
		return sanPham;
	}

	public void setSanPham(Long sanPham) {
		this.sanPham = sanPham;
	}

	public Long getDdh() {
		return ddh;
	}

	public void setDdh(Long ddh) {
		this.ddh = ddh;
	}

	// @Column(name = "TONGTIEN")
	private Integer tongTien;

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Integer getTongTien() {
		return tongTien;
	}

	public void setTongTien(Integer tongTien) {
		this.tongTien = tongTien;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
