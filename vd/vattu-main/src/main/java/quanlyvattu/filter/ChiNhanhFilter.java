package quanlyvattu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quanlyvattu.model.UserModel;
import quanlyvattu.utils.SessionUtil;

import java.io.IOException;

public class ChiNhanhFilter implements Filter {

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
		
		if (url.indexOf("/dang-nhap") != -1 || url.equals("/CNPM/")) {
			filterChain.doFilter(servletRequest, servletResponse);
		}

		
		else
		if (!model.getRoleID().equals("CONGTY")) {
			if (url.indexOf("/cn1/") != -1 && !model.getMaChiNhanh().equals("cn1")) {
				 response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
			}
			else if (url.indexOf("/cn2/") != -1 && !model.getMaChiNhanh().equals("cn2")) {
				 response.sendRedirect(request.getContextPath()
							+ "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
			}
			else filterChain.doFilter(servletRequest, servletResponse);
		}
		
		
		else filterChain.doFilter(servletRequest, servletResponse);
		
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
