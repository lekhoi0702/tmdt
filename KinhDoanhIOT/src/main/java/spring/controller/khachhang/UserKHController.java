package spring.controller.khachhang;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.Collector;
import spring.bean.Password;
import spring.dto.LoginKHDTO;
import spring.dto.UserKHDTO;

@Controller
public class UserKHController {

	@Autowired
	ServletContext session;

	@RequestMapping(value = "KHuser", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request) {
		LoginKHDTO user = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
		model.addAttribute("user", user);
		model.addAttribute("nv", user);
		model.addAttribute("changePW", new Password());
		return "khachhang/userkh";
	}

	@RequestMapping(value = "KHuser", params = "btnupdate-info", method = RequestMethod.POST)
	public String updateInfo(HttpServletRequest request, ModelMap model, @ModelAttribute("nv") LoginKHDTO user,
			BindingResult er) {
		
		String check = Collector.putMess("/khachhang2", user);
		if (check.equals("00")) {
			model.addAttribute("message", "tao tk thanh cong");
			return "redirect:KHuser.htm?thanhcong";

		} else {
			return "redirect:KHuser.htm?thatBai";
		}
		
	}

	@RequestMapping(value = "KHuser", params = "btnChangePw", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest request, ModelMap model,
			@ModelAttribute("password") Password password, BindingResult er) {
		// validation
		System.out.println(password.getPassword().equals(""));
		System.out.println(password.getNewpassword().equals(""));
		System.out.println(password.getRenewpassword().equals(""));
		System.out.println(!password.getNewpassword().equals(password.getRenewpassword()));
		if (password.getPassword().equals("")) {
			er.rejectValue("password", "changePW", "Vui lòng nhập password");
		}

		if (password.getNewpassword().equals("")) {
			er.rejectValue("newpassword", "changePW", "Vui lòng nhập password mới");
		}
		if (password.getRenewpassword().equals("")) {
			er.rejectValue("renewpassword", "changePW", "Vui lòng nhập lại password mới");
		}
		if (!password.getNewpassword().equals(password.getRenewpassword())) {
			er.rejectValue("renewpassword", "changePW", "Vui lòng nhập password đúng");
		}

		// end validation
		if (er.hasErrors()) {
			session.setAttribute("message1", "Cập nhật password không thành công, kiểm tra lại các trường");

		} else {
			Integer temp = changePW(request, password.getPassword(), password.getNewpassword(),
					password.getRenewpassword());
			if (temp != 0) {
				session.setAttribute("message1", "Cập nhật password thành công");
			} else {
				session.setAttribute("message1", "Cập nhật password không thành công");
			}
		}
		// LoginKHDTO user1 = (LoginKHDTO) SessionUtil.getInstance().putValue(request,
		// "USERMODEL");

		return "redirect:userkh.htm";
	}

	public Integer changePW(HttpServletRequest request, String password, String newpassword, String renewpassword) {
		LoginKHDTO user1 = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
		// String id = user1.getUserName();
		if (password.equals(user1.getPasswd()) && !newpassword.isEmpty() && !renewpassword.isEmpty()
				&& newpassword.equals(renewpassword)) {
			UserKHDTO user2 = new UserKHDTO();

			user2.setPasswd(newpassword);
			user2.setEmail(user1.getEmail());

			user2.setIcon("1234");
			user2.setUserName(user1.getUserName());

			String check = Collector.putMess("/userkh", user2);
			if (check.equals("00")) {
				return 1;
			} else
				return 0;
		}
		return 0;
	};
}