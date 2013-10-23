/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.CustomerJdbcDAO;
import domain.Customer;
import domain.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author devpu550
 */
public class LoginCustomerServlet extends HttpServlet {

   /**
    * Processes requests for both HTTP
    * <code>GET</code> and
    * <code>POST</code> methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      HttpSession session = request.getSession();



      String username = request.getParameter("username");
      String password = request.getParameter("password");
      Customer cust = new CustomerJdbcDAO().login(username, password);
// did DAO find a customer with those credentials?
      if (cust != null) {

         String requestedPath = (String) session.getAttribute("requestedPath");

         if (requestedPath != null) {
//The requested path comes from the filter when user
// was attempting access something forbidden to non..logged..in users
            session.removeAttribute("requestedPath");
            response.sendRedirect(requestedPath);
         } else {
            response.sendRedirect("/OnlineShoppingPhase2");
         }


// if so store the customer in the session

         session.setAttribute("customer", cust);
// also create and store an Order that will be used as a shopping cart
         session.setAttribute("order", new Order(cust, new Date()));
// go back to home page
         
      } else {
// no customer has those details so send a 401 error
         response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Log in failed. Try again.");
      }

   }

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   /**
    * Handles the HTTP
    * <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      processRequest(request, response);
   }

   /**
    * Handles the HTTP
    * <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      processRequest(request, response);
   }

   /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */
   @Override
   public String getServletInfo() {
      return "Short description";
   }// </editor-fold>
}
