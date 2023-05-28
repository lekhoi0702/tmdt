package spring.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.Collector;
import spring.dto.ChucVuDTO;
import spring.dto.NhanVienDTO;
import spring.dto.Nhan_SanPhamDTO;
import spring.dto.UserDTO;
import spring.dto.UserKHDTO;
import spring.dto.KhachHangDTO;
import spring.dto.LoginDTO;

@Controller
@RequestMapping(value = "/admin-home/")
public class QLTaiKhoanKHController {

	// CONTROLLER
	@RequestMapping(value = "admin-taikhoankh", method = RequestMethod.GET)
	public <E> String index_TaiKhoan(HttpServletRequest request, ModelMap model) {

		List<UserKHDTO> list = null;
		try {
			list = Collector.getListAll("/userkh", UserKHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		return "admin/qltaikhoankh";
	}
	
	
	@RequestMapping(value = "admin-taikhoankh", params = "linkDelete", method = RequestMethod.GET)
	public <E> String deleteNV(HttpServletRequest requests, ModelMap model, @ModelAttribute("tk") UserKHDTO tk) {

		String error = "";
		Integer temp = 0;

		String userName = tk.getUserName();

		System.out.println(userName);

			UserKHDTO tmp = this.getTaiKhoan(userName);
			tmp.setStatus(0);
			temp = this.updateTK(tmp);
		if (temp != 0) {
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa k thành công" + error);
		}
		return "admin/qltaikhoankh";

	}
	
	public Integer updateTK(UserKHDTO tk) {
		String flag = Collector.putMess("/userkh", tk);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}
	
	public UserKHDTO getTaiKhoan(String userName) {
		List<UserKHDTO> list = null;
		try {
			list = Collector.getListAll("/userkh", UserKHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserKHDTO ss = new UserKHDTO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(userName))
				ss = list.get(i);
		}

		return ss;
	}
	
	

	
	
}