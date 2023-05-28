package springboot.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PHIEUNHAP")
public class PhieuNhapEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAPN")
	private Long id;

	@Column(name = "NGAY")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date ngayThucHien;

	@Column(name = "TINHTRANG")
	private Integer tinhTrang;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private NhanVienEntity nvThucHien;
	
	@OneToMany(mappedBy = "phieuNhap", fetch = FetchType.LAZY)
	private Collection<CTPNEntity> ctpns;
	
	public Collection<CTPNEntity> getCtpns() {
		return ctpns;
	}



	public void setCtpns(Collection<CTPNEntity> ctpns) {
		this.ctpns = ctpns;
	}



	public String getNcc() {
		return ncc;
	}



	public void setNcc(String ncc) {
		this.ncc = ncc;
	}

	@Column(name = "NHACC")
	private String ncc;

	

	public NhanVienEntity getNvThucHien() {
		return nvThucHien;
	}



	public void setNvThucHien(NhanVienEntity nvThucHien) {
		this.nvThucHien = nvThucHien;
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

	
	public PhieuNhapEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


}
