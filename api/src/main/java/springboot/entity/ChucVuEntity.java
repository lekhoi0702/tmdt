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
@Table(name="CHUCVU")
public class ChucVuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="TENCHUCVU")
	private String tenChucVu;
	
	@OneToMany(mappedBy="chucVu", fetch = FetchType.LAZY)
	private Collection<UserEntity> userTB;


	public Collection<UserEntity> getUserTB() {
		return userTB;
	}

	public void setUserTB(Collection<UserEntity> userTB) {
		this.userTB = userTB;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenChucVu() {
		return tenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}

	public Collection<UserEntity> getUser() {
		return userTB;
	}

	public void setUser(Collection<UserEntity> userTB) {
		this.userTB = userTB;
	}

	public ChucVuEntity(Long id, String tenChucVu, Collection<UserEntity> userTB) {
		super();
		this.id = id;
		this.tenChucVu = tenChucVu;
		this.userTB = userTB;
	}

	public ChucVuEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
