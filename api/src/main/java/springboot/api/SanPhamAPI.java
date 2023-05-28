package springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.SanPhamDTO;
import springboot.entity.SanPhamEntity;
import springboot.input.ObjDelLong;
import springboot.repository.LoaiSPRepository;
import springboot.repository.SanPhamRepository;

@RestController
public class SanPhamAPI {
	@Autowired
	SanPhamRepository repo;
	@Autowired
	LoaiSPRepository loaiRepo;


	@GetMapping("/sanpham")
	public List<SanPhamDTO> getSanPham() {
		List<SanPhamEntity> list = repo.findAllActive();
		List<SanPhamDTO> listDTO = new ArrayList<SanPhamDTO>();
		for (SanPhamEntity model: list) {
			SanPhamDTO save = new SanPhamDTO();
			save.setDvt(model.getDvt());
			save.setGia(model.getGia());
			save.setIcon(model.getIcon());
			save.setID(model.getId());
			save.setLoai(model.getLoaiSP().getId());
			save.setSlTon(model.getSlTon());
			save.setTen(model.getTen());
			save.setTrangThai(1);
			save.setMoTa(model.getMoTa());
			
			listDTO.add(save);
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/sanpham")
	public String createSP(@RequestBody SanPhamDTO model) {

		SanPhamEntity save = new SanPhamEntity();
		SanPhamEntity check = null;
		try {
			save.setDvt(model.getDvt());
			save.setGia(model.getGia());
			save.setIcon(model.getIcon());
			save.setLoaiSP(loaiRepo.findById(model.getLoai()).get());
			save.setSlTon(model.getSlTon());
			save.setTen(model.getTen());
			save.setTrangThai(1);
			save.setMoTa(model.getMoTa());

			check = repo.save(save);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(model.getTen());
		
			return "01";
		}
		if (check == null) {

			return "02";
		}
		return "00";
	}

	@PutMapping(value = "/sanpham")
	public String updateSP(@RequestBody SanPhamDTO model) {

		Optional<SanPhamEntity> nvoption = repo.findById(model.getID());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			SanPhamEntity save = nvoption.get();
			SanPhamEntity check = null;
			try {

				save.setDvt(model.getDvt());
				save.setGia(model.getGia());
				save.setIcon(model.getIcon());
				save.setId(model.getID());
				save.setLoaiSP(loaiRepo.findById(model.getLoai()).get());
				save.setSlTon(model.getSlTon());
				save.setTen(model.getTen());
				save.setTrangThai(1);
				save.setMoTa(model.getMoTa());

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

	@DeleteMapping(value = "/sanpham")
	public String deleteSP(@RequestBody ObjDelLong ids) {
		Optional<SanPhamEntity> nvoption = repo.findById(ids.getId());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			SanPhamEntity save = nvoption.get();
			SanPhamEntity check = null;
			try {

				save.setTrangThai(0);
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
