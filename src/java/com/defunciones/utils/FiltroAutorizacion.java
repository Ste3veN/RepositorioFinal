package com.defunciones.utils;

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

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class FiltroAutorizacion implements Filter {

    public FiltroAutorizacion() {
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
            HttpSession ses = reqt.getSession(true);

            String reqURI = reqt.getRequestURI();
            if (reqURI.indexOf("/views/login.xhtml") >= 0
                    || reqURI.indexOf("/views/registro.xhtml") >= 0
                    || reqURI.indexOf("/views/index.xhtml") >= 0
                    || reqURI.indexOf("/views/admin/login.xhtml") >= 0
                    || (ses != null && ses.getAttribute("usuario") != null)
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource")) {
                if ("cliente".equals(ses.getAttribute("rol"))) {
                    if (reqURI.indexOf("/views/home") >= 0) {
                        chain.doFilter(request, response);
                    } else {
                        resp.sendRedirect(reqt.getContextPath() + "/faces/views/home/home.xhtml");
                    }
                } else if ("admin".equals(ses.getAttribute("rol"))) {
                    if (reqURI.indexOf("/views/admin") >= 0) {
                        chain.doFilter(request, response);
                    } else {
                        resp.sendRedirect(reqt.getContextPath() + "/faces/views/admin/datos.xhtml");
                    }
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                resp.sendRedirect(reqt.getContextPath() + "/faces/views/index.xhtml");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
    }

}
