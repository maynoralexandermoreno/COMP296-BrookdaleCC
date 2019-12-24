package addCustomers;

// Necessary Imports
import java.net.URL;
import java.util.ResourceBundle;
import application.GUIController;
import connectJDBC.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * Controller for AddCustomer.fxml
 */
public class AddCustomerController implements Initializable {
	@FXML
	private Button add;
	@FXML
	private TextField first;
	@FXML
	private TextField last;
	
	private DBConnection landscapeDB = GUIController.getLandscapeDB();

/*
 * Adds into Customers table item with user information generated from the first and second TextField
 */
	public void addCustomer() {
		landscapeDB.createCustomer(first.getText(), last.getText());	
	}

/*
 * Commits the information input by user into Customers table in database
 * Closes window afterwards
 */
	@FXML
	public void addCustomerButton(ActionEvent event) {
		try {
			addCustomer();
			Stage primaryStage = (Stage) add.getScene().getWindow();
			primaryStage.close();
		} catch(Exception e) {
		}
	}
/*
 * 	Allows for opening without start()
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
