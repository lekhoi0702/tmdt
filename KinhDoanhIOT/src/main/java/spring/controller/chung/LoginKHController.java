package spring.controller.chung;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.FormUtil;
import com.quancafehighland.utils.SessionUtil;

import spring.bean.Collector;
import spring.dto.LoginKHDTO;

@Controller
public class LoginKHController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Locale localeVi = new Locale("vi");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message_vi", localeVi);

//	@Autowired
//	RestTemplate rest;

	@RequestMapping(value = "khachhang-login", method = RequestMethod.GET)
	protected void doGetKH(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/jsp-views/khachhang/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERKHMODEL");
			response.sendRedirect(request.getContextPath() + "/khachhang-login.htm?action=login");

		} else {
			response.sendRedirect(request.getContextPath() + "/khachhang-login.htm?action=login");
		}
	}

	@RequestMapping(value = "khachhang-login", method = RequestMethod.POST)
	protected void doPostKH(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {

			LoginKHDTO model = FormUtil.toModel(LoginKHDTO.class, request);
			try {
				model = Collector.postObj("/loginkh", model, LoginKHDTO.class);
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath()
						+ "/khachhang-login.htm?action=login&message=username_password_invalid&alert=danger");
				return;

			}
			if (model.getMaKH() != null && model.getTrangThai() != 0) {
				System.out.println("trang thai" + model.getTrangThai());
				SessionUtil.getInstance().putValue(request, "USERKHMODEL", model);

				response.sendRedirect(request.getContextPath() + "/khachhanghome.htm");

			} else {
				response.sendRedirect(request.getContextPath()
						+ "/khachhang-login.htm?action=login&message=username_password_invalid&alert=danger");
			}

		}
	}

}
