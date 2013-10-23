/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.InputValidationException;
import dao.OrderDAO;
import dao.OrderJdbcDAO;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.eclipse.jdt.core.compiler.InvalidInputException;

/**
 *
 * @author devpu550
 */
public class AddProductAndQuantityServlet extends HttpServlet {

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
         throws ServletException, IOException{
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
     HttpSession session = request.getSession();
       Double quantityPurchased = Double.parseDouble(request.getParameter("quantityPurchased"));
      Product product = (Product)request.getSession().getAttribute("product"); 
     // Integer orderId = Integer.parseInt(request.getParameter("orderId"));
     // Date date = request.getParameter("date");
      Order order = (Order) request.getSession().getAttribute("order");
      
      //OrderDAO o1 = new OrderJdbcDAO();
      OrderItem item = new OrderItem(quantityPurchased, product, order);
      double quantitytpurchased = item.getQuantityPurchased();
      double quantityinstock = product.getQuantityInStock();
      if(quantityPurchased <=  quantityinstock && quantityPurchased >= 0){
      order.addItem(item);
      response.sendRedirect("/OnlineShoppingPhase2/restricted/CheckOut.jsp");
      }
      else{
     throw new InputValidationException("The number of products you ordered is not valid or more than the stock left. Please check");
  
      
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
