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

import springboot.dto.LoaiBanDTO;
import springboot.entity.LoaiBanEntity;
import springboot.input.ObjDelLong;
import springboot.repository.LoaiBanRepository;

@RestController
public class LoaiBanAPI {
	@Autowired
	LoaiBanRepository repo;

	@GetMapping("/loaiban")
	public List<LoaiBanDTO> getLoaiBan() {
		List<LoaiBanDTO> list1= new ArrayList<LoaiBanDTO>();
		List<LoaiBanEntity> list = repo.findAll();
		for (LoaiBanEntity item : list) {
			item.setBan(null);
		}
		System.out.print(list.size());
		//cao ky them vao sua ham get tu loaiBanEntity thanh LoaiBanDTO
		for (LoaiBanEntity item : list) {
			LoaiBanDTO b = new LoaiBanDTO();
			b.setGiaDat(item.getGiaDat());
			b.setid(item.getId());;
			b.setTenLoai(item.getTenLoai());
			list1.add(b);
		}
		return list1;

	}

	@PostMapping(value = "/loaiban")
	public String create(@RequestBody LoaiBanDTO model) {

		LoaiBanEntity save = new LoaiBanEntity();
		LoaiBanEntity check = null;
		try {
			save.setGiaDat(model.getGiaDat());
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

	@PutMapping(value="/loaiban")
	public String update(@RequestBody LoaiBanDTO model) {

		Optional<LoaiBanEntity> option = repo.findById(model.getid());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			LoaiBanEntity save = option.get();
			LoaiBanEntity check = null;
			try {

				save.setGiaDat(model.getGiaDat());
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

	@DeleteMapping(value="/loaiban")
	public String delete(@RequestBody ObjDelLong ids) {

		Optional<LoaiBanEntity> option = repo.findById(ids.getId());
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
