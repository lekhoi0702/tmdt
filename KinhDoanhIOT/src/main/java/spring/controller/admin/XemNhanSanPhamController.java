package spring.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.Nhan_SanPhamDTO;

@Controller
@RequestMapping(value = "/admin-home/")
public class XemNhanSanPhamController {

	@RequestMapping(value = "admin-qlnhansanpham", method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model, HttpServletRequest request) {

		List<Nhan_SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/nhansanpham", Nhan_SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);

		return "admin/qlnhansanpham";
	}

	
}
