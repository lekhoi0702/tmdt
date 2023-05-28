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

import springboot.dto.ThucDonDTO;
import springboot.entity.ThucDonEntity;
import springboot.input.ObjDelString;
import springboot.repository.LoaiThucUongRepository;
import springboot.repository.ThucDonRepository;

@RestController
public class ThucDonAPI {
	@Autowired
	ThucDonRepository repo;
	@Autowired
	LoaiThucUongRepository lturepo;
	@GetMapping("/thucdon")
	public List<ThucDonDTO> getThucDon() {
		List<ThucDonEntity> list = repo.findAll();
		List<ThucDonDTO> listDTO = new ArrayList<ThucDonDTO>();
		for (ThucDonEntity item : list) {
			ThucDonDTO e = new ThucDonDTO();
			e.setGia(item.getGia());
			e.setid(item.getId());
			e.setLoaiThucUong(item.getLoaiThucUong().getTenLoai());
			e.setTen(item.getTen());
			listDTO.add(e);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/thucdon")
	public String create(@RequestBody ThucDonDTO model) {

		ThucDonEntity save = new ThucDonEntity();
		ThucDonEntity check = null;
		try {
			save.setId(model.getid());
			save.setTen(model.getTen());
			save.setGia(model.getGia());
			save.setLoaiThucUong(lturepo.getOne(model.getLoaiThucUong()));
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

	@PutMapping(value = "/thucdon")
	public String update(@RequestBody ThucDonDTO model) {

		Optional<ThucDonEntity> option = repo.findById(model.getid());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			ThucDonEntity save = option.get();
			ThucDonEntity check = null;
			try {
				save.setId(model.getid());

				save.setTen(model.getTen());
				save.setGia(model.getGia());
				save.setLoaiThucUong(lturepo.getOne(model.getLoaiThucUong()));
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

	@DeleteMapping(value = "/thucdon")
	public String delete(@RequestBody ObjDelString ids) {

		Optional<ThucDonEntity> option = repo.findById(ids.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		} else {
			System.out.print("tồn tại");

			try {

				repo.deleteById(ids.getId());
			} catch (Exception e) {
				e.printStackTrace();
				return "02";
			}

			return "00";
		}
	}
}
