package springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.UserKHDTO;
import springboot.entity.UserKHEntity;
import springboot.input.ObjDelString;
import springboot.repository.ChucVuRepository;
import springboot.repository.KhachHangRepository;
import springboot.repository.UserKHRepository;

@RestController
public class UserKHAPI {
	@Autowired
	UserKHRepository repo;
	@Autowired
	KhachHangRepository khrepo;
	@Autowired
	ChucVuRepository cvrepo;

	@GetMapping("/userkh")
	public List<UserKHDTO> getUserKH() {
		List<UserKHEntity> list = repo.findAll();
		List<UserKHDTO> listDTO = new ArrayList<UserKHDTO>();
		for (UserKHEntity item : list) {
			UserKHDTO e = new UserKHDTO();
			e.setEmail(item.getEmail());
			e.setIcon(item.getIcon());
			e.setID(item.getUserkh().getMaKH());
			e.setPasswd(item.getPasswd());
			e.setUserName(item.getUserName());
			e.setStatus(item.getStatus());
	
			listDTO.add(e);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/userkh")
	public String create(@RequestBody UserKHDTO model) {

		UserKHEntity save = new UserKHEntity();
		UserKHEntity check = null;
		try {
			save.setUserName(model.getUserName());
			save.setPasswd(model.getPasswd());
			save.setUserkh(khrepo.getOne(model.getID()));
			save.setEmail(model.getEmail());
			save.setIcon(model.getIcon());
			save.setMaXacThuc(model.getMaXacThuc());
			save.setStatus(model.getStatus());

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

	@PutMapping(value = "/userkh")
	public String update(@RequestBody UserKHDTO model) {

		Optional<UserKHEntity> option = repo.findById(model.getUserName());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			UserKHEntity save = option.get();
			UserKHEntity check = null;
			try {

				save.setUserName(model.getUserName());
				save.setPasswd(model.getPasswd());
				save.setUserkh(khrepo.getOne(model.getID()));
				save.setEmail(model.getEmail());
				save.setIcon(model.getIcon());
				save.setStatus(model.getStatus());
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

	@DeleteMapping(value = "/userkh")
	public String deleteUserKH(@RequestBody String ids) {
		Optional<UserKHEntity> option = repo.findById(ids);
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

	@PatchMapping(value = "/userkh")
	public String deleteNV(@RequestBody ObjDelString maXacNhan) {
		UserKHEntity nvoption = repo.findByMaXacThucAndStatus(maXacNhan.getId(), 0);
		if (nvoption==null) {

			System.out.print("ko tồn tại code");
			return "404";
		}

		else {
			System.out.print("tồn tại code");
			UserKHEntity check = null;
			try {

				nvoption.setStatus(1);
				check = repo.save(nvoption);

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
}
