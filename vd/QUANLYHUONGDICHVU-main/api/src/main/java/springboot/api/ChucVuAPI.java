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

import springboot.dto.ChucVuDTO;
import springboot.dto.HoaDonDTO;
import springboot.entity.ChucVuEntity;
import springboot.input.ObjDelLong;
import springboot.repository.ChucVuRepository;

@RestController
public class ChucVuAPI {
	@Autowired
	ChucVuRepository repo;

	@GetMapping("/chucvu")
	public List<ChucVuDTO> getChucVu() {
		List<ChucVuDTO> listDTO = new ArrayList<ChucVuDTO>();
		List<ChucVuEntity> list = repo.findAll();
		for (ChucVuEntity item : list) {
			ChucVuDTO d = new ChucVuDTO();
			d.setId(item.getId());
			d.setTenChucVu(item.getTenChucVu());
			listDTO.add(d);
		}
		System.out.print(list.size());
		return listDTO;

	}

	@PostMapping(value = "/chucvu")

	public String create(@RequestBody ChucVuDTO model) {

		ChucVuEntity save = new ChucVuEntity();
		ChucVuEntity check = null;
		try {
			save.setUserTB(null);
			save.setTenChucVu(model.getTenChucVu());

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

	

	@PutMapping(value = "/chucvu")
	public String update(@RequestBody ChucVuDTO model) {

		Optional<ChucVuEntity> option = repo.findById(model.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			ChucVuEntity save = option.get();
			ChucVuEntity check = null;
			try {
				save.setTenChucVu(model.getTenChucVu());
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

	@DeleteMapping(value = "/chucvu")

	public String delete(@RequestBody ObjDelLong ids) {
		Optional<ChucVuEntity> option = repo.findById(ids.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}
		else {
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
