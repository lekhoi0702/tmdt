package spring.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.CTPNDTO;
import spring.dto.SanPhamDTO;

@Controller

@RequestMapping(value = "/admin-home/")
public class QLCTPNController {

	// CONTROLLER
	@RequestMapping(value = "ctpn", params = "add", method = RequestMethod.GET)
	public <E> String showformCTPN(HttpServletRequest request, ModelMap model) throws IOException {
		Long idpn = Long.parseLong(request.getParameter("id"));
		CTPNDTO ct = new CTPNDTO();
		ct.setPhieuNhap(idpn);
		List<CTPNDTO> ctpns = Collector.getListAll("/ctpn?mapn=" + idpn.toString(), CTPNDTO.class);
		model.addAttribute("ct", ct);
		model.addAttribute("ctpns", ctpns);

		model.addAttribute("sanphams", Collector.getListAll("/sanpham", SanPhamDTO.class));

		return "admin/form/inputCTPN";
	}

	@RequestMapping(value = "ctpn", params = "addctnh2", method = RequestMethod.POST)
	public <E> String postformCTPN(HttpServletRequest request, ModelMap model, @ModelAttribute("ct") CTPNDTO ct)
			throws IOException {
		String flag = Collector.postMess("/ctpn", ct);
		System.out.println(flag);
		if (flag.equals("00")) {
			model.addAttribute("them Thanh Cong");
			List<SanPhamDTO> lstSP = Collector.getListAll("/sanpham", SanPhamDTO.class);
			for (SanPhamDTO item : lstSP) {
				if (item.getID() == ct.getSanPham()) {
					item.setSlTon(item.getSlTon() + ct.getSoLuong());
					String check = Collector.putMess("/sanpham", item);
					if (check.equals("00")) {
						model.addAttribute("CNSL Thanh Cong");
						System.out.println("CNSL Thanh Cong");
					} else {
						model.addAttribute("CNSL That Bai");

					}
					break;
				}
			}
		} else
			model.addAttribute("That Bai");

		return "redirect:ctpn.htm?add&id=" + ct.getPhieuNhap().toString();
	}

}