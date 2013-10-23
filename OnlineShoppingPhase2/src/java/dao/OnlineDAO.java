/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author devpu550
 */
public interface OnlineDAO {
   
   Collection<String>  getCategories();
   Collection<Product> getAllProducts();
   
   void save(Product product);
  
   
   void delete(Product product);
   
 
   Collection<Product> getByCategory(String c);
   
   Product getByProductId(Integer p);
   
}
