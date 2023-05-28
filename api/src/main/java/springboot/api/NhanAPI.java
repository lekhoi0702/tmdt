package springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.NhanDTO;
import springboot.entity.NhanEntity;
import springboot.input.ObjDelString;
import springboot.repository.NhanRepository;
import springboot.repository.SanPhamRepository;

@RestController
public class NhanAPI {
	@Autowired
	NhanRepository repo;
	@Autowired
	SanPhamRepository sanPhamRepo;


	@GetMapping("/nhan")
	public List<NhanDTO> getNhan() {

	
			System.out.print("null");

			{
				List<NhanEntity> list = repo.findAll();
				List<NhanDTO> listDTO = new ArrayList<NhanDTO>();
				for (NhanEntity model : list) {
					NhanDTO save = new NhanDTO();
					save.setTenNhan(model.getTenNhan());
					save.setNvSua(model.getNvSua());
					save.setNvTao(model.getNvTao());
					save.setNgayTao(model.getNgayTao());

					listDTO.add(save);
				}
				System.out.print(list.size());
				return listDTO;
			}
		}

	
	

	@PostMapping(value = "/nhan")
	public String createNhan(@RequestBody NhanDTO model) {

		NhanEntity save = new NhanEntity();
		NhanEntity check = null;
		try {
			save.setTenNhan(model.getTenNhan());
			save.setNvSua(model.getNvSua());
			save.setNvTao(model.getNvTao());
			save.setNgayTao(model.getNgayTao());

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

	

	@DeleteMapping(value = "/nhan")
	public String delete(@RequestBody ObjDelString ids) {
		Optional<NhanEntity> option = repo.findById(ids.getId());
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
