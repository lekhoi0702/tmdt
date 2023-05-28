package spring.controller.khachhang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.APIFunction;
import spring.bean.Collector;
import spring.dto.CTDDHDTO;
import spring.dto.DDHDTO;
import spring.dto.LoginKHDTO;

@Controller

public class TheoDoiDDHController {

	// CONTROLLER
	@RequestMapping(value = "KHdonhang", method = RequestMethod.GET)
	public <E> String showDonHang(HttpServletRequest request, ModelMap model) {
		LoginKHDTO kh = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
		System.out.println(kh.getMaKH());

		List<DDHDTO> list = null;
		try {
			list = Collector.getListAll("/ddh", DDHDTO.class);
			list = list.stream().filter(ddh -> ddh.getKhThucHien() == kh.getMaKH()).collect(Collectors.toList());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);

		return "khachhang/QLDDH";
	}

	@RequestMapping(value = "KHdonhang", params = "linkView")
	public <E> String xemChiTietHD(HttpServletRequest request, ModelMap model) throws IOException {

		Long id = Long.parseLong(request.getParameter("id"));

		List<CTDDHDTO> cthds = APIFunction.getCtDDHs(id);
		for (CTDDHDTO ct : cthds) {
			ct.setTenSP(APIFunction.getSP(ct.getSanPham()).getTen());
		}
		List<DDHDTO> listDDH = new ArrayList<DDHDTO>();
		try {
			listDDH = Collector.getListAll("/ddh", DDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("danhGia", false);
		for (DDHDTO ddh : listDDH) {
			if (ddh.getTinhTrang() >= 4 && ddh.getId() == id) {
				model.addAttribute("danhGia", true);
				break;
			}

		}

		model.addAttribute("chiTiet", cthds);

		int tong = 0;
		for (CTDDHDTO ctpn : cthds) {
			tong += ctpn.getTongTien();
		}
		model.addAttribute("tongTien", tong);
		model.addAttribute("idddh", id);

		return "khachhang/QLCTDDH";
	}
	
	
	@RequestMapping(value = "KHdonhang", params = "linkHuy")
	public <E> String huyDDH(HttpServletRequest request, ModelMap model) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		DDHDTO ddh = APIFunction.getDDH(id);
		if (ddh == null) {
			return "web/QLCTDDH";
		}
		if (ddh.getTinhTrang() <= 2 && ddh.getTinhTrang() >=0) {
			ddh.setTinhTrang(-1);
			Collector.putMess("/ddh", ddh);

		}


		return "redirect:KHdonhang.htm";
	}

	@RequestMapping(value = "KHdanhGia", method = RequestMethod.GET)
	public <E> String danhGiaCTDonHang(HttpServletRequest request, ModelMap model) {
		//LoginKHDTO kh = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
		Long idct = Long.parseLong(request.getParameter("idct"));
		List<CTDDHDTO> cthds = APIFunction.getCtDDHFull();
		for (CTDDHDTO ct:cthds) {
			System.out.println("idct "+ct.getId());

			if (ct.getId()==idct) {
				model.addAttribute("ct", ct);
				return "khachhang/form/danhgia";

			}
		}

		


		return "redirect:khachhanghome.htm";
	}
	
	@RequestMapping(value = "KHdanhGia", method = RequestMethod.POST)
	public <E> String guidanhGiaCTDonHang(HttpServletRequest request, ModelMap model,@ModelAttribute("ct") CTDDHDTO ct) {
		//LoginKHDTO kh = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
		String check = Collector.putMess("/danhgia", ct);
		if (check.equals("00")) {
			model.addAttribute("message", "tao tk thanh cong");
			return "redirect:KHdonhang.htm?thanhcong";

		} else {
			return "redirect:KHdonhang.htm?thatBai";
		}
		


	}

}