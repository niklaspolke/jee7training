package de.training.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.training.beans.LoginBean;

@WebFilter("/*")
public class LoginFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Logger logger = Logger.getLogger(URILoggerFilter.class.getName());
		
		HttpSession session = httpRequest.getSession(false);
		boolean loggedIn = false;
		LoginBean login = null;
		if (session != null) {
			login = (LoginBean) session.getAttribute("loginBean");
			if (login != null) {
				loggedIn = login.isLoggedIn();
			}
		}
		logger.info("logged in as " + (loggedIn ? login.getUser() : "---"));
		if (loggedIn || httpRequest.getRequestURI().endsWith("login.xhtml")) {
			filterChain.doFilter(request, response);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
}
