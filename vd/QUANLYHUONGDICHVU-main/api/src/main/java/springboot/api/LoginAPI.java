package springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.LoginDTO;
import springboot.entity.NhanVienEntity;
import springboot.entity.UserTBEntity;
import springboot.repository.NhanVienRepository;
import springboot.repository.UserTBRepository;

@RestController
public class LoginAPI {
	@Autowired
	UserTBRepository urepo;
	@Autowired
	NhanVienRepository nvrepo;


	@PostMapping(value = "/login")
	public LoginDTO checkUserNameAndPass(@RequestBody LoginDTO model) {

	
		UserTBEntity user = urepo.findByUserNameAndPasswd(model.getUserName(),model.getPasswd());
		if (user==null) {
		return null;
		}
		else {
			
			LoginDTO e=new LoginDTO();
			NhanVienEntity nv=user.getUsernv();
			e.setMaNV(nv.getMaNV());
			e.setDiaChi(nv.getDiaChi());
			e.setGioiTinh(nv.getGioiTinh());
			e.setHoTen(nv.getHoTen());
			e.setLuong(nv.getLuong());
			e.setNgaySinh(nv.getNgaySinh());
			e.setNgayVaoLam(nv.getNgayVaoLam());
			e.setSdt(nv.getSdt());
			e.setCmnd(nv.getCmnd());
			e.setEmail(user.getEmail());
			e.setIcon(user.getIcon());
			e.setID(user.getUsernv().getMaNV());
			e.setPasswd(user.getPasswd());
			e.setRoleID(user.getChucVu().getId());
			e.setStatus(user.getStatus());
			e.setUserName(user.getUserName());
			return e;
		}
	}

	

}
