package spring.controller.khachhang;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.bean.ObjDelString;
import spring.dto.LoginKHDTO;

@Controller

public class DangKyKHController {

	// CONTROLLER
	@RequestMapping(value = "khachhang-dangky", method = RequestMethod.GET)
	public <E> String showSanPham(HttpServletRequest request, ModelMap model) {
		model.addAttribute("user", new LoginKHDTO());

		return "khachhang/form/dangky";

	}
	
	@Autowired
	JavaMailSender mailer;

	@RequestMapping(value = "khachhang-dangky", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") LoginKHDTO user, HttpServletRequest request, ModelMap model) {

		// Generate random authentication code
		String authCode = generateRandomString();
		// Save user and authentication code to database
		user.setMaXacThuc(authCode);
		String check = Collector.postMess("/khachhang", user);
		if (check.equals("00")) {
			sendAuthCodeEmail(user.getEmail(), authCode);
			model.addAttribute("message", "tao tk thanh cong");
		} else {
			model.addAttribute("message", "tao tk that bai");
		}
		// Redirect to authentication page
		return "khachhang/form/maxacnhan";
	}
	
	@RequestMapping(value = "khachhang-xacnhan", method = RequestMethod.GET)
	public <E> String showxXacNhan(HttpServletRequest request, ModelMap model) {

		return "khachhang/form/maxacnhan";

	}
	@RequestMapping(value = "khachhang-xacnhan", method = RequestMethod.POST)
	public <E> String postXacNhan(HttpServletRequest request, ModelMap model) {
		ObjDelString code = new ObjDelString();
		code.setId(request.getParameter("maXacNhan"));
		String check=Collector.postMess("/userkh2", code);
		if (check.equals("00")) {
		
			model.addAttribute("message", "xac nhan thanh cong");
			return "redirect:khachhang-login.htm?action=login&xac nhan thanh cong";
		} else {
			model.addAttribute("message", "xac nhan  that bai");
			return "redirect:khachhang-login.htm?action=login&xac nhan that bai";
		}
	}

	private String generateRandomString() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(characters.charAt(random.nextInt(characters.length())));
		}
		return sb.toString();
	}

	public String sendAuthCodeEmail(String to, String code) {

		try {

			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");

			helper.setFrom("lekhoi0702@gmail.com");
			helper.setTo(to);
			helper.setSubject("Kinh doanh IOT KÍNH GỬI");
			String text = "<h3>Mã xác nhận của bạn là:" + "<span style=\"font-size: 24px; color: blue;\">" + code
					+ "</span></h3>";
			mail.setContent(text, "text/html; charset=utf-8");
			mailer.send(mail);
			// model.addAttribute("message", "truy cập gmail để nhận mã!");
		} catch (Exception ex) {
			ex.printStackTrace();
			return "01";
					
		}
		return "00";
	}
}