package springboot.entity;

import java.util.ArrayList;
import java.util.Collection;

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

import org.hibernate.annotations.Type;

@Entity
@Table(name="SANPHAM")
public class SanPhamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@Column(name = "GIA")
	private Integer gia;
	
	@OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
	private Collection<Nhan_SanPhamEntity> chiTietNhan= new ArrayList<Nhan_SanPhamEntity>();
	
	public Collection<Nhan_SanPhamEntity> getChiTietNhan() {
		return chiTietNhan;
	}

	public void setChiTietNhan(Collection<Nhan_SanPhamEntity> chiTietNhan) {
		this.chiTietNhan = chiTietNhan;
	}

	@Column(name = "SOLUONGTON")
	private Integer slTon;

	@Column(name = "DONVITINH")
	private String dvt;

	@Column(name = "TEN")
	private String ten;

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	@Column(name = "ICON")
	private String icon;


	@Column(name = "TRANGTHAI")
	private Integer trangThai;
	
	
	@Column(name = "MOTA")
	@Type(type="text")
	private String moTa;
	
	@ManyToOne
	@JoinColumn(name="LOAI")
	private LoaiSPEntity loaiSP;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGia() {
		return gia;
	}

	public void setGia(Integer gia) {
		this.gia = gia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Integer getSlTon() {
		return slTon;
	}

	public void setSlTon(Integer slTon) {
		this.slTon = slTon;
	}

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvTinh) {
		this.dvt = dvTinh;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}

	public LoaiSPEntity getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(LoaiSPEntity loaiSP) {
		this.loaiSP = loaiSP;
	}

//	@OneToMany(mappedBy = "nvThucHien", fetch = FetchType.LAZY)
//	private Collection<HoaDonEntity> hoadon;
//
//	@OneToMany(mappedBy = "nvTao", fetch = FetchType.LAZY)
//	private Collection<ChiPhiEntity> chiPhi;
	
	
}
