package springboot.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.ChiTietHDDTO;
import springboot.dto.HoaDonDTO;
import springboot.entity.HoaDonEntity;
import springboot.repository.BanRepository;
import springboot.repository.HoaDonRepository;
import springboot.repository.NhanVienRepository;
import springboot.config.Collector;

@RestController
public class HoaDonAPI {
	@Autowired
	HoaDonRepository repo;
	@Autowired
	BanRepository brepo;
	@Autowired
	NhanVienRepository nvrepo;

	@GetMapping("/hoadon")
	public List<HoaDonDTO> getHoaDon() {
		List<HoaDonEntity> list = repo.findByOrderByIdDesc();
		List<HoaDonDTO> listDTO = new ArrayList<HoaDonDTO>();
		for (HoaDonEntity item : list) {
			HoaDonDTO e = new HoaDonDTO();
			e.setId(item.getId());
			e.setBan(item.getBan().getId());
			e.setNgayThucHien(item.getNgayThucHien());
			e.setNvThucHien(item.getNvThucHien().getMaNV());

			listDTO.add(e);

		}
		System.out.print(list.size());
		return listDTO;
	}

	@PostMapping(value = "/hoadon")
	public String create(@RequestBody HoaDonDTO model) {

		HoaDonEntity save = new HoaDonEntity();
		HoaDonEntity check = null;
		try {
			save.setBan(brepo.getOne(model.getBan()));
			save.setNvThucHien(nvrepo.getOne(model.getNvThucHien()));
			save.setNgayThucHien(new Date());
			save.setTinhTrang(1);

			check = repo.save(save);
		} catch (Exception e) {
			e.printStackTrace();

			return "01";
		}
		if (check == null) {

			return "02";
		} else {
			String flat="00";
			for (ChiTietHDDTO item : model.getCthds()) {
				item.setMaHD(check.getId());
				item.setId(0l);
				flat= Collector.postMess("/chitiethd", item);
			}
		
			return flat;
		}
	}

	@PutMapping(value = "/hoadon")
	public String update(@RequestBody HoaDonDTO model) {

		Optional<HoaDonEntity> option = repo.findById(model.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			HoaDonEntity save = option.get();
			HoaDonEntity check = null;
			try {
				save.setBan(brepo.getOne(model.getBan()));
				save.setNvThucHien(nvrepo.getOne(model.getNvThucHien()));
				save.setNgayThucHien(new Date());
				save.setTinhTrang(1);

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

	@PatchMapping(value = "/hoadon")
	public String delete(@RequestBody Long ids) {
		Optional<HoaDonEntity> option = repo.findById(ids);
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		} else {
			System.out.print("tồn tại");
			HoaDonEntity save = option.get();

			try {
				save.setTinhTrang(0);
				repo.save(save);
			} catch (Exception e) {
				e.printStackTrace();
				return "02";
			}

			return "00";
		}
	}

}
