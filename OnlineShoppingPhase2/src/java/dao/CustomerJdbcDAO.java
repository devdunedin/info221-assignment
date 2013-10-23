/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author devpu550
 */
public class CustomerJdbcDAO implements CustomerDAO{

   public void save(Customer customer) {
      try {
         Connection connection = JdbcConnection.getConnection();
          PreparedStatement saveCustomerStmt =
               connection.prepareStatement(
               "insert into customers (userName, customerName, address, creditCardDetails, password) values (?,?,?,?,?)");
          
         saveCustomerStmt.setString(1,customer.getUserName());
         saveCustomerStmt.setString(2, customer.getCustomerName());
         saveCustomerStmt.setString(3, customer.getAddress());
         saveCustomerStmt.setString(4, customer.getCreditCardDetails());
         saveCustomerStmt.setString(5, customer.getPassword());
         saveCustomerStmt.executeUpdate();
         saveCustomerStmt.close();
         connection.close();
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   public Customer login(String UserName, String Password) {

      try {
         Connection connection = JdbcConnection.getConnection();
         PreparedStatement loginCustomerStmt =
         connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE USERNAME =? AND PASSWORD = ?");
         loginCustomerStmt.setString(1, UserName);
         loginCustomerStmt.setString(2, Password);
         Customer c = null;
         ResultSet rs = loginCustomerStmt.executeQuery();
         if (rs.first()) {
            String userName = rs.getString("userName");
            String customerName = rs.getString("customerName");
            String address = rs.getString("address");
            String creditCardDetails = rs.getString("creditCardDetails") ;
            String password = rs.getString("password");
            c = new Customer(userName, customerName, address, creditCardDetails, password);
         }
         rs.close();
       loginCustomerStmt.close();
       connection.close();
       return c;

      } catch (SQLException ex) {
          
           throw new DAOException(ex.getMessage(), ex);
      }
   }
}
