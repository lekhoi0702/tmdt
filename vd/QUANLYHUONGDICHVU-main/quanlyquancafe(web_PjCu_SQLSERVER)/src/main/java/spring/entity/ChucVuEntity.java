package spring.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CHUCVU")
public class ChucVuEntity {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="TENCHUCVU")
	private String tenChucVu;
	
	@OneToMany(mappedBy="chucVu", fetch = FetchType.EAGER)
	private Collection<UserTBEntity> userTB;

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

	public Collection<UserTBEntity> getUserTB() {
		return userTB;
	}

	public void setUserTB(Collection<UserTBEntity> userTB) {
		this.userTB = userTB;
	}
	
	
}
