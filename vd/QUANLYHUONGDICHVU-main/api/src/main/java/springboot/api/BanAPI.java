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

import springboot.dto.BanDTO;
import springboot.entity.BanEntity;
import springboot.repository.BanRepository;
import springboot.repository.LoaiBanRepository;

@RestController
public class BanAPI {
	@Autowired
	BanRepository repo;
	@Autowired
	LoaiBanRepository loaibanrepo;

	@GetMapping("/ban")
	public List<BanDTO> getBan() {
		List<BanEntity> list = repo.findAllActive();
		List<BanDTO> listDTO = new ArrayList<BanDTO>();
		for (BanEntity item : list) {
			BanDTO e = new BanDTO();
			e.setId(item.getId());
			e.setTenLoai(item.getLoaiBan().getTenLoai());
			e.setLoaiBan(item.getLoaiBan().getId());
			e.setSoGhe(item.getSoGhe());
			e.setTinhTrang(item.getTinhTrang());
			listDTO.add(e);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/ban")
	public String create(@RequestBody BanDTO model) {

		BanEntity save = new BanEntity();
		BanEntity check = null;
		try {
			save.setLoaiBan(loaibanrepo.findById(model.getLoaiBan()).get());
			save.setSoGhe(model.getSoGhe());
			save.setTinhTrang(model.getTinhTrang());

			check = repo.saveAndFlush(save);
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.print(model.getLoaiBan());

			return "01";
		}
		if (check == null) {

			return "02";
		}
		return "00";
	}

	@PutMapping(value = "/ban")
	public String update(@RequestBody BanDTO model) {

		Optional<BanEntity> option = repo.findById(model.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			BanEntity save = option.get();
			BanEntity check = null;
			try {

				save.setLoaiBan(loaibanrepo.findById(model.getLoaiBan()).get());
				save.setSoGhe(model.getSoGhe());
				save.setTinhTrang(model.getTinhTrang());
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

	@PatchMapping(value = "/ban")
	public String delete(@RequestBody BanDTO ids) {
		Optional<BanEntity> option = repo.findById(ids.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại nv");
			return "404";
		}

		else {
			System.out.print("tồn tại nv");
			BanEntity save = option.get();
			BanEntity check = null;
			try {

				save.setTinhTrang(0);
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

}
