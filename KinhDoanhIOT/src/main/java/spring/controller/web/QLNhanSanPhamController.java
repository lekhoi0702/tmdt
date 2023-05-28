package spring.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.Collector;
import spring.dto.LoginDTO;
import spring.dto.NhanDTO;
import spring.dto.Nhan_SanPhamDTO;
import spring.dto.SanPhamDTO;

@Controller
public class QLNhanSanPhamController {

	@RequestMapping(value = "qlnhansanpham", method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model, HttpServletRequest request) {

		List<Nhan_SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/nhansanpham", Nhan_SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);

		return "web/qlnhansanpham";
	}

	/* hiển thị form */
	@RequestMapping(value = "formNhanSanPham", method = RequestMethod.GET)
	public String index_formSanPham(ModelMap model, @ModelAttribute("nsp") Nhan_SanPhamDTO nsp) {

		model.addAttribute("nsp", new Nhan_SanPhamDTO());

		model.addAttribute("nhans", getNhans());
		model.addAttribute("sanphams", getSanPhams());

		return "web/form/inputNhanSanPham";
	}

	public List<NhanDTO> getNhans() {
		List<NhanDTO> list = null;
		try {
			list = Collector.getListAll("/nhan", NhanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<SanPhamDTO> getSanPhams() {
		List<SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/sanpham", SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "formNhanSanPham", params = "Insert", method = RequestMethod.POST)
	public String addTuKhoa(HttpServletRequest request, ModelMap model, @ModelAttribute("nsp") Nhan_SanPhamDTO nsp) {
		LoginDTO nhanvien = (LoginDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
		nsp.setNvTao(nhanvien.getMaNV());

		nsp.setNgayTao(new Date());

		// List<String> listError = checkInfo(nsp);
		Integer temp = this.insertNhanSanPham(nsp);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

			nsp = new Nhan_SanPhamDTO();

		} else {
			model.addAttribute("message", "Thêm thất bại! ");
		}

		return "web/qlnhansanpham";
	}

	public List<String> checkInfo(Nhan_SanPhamDTO nsp) {

		List<String> listError = new ArrayList<>();

		return listError;

	}

	public Integer insertNhanSanPham(Nhan_SanPhamDTO nsp) {
		String flag = Collector.postMess("/nhansanpham", nsp);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;

	}

}
