package spring.controller.web;

import java.io.IOException;
import java.security.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.NhanDTO;
import spring.dto.SanPhamDTO;
import spring.dto.TuKhoaDTO;
import spring.dto.TuKhoaDTO;




@Controller
public class TuKhoaController {

	@RequestMapping(value = "tukhoa" , method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model,HttpServletRequest request) {

		List<TuKhoaDTO> list=null;
		try {
			list = Collector.getListAll("/tukhoa",TuKhoaDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		
        
		return "web/tukhoa";
	}
	
	
	
	
	
	/* hiển thị form */
	@RequestMapping(value = "formTuKhoa", method = RequestMethod.GET)
	public String index_formSanPham(ModelMap model, @ModelAttribute("nsp") TuKhoaDTO nsp) {
		if (nsp == null) {
			model.addAttribute("nsp", new TuKhoaDTO());
		} else {
			model.addAttribute("nhans", getNhans());
		}
		
		
	
		return "web/form/inputTuKhoa";
	}
	
	
	
	public List<NhanDTO> getNhans() {
		List<NhanDTO> list = null;
		try {
			list = Collector.getListAll("/nhan", NhanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	@RequestMapping(value = "formTuKhoa", params = "Insert", method = RequestMethod.POST)
	public String addTuKhoa(HttpServletRequest request, ModelMap model, @ModelAttribute("nsp") TuKhoaDTO nsp) {
		nsp.setNvTao((long) 2);
		
		nsp.setNgayTao(new Date());

		List<String> listError = checkInfo(nsp);
		Integer temp = this.insertTuKhoa(nsp);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

			nsp = new TuKhoaDTO();

		} else {
			model.addAttribute("message", "Thêm thất bại! " + listError);
		}

		return "web/tukhoa";
	}

	public List<String> checkInfo(TuKhoaDTO nsp) {

		List<String> listError = new ArrayList<>();

		return listError;

	}
	
	
	

	public Integer insertTuKhoa(TuKhoaDTO nsp) {
		String flag = Collector.postMess("/tukhoa", nsp);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;

	}
	
	
	
	
}
	
	
	
	


	
	
	