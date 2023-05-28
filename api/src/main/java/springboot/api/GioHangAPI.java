package springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.GioHangDTO;
import springboot.entity.GioHangEntity;
import springboot.input.ObjDelLong;
import springboot.repository.GioHangRepository;
import springboot.repository.KhachHangRepository;
import springboot.repository.SanPhamRepository;

@RestController
public class GioHangAPI {
	@Autowired
	GioHangRepository repo;
	@Autowired
	SanPhamRepository sanPhamRepo;
	@Autowired
	KhachHangRepository KHRepo;

	@GetMapping("/giohang")
	public List<GioHangDTO> getGioHang(HttpServletRequest request) {
		String idkh = request.getParameter("makh");
		long id;
		if (idkh == null) {
			System.out.print("null");

			return new ArrayList<GioHangDTO>();
		}

		else {
			try {
				id = Long.parseLong(idkh);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			List<GioHangEntity> list = repo.findByKhachHangMaKH(id);
			List<GioHangDTO> listDTO = new ArrayList<GioHangDTO>();
			for (GioHangEntity model : list) {
				GioHangDTO save = new GioHangDTO();
				save.setID(model.getID());
				save.setMaKH(id);
				save.setMaSP(model.getSp().getId());
				save.setSoLuong(model.getSoLuong());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}
	}

	@GetMapping("/slgiohang")
	public ObjDelLong getSLGioHang(HttpServletRequest request) {
		ObjDelLong sl = new ObjDelLong();
		String idkh = request.getParameter("makh");
		long id;
		if (idkh == null) {
			System.out.print("null");

			sl.setId(0);
		}

		else {
			try {
				id = Long.parseLong(idkh);
				List<GioHangEntity> list = repo.findByKhachHangMaKH(id);
				sl.setId(list.size());

			} catch (Exception e) {
				e.printStackTrace();
				sl.setId(0);
			}

		}
		return sl;
	}

	@PostMapping(value = "/giohang")
	public String createGioHang(@RequestBody GioHangDTO model) {

		GioHangEntity save = new GioHangEntity();
		GioHangEntity check = null;
		try {
			save.setKhachHang(KHRepo.findById(model.getMaKH()).get());
			save.setSp(sanPhamRepo.findById(model.getMaSP()).get());
			save.setSoLuong(model.getSoLuong());

			check = repo.save(save);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(model.getMaKH());

			return "01";
		}
		if (check == null) {

			return "02";
		}
		return "00";
	}

	@PutMapping(value = "/giohang")
	public String updateGioHang(@RequestBody GioHangDTO model) {

		Optional<GioHangEntity> nvoption = repo.findById(model.getID());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			GioHangEntity save = nvoption.get();
			GioHangEntity check = null;
			try {

				save.setID(model.getID());
				save.setKhachHang(KHRepo.findById(model.getMaKH()).get());
				save.setSp(sanPhamRepo.findById(model.getMaSP()).get());
				save.setSoLuong(model.getSoLuong());
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

	@DeleteMapping(value = "/giohang")
	public String delete(@RequestBody ObjDelLong ids) {
		Optional<GioHangEntity> option = repo.findById(ids.getId());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại ");
			System.out.println(ids.getId());

//			try {
//
//				repo.deleteById(ids.getId());
//			} catch (Exception e) {
//				e.printStackTrace();
//				return "02";
//			}

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
