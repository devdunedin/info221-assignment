
import gui.MainMenuFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author delfe834
 */
public class Main {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      
             java.awt.EventQueue.invokeLater(new Runnable() {

         @Override
         public void run() {
            new MainMenuFrame().setVisible(true);
         }
      });
   }
}
