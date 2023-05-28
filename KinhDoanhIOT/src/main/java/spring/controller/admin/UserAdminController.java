package spring.controller.admin;
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
import spring.dto.LoginDTO;
import spring.dto.UserDTO;

@Controller

@RequestMapping(value = "/admin-home/")
public class UserAdminController {

	@Autowired
	ServletContext session;
	
	
	@RequestMapping(value = "admin-user", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request) {
		LoginDTO user = (LoginDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
		model.addAttribute("user", user);
		model.addAttribute("nv", user);
		model.addAttribute("changePW", new Password());
		return "admin/user";
	}
	
	@RequestMapping(value = "user", params = "btnChangePw", method = RequestMethod.POST)
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

		return "redirect:/admin-home/admin-user.htm";
	}

	public Integer changePW(HttpServletRequest request,String password,
			String newpassword, String renewpassword) {
		LoginDTO user1 = (LoginDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
		//String id = user1.getUserName();
		if (password.equals(user1.getPasswd()) && !newpassword.isEmpty() && !renewpassword.isEmpty()
				&& newpassword.equals(renewpassword)) {
			UserDTO user2 = new UserDTO();

			user2.setPasswd(newpassword);
			user2.setEmail(user1.getEmail());
			user2.setID(user1.getID());
			user2.setRoleID(user1.getRoleID());
			user2.setIcon("1234");
			user2.setUserName(user1.getUserName());
			
			String check= Collector.putMess("/user", user2);
			if (check.equals("00")) {
				return 1;
			}
			else return 0;
		}
		return 0;
	};

	

	
}
