package spring.controller.chung;

import java.io.IOException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.service.impl.UserService;

import spring.Recaptcha.RecaptchaVerification;
import spring.entity.UserTBEntity;

@Transactional
@Controller
@RequestMapping("/mailer/")
public class MailerController {
	int code = -1;
	
	@Autowired
	SessionFactory factory;
	
	public String hashPass(String matKhau) {
		String hashpw = DigestUtils.md5Hex(matKhau);
		return hashpw;
	}
	
	@RequestMapping("form")
	public String index(ModelMap model) {
		model.addAttribute("email", null);
		return "mailer/form";
	}

	@Autowired
	JavaMailSender mailer;

	@RequestMapping(value="form", method=RequestMethod.POST)
	public String send(ModelMap model,HttpServletRequest request) throws IOException {
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = RecaptchaVerification.verify(gRecaptchaResponse);
		if (!verify) {
			model.addAttribute("reCaptra", "Vui lòng nhập reCaptra");
		} else {
			if (code == -1) {
				model.addAttribute("message", "Vui lòng nhấn gửi mã!");
			}
			else {
				int code1 =  -1;
				String newPassWord = null;
				String renewPassWord = null;
				try {
					code1 =  Integer.parseInt(request.getParameter("code"));
				} catch (Exception e) {}
				try {
					newPassWord = request.getParameter("newpassword");
				} catch (Exception e) {}
				try {
					renewPassWord = request.getParameter("renewpassword");
				} catch (Exception e) {}
				
				if (code1 == -1 || newPassWord.isEmpty() || renewPassWord.isEmpty()) {
					model.addAttribute("message", "Không được bỏ trống!");
				} else {
					if (code == code1) {
						if (newPassWord.equals(renewPassWord)) {
							UserService usv = new UserService();
							UserModel user = usv.findByEmail(request.getParameter("to"));
							String id = user.getUserName();
							UserTBEntity user2 = this.getUser(id);
							user2.setPasswd(hashPass(newPassWord));
							code = -1;
							model.addAttribute("message", "Đổi mật khẩu thành công!");
						} else {
							model.addAttribute("message", "Mật khẩu không khớp!");
						}
					} else {
						model.addAttribute("message", "Bạn nhập sai mã!");
					}
				}				
			}
		}
		model.addAttribute("email", request.getParameter("to"));
		return "mailer/form";
	}
	
	@RequestMapping(value="form", params="guima", method=RequestMethod.POST)
	public String guiMa(ModelMap model, @RequestParam("from") String from, @RequestParam("to") String to,
			HttpServletRequest request) {
		UserService usv = new UserService();
		UserModel user = usv.findByEmail(to);
		if (user == null) {
			model.addAttribute("message", "user có email này không tồn tại trong hệ thống");			
		} else {
			try {
				Random random = new Random();
				code = random.nextInt(1000000);
				
				MimeMessage mail = mailer.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail,true,"utf-8");
				
				helper.setFrom(from, from);
				helper.setTo(to);
				helper.setReplyTo(from, from);
				helper.setSubject("QUÁN CAFE HIGHLAND KÍNH GỬI");
				String text = "<h3>Mã xác nhận của bạn là:" + 
						"<span style=\"font-size: 24px; color: blue;\">"+code+"</span></h3>";
				mail.setContent(text,"text/html; charset=utf-8");
				mailer.send(mail);
				model.addAttribute("message", "truy cập gmail để nhận mã!");
			} catch (Exception ex) {
				model.addAttribute("message", "Lỗi gửi mail !");
			}
		}
		model.addAttribute("email", to);
		return "mailer/form";
	}
	
	public UserTBEntity getUser(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where userName = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		UserTBEntity list = (UserTBEntity) query.list().get(0);
		return list;
	}
}