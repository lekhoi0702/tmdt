package springboot.entity;

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

@Entity
@Table(name = "BAN")
public class BanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "SOGHE")
	private Integer soGhe;

	@ManyToOne
	@JoinColumn(name = "LOAI")
	private LoaiBanEntity loaiBan;

	@OneToMany(mappedBy = "ban", fetch = FetchType.LAZY)
	private Collection<HoaDonEntity> hoaDon;

	@Column(name = "TINHTRANG")
	private int tinhTrang;

	public int getTinhTrang() {
		return tinhTrang;
	}



	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(Integer soGhe) {
		this.soGhe = soGhe;
	}

	public LoaiBanEntity getLoaiBan() {
		return loaiBan;
	}

	public void setLoaiBan(LoaiBanEntity loaiBan) {
		this.loaiBan = loaiBan;
	}

	public Collection<HoaDonEntity> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(Collection<HoaDonEntity> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public BanEntity(Long id, Integer soGhe, LoaiBanEntity loaiBan, Collection<HoaDonEntity> hoaDon, int tinhTrang) {
		super();
		this.id = id;
		this.soGhe = soGhe;
		this.loaiBan = loaiBan;
		this.hoaDon = hoaDon;

		this.tinhTrang = tinhTrang;
	}

	public BanEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
