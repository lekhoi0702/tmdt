package quanlyvattu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quanlyvattu.model.UserModel;
import quanlyvattu.utils.SessionUtil;

import java.io.IOException;

public class AuthorizationFilter implements Filter {

	private ServletContext context = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.setContext(filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		// neu la dang nhap thi cho qua
		if (url.indexOf("/dang-nhap") != -1 || url.equals("/CNPM/")) {
			filterChain.doFilter(servletRequest, servletResponse);
		}

		// neu ko phai dang nhap, co ten nhom thi chi nhom do dc qua, ko co ten nhom thi
		// nhom nao cung co the qua nhung phai dang nhap
		else if (model != null) {
			if (url.indexOf("/congty") != -1) {

				switch (model.getRoleID()) {
				case "CONGTY":
					filterChain.doFilter(servletRequest, servletResponse);
					break;
				case "CHINHANH":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
					break;
				case "USER":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
					break;

				}

			}

			else if (url.indexOf("/chinhanh") != -1) {

				switch (model.getRoleID()) {
				case "CONGTY":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
					break;
				case "CHINHANH":
					filterChain.doFilter(servletRequest, servletResponse);
					break;
				case "USER":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
					break;

				}

			}

			else if (url.indexOf("/user") != -1) {

				switch (model.getRoleID()) {
				case "CONGTY":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
					break;
				case "CHINHANH":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
					break;
				case "USER":
					filterChain.doFilter(servletRequest, servletResponse);
					break;

				}

			}

			//khi khong co ten nhom trong url
			else filterChain.doFilter(servletRequest, servletResponse);
			
		}
		//khi khong dang nhap
		else response.sendRedirect(request.getContextPath() + "/dang-nhap.htm?action=login&message=not_login&alert=danger");
	}

	@Override
	public void destroy() {

	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
}
