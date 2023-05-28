package spring.controller.chung;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.NhanVienModel;
import com.quancafehighland.model.UserModel;
import com.quancafehighland.service.INhanVienService;
import com.quancafehighland.service.IUserService;
import com.quancafehighland.service.impl.NhanVienService;
import com.quancafehighland.service.impl.UserService;
import com.quancafehighland.utils.FormUtil;
import com.quancafehighland.utils.SessionUtil;

import spring.Recaptcha.RecaptchaVerification;

@Controller
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Locale localeVi = new Locale("vi");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message_vi",localeVi);
	private INhanVienService nhanVienService = new NhanVienService();
	private IUserService userService = new UserService();
	
	public String hashPass(String matKhau) {
		String hashpw = DigestUtils.md5Hex(matKhau);
		return hashpw;
	}
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.GET)
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/jsp-views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath() + "/dang-nhap.htm?action=login");
			
		} else {
			response.sendRedirect(request.getContextPath() + "/dang-nhap.htm?action=login");
		}
	}

	@RequestMapping(value = "dang-nhap", method = RequestMethod.POST)
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = RecaptchaVerification.verify(gRecaptchaResponse);
		if (action != null && action.equals("login")) {
			if (!verify) {
				response.sendRedirect(request.getContextPath()
						+ "/dang-nhap.htm?action=login&message=fail-captcha&alert=danger");
			} else {
				UserModel model = FormUtil.toModel(UserModel.class, request);
				model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), hashPass(model.getPasswd()) , 1);

				if (model != null) {
					
					SessionUtil.getInstance().putValue(request, "USERMODEL", model);
					NhanVienModel nv = nhanVienService.findOne(model.getID());
					SessionUtil.getInstance().putValue(request, "NHANVIEN", nv);
					if (model.getRoleID() == 1) {
						response.sendRedirect(request.getContextPath() + "/admin-home/index.htm");
					} else if (model.getRoleID() != null) {
						response.sendRedirect(request.getContextPath() + "/trang-chu.htm");
					}
				} else {
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=username_password_invalid&alert=danger");
				}
			}
		}
	}
}
