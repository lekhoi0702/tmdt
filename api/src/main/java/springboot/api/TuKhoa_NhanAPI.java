package springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.TuKhoa_NhanDTO;
import springboot.entity.TuKhoa_NhanEntity;
import springboot.input.ObjDelLong;
import springboot.repository.NhanRepository;
import springboot.repository.TuKhoaRepository;
import springboot.repository.TuKhoa_NhanRepository;

@RestController
public class TuKhoa_NhanAPI {
	@Autowired
	TuKhoa_NhanRepository repo;
	@Autowired
	TuKhoaRepository tkRepo;
	@Autowired
	NhanRepository nRepo;


	@GetMapping("/tukhoanhan")
	public List<TuKhoa_NhanDTO> getTuKhoa_Nhan(HttpServletRequest request) {

		String nhan = request.getParameter("tennhan");
		String tuKhoa = request.getParameter("tentukhoa");

		if (nhan != null) {
			List<TuKhoa_NhanEntity> list = (List<TuKhoa_NhanEntity>) nRepo.findById(nhan).get().getChiTietNhan();
			List<TuKhoa_NhanDTO> listDTO = new ArrayList<TuKhoa_NhanDTO>();
			for (TuKhoa_NhanEntity model : list) {
				TuKhoa_NhanDTO save = new TuKhoa_NhanDTO();
				save.setNhan(model.getNhan().getTenNhan());
				save.setTuKhoa(model.getTuKhoa().getTenTuKhoa());
				save.setNvSua(model.getNvSua());
				save.setNvTao(model.getNvTao());
				save.setNgayTao(model.getNgayTao());
				

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}
			
		else if (tuKhoa != null) {
			List<TuKhoa_NhanEntity> list = (List<TuKhoa_NhanEntity>) tkRepo.findById(tuKhoa).get().getChiTietTuKhoa();
			List<TuKhoa_NhanDTO> listDTO = new ArrayList<TuKhoa_NhanDTO>();
			for (TuKhoa_NhanEntity model : list) {
				TuKhoa_NhanDTO save = new TuKhoa_NhanDTO();
				save.setNhan(model.getNhan().getTenNhan());
				save.setTuKhoa(model.getTuKhoa().getTenTuKhoa());
				save.setNvSua(model.getNvSua());
				save.setNvTao(model.getNvTao());
				save.setNgayTao(model.getNgayTao());
				

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}
		else {
			List<TuKhoa_NhanEntity> list = (List<TuKhoa_NhanEntity>) repo.findAll();
			List<TuKhoa_NhanDTO> listDTO = new ArrayList<TuKhoa_NhanDTO>();
			for (TuKhoa_NhanEntity model : list) {
				TuKhoa_NhanDTO save = new TuKhoa_NhanDTO();
				save.setNhan(model.getNhan().getTenNhan());
				save.setTuKhoa(model.getTuKhoa().getTenTuKhoa());
				save.setNvSua(model.getNvSua());
				save.setNvTao(model.getNvTao());
				save.setNgayTao(model.getNgayTao());
				

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}
	}

	
	

	@PostMapping(value = "/tukhoanhan")
	public String createTuKhoa_Nhan(@RequestBody TuKhoa_NhanDTO model) {

		TuKhoa_NhanEntity save = new TuKhoa_NhanEntity();
		TuKhoa_NhanEntity check = null;
		try {
			save.setNhan(nRepo.findById(model.getNhan()).get());
			save.setTuKhoa(tkRepo.findById(model.getTuKhoa()).get());
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

	

	@DeleteMapping(value = "/tukhoanhan")
	public String delete(@RequestBody ObjDelLong ids) {
		Optional<TuKhoa_NhanEntity> option = repo.findById(ids.getId());
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
