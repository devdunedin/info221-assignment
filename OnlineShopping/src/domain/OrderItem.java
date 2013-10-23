/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author delfe834
 */
public class OrderItem {
   
   private double quantityPurchased;
 
   public OrderItem(){
      
   }
    
   public OrderItem(double quantityPurchased) {
      this.quantityPurchased = quantityPurchased;
   }

   public double getQuantityPurchased() {
      return quantityPurchased;
   }

   public void setQuantityPurchased(double quantityPurchased) {
      this.quantityPurchased = quantityPurchased;
   }
   
   public double getItemTotal(){
      Product product = new Product();
      OrderItem item = new OrderItem();
      double items;
      items = product.getPrice()* item.getQuantityPurchased();
      return items;
   }
   
}

