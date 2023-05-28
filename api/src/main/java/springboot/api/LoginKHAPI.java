package springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.LoginKHDTO;
import springboot.dto.UserKHDTO;
import springboot.entity.KhachHangEntity;
import springboot.entity.UserKHEntity;
import springboot.repository.KhachHangRepository;
import springboot.repository.UserKHRepository;

@RestController
public class LoginKHAPI {
	@Autowired
	UserKHRepository urepo;
	@Autowired
	KhachHangRepository khrepo;


	@PostMapping(value = "/loginkh")
	public LoginKHDTO checkUserNameAndPass(@RequestBody UserKHDTO model) {

	
		UserKHEntity user = urepo.findByUserNameAndPasswdAndStatus(model.getUserName(),model.getPasswd(),1);
		if (user==null) {
		return null;
		}
		else {
			
			LoginKHDTO e=new LoginKHDTO();
			KhachHangEntity kh=user.getUserkh();
			e.setMaKH(kh.getMaKH());
			e.setDiaChi(kh.getDiaChi());
			e.setGioiTinh(kh.getGioiTinh());
			e.setHoTen(kh.getHoTen());
			e.setNgaySinh(kh.getNgaySinh());
			e.setSdt(kh.getSdt());
			e.setEmail(user.getEmail());
			e.setIcon(user.getIcon());
			e.setPasswd(user.getPasswd());
			e.setUserName(user.getUserName());
			e.setTrangThai(kh.getTrangThai());
			return e;
		}
	}

	

}
