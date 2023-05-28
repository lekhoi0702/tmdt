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

import springboot.dto.LoaiSPDTO;
import springboot.entity.LoaiSPEntity;
import springboot.input.ObjDelLong;
import springboot.repository.LoaiSPRepository;

@RestController
public class LoaiSPAPI {
	@Autowired
	LoaiSPRepository repo;

	@GetMapping("/loaisp")
	public List<LoaiSPDTO> getLoaiSP() {
		List<LoaiSPEntity> list = repo.findAll();
		List<LoaiSPDTO> listDTO = new ArrayList<LoaiSPDTO>();
		for (LoaiSPEntity model : list) {
			LoaiSPDTO save = new LoaiSPDTO();

			save.setid(model.getId());

			save.setTenLoai(model.getTenLoai());

			listDTO.add(save);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/loaisp")
	public String createSP(@RequestBody LoaiSPDTO model) {

		LoaiSPEntity save = new LoaiSPEntity();
		LoaiSPEntity check = null;
		try {

			save.setTenLoai(model.getTenLoai());

			check = repo.save(save);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(model.getTenLoai());

			return "01";
		}
		if (check == null) {

			return "02";
		}
		return "00";
	}

	@PutMapping(value = "/loaisp")
	public String updateSP(@RequestBody LoaiSPDTO model) {

		Optional<LoaiSPEntity> nvoption = repo.findById(model.getid());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			LoaiSPEntity save = nvoption.get();
			LoaiSPEntity check = null;
			try {

				save.setId(model.getid());

				save.setTenLoai(model.getTenLoai());

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

	@DeleteMapping(value = "/chiphi")
	public String delete(@RequestBody ObjDelLong ids) {
		Optional<LoaiSPEntity> option = repo.findById(ids.getId());
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
