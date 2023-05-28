package springboot.dto;

//@Entity
//@Table(name = "TUKHOA")
public class TuKhoaDTO extends LogDTO{
	//@Id
	//@Column(name="TENTUKHOA")
	private String tenTuKhoa;
	



	public String getTenTuKhoa() {
		return tenTuKhoa;
	}



	public void setTenTuKhoa(String tenTuKhoa) {
		this.tenTuKhoa = tenTuKhoa;
	}




	public TuKhoaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


}
