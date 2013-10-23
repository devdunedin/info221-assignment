/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.InputValidationException;

/**
 *
 * @author delfe834
 */
public class Product implements Comparable<Product>{
   
   private Integer productId;
   private String productName;
   private String description;
   private String category;
   private Double price;
   private Double quantityInStock;
   
   public Product(){
   }
   

   public Product(Integer productId, String productName, String description, String category, Double price, Double quantityInStock) {
     
      this.description = description;
      
      
      setProductId(productId);
      setProductName(productName);
      setCategory(category);
      setPrice(price);
      setQuantityInStock(quantityInStock);
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      
      if(category == null || category.isEmpty()){
         throw new InputValidationException("Please enter a category. Category should not be empty");
      }
      this.category = category;
      
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getProductId() {
      return productId;
   }

   public void setProductId(Integer productId) {
     // if (productId == null){
       //  throw new InputValidationException("Please enter an Id. Id should not be empty");
     // }
      
      if (productId < 0) {
         throw new InputValidationException("ID must be a positive value.");
      } 
      this.productId = productId;
   }     

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      if (productName == null || productName.isEmpty()){
         throw new InputValidationException("Please enter a Name. Name should not be empty");
      }
         this.productName = productName;
      }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      //if (price == null){
        // throw new InputValidationException("Please enter a price. Price should not be empty");
     // }
      if (price < 0){
         throw new InputValidationException("Price must not be less the $0");
      }
      this.price = price;
   }

   public Double getQuantityInStock() {
      return quantityInStock;
   }

   public void setQuantityInStock(Double quantityInStock) {
     // if (quantityInStock == null){
       //  throw new InputValidationException("Please enter a quantityInstock. quantityInStock should not be empty");
     // }
      if (quantityInStock < 0) {
         throw new InputValidationException("Quantity in Stock cannot be negative");
      }
      this.quantityInStock = quantityInStock;
   }

   @Override
   public String toString() {
      return "ID: "+ this.productId + ", Name: " + this.productName; 
   }
   
   @Override
   public int compareTo(Product anotherProduct) {
      String myCategory = this.getCategory();
      String theirCategory = anotherProduct.getCategory();
      return myCategory.compareTo(theirCategory);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Product other = (Product) obj;
      if (this.productId != other.productId && (this.productId == null || !this.productId.equals(other.productId))) {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode() {
      int hash = 3;
      hash = 29 * hash + (this.productId != null ? this.productId.hashCode() : 0);
      return hash;
   }
   
   
}
