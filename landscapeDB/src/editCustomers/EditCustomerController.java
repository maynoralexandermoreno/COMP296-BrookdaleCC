/*
 * Unused Class; Kept as launching off point of future edit functions
 */


//package editCustomers;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import application.GUIController;
//import connectJDBC.DBConnection;
//import holderObjects.Customer;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//
//public class EditCustomerController implements Initializable {
//	@FXML
//	private Button add;
//	@FXML
//	private TextField first;
//	@FXML
//	private TextField last;
//	public static Customer c;
//	private DBConnection landscapeDB = GUIController.getLandscapeDB();
//	
//	public void updateCustomer() {
//		landscapeDB.editCustomer(c.getCustomer_id(), first.getText(), last.getText());	
//	}
//	
//	@FXML
//	public void addCustomerButton(ActionEvent event) {
//		try {
//			updateCustomer();
//			Stage primaryStage = (Stage) add.getScene().getWindow();
//			primaryStage.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//	}
//
//}
