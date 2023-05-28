package com.quancafehighland.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.quancafehighland.constant.SystemConstant;*/
import com.quancafehighland.utils.SessionUtil;

import spring.dto.LoginDTO;
import spring.dto.LoginKHDTO;

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
		LoginDTO model = (LoginDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
		LoginKHDTO modelkh = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
//admin
		if (url.indexOf("/admin") != -1) {

			if (model != null) {
				if (model.getRoleID() == 1) {
					filterChain.doFilter(servletRequest, servletResponse);
				} else if (model.getRoleID() != 1) {
					response.sendRedirect(
							request.getContextPath() + "/dang-nhap.htm?action=login&message=not_permission&alert=danger");
				}
			} else {
				response.sendRedirect(
						request.getContextPath() + "/dang-nhap.htm?action=login&message=not_login&alert=danger");
			}
		} 
		
		// khach hang
		else if (url.indexOf("/thanhtoan") != -1 || url.indexOf("/giohang")!=-1|| url.indexOf("KH")!=-1  ) {
			if (modelkh == null) {
				response.sendRedirect(
						request.getContextPath() + "//khachhang-login.htm?action=login&message=not_login&alert=danger");
			}
			else filterChain.doFilter(servletRequest, servletResponse);
		}
		
		
		//login chung
		else if (url.indexOf("/dang-nhap") != -1 || url.equals("/dichvu/") || url.indexOf("template")!=-1 || url.indexOf("common")!=-1 || url.indexOf("/khachhang")!=-1 ) {
			filterChain.doFilter(servletRequest, servletResponse);
		} 
		//nhan vien
		else {
			if (model == null) {
				response.sendRedirect(
						request.getContextPath() + "/dang-nhap.htm?action=login&message=not_login&alert=danger");
			}
			else filterChain.doFilter(servletRequest, servletResponse);
		}
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
