package spring.dto;

public class GioHangDTO {

	
		private Long ID;
		

		private Long maKH;


		private Long MaSP;
		
		
		private Integer soLuong;

		public Long getID() {
			return ID;
		}

		public void setID(Long iD) {
			ID = iD;
		}

		public Long getMaKH() {
			return maKH;
		}

		public void setMaKH(Long maKH) {
			this.maKH = maKH;
		}

		public Long getMaSP() {
			return MaSP;
		}

		public void setMaSP(Long maSP) {
			MaSP = maSP;
		}

		public Integer getSoLuong() {
			return soLuong;
		}

		public void setSoLuong(Integer soLuong) {
			this.soLuong = soLuong;
		}

	
}
