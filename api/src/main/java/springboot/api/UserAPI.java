package springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.UserDTO;
import springboot.entity.UserEntity;
import springboot.repository.ChucVuRepository;
import springboot.repository.NhanVienRepository;
import springboot.repository.UserRepository;

@RestController
public class UserAPI {
	@Autowired
	UserRepository repo;
	@Autowired
	NhanVienRepository nvrepo;
	@Autowired
	ChucVuRepository cvrepo;

	@GetMapping("/user")
	public List<UserDTO> getUser() {
		List<UserEntity> list = repo.findAllActive();
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		for (UserEntity item : list) {
			UserDTO e = new UserDTO();
			e.setEmail(item.getEmail());
			e.setIcon(item.getIcon());
			e.setID(item.getUsernv().getMaNV());
			e.setPasswd(item.getPasswd());
			e.setRoleID(item.getChucVu().getId());
			e.setUserName(item.getUserName());
	
			listDTO.add(e);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/user")
	public String create(@RequestBody UserDTO model) {

		UserEntity save = new UserEntity();
		UserEntity check = null;
		try {
			save.setUserName(model.getUserName());
			save.setPasswd(model.getPasswd());
			save.setUsernv(nvrepo.getOne(model.getID()));
			save.setChucVu(cvrepo.getOne(model.getRoleID()));
			save.setEmail(model.getEmail());
			save.setIcon("logo.webp");

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

		Optional<UserEntity> option = repo.findById(model.getUserName());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			UserEntity save = option.get();
			UserEntity check = null;
			try {

				save.setUserName(model.getUserName());
				save.setPasswd(model.getPasswd());
				save.setUsernv(nvrepo.getOne(model.getID()));
				save.setChucVu(cvrepo.getOne(model.getRoleID()));
				save.setEmail(model.getEmail());
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

	@DeleteMapping(value = "/user")
	public String deleteUser(@RequestBody String ids) {
		Optional<UserEntity> option = repo.findById(ids);
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		} else {
			System.out.print("tồn tại");

			try {
				repo.deleteById(ids);
			} catch (Exception e) {
				e.printStackTrace();
				return "02";
			}

			return "00";
		}
	}
}
