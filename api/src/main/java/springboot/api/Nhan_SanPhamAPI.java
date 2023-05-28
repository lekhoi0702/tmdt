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

import springboot.dto.Nhan_SanPhamDTO;
import springboot.entity.Nhan_SanPhamEntity;
import springboot.input.ObjDelLong;
import springboot.repository.NhanRepository;
import springboot.repository.Nhan_SanPhamRepository;
import springboot.repository.SanPhamRepository;

@RestController
public class Nhan_SanPhamAPI {
	@Autowired
	Nhan_SanPhamRepository repo;
	@Autowired
	SanPhamRepository spRepo;
	@Autowired
	NhanRepository nRepo;


	@GetMapping("/nhansanpham")
	public List<Nhan_SanPhamDTO> getNhan_SanPham(HttpServletRequest request) {
		System.out.print("heloo get");

		String nhan = request.getParameter("tennhan");
		String sanPham = request.getParameter("sanpham");
		Long idSanPham;
		
		if (nhan == null && sanPham == null)  {
			List<Nhan_SanPhamEntity> list = (List<Nhan_SanPhamEntity>) repo.findAll();
			List<Nhan_SanPhamDTO> listDTO = new ArrayList<Nhan_SanPhamDTO>();
			for (Nhan_SanPhamEntity model : list) {
				Nhan_SanPhamDTO save = new Nhan_SanPhamDTO();
				save.setNhan(model.getNhan().getTenNhan());
				save.setSanPham(model.getSanPham().getId());
				save.setNvSua(model.getNvSua());
				save.setNvTao(model.getNvTao());
				save.setNgayTao(model.getNgayTao());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}

		else if (nhan != null) {
			List<Nhan_SanPhamEntity> list = (List<Nhan_SanPhamEntity>) nRepo.findById(nhan).get().getChiTietSp();
			List<Nhan_SanPhamDTO> listDTO = new ArrayList<Nhan_SanPhamDTO>();
			for (Nhan_SanPhamEntity model : list) {
				Nhan_SanPhamDTO save = new Nhan_SanPhamDTO();
				save.setNhan(model.getNhan().getTenNhan());
				save.setSanPham(model.getSanPham().getId());
				save.setNvSua(model.getNvSua());
				save.setNvTao(model.getNvTao());
				save.setNgayTao(model.getNgayTao());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}
			
		else if (sanPham != null) {
			try {
				idSanPham = Long.parseLong(sanPham);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			List<Nhan_SanPhamEntity> list = (List<Nhan_SanPhamEntity>) spRepo.findById(idSanPham).get().getChiTietNhan();
			List<Nhan_SanPhamDTO> listDTO = new ArrayList<Nhan_SanPhamDTO>();
			for (Nhan_SanPhamEntity model : list) {
				Nhan_SanPhamDTO save = new Nhan_SanPhamDTO();
				save.setNhan(model.getNhan().getTenNhan());
				save.setSanPham(model.getSanPham().getId());
				save.setNvSua(model.getNvSua());
				save.setNvTao(model.getNvTao());
				save.setNgayTao(model.getNgayTao());

				listDTO.add(save);
			}
			System.out.print(list.size());
			return listDTO;
		}
		else {
			System.out.print("null");

			return null;
		}
	}

	
	

	@PostMapping(value = "/nhansanpham")
	public String createNhan_SanPham(@RequestBody Nhan_SanPhamDTO model) {
		System.out.print("heloo post");

		Nhan_SanPhamEntity save = new Nhan_SanPhamEntity();
		Nhan_SanPhamEntity check = null;
		try {
			System.out.print("heloo post");

			save.setNhan(nRepo.findById(model.getNhan()).get());
			save.setSanPham(spRepo.findById(model.getSanPham()).get());
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

	

	@DeleteMapping(value = "/nhansanpham")
	public String delete(@RequestBody ObjDelLong ids) {
		Optional<Nhan_SanPhamEntity> option = repo.findById(ids.getId());
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
