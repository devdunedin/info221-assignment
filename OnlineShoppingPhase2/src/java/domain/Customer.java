/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.InputValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      this.setUserName(userName);
      this.setCustomerName(customerName);
      this.setAddress(address);;
      this.setCreditCardDetails(creditCardDetails);
      this.setPassword(password);
   }

   public Customer() {
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      if(address == ""){
       throw new InputValidationException("The address cannot be empty");
      }
       else{
      this.address = address;
      }
   }

   public String getCreditCardDetails() {
      return creditCardDetails;
   }

   public void setCreditCardDetails(String creditCardDetails) {
      
      if(creditCardDetails == ""){
       throw new InputValidationException("The credit card details cannot be empty");
      }
       else{
      this.creditCardDetails = creditCardDetails;
      }
   }

   public String getCustomerName() {
      
      return customerName;
   }

   public void setCustomerName(String customerName) {
       Pattern p1= Pattern.compile("[a-zA-Z]+");
       Matcher m = p1.matcher(customerName);
       boolean b = m.matches();
        if(customerName == ""){
       throw new InputValidationException("The customer name cannot be empty");
      }
       if(!b){
       
       throw new InputValidationException("The customer name cannot be numbers or invalid characters");
       }
      if(customerName == ""){
       throw new InputValidationException("The customer name cannot be empty");
      }
       else{
      this.customerName = customerName;
      }
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
       if(password == ""){
       throw new InputValidationException("The password cannot be empty");
      }
       else{
      this.password = password;
       }
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      if(userName == ""){
       throw new InputValidationException("The username cannot be empty");
      }
      else{   
         this.userName = userName;
      
      }
   }
   
   
   
}
