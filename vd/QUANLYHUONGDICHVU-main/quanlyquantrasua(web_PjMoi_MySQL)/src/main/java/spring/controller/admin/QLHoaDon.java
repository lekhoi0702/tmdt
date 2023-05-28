package spring.controller.admin;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.BanDTO;
import spring.dto.HoaDonDTO;

@Controller

@RequestMapping(value = "/admin-home/")
public class QLHoaDon {


	// CONTROLLER
	@RequestMapping(value = "admin-hoadon", method = RequestMethod.GET)
	public <E> String showHoaDon(HttpServletRequest request,ModelMap model){	

		List<HoaDonDTO> list=null;
		try {
			list = Collector.getListAll("/hoadon",HoaDonDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		
		return "admin/qlhoadon";
	}
	
//	phần tìm kiếm

	@RequestMapping(value="admin-hoadon", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchHoaDon(HttpServletRequest request, ModelMap model) {
	
		
		return "admin/qlhoadon";
	}
	
}