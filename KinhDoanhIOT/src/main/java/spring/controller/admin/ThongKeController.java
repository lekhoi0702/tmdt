package spring.controller.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.DateDTO;
import spring.dto.DDHDTO;
import spring.dto.ThongKeDTO;

@Controller
@RequestMapping(value = "/admin-home/")
public class ThongKeController {

	@RequestMapping(value = "thong-ke", method = RequestMethod.GET)
	public String thongKe(ModelMap model, HttpServletRequest request) {

		DateDTO date = new DateDTO();
		Calendar calendar = Calendar.getInstance();
		date.setNgay(-1);
		date.setThang(-1);
		date.setNam(calendar.get(Calendar.YEAR));

		ThongKeDTO thongKe = Collector.postObj("/thongke", date, ThongKeDTO.class);
		ThongKeDTO thongKeList = Collector.postObj("/thongke?flaglist=true", date, ThongKeDTO.class);

		List<Integer> s = new ArrayList<Integer>();
		List<DDHDTO> b = thongKeList.getDDHs();

		model.addAttribute("timeradio", "day");
		model.addAttribute("day", date.getNgay());
		model.addAttribute("month", date.getThang());
		model.addAttribute("year", date.getNam());
		model.addAttribute("soDDH", thongKe.getSoDDH());
		model.addAttribute("doanhThu", thongKe.getDoanhThu());
		model.addAttribute("chiPhi", thongKe.getPhieuNhap());
		model.addAttribute("loiNhuan", thongKe.getLoiNhuan());

		model.addAttribute("hoaDon", b);
		model.addAttribute("bangPhieuNhap", thongKeList.getPhieuNhaps());
		model.addAttribute("tongHD", s);
		return "admin/thongke";
	}

	@RequestMapping(value = "thong-ke", params = "btn-search", method = RequestMethod.POST)
	public String searchThongKe1(ModelMap model, HttpServletRequest request) {
		DateDTO date = new DateDTO();
		Calendar calendar = Calendar.getInstance();

		try {
			date.setNgay(Integer.parseInt(request.getParameter("day")));
		} catch (Exception e) {
			date.setNgay(-1);
		}
		try {
			date.setThang(Integer.parseInt(request.getParameter("month")));
		} catch (Exception e) {
			date.setThang(-1);
		}
		try {
			date.setNam(Integer.parseInt(request.getParameter("year")));
		} catch (Exception e) {
			date.setNam(calendar.get(Calendar.YEAR));
		}

		ThongKeDTO thongKe = Collector.postObj("/thongke", date, ThongKeDTO.class);
		ThongKeDTO thongKeList = Collector.postObj("/thongke?flaglist=true", date, ThongKeDTO.class);

		List<Integer> s = new ArrayList<Integer>();
		List<DDHDTO> b = thongKeList.getDDHs();
	
		model.addAttribute("timeradio", "day");
		model.addAttribute("day", date.getNgay());
		model.addAttribute("month", date.getThang());
		model.addAttribute("year", date.getNam());
		model.addAttribute("soDDH", thongKe.getSoDDH());
		model.addAttribute("doanhThu", thongKe.getDoanhThu());
		model.addAttribute("chiPhi", thongKe.getPhieuNhap());
		model.addAttribute("loiNhuan", thongKe.getLoiNhuan());

		model.addAttribute("hoaDon", b);
		model.addAttribute("bangPhieuNhap", thongKeList.getPhieuNhaps());
		model.addAttribute("tongHD", s);
		return "admin/thongke";

	}
}