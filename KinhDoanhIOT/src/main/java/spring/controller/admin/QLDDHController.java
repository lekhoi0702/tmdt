package spring.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.APIFunction;
import spring.bean.Collector;
import spring.dto.CTDDHDTO;
import spring.dto.DDHDTO;

@Controller

@RequestMapping(value = "/admin-home/")
public class QLDDHController {

	// CONTROLLER
	@RequestMapping(value = "admin-ddh", method = RequestMethod.GET)
	public <E> String showDDH(HttpServletRequest request, ModelMap model) {

		List<DDHDTO> list = null;
		try {
			list = Collector.getListAll("/ddh", DDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "admin/qlddh";
	}

	@RequestMapping(value = "admin-ddh/{id}.htm", params = "linkView")
	public <E> String xemChiTietDDH(HttpServletRequest request, ModelMap model, @PathVariable("id") Long id) {
		List<CTDDHDTO> cthds = this.getCtDDHs(id);

		model.addAttribute("chiTiet", cthds);
		for (CTDDHDTO ct : cthds) {
			ct.setTenSP(APIFunction.getSP(ct.getSanPham()).getTen());
		}

		int tong = 0;
		for (CTDDHDTO ctpn : cthds) {
			tong += ctpn.getTongTien();
		}
		model.addAttribute("tongTien", tong);
		model.addAttribute("idhd", id);
		return "admin/ctddh";
	}
//	phần tìm kiếm

	@RequestMapping(value = "admin-ddh", params = "btnsearch", method = RequestMethod.POST)
	public <E> String searchDDH(HttpServletRequest request, ModelMap model) {

		return "admin/qlddh";
	}

	public List<CTDDHDTO> getCtDDHs(Long idpn) {
		List<CTDDHDTO> list = null;
		try {
			list = Collector.getListAll("/ctddh?maddh=" + idpn.toString(), CTDDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}