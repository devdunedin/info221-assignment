/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import dao.CustomerDAO;
import dao.CustomerJdbcDAO;
import dao.InputValidationException;
import domain.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author devpu550
 */
public class AddCustomerServlet extends HttpServlet {

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
   
   Customer customer = new Customer();
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      String userName = request.getParameter("userName");
      String customerName = request.getParameter("customerName");
      String address = request.getParameter("address");
      String creditCardDetails = request.getParameter("creditCardDetails");
      String password  = request.getParameter("password");
      CustomerDAO c1 = new CustomerJdbcDAO();
      customer.setUserName(userName);
      customer.setCustomerName(customerName);
      customer.setAddress(address);
      customer.setCreditCardDetails(creditCardDetails);
      customer.setPassword(password);
      
      try {
      c1.save(customer);
      } catch(dao.DAOException ex) {
         SQLException e = (SQLException)ex.getCause();
         
         if(e!= null && e.getSQLState().equals("23505") ) {
            throw new InputValidationException("That username already exists. Please use a new username");
         } else {
            throw ex;
         }
            
      }
      response.sendRedirect("/OnlineShoppingPhase2/");

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
