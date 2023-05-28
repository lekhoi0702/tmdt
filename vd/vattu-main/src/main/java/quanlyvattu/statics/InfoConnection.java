package quanlyvattu.statics;

public class InfoConnection {

	private static String userNameChu="sa";
	private static String passWordChu="1234";
	private static String urlChu="jdbc:sqlserver://GHDBP-20210702Z; Database=QLVT_DATHANG";
	private static String driverChu="com.microsoft.sqlserver.jdbc.SQLServerDriver";

	
	private static String userNamePM;
	private static String passWordPM;
	private static String urlPM;
	private static String driverPM="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private static String userNameTC="sa";
	private static String passWordTC="1234";
	private static String urlTC="jdbc:sqlserver://GHDBP-20210702Z\\SQLSV3; Database=QLVT_DATHANG";
	private static String driverTC="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String getUserNameChu() {
		return userNameChu;
	}
	public static void setUserNameChu(String userNameChu) {
		InfoConnection.userNameChu = userNameChu;
	}
	public static String getPassWordChu() {
		return passWordChu;
	}
	public static void setPassWordChu(String passWordChu) {
		InfoConnection.passWordChu = passWordChu;
	}
	public static String getUrlChu() {
		return urlChu;
	}
	public static void setUrlChu(String urlChu) {
		InfoConnection.urlChu = urlChu;
	}
	public static String getDriverChu() {
		return driverChu;
	}
	public static void setDriverChu(String driverChu) {
		InfoConnection.driverChu = driverChu;
	}
	public static String getUserNamePM() {
		return userNamePM;
	}
	public static void setUserNamePM(String userNamePM) {
		InfoConnection.userNamePM = userNamePM;
	}
	public static String getPassWordPM() {
		return passWordPM;
	}
	public static void setPassWordPM(String passWordPM) {
		InfoConnection.passWordPM = passWordPM;
	}
	public static String getUrlPM() {
		return urlPM;
	}
	public static void setUrlPM(String urlPM) {
		InfoConnection.urlPM = "jdbc:sqlserver://"+urlPM+"; Database=QLVT_DATHANG";
	}
	public static String getDriverPM() {
		return driverPM;
	}
	public static void setDriverPM(String driverPM) {
		InfoConnection.driverPM = driverPM;
	}
	public static String getUserNameTC() {
		return userNameTC;
	}
	public static void setUserNameTC(String userNameTC) {
		InfoConnection.userNameTC = userNameTC;
	}
	public static String getPassWordTC() {
		return passWordTC;
	}
	public static void setPassWordTC(String passWordTC) {
		InfoConnection.passWordTC = passWordTC;
	}
	public static String getUrlTC() {
		return urlTC;
	}
	public static void setUrlTC(String urlTC) {
		InfoConnection.urlTC = urlTC;
	}
	public static String getDriverTC() {
		return driverTC;
	}
	public static void setDriverTC(String driverTC) {
		InfoConnection.driverTC = driverTC;
	}
	
	
	

}
