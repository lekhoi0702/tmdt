package springboot.dto;

//@Entity
//@Table(name = "NHAN")
public class NhanDTO extends LogDTO{
	//@Id
	//@Column(name="TENNHAN")
	private String tenNhan;
	





	//@ManyToOne
	//@JoinColumn(name = "SANPHAM")
	public String getTenNhan() {
		return tenNhan;
	}



	public void setTenNhan(String tenNhan) {
		this.tenNhan = tenNhan;
	}




	public NhanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


}
