package gui;



import dao.OnlineDAO;
import dao.OnlineJdbcDAO;
import domain.Product;
import gui.helpers.SimpleListModel;
import static org.fest.swing.core.matcher.DialogMatcher.withTitle;
import static org.fest.swing.core.matcher.JButtonMatcher.withText;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CRUDTests {

  private OnlineDAO dao = new OnlineJdbcDAO();
   
   private FrameFixture menuFrame;
   
   private Product pepsi, mcDonalds;

   private static final int PEPSI_ID = 1111;
   private static final int MCDONALDS_ID = 2222;
   private static final String PEPSI_NAME = "Pepsi";
   private static final String MCDONALDS_NAME = "McDonalds";
   private static final String PEPSI_DESCRIPTION = "This is the right choice baby aha";        
   private static final String MCDONALDS_DESCRIPTION = "I'm Loving it";
   private static final String PEPSI_CATEGORY = "Soft Drinks";
   private static final String MCDONALDS_CATEGORY = "Fast Food";
   private static final Double PEPSI_PRICE = 1.50;
   private static final Double MCDONALDS_PRICE = 4.70;
   private static final Double PEPSI_QUANTITYINSTOCK = 1.0;
   private static final Double MCDONALDS_QUANTITYINSTOCK = 1.0;


   /**
    * This turns on some additional Swing testing that ensures we 
    * are creating our GUI the correct way.
    */
   @BeforeClass
   public static void setupOnce() {
      FailOnThreadViolationRepaintManager.install();
   }

   @Before
   public void setUp() {
      // create some dummy products
      pepsi = new Product(PEPSI_ID, PEPSI_NAME, PEPSI_DESCRIPTION,PEPSI_CATEGORY,PEPSI_PRICE,PEPSI_QUANTITYINSTOCK );
      mcDonalds = new Product(MCDONALDS_ID,MCDONALDS_NAME,MCDONALDS_DESCRIPTION,MCDONALDS_CATEGORY,MCDONALDS_PRICE,MCDONALDS_QUANTITYINSTOCK);

      // store them in the DAO
      dao.save(pepsi);
      dao.save(mcDonalds);

      // create an instance of out main menu frame
      MainMenuFrame frame = GuiActionRunner.execute(new GuiQuery<MainMenuFrame>() {
         @Override
         protected MainMenuFrame executeInEDT() {
            return new MainMenuFrame();
         }
      });

      // create a test fixture for the main menu frame
      menuFrame = new FrameFixture(frame);

      
      // robot's speed so we can see what is happening
      menuFrame.robot.settings().delayBetweenEvents(75);

      // show the main menu
      menuFrame.show();
   }

   @After
   public void cleanUp() {
      // clean up FEST resources
      menuFrame.cleanUp();

      // remove pepsi and mcDonals so they don't affect other tests
      dao.delete(pepsi);
      dao.delete(mcDonalds);
   }

   @Test
   public void addNewProduct() {
      // store the initial size of the DAO so we know if it is changed
      int origSize = dao.getAllProducts().size();

      // click "Add Product" button on the main menu
      menuFrame.button("btnAdd").click();

      // get the dialog that appears
      DialogFixture dialog = menuFrame.dialog("ProductEditorDialog");

      // select and delete the default ProductID text in the ID text field
      dialog.textBox("txtProductId").selectAll().deleteText();

      // enter some values into the text fields
      dialog.textBox("txtProductId").enterText("12345");
      dialog.textBox("txtProductName").enterText("KFC");

      dialog.textBox("txtDescription").enterText("It's finger Licking good!");
      
      // select "Fast Food" in the combo box
      dialog.comboBox().selectItem(MCDONALDS_CATEGORY.toString());
      dialog.textBox("txtPrice").enterText("2.0");
      dialog.textBox("txtQuantityInStock").enterText("3.5");

      // click the save button
      dialog.button("btnSave").click();

      // get the new DAO size
      int newSize = dao.getAllProducts().size();

      // if all is correct then the new size should have increased by one
      //assertTrue("DAO size increased by one", newSize - origSize == 1);

      // get the new product from the DAO
      Product product = dao.getByProductId(12345);

      // check that it actually was created
      assertNotNull("Product 12345 exists in the DAO", product);

      // check that the details are correct
      assertEquals("ProductName is correct", "KFC", product.getProductName());
      assertEquals("Description is correct", "It's finger Licking good!", product.getDescription());
      assertEquals("Category is correct", MCDONALDS_CATEGORY, product.getCategory());
      assertEquals("Price is correct",2.0,product.getPrice(),0); 
      assertEquals("QuantityInStock is correct",3.5,product.getQuantityInStock(),0); 

      // delete new product so it doesn't affect other tests
      dao.delete(product);
   }

   @Test
   public void viewProducts() {
      // click the view products button
      menuFrame.button("btnView").click();

      // get the dialog that appears
      DialogFixture dialog = menuFrame.dialog("ViewProductsDialog");

      // get the JList's model
      SimpleListModel model = (SimpleListModel) dialog.list().component().getModel();

      // check that the model has the same number of objects as the DAO.
      assertEquals("DAO and report's list model contain same number of objects", dao.getAllProducts().size(), model.getSize());

      // check that the model contains pepsi and mcDonalds
      assertTrue("List contains pepsi", model.contains(pepsi));
      assertTrue("List contains mcDonalds", model.contains(mcDonalds));

      // select pepsi in the list
      dialog.list().selectItem(pepsi.toString());
      

      // get string from selected list item
      String[] selectionArray = dialog.list().selection();

      // we only selected one product so get it from the returned array
      String selection = selectionArray[0];

      // make sure it contains products's ID, Name, Description,Category, Price and Quantity.
      assertTrue("Report displays PRODUCT ID", selection.contains(String.valueOf(PEPSI_ID)));
      assertTrue("Report displays PRODUCT NAME", selection.contains(String.valueOf(PEPSI_NAME)));
      //assertTrue("Report displays DESCRIPTION", selection.contains(String.valueOf(PEPSI_DESCRIPTION)));
      //assertTrue("Report displays CATEGORY NAME", selection.contains(String.valueOf(PEPSI_CATEGORY)));
      //assertTrue("Report displays PRICE", selection.contains(String.valueOf(PEPSI_PRICE)));
     //assertTrue("Report displays QUANTITYINSTOCK", selection.contains(String.valueOf(PEPSI_QUANTITYINSTOCK)));
   }

   @Test
   public void deleteProduct() {
      // store the initial size of the DAO so we know if it is changed
      int origSize = dao.getAllProducts().size();

      // click the view button
      menuFrame.button("btnView").click();

      // get the dialog that appears
      DialogFixture dialog = menuFrame.dialog("ViewProductsDialog");

      // select mcDonalds in the JList
      dialog.list().selectItem(mcDonalds.toString());

      // click the delete button
      dialog.button("btnDelete").click();

      // get the confirmation dialog that appears and click yes
      // we have to use the dialog's title and button's caption here since this
      // is an internal confirmation dialog and we don't know the names of the
      // components
      dialog.dialog(withTitle("Select an Option")).button(withText("Yes")).click();

      // get the new DAO size
      int newSize = dao.getAllProducts().size();

      // if all is correct then the new size should have decreased by one
      assertTrue("DAO size decreased by one", newSize - origSize == -1);

      // and mcDonalds should no longer be in the DAO
      assertNull("mcDonalds should not be in the DAO", dao.getByProductId(MCDONALDS_ID));
   }

   @Test
   public void editProduct() {
    
      // store the initial size of the DAO so we know if it is changed
      int origSize = dao.getAllProducts().size();

      // click the view button
      menuFrame.button("btnView").click();

      // get the dialog that appears
      DialogFixture reportDialog = menuFrame.dialog("ViewProductsDialog");

      // select mcDonalds in the JList
      reportDialog.list().selectItem(mcDonalds.toString());

      // click the edit button
      reportDialog.button("btnEdit").click();

      // get the edit dialog that appears
      DialogFixture editDialog = reportDialog.dialog("ProductEditorDialog");

      // Check that mcDonalds details appear in the components on the dialog.
      // These 'require' methods are built in asserts that FEST has for 
      // asserting that a component has certain properties.
      editDialog.textBox("txtProductId").requireText(String.valueOf(MCDONALDS_ID));
      editDialog.textBox("txtProductName").requireText(MCDONALDS_NAME);
     editDialog.textBox("txtDescription").requireText(MCDONALDS_DESCRIPTION);
      editDialog.comboBox("cmbCategory").requireSelection(MCDONALDS_CATEGORY);
     editDialog.textBox("txtPrice").requireText(MCDONALDS_PRICE.toString());
    editDialog.textBox("txtQuantityInStock").requireText(MCDONALDS_QUANTITYINSTOCK.toString());
      

      // change mcDonalds's name (delete existing text first).
      editDialog.textBox("txtProductName").selectAll().deleteText().enterText("BurgerKing");
      
      editDialog.textBox("txtDescription").selectAll().deleteText().enterText("king is awesome");

      // change mcDonalds's category
      editDialog.comboBox("cmbCategory").selectItem(PEPSI_CATEGORY);
      
     editDialog.textBox("txtPrice").selectAll().deleteText().enterText(PEPSI_PRICE.toString());
     editDialog.textBox("txtQuantityInStock").selectAll().deleteText().enterText(PEPSI_QUANTITYINSTOCK.toString());
      // click save
      editDialog.button("btnSave").click();

      // check DAO size has not changed.
      int newSize = dao.getAllProducts().size();
      assertTrue("DAO size is unchanged", origSize == newSize);

      // get updated object from DAO
      Product updatedmcDonalds = dao.getByProductId(MCDONALDS_ID);

      // check that the updates worked
   assertEquals("Product Name was updated", "BurgerKing", updatedmcDonalds.getProductName());
   assertEquals("Product Description was updated", "king is awesome", updatedmcDonalds.getDescription());
   assertEquals("Category was updated", PEPSI_CATEGORY, updatedmcDonalds.getCategory());
   assertEquals("Product Price was updated", PEPSI_PRICE, updatedmcDonalds.getPrice());
   assertEquals("Product QuantityInStock", PEPSI_QUANTITYINSTOCK, updatedmcDonalds.getQuantityInStock());

   }
}
