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
import spring.dto.LoginDTO;

@Controller
public class LoginController extends HttpServlet {


	private static final long serialVersionUID = 1L;
	Locale localeVi = new Locale("vi");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message_vi", localeVi);

//	@Autowired
//	RestTemplate rest;
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {

			LoginDTO model = FormUtil.toModel(LoginDTO.class, request);
			try {
			model = Collector.postObj("/login",model,LoginDTO.class);
			}catch(Exception e) {
				response.sendRedirect(request.getContextPath()
						+ "/dang-nhap.htm?action=login&message=username_password_invalid&alert=danger");
			}
			if (model.getMaNV() != null) {

				SessionUtil.getInstance().putValue(request, "USERMODEL", model);

				if (model.getRoleID() == 1) {
					response.sendRedirect(request.getContextPath() + "/admin-home/index.htm");
				} else if (model.getRoleID() != null) {
					response.sendRedirect(request.getContextPath() + "/goi-mon.htm");
				}
			} 
		}
	}
}
