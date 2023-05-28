package springboot.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOAIBAN")
public class LoaiBanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID")
	private Long id;
	@Column(name = "TENLOAI")
	private String tenLoai;
	@Column(name = "GIADAT")
	private int giaDat;
	
	@OneToMany(mappedBy = "loaiBan", fetch = FetchType.LAZY)
	private Collection<BanEntity> ban;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public int getGiaDat() {
		return giaDat;
	}

	public void setGiaDat(int giaDat) {
		this.giaDat = giaDat;
	}

	public Collection<BanEntity> getBan() {
		return ban;
	}

	public void setBan(Collection<BanEntity> ban) {
		this.ban = ban;
	}

	public LoaiBanEntity(Long id, String tenLoai, int giaDat, Collection<BanEntity> ban) {
		super();
		this.id = id;
		this.tenLoai = tenLoai;
		this.giaDat = giaDat;
		this.ban = ban;
	}

	public LoaiBanEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
