package springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.entity.UserKHEntity;
import springboot.input.ObjDelString;
import springboot.repository.ChucVuRepository;
import springboot.repository.KhachHangRepository;
import springboot.repository.UserKHRepository;

@RestController
public class UserKHAPI2 {
	@Autowired
	UserKHRepository repo;
	@Autowired
	KhachHangRepository khrepo;
	@Autowired
	ChucVuRepository cvrepo;


	@PostMapping(value = "/userkh2")
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
