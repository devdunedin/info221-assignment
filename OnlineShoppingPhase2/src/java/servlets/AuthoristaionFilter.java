/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author devpu550
 */
public class AuthoristaionFilter implements Filter {

   public void destroy() {
     
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     HttpServletRequest httpRequest = (HttpServletRequest) request;
     HttpServletResponse httpResponse = (HttpServletResponse) response;
     HttpSession session = httpRequest.getSession();
     
     if(session.getAttribute("customer")== null){
        //store requested path in session so it can be loaded after login
        String requestedpath = httpRequest.getRequestURI();
        session.setAttribute("requestedpath", requestedpath);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"You need to login to view this page");
     }
     else{
        chain.doFilter(request, response);
     }
   }

   public void init(FilterConfig filterConfig) throws ServletException {
     
   }
   
}
