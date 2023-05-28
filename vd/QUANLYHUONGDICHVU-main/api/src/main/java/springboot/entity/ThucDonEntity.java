package springboot.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="THUCDON")
public class ThucDonEntity {

	@Id
	@Column(name="ID")
	private String id;
	@OneToMany(mappedBy="thucDon", fetch=FetchType.LAZY)
	private Collection<ChiTietHDEntity> chiTietHD;
	@Column(name="TEN")
	private String ten;

	@ManyToOne
	@JoinColumn(name="LOAI")
	private LoaiThucUongEntity loaiThucUong;
	
	@Column(name="GIA")
	private Integer gia;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public LoaiThucUongEntity getLoaiThucUong() {
		return loaiThucUong;
	}

	public void setLoaiThucUong(LoaiThucUongEntity loaiThucUong) {
		this.loaiThucUong = loaiThucUong;
	}

	public Integer getGia() {
		return gia;
	}

	public void setGia(Integer gia) {
		this.gia = gia;
	}

	public Collection<ChiTietHDEntity> getChiTietHD() {
		return chiTietHD;
	}

	public void setChiTietHD(Collection<ChiTietHDEntity> chiTietHD) {
		this.chiTietHD = chiTietHD;
	}

	public ThucDonEntity(String id, Collection<ChiTietHDEntity> chiTietHD, String ten, LoaiThucUongEntity loaiThucUong,
			Integer gia) {
		super();
		this.id = id;
		this.chiTietHD = chiTietHD;
		this.ten = ten;
		this.loaiThucUong = loaiThucUong;
		this.gia = gia;
	}

	public ThucDonEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
