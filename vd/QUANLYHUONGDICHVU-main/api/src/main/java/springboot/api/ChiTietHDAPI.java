package springboot.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.ChiTietHDDTO;
import springboot.entity.ChiTietHDEntity;
import springboot.entity.HoaDonEntity;
import springboot.entity.ThucDonEntity;
import springboot.repository.ChiTietHDRepository;
import springboot.repository.HoaDonRepository;
import springboot.repository.NhanVienRepository;
import springboot.repository.ThucDonRepository;

@RestController
public class ChiTietHDAPI {
	@Autowired
	ChiTietHDRepository repo;
	@Autowired
	NhanVienRepository nvrepo;

	@Autowired
	HoaDonRepository hdrepo;
	@Autowired
	ThucDonRepository tdrepo;

	@GetMapping("/chitiethd")
	public List<ChiTietHDDTO> getCTHD(HttpServletRequest request) {
		String idhd = request.getParameter("idhd");

	
		List<ChiTietHDDTO> cts = new ArrayList<ChiTietHDDTO>();
		long id;
		if (idhd == null)
			return null;
		else {
			try {
				id = Long.parseLong(idhd);
			} catch (Exception e) {
				return null;
			}
			List<ChiTietHDEntity> list = repo.findByHoaDonId(id);
			for (ChiTietHDEntity item : list) {
				ChiTietHDDTO e = new ChiTietHDDTO();
				e.setId(item.getId());
				e.setMaHD(item.getHoaDon().getId());
				e.setMaSP(item.getThucDon().getId());
				e.setSoLuong(item.getSoLuong());
				e.setTongTien(item.getTongTien());
			
				cts.add(e);
			}
			System.out.print(list.size());
			return cts;

		}

	}

	@PostMapping(value = "/chitiethd")
	public String create(@RequestBody ChiTietHDDTO model) {

		ThucDonEntity td = tdrepo.findById(model.getMaSP()).get();
		HoaDonEntity hd = hdrepo.findById(model.getMaHD()).get();

		ChiTietHDEntity save = new ChiTietHDEntity();
		ChiTietHDEntity check = null;
		try {
			save.setHoaDon(hd);
			save.setThucDon(td);
			save.setSoLuong(model.getSoLuong());
			save.setTongTien(td.getGia() * model.getSoLuong());

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

	@PutMapping(value = "/chitiethd")
	public String update(@RequestBody ChiTietHDDTO model) {

		ChiTietHDEntity option = (repo.findByHoaDonIdAndThucDonId(model.getMaHD(), model.getMaSP()));
		if (option == null) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			ThucDonEntity td = tdrepo.findById(model.getMaSP()).get();
			HoaDonEntity hd = hdrepo.findById(model.getMaHD()).get();
			ChiTietHDEntity save = option;
			ChiTietHDEntity check = null;
			try {
				save.setHoaDon(hd);
				save.setThucDon(td);
				save.setSoLuong(model.getSoLuong());
				save.setTongTien(td.getGia() * model.getSoLuong());
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

	@DeleteMapping(value = "/chitiethd")
	public String delete(@RequestBody ChiTietHDDTO model) {
		ChiTietHDEntity option = (repo.findByHoaDonIdAndThucDonId(model.getMaHD(), model.getMaSP()));
		long id=0;
		if (option == null) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			try {

				id = repo.getIdChiTietHD(model.getMaHD(), model.getMaSP());
			} catch (Exception e) {
				e.printStackTrace();
				return "01";
			}

			try {

				repo.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
				return "02";
			}

			return "00";
		}

	}
}
