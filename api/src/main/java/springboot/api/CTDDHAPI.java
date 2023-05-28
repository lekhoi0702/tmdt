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

import springboot.dto.CTDDHDTO;
import springboot.entity.CTDDHEntity;
import springboot.input.ObjDelLong;
import springboot.repository.CTDDHRepository;
import springboot.repository.DDHRepository;
import springboot.repository.SanPhamRepository;

@RestController
public class CTDDHAPI {
	@Autowired
	CTDDHRepository repo;
	@Autowired
	SanPhamRepository sanPhamRepo;
	@Autowired
	DDHRepository DDHRepo;

	@GetMapping("/ctddh")
	public List<CTDDHDTO> getCTDDH(HttpServletRequest request) {
		String idkh = request.getParameter("maddh");
		String idsp = request.getParameter("maspreview");

		long id;
		if (idkh == null && idsp == null) {
			//System.out.print("null");

			{
				List<CTDDHEntity> list = repo.findAll();
				List<CTDDHDTO> listDTO = new ArrayList<CTDDHDTO>();
				for (CTDDHEntity model : list) {
					CTDDHDTO save = new CTDDHDTO();
					save.setId(model.getId());
					save.setDdh(model.getDdh().getId());
					save.setSanPham(model.getSanPham().getId());
					save.setSoLuong(model.getSoLuong());
					save.setTongTien(model.getTongTien());

					listDTO.add(save);
				}
				System.out.print(list.size());
				return listDTO;
			}
		}

		else if (idsp == null) {
			try {
				id = Long.parseLong(idkh);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			List<CTDDHEntity> list = (List<CTDDHEntity>) DDHRepo.findById(id).get().getChiTietDDH();
			List<CTDDHDTO> listDTO = new ArrayList<CTDDHDTO>();
			for (CTDDHEntity model : list) {
				CTDDHDTO save = new CTDDHDTO();
				save.setId(model.getId());
				save.setDdh(model.getDdh().getId());
				save.setSanPham(model.getSanPham().getId());
				save.setSoLuong(model.getSoLuong());
				save.setTongTien(model.getTongTien());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		} else {
			try {
				id = Long.parseLong(idsp);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			List<CTDDHEntity> list = (List<CTDDHEntity>) repo.findReview(id);
			List<CTDDHDTO> listDTO = new ArrayList<CTDDHDTO>();
			for (CTDDHEntity model : list) {
				CTDDHDTO save = new CTDDHDTO();
				save.setId(model.getId());
				save.setDdh(model.getDdh().getId());
				save.setSanPham(model.getSanPham().getId());
				save.setSoLuong(model.getSoLuong());
				save.setTongTien(model.getTongTien());
				save.setDanhGia(model.getDanhGia());
				save.setDiem(model.getDiem());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}

	}

	@PostMapping(value = "/ctddh")
	public String createCTDDH(@RequestBody CTDDHDTO model) {

		CTDDHEntity save = new CTDDHEntity();
		CTDDHEntity check = null;
		try {
			save.setDdh(DDHRepo.findById(model.getDdh()).get());
			save.setSanPham(sanPhamRepo.findById(model.getSanPham()).get());
			save.setSoLuong(model.getSoLuong());
			save.setTongTien(model.getTongTien());
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

	@PutMapping(value = "/danhgia")
	public String danhGiaCtddh(@RequestBody CTDDHDTO model) {

		Optional<CTDDHEntity> nvoption = repo.findById(model.getId());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			CTDDHEntity save = nvoption.get();
			CTDDHEntity check = null;
			try {
				save.setDanhGia(model.getDanhGia());
				save.setDiem(model.getDiem());

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

	@PutMapping(value = "/ctddh")
	public String updateCTDDH(@RequestBody CTDDHDTO model) {

		Optional<CTDDHEntity> nvoption = repo.findById(model.getId());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			CTDDHEntity save = nvoption.get();
			CTDDHEntity check = null;
			try {

				save.setId(model.getId());
				save.setDdh(DDHRepo.findById(model.getDdh()).get());
				save.setSanPham(sanPhamRepo.findById(model.getSanPham()).get());
				save.setSoLuong(model.getSoLuong());
				save.setTongTien(model.getTongTien());
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

	@DeleteMapping(value = "/ctddh")
	public String delete(@RequestBody ObjDelLong ids) {
		Optional<CTDDHEntity> option = repo.findById(ids.getId());
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
