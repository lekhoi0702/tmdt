package springboot.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERKH")
public class UserKHEntity {
	@Id
	@Column(name = "USERNAME")
	private String userName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MAKH")
	private KhachHangEntity userkh;

	@Column(name = "PASSWD")
	private String passwd;

	@Column(name = "MAXACTHUC")
	private String maXacThuc;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ICON")
	private String icon;

	@Column(name = "STATUS")
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public String getMaXacThuc() {
		return maXacThuc;
	}

	public void setMaXacThuc(String maXacThuc) {
		this.maXacThuc = maXacThuc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public KhachHangEntity getUserkh() {
		return userkh;
	}

	public void setUserkh(KhachHangEntity userkh) {
		this.userkh = userkh;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public UserKHEntity(String userName, KhachHangEntity userkh, String passwd, Integer status, String email,
			String icon) {
		super();
		this.userName = userName;
		this.userkh = userkh;
		this.passwd = passwd;
		this.email = email;
		this.icon = icon;
	}

	public UserKHEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
