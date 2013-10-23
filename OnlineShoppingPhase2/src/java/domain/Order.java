/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.InputValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author delfe834
 */
public class Order {
   
   private int orderId;
   private Date date;
   private Customer customer;
   private List<OrderItem> items = new ArrayList<OrderItem>();

//   public Order(int orderId, Date date) {
//      this.orderId = orderId;
//      this.date = date;
//   }

   public Order(Customer customer,  Date date) {
      this.customer = customer;
      this.date = date;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public List<OrderItem> getItems() {
      return items;
   }

   public void setItems(List<OrderItem> items) {
      this.items = items;
   }

   public int getOrderId() {
      return orderId;
   }

   public void setOrderId(int orderId) {
      this.orderId = orderId;
   }
   
   public void addItem(OrderItem item){
      boolean f = false;
      Product p = item.getProduct();
      double total = 0.0;
      for(OrderItem i : items){
         if(i.getProduct().equals(p)) {
            total = i.getQuantityPurchased() + item.getQuantityPurchased();
            if(total > p.getQuantityInStock()){
               throw new InputValidationException("The quantity you want is more than that in stock. Please Check again.");
            }
             else{
            i.setQuantityPurchased(total);
         }
            return;
         }
      }
      this.items.add(item);
   }
   public double getTotal(){
      double total =0.0;
      for(int i =0; i<items.size();i++){
         total += items.get(i).getItemTotal();
      }
   
    return total;
   }
   
   
}
