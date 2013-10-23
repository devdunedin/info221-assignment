/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.*;

/**
 *
 * @author devpu550
 */
public class OnlineCollectionsDAO implements OnlineDAO {

   private static Map<Integer, Product> products = new TreeMap<Integer, Product>();
    private static Collection<Product> productsTree = new TreeSet<Product>();
   private static Collection<String> categories = new TreeSet<String>();

   @Override
   public void save(Product product) {
      products.put(product.getProductId(), product);
      categories.add(product.getCategory());
   }

   @Override
   public void delete(Product product) {
      products.remove(product.getProductId());
   }

   @Override
   public Collection<Product> getAllProducts() {
      return products.values();
   }

   @Override
   public Collection<String> getCategories() {
      return categories;
   }
   
 

   @Override
   public Collection<Product> getByCategory(String c) {
      Collection<Product> productsA = new TreeSet<Product>();
      Iterator<Product> it = products.values().iterator();

      while (it.hasNext()) {
         Product p = it.next();
         if (p.getCategory().equals(c)) {
            productsA.add(p);
         }
      }
      return productsA;
   }

   @Override
   public Product getByProductId(Integer p) {
      return products.get(p);
   }

  

}
