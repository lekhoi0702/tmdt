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

import springboot.config.Collector;
import springboot.dto.CTPNDTO;
import springboot.dto.PhieuNhapDTO;
import springboot.entity.PhieuNhapEntity;
import springboot.input.ObjDelLong;
import springboot.repository.PhieuNhapRepository;
import springboot.repository.KhachHangRepository;
import springboot.repository.NhanVienRepository;

@RestController
public class PhieuNhapAPI {
	@Autowired
	PhieuNhapRepository repo;
	@Autowired
	NhanVienRepository nvRepo;
	@Autowired
	KhachHangRepository khRepo;

	@GetMapping("/phieunhap")
	public List<PhieuNhapDTO> getPhieuNhap() {

		List<PhieuNhapEntity> list = repo.findAll();
		List<PhieuNhapDTO> listDTO = new ArrayList<PhieuNhapDTO>();
		for (PhieuNhapEntity model : list) {
			PhieuNhapDTO save = new PhieuNhapDTO();
			save.setId(model.getId());
			save.setNgayThucHien(model.getNgayThucHien());
			save.setNvThucHien(model.getNvThucHien().getMaNV());
			save.setTinhTrang(model.getTinhTrang());
			save.setNcc(model.getNcc());
			

			listDTO.add(save);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/phieunhap")
	public String createPhieuNhap(@RequestBody PhieuNhapDTO model) {

		PhieuNhapEntity save = new PhieuNhapEntity();
		PhieuNhapEntity check = null;
		try {
			save.setNgayThucHien(model.getNgayThucHien());
			save.setNvThucHien(nvRepo.findById(model.getNvThucHien()).get());
			save.setTinhTrang(model.getTinhTrang());
			save.setNcc(model.getNcc());

			check = repo.save(save);
		} catch (Exception e) {
			e.printStackTrace();

			return "01";
		}
		if (check == null) {

			return "02";
		}
		else {
			String flat="00";
			for (CTPNDTO item : model.getCtpns()) {
				item.setPhieuNhap(check.getId());
				item.setId(0l);
				flat= Collector.postMess("/ctpn", item);
			}
		
			return flat;
		}
	}

	@PutMapping(value = "/phieunhap")
	public String updatePhieuNhap(@RequestBody PhieuNhapDTO model) {

		Optional<PhieuNhapEntity> nvoption = repo.findById(model.getId());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			PhieuNhapEntity save = nvoption.get();
			PhieuNhapEntity check = null;
			try {

				save.setId(model.getId());
				save.setNgayThucHien(model.getNgayThucHien());
				save.setNvThucHien(nvRepo.findById(model.getNvThucHien()).get());
				save.setTinhTrang(model.getTinhTrang());
				save.setNcc(model.getNcc());

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

	@DeleteMapping(value = "/phieunhap")
	public String delete(@RequestBody ObjDelLong ids) {
		Optional<PhieuNhapEntity> option = repo.findById(ids.getId());
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
