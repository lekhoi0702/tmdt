package spring.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.ChiTietHDDTO;
import spring.dto.HoaDonDTO;



@Controller
public class HoaDonController {

	@RequestMapping(value = "hoa-don" , method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model,HttpServletRequest request) {

		List<HoaDonDTO> list=null;
		try {
			list = Collector.getListAll("/hoadon",HoaDonDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		
        
		return "web/bill";
	}
	
	@RequestMapping(value = "hoa-don", params="btnsearch")
	public <E> String showMen2u(ModelMap model,HttpServletRequest request) {
		
		
		


	
		return "web/bill";
	}
	
	@RequestMapping(value = "hoa-don/{id}.htm", params = "linkView")
	public <E> String xemChiTietHD(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id) throws IOException {

		
		List<ChiTietHDDTO> chiTietHD = Collector.getListAll("/chitiethd?idhd="+Long.toString(id),ChiTietHDDTO.class);
		model.addAttribute("chiTietHD", chiTietHD);
		int tong=0;
		for (ChiTietHDDTO cthd : chiTietHD) {
			tong += cthd.getTongTien();
		}

		model.addAttribute("tongTien",tong);
		model.addAttribute("idhd",id);
		return "web/bill2";
	}

	
	



}