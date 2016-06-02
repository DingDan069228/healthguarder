package com.health.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.health.entity.User;

public class FilterServlet implements Filter {

	private FilterConfig config;

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) arg0;
			HttpServletResponse response = (HttpServletResponse) arg1;
			WrapperRequest wrapperRequest = new WrapperRequest(request,
					response);
			chain.doFilter(wrapperRequest, response);
		} catch (Exception e) {
			e.printStackTrace();
			config.getServletContext().log(e.getMessage());
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public class WrapperResponse extends HttpServletResponseWrapper {

		public WrapperResponse(HttpServletResponse httpServletResponse) {
			super(httpServletResponse);
		}

	}

	public class WrapperRequest extends HttpServletRequestWrapper {

		public WrapperRequest(HttpServletRequest request,
				HttpServletResponse response) {
			super(request);
			HttpSession session = request.getSession();
			String url = request.getRequestURL().toString();

			if (url.endsWith("cms/") || url.endsWith("cms")
					|| url.contains("index.jsp")
					|| url.contains("errorLogin.action") || url.endsWith(".js")
					|| url.endsWith(".jpg") || url.endsWith(".css")
					|| url.endsWith(".png") || url.endsWith(".gif")
					|| url.endsWith("image.jsp")
					|| url.endsWith("login.action")) {
			} else {
				User user = (User) session.getAttribute("user");
				if (user == null) {
					/*
					 * try { String path = getContextPath();
					 * response.sendRedirect(path + "/errorLogin.action"); }
					 * catch (IOException e) { e.printStackTrace(); }
					 */
				}
			}
		}
	}
}
