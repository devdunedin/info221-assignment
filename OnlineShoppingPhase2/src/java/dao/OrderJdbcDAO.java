/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Order;
import domain.OrderItem;
import domain.Product;
import java.sql.*;
import java.util.Collection;

/**
 *
 * @author devpu550
 */
public class OrderJdbcDAO implements OrderDAO {
   public void save(Order order) {

	Connection con = null;
	PreparedStatement insertOrderStmt = null;
	PreparedStatement insertOrderItemStmt = null;
	PreparedStatement updateProductStmt = null;

	try {
		con = JdbcConnection.getConnection();

		insertOrderStmt = con.prepareStatement(
					"insert into Orders (date, customer) values (?,?)",
					Statement.RETURN_GENERATED_KEYS);

		insertOrderItemStmt = con.prepareStatement(
					"insert into OrderItems ( orderId,productId, quantityPurchased) values (?,?,?)");

		updateProductStmt = con.prepareStatement(
					"update products SET quantityInStock = (?) where productId = (?)");

		// since saving and order involves multiple statements across
		// multiple tables we need to control the transaction ourselves
		// to ensure our DB remains consistent

		// turn off auto-commit which effectively starts a new transaction
		con.setAutoCommit(false);
		// -- save the order --
		// convert the order's java.util.Date into a java.sql.Timestamp
		Timestamp timestamp = new Timestamp(order.getDate().getTime());
		// get the customer's username since it is the FK that links order and customer
		String username = order.getCustomer().getUserName();
		// ****
		// write code here that saves the timestamp and username in the order table
		// using the insertOrderStmt prepared statement
		// ****
            insertOrderStmt.setTimestamp(1, timestamp);
            insertOrderStmt.setString(2, username);
            //insertOrderStmt.executeQuery();
            insertOrderStmt.executeUpdate();

		// get the auto-generated order ID from the database
		ResultSet rs = insertOrderStmt.getGeneratedKeys();

		Integer orderId = null;

		if (rs.next()) {
			orderId = rs.getInt(1);
		} else {
			throw new DAOException("Problem getting generated Order ID");
		}

		// -- save the order items --

		Collection<OrderItem> items = order.getItems();

		// ****
		// write code here that iterates through the order items and saves
		// them using the insertOrderItemStmt prepared statement.
		// ****
         for (OrderItem item : items) {

            insertOrderItemStmt.setInt(1, orderId);
            insertOrderItemStmt.setInt(2, item.getProduct().getProductId());
            insertOrderItemStmt.setDouble(3, item.getQuantityPurchased());
            insertOrderItemStmt.executeUpdate();

         }
		// -- update the product quantities --

		for (OrderItem item : items) {

			Product product = item.getProduct();

			// *******************************************************************
			// write code here that updates the product quantity using the
			// using the updateProductStmt prepared statement.
			// *******************************************************************
                 
                  updateProductStmt.setDouble(1, (product.getQuantityInStock()-item.getQuantityPurchased()));
                  updateProductStmt.setInt(2, item.getProduct().getProductId());
                  updateProductStmt.executeUpdate();
		}


		// -- commit and clean-up --

		// commit the transaction
		con.commit();

		// turn auto-commit back on
		con.setAutoCommit(true);

		// close the statements and connection
		insertOrderStmt.close();
		insertOrderItemStmt.close();
		updateProductStmt.close();
		con.close();

	} catch (SQLException ex1) {

		// something went wrong so rollback the entire transaction
		try {
			con.rollback();
		} catch (SQLException ex) {
		}

		// and throw an exception to tell the user what happened
		throw new DAOException(ex1.getMessage(), ex1);
	}
}
   
}
