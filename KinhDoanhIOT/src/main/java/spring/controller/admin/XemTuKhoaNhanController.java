package spring.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.TuKhoa_NhanDTO;




@Controller
@RequestMapping(value = "/admin-home/")
public class XemTuKhoaNhanController {

	@RequestMapping(value = "admin-qltukhoanhan" , method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model,HttpServletRequest request) {

		List<TuKhoa_NhanDTO> list=null;
		try {
			list = Collector.getListAll("/tukhoanhan",TuKhoa_NhanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		
        
		return "admin/qltukhoanhan";
	}
	
	
	
	
	
	
	
	
	
}
	
	
	
	


	
	
	