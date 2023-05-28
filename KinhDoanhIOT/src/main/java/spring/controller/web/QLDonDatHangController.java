package spring.controller.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.APIFunction;
import spring.bean.Collector;
import spring.dto.CTDDHDTO;
import spring.dto.DDHDTO;
import spring.dto.SanPhamDTO;

@Controller
public class QLDonDatHangController {

	@RequestMapping(value = "ddh", method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model, HttpServletRequest request) {

		List<DDHDTO> list = null;
		try {
			list = Collector.getListAll("/ddh", DDHDTO.class);
			list = list.stream().filter(ddh -> ddh.getTinhTrang() != 0).collect(Collectors.toList());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);

		return "web/QLDDH";
	}

	@RequestMapping(value = "ddh", params = "linkView")
	public <E> String xemChiTietHD(HttpServletRequest request, ModelMap model) throws IOException {

		Long id = Long.parseLong(request.getParameter("id"));

		List<CTDDHDTO> cthds = this.getCtDDHs(id);

		for (CTDDHDTO ct : cthds) {
			ct.setTenSP(APIFunction.getSP(ct.getSanPham()).getTen());
		}
		model.addAttribute("chiTiet", cthds);

		int tong = 0;
		for (CTDDHDTO ctpn : cthds) {
			tong += ctpn.getTongTien();
		}
		model.addAttribute("tongTien", tong);
		model.addAttribute("idddh", id);

		return "web/QLCTDDH";
	}

	@RequestMapping(value = "ddh", params = "linkXacNhan")
	public <E> String chuyenTT(HttpServletRequest request, ModelMap model) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		DDHDTO ddh = APIFunction.getDDH(id);
		if (ddh == null) {
			model.addAttribute("don dat hang khong ton tai");
			return "web/QLCTDDH";
		}
		if (ddh.getTinhTrang() <= 2 && ddh.getTinhTrang() > 0) {
			ddh.setTinhTrang(ddh.getTinhTrang() + 1);
			Collector.putMess("/ddh", ddh);
			List<CTDDHDTO> cthds = this.getCtDDHs(id);
			List<SanPhamDTO> sps = APIFunction.getSanPhams();
			if (ddh.getTinhTrang() == 3)
				for (CTDDHDTO ct : cthds)
					for (SanPhamDTO sp : sps) {
						if (sp.getID() == ct.getSanPham()) {
							if (sp.getSlTon() - ct.getSoLuong() < 0) {
								ddh.setTinhTrang(2);
								Collector.putMess("/ddh", ddh);
								return "redirect:ddh.htm?khong du san pham de giao";

							}
						}

					}
		} else if (ddh.getTinhTrang() == 3) {
			ddh.setTinhTrang(4);
			Collector.putMess("/ddh", ddh);

			List<CTDDHDTO> cthds = this.getCtDDHs(id);
			List<SanPhamDTO> sps = APIFunction.getSanPhams();
			for (CTDDHDTO ct : cthds)
				for (SanPhamDTO sp : sps) {
					if (sp.getID() == ct.getSanPham()) {
						if (sp.getSlTon() - ct.getSoLuong() < 0) {
							ddh.setTinhTrang(3);
							Collector.putMess("/ddh", ddh);
							return "redirect:ddh.htm?khong du san pham de giao";

						}

						sp.setSlTon(sp.getSlTon() - ct.getSoLuong());

						Collector.putMess("/sanpham", sp);
						System.out.println("tru so luong thanh cong" + sp.getTen());
						break;

					}
				}

		}

		return "redirect:ddh.htm";
	}

	@RequestMapping(value = "ddh", params = "linkHuy")
	public <E> String huyDDH(HttpServletRequest request, ModelMap model) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		DDHDTO ddh = APIFunction.getDDH(id);
		if (ddh == null) {
			return "web/QLCTDDH";
		}
		if (ddh.getTinhTrang() <= 3 && ddh.getTinhTrang() > 0) {
			ddh.setTinhTrang(-1);
			Collector.putMess("/ddh", ddh);

		}

		return "redirect:ddh.htm";
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