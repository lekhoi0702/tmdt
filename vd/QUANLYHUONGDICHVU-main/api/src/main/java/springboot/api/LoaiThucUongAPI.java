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

import springboot.dto.LoaiThucUongDTO;
import springboot.entity.LoaiThucUongEntity;
import springboot.input.ObjDelString;
import springboot.repository.LoaiThucUongRepository;

@RestController
public class LoaiThucUongAPI {
	@Autowired
	LoaiThucUongRepository repo;

	@GetMapping("/loaithucuong")
	public List<LoaiThucUongDTO> getLoaiThucUong(HttpServletRequest request) {
		String tenLoai = request.getParameter("tenloai");
		String idLoai = request.getParameter("idloai");		
	
		if (tenLoai == null && idLoai == null) {
			List<LoaiThucUongEntity> list = repo.findAll();
			List<LoaiThucUongDTO> listDTO = new ArrayList<LoaiThucUongDTO>();

			for (LoaiThucUongEntity item : list) {
				LoaiThucUongDTO e = new LoaiThucUongDTO();
				e.setDonVi(item.getTenLoai());
				e.setid(item.getId());
				e.setTenLoai(item.getTenLoai());
			
				listDTO.add(e);
			}
			return listDTO;
		}
		
		
		else if (tenLoai!=null){
			List<LoaiThucUongEntity> list = repo.findByTenLoai(tenLoai);
			List<LoaiThucUongDTO> listDTO = new ArrayList<LoaiThucUongDTO>();

			for (LoaiThucUongEntity item : list) {
				LoaiThucUongDTO e = new LoaiThucUongDTO();
				e.setDonVi(item.getTenLoai());
				e.setid(item.getId());
				e.setTenLoai(item.getTenLoai());
			
				listDTO.add(e);
			}
			return listDTO;
		}
		else if (idLoai!=null) {
			List<LoaiThucUongEntity> list =new ArrayList<LoaiThucUongEntity>();
			List<LoaiThucUongDTO> listDTO = new ArrayList<LoaiThucUongDTO>();

			for (LoaiThucUongEntity item : list) {
				LoaiThucUongDTO e = new LoaiThucUongDTO();
				e.setDonVi(item.getTenLoai());
				e.setid(item.getId());
				e.setTenLoai(item.getTenLoai());
			
				listDTO.add(e);
			}
			return listDTO;
		}
		
		return null;
	}

	@PostMapping(value = "/loaithucuong")
	public String create(@RequestBody LoaiThucUongDTO model) {

		LoaiThucUongEntity save = new LoaiThucUongEntity();
		LoaiThucUongEntity check = null;
		try {
			save.setDonVi(model.getDonVi());
			save.setTenLoai(model.getTenLoai());
			save.setId(model.getid());

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

	@PutMapping(value = "/loaithucuong")
	public String update(@RequestBody LoaiThucUongDTO model) {

		Optional<LoaiThucUongEntity> option = repo.findById(model.getid());
		if (option.isEmpty()) {

			System.out.print("ko tồn tại");
			return "404";
		}

		else {
			System.out.print("tồn tại");
			LoaiThucUongEntity save = option.get();
			LoaiThucUongEntity check = null;
			try {
				save.setDonVi(model.getDonVi());
				save.setId(model.getid());

				save.setTenLoai(model.getTenLoai());
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

	@DeleteMapping(value = "/loaithucuong")
	public String delete(@RequestBody ObjDelString ids) {
		Optional<LoaiThucUongEntity> option = repo.findById(ids.getId());
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
