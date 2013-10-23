/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author delfe834
 */
public class InputValidationException extends RuntimeException {

   /**
    * Creates a new instance of
    * <code>DAOException</code> without detail message.
    */
   public InputValidationException() {
   }

   /**
    * Constructs an instance of
    * <code>DAOException</code> with the specified detail message.
    *
    * @param msg the detail message.
    */
   public InputValidationException(String msg) {
      super(msg);
   }
}
