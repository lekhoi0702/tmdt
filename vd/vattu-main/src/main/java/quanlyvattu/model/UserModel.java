package quanlyvattu.model;




public class UserModel {

	
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String userName;
	
	private String passwd;


	private String chiNhanh;
	
	private String maChiNhanh;
	
	private String roleID;
	
	private String hoTen;
	
	private int maNV;
	
	public String getHoTen() {
		return hoTen;
	}



	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}



	public int getMaNV() {
		return maNV;
	}



	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}






	public String getUserName() {
		return userName;
	}
	
	

	public void setUserName(String userName) {
		this.userName = userName;
	}








	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getRoleID() {
		return roleID;
	}


	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}


	public String getChiNhanh() {
		return chiNhanh;
	}


	public void setChiNhanh(String chiNhanh) {
		this.chiNhanh = chiNhanh;
	}



	public String getMaChiNhanh() {
		return maChiNhanh;
	}



	public void setMaChiNhanh(String maChiNhanh) {
		this.maChiNhanh = maChiNhanh;
	}




	
	
}
