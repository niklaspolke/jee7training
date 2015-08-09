package de.training.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.application.ResourceHandler;
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
		LoginBean login = (session != null) ? (LoginBean) session.getAttribute("loginBean") : null;

		boolean loggedIn = login != null && login.isLoggedIn();
		boolean loginRequest = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/login.xhtml");
		boolean resourceRequest = httpRequest.getRequestURI()
				.startsWith(httpRequest.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

		if (loggedIn || loginRequest || resourceRequest) {
			filterChain.doFilter(request, response);
		} else {
			logger.info("redirect to login page (original target: " + httpRequest.getRequestURI() + ")");
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
