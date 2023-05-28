package spring.controller.khachhang;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.APIFunction;
import spring.bean.Collector;
import spring.bean.ObjDelLong;

import spring.dto.LoaiSPDTO;
import spring.dto.LoginKHDTO;
import spring.dto.NhanDTO;
import spring.dto.SanPhamDTO;

@Controller
public class HomeKHController {


	// CONTROLLER
	@RequestMapping(value = "khachhanghome", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		try {
			LoginKHDTO kh = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
			if(kh!=null) {
			long sl=Collector.getObj("/slgiohang?makh="+kh.getMaKH().toString(), ObjDelLong.class).getId();
			model.addAttribute("slGioHang", sl);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	
			model.addAttribute("LoaiSPs", getLoaiSPs());
			model.addAttribute("Nhans", getNhans());
		
	
			model.addAttribute("SanPhams", getSanPhams());
		

		return "khachhang/home";
	}


	@RequestMapping(value = "khachhangsearch-loai", method = RequestMethod.GET)
	public String indexLoai(HttpServletRequest request, ModelMap model) {
		List<SanPhamDTO> sps = APIFunction.getSanPhams();
		List<SanPhamDTO> sps2 = new ArrayList<SanPhamDTO>();
		
		Long idLoai = Long.parseLong(request.getParameter("id"));
		for (int i = 0; i< sps.size();i++) {
			if (sps.get(i).getLoai() == idLoai ) {
				sps2.add(sps.get(i));
			}
		}

		model.addAttribute("LoaiSPs", getLoaiSPs());
		model.addAttribute("Nhans", getNhans());
		
		model.addAttribute("SanPhams",sps2);

		return "khachhang/home";
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

	public List<SanPhamDTO> getSanPhams() {
		List<SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/sanpham", SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
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
}
