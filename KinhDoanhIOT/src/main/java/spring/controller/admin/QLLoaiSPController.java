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
import spring.dto.LoaiSPDTO;

@Controller

@RequestMapping(value = "/admin-home/")
public class QLLoaiSPController {

	// CONTROLLER
	@RequestMapping(value = "admin-qlloaisp", method = RequestMethod.GET)
	public <E> String showLoaiSP(HttpServletRequest request, ModelMap model) {

		List<LoaiSPDTO> list = null;
		try {
			list = Collector.getListAll("/loaisp", LoaiSPDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "admin/qlloaisp";
	}


	/* hiển thị form */
	@RequestMapping(value = "formLoaiSP", method = RequestMethod.GET)
	public String index_formLoaiSP(ModelMap model) {
		model.addAttribute("loaisp", new LoaiSPDTO());

		return "admin/form/inputLoaiSP";
	}

	@RequestMapping(value = "formLoaiSP", params = "linkEdit")
	public String editTD_showform(HttpServletRequest request, ModelMap model, @ModelAttribute("td") LoaiSPDTO td) {

		
		LoaiSPDTO xDTO = this.findOne(td.getId());
		model.addAttribute("loaisp", xDTO);

	

		/* model.addAttribute("td",td); */
		model.addAttribute("btnupdate", "true");
		model.addAttribute("read", "true");
		return "admin/form/inputLoaiSP";
	}

	// thêm
	@RequestMapping(value = "formLoaiSP", params = "Insert", method = RequestMethod.POST)
	public <E> String addLoaiSP(HttpServletRequest request, ModelMap model, @ModelAttribute("td") LoaiSPDTO td) {
		String error = "";
		Integer temp = 0;

		temp = this.insertLoaiSP(td);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

		} else {
			model.addAttribute("message", "Thêm thất bại " + error);
		}

		return "redirect:admin-qlloaisp.htm";
	}

	@RequestMapping(value = "formLoaiSP", params = "btnupdate", method = RequestMethod.POST)
	public <E> String editTD(HttpServletRequest requets, ModelMap model, @ModelAttribute("td") LoaiSPDTO td) {

		Integer temp = this.updateLoaiSp(td);
		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật không thành công");

		}

		return "redirect:admin-qlloaisp.htm";
	}

	@RequestMapping(value = "admin-qlloaisp", params = "linkDelete")
	public <E> String deleteDonNhapHang(HttpServletRequest request, ModelMap model,
			@ModelAttribute("td") LoaiSPDTO td) {

		Integer temp = this.deleteLoaiSP(td);
		if (temp != 0) {
			model.addAttribute("message", "Delete thành công");
		} else {
			model.addAttribute("message", "Delete không thành công ");
		}

		return "redirect:admin-qlloaisp.htm";
	}

	public Integer deleteLoaiSP(LoaiSPDTO td) {
		String flag = Collector.delMess("/loaisp", td);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

	public List<LoaiSPDTO> getLoaiSPs() {
		List<LoaiSPDTO> list = null;
		try {
			list = Collector.getListAll("/loaisp", LoaiSPDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Integer insertLoaiSP(LoaiSPDTO td) {
		String flag;
		try {
			flag = Collector.postMess("/loaisp", td);
			System.out.println(flag);
		} catch (Exception e) {
			System.out.println("loi api");
			e.printStackTrace();
			return 0;
		}

		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

	public Integer updateLoaiSp(LoaiSPDTO td) {
		String flag = Collector.putMess("/loaisp", td);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}
	
	public LoaiSPDTO findOne (Long id) {
		List<LoaiSPDTO> list = null;
		try {
			list = Collector.getListAll("/loaisp", LoaiSPDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoaiSPDTO ss = new LoaiSPDTO();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id))
				ss = list.get(i);
		}
		
		return ss;
	}
}