/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;

/**
 *
 * @author devpu550
 */
public interface CustomerDAO {
   
   void save(Customer customer);
   Customer login(String userName, String password);
  
      
   
}
