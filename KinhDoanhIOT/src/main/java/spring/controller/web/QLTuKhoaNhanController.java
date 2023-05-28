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

import com.quancafehighland.utils.SessionUtil;

import spring.bean.Collector;
import spring.dto.LoginDTO;
import spring.dto.NhanDTO;
import spring.dto.SanPhamDTO;
import spring.dto.TuKhoaDTO;
import spring.dto.TuKhoa_NhanDTO;




@Controller
public class QLTuKhoaNhanController {

	@RequestMapping(value = "qltukhoanhan" , method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model,HttpServletRequest request) {

		List<TuKhoa_NhanDTO> list=null;
		try {
			list = Collector.getListAll("/tukhoanhan",TuKhoa_NhanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		
        
		return "web/qltukhoanhan";
	}
	
	
	
	
	
	/* hiển thị form */
	@RequestMapping(value = "formTuKhoaNhan", method = RequestMethod.GET)
	public String index_formSanPham(ModelMap model, @ModelAttribute("nsp") TuKhoa_NhanDTO nsp) {
		if (nsp == null) {
			model.addAttribute("nsp", new TuKhoa_NhanDTO());
		} else {
			model.addAttribute("nhans", getNhans());
			model.addAttribute("tukhoas", getTuKhoas());
			
		}
		
		
	
		return "web/form/inputTuKhoaNhan";
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
	
	public List<TuKhoaDTO> getTuKhoas() {
		List<TuKhoaDTO> list = null;
		try {
			list = Collector.getListAll("/tukhoa", TuKhoaDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	@RequestMapping(value = "formTuKhoaNhan", params = "Insert", method = RequestMethod.POST)
	public String addTuKhoa(HttpServletRequest request, ModelMap model, @ModelAttribute("nsp") TuKhoa_NhanDTO nsp) {
		LoginDTO nhanvien = (LoginDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
		nsp.setNvTao(nhanvien.getMaNV());
		
		nsp.setNgayTao(new Date());

		List<String> listError = checkInfo(nsp);
		Integer temp = this.insertTuKhoaNhan(nsp);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

			nsp = new TuKhoa_NhanDTO();

		} else {
			model.addAttribute("message", "Thêm thất bại! " + listError);
		}

		return "web/qltukhoanhan";
	}

	public List<String> checkInfo(TuKhoa_NhanDTO nsp) {

		List<String> listError = new ArrayList<>();

		return listError;

	}
	
	
	

	public Integer insertTuKhoaNhan(TuKhoa_NhanDTO nsp) {
		String flag = Collector.postMess("/tukhoanhan", nsp);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;

	}
	
	
	
	
}
	
	
	
	


	
	
	