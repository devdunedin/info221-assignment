/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 *
 * @author devpu550
 */
public class OnlineJdbcDAO implements OnlineDAO{
   
     
   @Override
   public Collection<String> getCategories() {
     try {
         Connection connection = JdbcConnection.getConnection();
         PreparedStatement getCategoriesStmt = connection.prepareStatement("select distinct category from products");
           ResultSet rm = getCategoriesStmt.executeQuery();
            Collection<String> categories = new TreeSet<String>();
             while (rm.next()) {
                 String category =rm.getString("category");
                 categories.add(category);
             }
               rm.close();
               getCategoriesStmt.close();
               connection.close();
               return categories;
             }
     catch (SQLException ex){
        throw new DAOException(ex.getMessage(), ex);
     }
   }

   
   @Override
   public Collection<Product> getAllProducts() {
      try {
          Connection connection = JdbcConnection.getConnection();
          PreparedStatement getAllProductsStmt =
                connection.prepareStatement("select * from products order by productId");
            Collection<Product> products = new ArrayList<Product>();
              ResultSet rs = getAllProductsStmt.executeQuery();
                 while (rs.next()) {
            Integer productId = rs.getInt("productId");
            String productName = rs.getString("productName");
            String description = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Double quantityInStock = rs.getDouble("quantityInStock");
            Product p = new Product(productId, productName, description, category, price, quantityInStock);
            products.add(p);    
                 }
                   rs.close();
                    getAllProductsStmt.close();
                    connection.close();
                    return products;
      }
     
       catch (SQLException ex) {
          throw new DAOException(ex.getMessage(), ex);
      }
   }

   @Override
   public void save(Product product) {
      try {
         Connection connection = JdbcConnection.getConnection();
          PreparedStatement saveProductStmt =
               connection.prepareStatement(
               "merge into products (productId, productName, description, category, price, quantityInStock) values (?,?,?,?,?,?)");
         saveProductStmt.setInt(1,product.getProductId());
         saveProductStmt.setString(2, product.getProductName());
         saveProductStmt.setString(3, product.getDescription());
         saveProductStmt.setString(4, product.getCategory());
         saveProductStmt.setDouble(5, product.getPrice());
         saveProductStmt.setDouble(6, product.getQuantityInStock());
         saveProductStmt.executeUpdate();
         saveProductStmt.close();
         connection.close();
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   @Override
   public void delete(Product product) {
     try {
         Connection connection = JdbcConnection.getConnection();
         PreparedStatement delProductStmt = connection.prepareStatement("delete from products where productId = ?");
         delProductStmt.setInt(1, product.getProductId());
         delProductStmt.executeUpdate();
         delProductStmt.close();
         connection.close();
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   

   @Override
   public Collection<Product> getByCategory(String Key) {
     try {
               Connection connection = JdbcConnection.getConnection();
                  PreparedStatement getByCategoryStmt = connection.prepareStatement("select * from products  where category =(?)");
                  getByCategoryStmt.setString(1,Key);
                  Collection<Product> productsA = new TreeSet<Product>(); 
                  Product p = null;
                  ResultSet rs = getByCategoryStmt.executeQuery();
                  if (rs.next()) {
                     Integer productId = rs.getInt("productId");
                     String productName = rs.getString("productName");
                     String description = rs.getString("description");
                     String category = rs.getString("category");
                     Double price = rs.getDouble("price");
                     Double quantityInStock = rs.getDouble("quantityInStock");
                     p = new Product(productId, productName, description, category, price, quantityInStock);
                  }
                  rs.close();
               getByCategoryStmt.close();
               connection.close();
               productsA.add(p);
               return productsA;
                     
                  }catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
            }
   }

   @Override
   public Product getByProductId(Integer m) {
        try {
      Connection connection = JdbcConnection.getConnection();
      PreparedStatement getByProductIdStmt = connection.prepareStatement("select * from products  where productid =(?)");
      getByProductIdStmt.setInt(1, m);
       Product p2 = null;
       ResultSet rs2 = getByProductIdStmt.executeQuery();
       if (rs2.first()){
          Integer productId = rs2.getInt("productId");
          String productName = rs2.getString("productName");
          String description = rs2.getString("description");
          String category = rs2.getString("category");
          Double price = rs2.getDouble("price");
          Double quantityInStock = rs2.getDouble("quantityInStock");
          p2 = new Product(productId, productName, description, category, price, quantityInStock);
          }
          rs2.close();
          getByProductIdStmt.close();
          connection.close();
          return p2;
        }catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
      
   }
   }
}

      
   
