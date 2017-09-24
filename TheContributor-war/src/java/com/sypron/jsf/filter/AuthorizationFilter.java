/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sypron.jsf.filter;
import java.io.IOException;
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

/**
 *
 * @author hisham
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
                        
                        if(reqURI.indexOf("/loging.xhtml") >= 0
                                &&ses != null && ses.getAttribute("userInfo") != null){
                            resp.sendRedirect(reqt.getContextPath() + "/homePage.xhtml");
                        }
                        
			if (reqURI.indexOf("/loging.xhtml") >= 0
                                        ||reqURI.indexOf("resource") >= 0
                                        ||reqURI.indexOf("javax.faces.resource") >= 0
					|| (ses != null && ses.getAttribute("userInfo") != null)
//					|| reqURI.indexOf("/public/") >= 0
                                        || reqURI.indexOf("/registration.xhtml") >= 0
//					|| reqURI.contains("javax.faces.resource")
                                )
				chain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + "/registration.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}
