package spring.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//@Entity
//@Table(name = "PHIEUNHAP")
public class PhieuNhapDTO {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "MAPN")
	private Long id;

	//@Column(name = "NGAY")
	//@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date ngayThucHien;



	//@ManyToOne
	//@JoinColumn(name = "MANV")
	private Long nvThucHien;
	
	//@Column(name = "NHACC")
	private String ncc;

	private List<CTPNDTO> ctpns=new ArrayList<CTPNDTO>();


	public Long getNvThucHien() {
		return nvThucHien;
	}

	//@Column(name = "TINHTRANG")
	private Integer tinhTrang;

	public void setNvThucHien(Long nvThucHien) {
		this.nvThucHien = nvThucHien;
	}



	public String getNcc() {
		return ncc;
	}



	public void setNcc(String ncc) {
		this.ncc = ncc;
	}



	public Integer getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNgayThucHien() {
		return ngayThucHien;
	}

	public void setNgayThucHien(Date ngayThucHien) {
		this.ngayThucHien = ngayThucHien;
	}

	
	public PhieuNhapDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public List<CTPNDTO> getCtpns() {
		return ctpns;
	}



	public void setCtpns(List<CTPNDTO> ctpns) {
		this.ctpns = ctpns;
	}




}
