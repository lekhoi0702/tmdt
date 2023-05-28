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

import springboot.dto.TuKhoaDTO;
import springboot.entity.TuKhoaEntity;
import springboot.input.ObjDelString;
import springboot.repository.SanPhamRepository;
import springboot.repository.TuKhoaRepository;

@RestController
public class TuKhoaAPI {
	@Autowired
	TuKhoaRepository repo;
	@Autowired
	SanPhamRepository sanPhamRepo;


	@GetMapping("/tukhoa")
	public List<TuKhoaDTO> getTuKhoa() {

	
			System.out.print("null");

			{
				List<TuKhoaEntity> list = repo.findAll();
				List<TuKhoaDTO> listDTO = new ArrayList<TuKhoaDTO>();
				for (TuKhoaEntity model : list) {
					TuKhoaDTO save = new TuKhoaDTO();
					save.setTenTuKhoa(model.getTenTuKhoa());
					save.setNvSua(model.getNvSua());
					save.setNvTao(model.getNvTao());
					save.setNgayTao(model.getNgayTao());
					listDTO.add(save);
				}
				System.out.print(list.size());
				return listDTO;
			}
		}

	
	

	@PostMapping(value = "/tukhoa")
	public String createTuKhoa(@RequestBody TuKhoaDTO model) {

		TuKhoaEntity save = new TuKhoaEntity();
		TuKhoaEntity check = null;
		try {
			save.setTenTuKhoa(model.getTenTuKhoa());
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

	

	@DeleteMapping(value = "/tukhoa")
	public String delete(@RequestBody ObjDelString ids) {
		Optional<TuKhoaEntity> option = repo.findById(ids.getId());
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
