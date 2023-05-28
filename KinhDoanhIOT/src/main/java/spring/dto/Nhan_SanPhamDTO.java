package spring.dto;

//@Entity
//@Table(name = "NHAN_SANPHAM")
public class Nhan_SanPhamDTO extends LogDTO{
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "ID")
	private Long id;

	//@ManyToOne
	//@JoinColumn(name = "SANPHAM")
	private Long sanPham;
	
	//@ManyToOne
	//@JoinColumn(name = "TENNHAN")
	private String nhan;

	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}







	public Long getSanPham() {
		return sanPham;
	}


	public void setSanPham(Long sanPham) {
		this.sanPham = sanPham;
	}


	public String getNhan() {
		return nhan;
	}


	public void setNhan(String nhan) {
		this.nhan = nhan;
	}


	public 	Nhan_SanPhamDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


}
