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
   private Product product;
   private Order order;
   
 
   public OrderItem(){
      
   }
    
   public OrderItem(double quantityPurchased,Product product, Order order) {
      this.quantityPurchased = quantityPurchased;
      this.product = product;
      this.order = order;
   }

   public double getQuantityPurchased() {
      return quantityPurchased;
   }

   public void setQuantityPurchased(double quantityPurchased) {
      this.quantityPurchased = quantityPurchased;
   }
   
   public double getItemTotal(){
     
      return product.getPrice()* getQuantityPurchased();
    
   }

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }
   
}

