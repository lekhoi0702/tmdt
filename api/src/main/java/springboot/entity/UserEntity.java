package springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class UserEntity {
	@Id
	@Column(name="USERNAME")
	private String userName;
	
	@OneToOne
	@JoinColumn(name="MANV")
	private NhanVienEntity usernv;
	
	@Column(name="PASSWD")
	private String passwd;
	
	
	@ManyToOne
	@JoinColumn(name="ROLEID")
	private ChucVuEntity chucVu;

	@Column(name ="EMAIL")
	private String email;
	
	@Column(name="ICON")
	private String icon;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public NhanVienEntity getUsernv() {
		return usernv;
	}

	public void setUsernv(NhanVienEntity usernv) {
		this.usernv = usernv;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public ChucVuEntity getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVuEntity chucVu) {
		this.chucVu = chucVu;
	}



	public UserEntity(String userName, NhanVienEntity usernv, String passwd, ChucVuEntity chucVu, Integer status,
			String email, String icon) {
		super();
		this.userName = userName;
		this.usernv = usernv;
		this.passwd = passwd;
		this.chucVu = chucVu;
		this.email = email;
		this.icon = icon;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
