package spring.controller.khachhang;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.APIFunction;
import spring.bean.GioHangForm;
import spring.dto.GioHangDTO;
import spring.dto.LoginKHDTO;
import spring.dto.SanPhamDTO;

@Controller
public class ThanhToanController {
	// CONTROLLER
	@RequestMapping(value = "KHthanhtoan", method = RequestMethod.POST)
	public String index(HttpServletRequest request, ModelMap model, @ModelAttribute("gh") GioHangForm gh1) {
		int tong = 0;
		LoginKHDTO kh = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");

		//List<GioHangDTO> gioHangs = APIFunction.getGioHangs(kh.getMaKH());

//		GioHangForm ghf = new GioHangForm();
//		ghf.setGioHangs(gioHangs);

		//model.addAttribute("gioHangForm", ghf);

		List<SanPhamDTO> lstSPs = APIFunction.getSanPhams();
		List<SanPhamDTO> spGioHang = new ArrayList<SanPhamDTO>();
		for (GioHangDTO gh : gh1.getGioHangs()) {
			
			SanPhamDTO sp = APIFunction.getSP(gh.getMaSP(), lstSPs);
			spGioHang.add(sp);
			tong += sp.getGia()*gh.getSoLuong();
		}
		model.addAttribute("spGioHang", spGioHang);
		model.addAttribute("tongtien",tong);
		model.addAttribute("ghform",gh1);
		
		
		System.out.print(gh1.getGioHangs().get(0).getSoLuong());
	

		return "khachhang/form/checkout";
	}
	
}
