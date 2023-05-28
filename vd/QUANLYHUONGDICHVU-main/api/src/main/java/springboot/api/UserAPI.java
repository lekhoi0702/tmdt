package springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.UserDTO;
import springboot.entity.UserTBEntity;
import springboot.repository.ChucVuRepository;
import springboot.repository.NhanVienRepository;
import springboot.repository.UserTBRepository;

@RestController
public class UserAPI {
	@Autowired
	UserTBRepository repo;
	@Autowired
	NhanVienRepository nvrepo;
	@Autowired
	ChucVuRepository cvrepo;

	@GetMapping("/user")
	public List<UserDTO> getUser() {
		List<UserTBEntity> list = repo.findAllActive();
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		for (UserTBEntity item : list) {
			UserDTO e = new UserDTO();
			e.setEmail(item.getEmail());
			e.setIcon(item.getIcon());
			e.setID(item.getUsernv().getMaNV());
			e.setPasswd(item.getPasswd());
			e.setRoleID(item.getChucVu().getId());
			e.setStatus(item.getStatus());
			e.setUserName(item.getUserName());
	
			listDTO.add(e);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/user")
	public String create(@RequestBody UserDTO model) {

		UserTBEntity save = new UserTBEntity();
		UserTBEntity check = null;
		try {
			save.setUserName(model.getUserName());
			save.setPasswd(model.getPasswd());
			save.setUsernv(nvrepo.getOne(model.getID()));
			save.setChucVu(cvrepo.getOne(model.getRoleID()));
			save.setEmail(model.getEmail());
			save.setStatus(1);
			save.setIcon(null);

			check = repo.save(save);
		} catch (Exception e) {
			e.printStackTrace();

			return "01";
		}
		if (check == null) {

			return "02";
		}
		return "00";
	}

	@PutMapping(value = "/user")
	public String update(@RequestBody UserDTO model) {

		Optional<UserTBEntity> option = repo.findById(model.getUserName());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			UserTBEntity save = option.get();
			UserTBEntity check = null;
			try {

				save.setUserName(model.getUserName());
				save.setPasswd(model.getPasswd());
				save.setUsernv(nvrepo.getOne(model.getID()));
				save.setChucVu(cvrepo.getOne(model.getRoleID()));
				save.setEmail(model.getEmail());
				save.setStatus(model.getStatus());
				save.setIcon(model.getIcon());
				check = repo.save(save);
			} catch (Exception e) {
				e.printStackTrace();
				return "01";
			}

			if (check == null) {
				return "02";
			}
			return "00";
		}

	}

	@PatchMapping(value = "/user")
	public String deleteUser(@RequestBody String ids) {
		Optional<UserTBEntity> option = repo.findById(ids);
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		} else {
			System.out.print("tồn tại");
			UserTBEntity save = option.get();

			try {
				save.setStatus(0);
				repo.save(save);
			} catch (Exception e) {
				e.printStackTrace();
				return "02";
			}

			return "00";
		}
	}
}
