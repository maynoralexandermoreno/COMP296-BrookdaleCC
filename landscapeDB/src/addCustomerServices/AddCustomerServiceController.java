package addCustomerServices;

//Necessary Imports
import java.net.URL;
import java.util.ResourceBundle;
import application.GUIController;
import connectJDBC.DBConnection;
import holderObjects.IdHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Controller for AddCustomerService.fxml
 */
public class AddCustomerServiceController implements Initializable {
/*
 *	Allows for the communication between this class and it's associated fxml page
 */
	@FXML
	private Button add;
	@FXML
	private Button searchID;
	@FXML
	private TextField second;
	@FXML
	private TextField last;

/*
 * Gets the DBConnection to perform inserts
 */
	private DBConnection landscapeDB = GUIController.getLandscapeDB();
/*
 * IdHolder object holds necessary id's to use for insertions
 */
	public static IdHolder hold = new IdHolder();
	
/*
 * Method calls createPrice method within DBConnection class
 * Inserts into Price the gathered data
 */	
	public void addService() {
		landscapeDB.createPrice(hold.getCustomer_id(), hold.getItem_id(), last.getText());
	}

/*
 * Commits all gathered information into Price table
 * Closes page;
 */
	@FXML
	public void addServiceButton(ActionEvent event) {
		try {
			addService();
			Stage primaryStage = (Stage) add.getScene().getWindow();
			primaryStage.close();
		} catch(Exception e) {
		}
	}
	
/*
 * Opens FindWindow.fxml within the package to search for the customer_id
 */
	@FXML
	public void findCustomerId(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addCustomerServices/FindWindow.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Find Customer ID");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
		}
	}

/*
 * Opens FindItemWindow.fxml within the package to search for the item_id
 */
	@FXML
	public void findItemId(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addCustomerServices/FindItemWindow.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Find Item ID");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
		}
	}

/*
 * Allows for the showing of class without a start function
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}


}