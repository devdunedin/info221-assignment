/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author devpu550
 */
public class GetByTest {
   
   private Product product;
   private Collection<Product> products;
   private OnlineDAO dao = new OnlineJdbcDAO();
   private String ca;
   
   public GetByTest() {
   }

   
   
   @Before
   public void setUp() {
      product = new Product(123, "Pepsi","This is the right choice","Soft Drinks",1.5,2.0);
      dao.save(product);
      ca = "fast food";
      products = dao.getByCategory(ca);
     //products = dao.getByCategory(ca);
      //products.dao.getByCategory(ca);
      
     
   }
   
   @After
   public void tearDown() {
      dao.delete(product);
      
   }
   // TODO add test methods here.
   // The methods must be annotated with annotation @Test. For example:
   //
   // @Test
   // public void hello() {}
  
      @Test
   public void getByProductId(){
      Product producta = dao.getByProductId(123);
      assertEquals("Comparing product to ID", this.product, producta);
      assertNull("Check whether or not there is a product for the id", dao.getByProductId(674));
     
   }
   
   @Test
   public void getByCategory(){
      
      Collection<Product> productsA = dao.getByCategory(ca);
      
      Collection<Product> productsB = null;
      
      try{
         productsB = dao.getByCategory("crap");
      } catch (Exception e) {
         productsB = null;
      }
      
      assertEquals("Comparing products to category", this.products, productsA);
       assertNull("Check whether or not there are products for the category", productsB);
        //productsA.removeAll(productsA);
   }
   }

