package springboot.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.ChiPhiDTO;
import springboot.entity.ChiPhiEntity;
import springboot.input.ObjDelLong;
import springboot.repository.ChiPhiRepository;
import springboot.repository.NhanVienRepository;

@RestController
public class ChiPhiAPI {
	@Autowired
	ChiPhiRepository repo;
	@Autowired
	NhanVienRepository nvrepo;

	@GetMapping("/chiphi")
	public List<ChiPhiDTO> getChiPhi() {
		List<ChiPhiEntity> list = repo.findAll();
		List<ChiPhiDTO> listDTO = new ArrayList<ChiPhiDTO>();
		for (ChiPhiEntity item : list) {
			ChiPhiDTO e = new ChiPhiDTO();
			e.setId(item.getId());
			e.setDonVi(item.getDonVi());
			e.setGhiChu(item.getGhiChu());
			e.setGiaDonVi(item.getGiaMoiDV());
			e.setLoai(item.getLoai());
			e.setNgayNhap(item.getNgayNhap());
			e.setNhaCungCap(item.getNhaCungCap());
			e.setNvTao(item.getNvTao().getMaNV());
			e.setSoLuong(item.getSoLuong());
			e.setTenChiPhi(item.getTenChiPhi());
			
			listDTO.add(e);
			
		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/chiphi")
	public String create(@RequestBody ChiPhiDTO model) {

		ChiPhiEntity save = new ChiPhiEntity();
		ChiPhiEntity check = null;
		try {
			save.setNvTao(nvrepo.findById(model.getNvTao()).get());
			save.setDonVi(model.getDonVi());
			save.setGhiChu(model.getGhiChu());
			save.setGiaMoiDV(model.getGiaDonVi());
			save.setLoai(model.getLoai());
			save.setNgayNhap(new Date());
			save.setNhaCungCap(model.getNhaCungCap());
			save.setSoLuong(model.getSoLuong());
			save.setTenChiPhi(model.getTenChiPhi());

			check = repo.save(save);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(model.getId());

			return "01";
		}
		if (check == null) {

			return "02";
		}
		return "00";
	}

	@PutMapping(value = "/chiphi")
	public String update(@RequestBody ChiPhiDTO model) {

		Optional<ChiPhiEntity> option = repo.findById(model.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			ChiPhiEntity save = option.get();
			ChiPhiEntity check = null;
			try {

				save.setNvTao(nvrepo.findById(model.getNvTao()).get());
				save.setDonVi(model.getDonVi());
				save.setGhiChu(model.getGhiChu());
				save.setGiaMoiDV(model.getGiaDonVi());
				save.setLoai(model.getLoai());
				save.setNgayNhap(new Date());
				save.setNhaCungCap(model.getNhaCungCap());
				save.setSoLuong(model.getSoLuong());
				save.setTenChiPhi(model.getTenChiPhi());
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
		Optional<ChiPhiEntity> option = repo.findById(ids.getId());
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
