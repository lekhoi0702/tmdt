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

import springboot.dto.CTPNDTO;
import springboot.entity.CTPNEntity;
import springboot.input.ObjDelLong;
import springboot.repository.CTPNRepository;
import springboot.repository.PhieuNhapRepository;
import springboot.repository.SanPhamRepository;

@RestController
public class CTPNAPI {
	@Autowired
	CTPNRepository repo;
	@Autowired
	SanPhamRepository sanPhamRepo;
	@Autowired
	PhieuNhapRepository PNRepo;

	@GetMapping("/ctpn")
	public List<CTPNDTO> getCTPN(HttpServletRequest request) {
		String idkh = request.getParameter("mapn");
		long id;
		if (idkh == null) {
			System.out.print("null");
			List<CTPNEntity> list = repo.findAll();
			List<CTPNDTO> listDTO = new ArrayList<CTPNDTO>();
			for (CTPNEntity model : list) {
				CTPNDTO save = new CTPNDTO();
				save.setId(model.getId());
				save.setPhieuNhap(model.getPhieuNhap().getId());
				save.setSanPham(model.getSanPham().getId());
				save.setSoLuong(model.getSoLuong());
				save.setDonGia(model.getDonGia());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}
			
		else {
			try {
				id = Long.parseLong(idkh);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			List<CTPNEntity> list = (List<CTPNEntity>) PNRepo.findById(id).get().getCtpns();
			List<CTPNDTO> listDTO = new ArrayList<CTPNDTO>();
			for (CTPNEntity model : list) {
				CTPNDTO save = new CTPNDTO();
				save.setId(model.getId());
				save.setPhieuNhap(model.getPhieuNhap().getId());
				save.setSanPham(model.getSanPham().getId());
				save.setSoLuong(model.getSoLuong());
				save.setDonGia(model.getDonGia());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}


	}

	@PostMapping(value = "/ctpn")
	public String createCTPN(@RequestBody CTPNDTO model) {

		CTPNEntity save = new CTPNEntity();
		CTPNEntity check = null;
		try {
			save.setPhieuNhap(PNRepo.findById(model.getPhieuNhap()).get());
			save.setSanPham(sanPhamRepo.findById(model.getSanPham()).get());
			save.setSoLuong(model.getSoLuong());
			save.setDonGia(model.getDonGia());
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

	@PutMapping(value = "/ctpn")
	public String updateCTPN(@RequestBody CTPNDTO model) {

		Optional<CTPNEntity> nvoption = repo.findById(model.getId());
		if (nvoption.isEmpty()) {

			System.out.print("ko tồn tại sp");
			return "404";
		}

		else {
			System.out.print("tồn tại sp");
			CTPNEntity save = nvoption.get();
			CTPNEntity check = null;
			try {

				save.setId(model.getId());
				save.setPhieuNhap(PNRepo.findById(model.getPhieuNhap()).get());
				save.setSanPham(sanPhamRepo.findById(model.getSanPham()).get());
				save.setSoLuong(model.getSoLuong());
				save.setDonGia(model.getDonGia());
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

	@DeleteMapping(value = "/ctpn")
	public String delete(@RequestBody ObjDelLong ids) {
		Optional<CTPNEntity> option = repo.findById(ids.getId());
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
