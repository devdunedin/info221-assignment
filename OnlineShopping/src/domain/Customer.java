/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author delfe834
 */
public class Customer {
   
   private String userName;
   private String customerName;
   private String address;
   private String creditCardDetails;
   private String password;

   public Customer(String userName, String customerName, String address, String creditCardDetails, String password) {
      this.userName = userName;
      this.customerName = customerName;
      this.address = address;
      this.creditCardDetails = creditCardDetails;
      this.password = password;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCreditCardDetails() {
      return creditCardDetails;
   }

   public void setCreditCardDetails(String creditCardDetails) {
      this.creditCardDetails = creditCardDetails;
   }

   public String getCustomerName() {
      return customerName;
   }

   public void setCustomerName(String customerName) {
      this.customerName = customerName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUserName() {
      return userName;
   }

   public void setUsername(String userName) {
      this.userName = userName;
   }
   
   
   
}
