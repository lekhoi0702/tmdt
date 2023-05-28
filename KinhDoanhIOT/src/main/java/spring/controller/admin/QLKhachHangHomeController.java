package spring.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.KhachHangDTO;

@Controller
@RequestMapping(value = "/admin-home/")
public class QLKhachHangHomeController {

	// CONTROLLER
	@RequestMapping(value = "admin-indexkh", method = RequestMethod.GET)
	public <E> String index(HttpServletRequest request, ModelMap model) {
		List<KhachHangDTO> list = null;
		try {
			list = Collector.getListAll("/khachhang", KhachHangDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		return "admin/QLKH";
	}

	@RequestMapping(value = "admin-indexkh", params = "linkDelete", method = RequestMethod.GET)
	public <E> String deleteNV(HttpServletRequest request, ModelMap model) {
		String id1 = request.getParameter("id");
		long maKH = Long.parseLong(id1);
		KhachHangDTO tmp = this.getKH(maKH);

		Integer temp = 1;
		tmp.setTrangThai(0);
		temp = this.updateKH(tmp);
		if (temp == 0) {
			model.addAttribute("message", "Xóa không thành công" );
		} else {
			model.addAttribute("message", "Xóa thành công");
		}

		return "redirect:/admin-home/indexkh.htm";

	}
	
	public Integer updateKH(KhachHangDTO kh) {
		String flag = Collector.putMess("/khachhang", kh);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}
	
	public KhachHangDTO getKH(long id) {
		List<KhachHangDTO> list = null;
		try {
			list = Collector.getListAll("/khachhang", KhachHangDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KhachHangDTO ss = new KhachHangDTO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaKH() == id)
				ss = list.get(i);
		}

		return ss;
	}

}
