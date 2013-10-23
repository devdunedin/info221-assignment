/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author delfe834
 */
public class Order {
   
   private int orderId;
   private int date;
   private List<OrderItem> items = new ArrayList<OrderItem>();

   public Order(int orderId, int date) {
      this.orderId = orderId;
      this.date = date;
   }

   public int getDate() {
      return date;
   }

   public void setDate(int date) {
      this.date = date;
   }

   public int getOrderId() {
      return orderId;
   }

   public void setOrderId(int orderId) {
      this.orderId = orderId;
   }
   
   public void addItem(OrderItem item){
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
