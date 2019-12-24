package addServices;

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
 * Controller for AddService.fxml
 */
public class AddServiceController implements Initializable {
/*
 *	Allows for the communication between this class and it's associated fxml page
 */
	@FXML
	private Button add;
	@FXML
	private TextField first;
	@FXML
	private TextField last;
	
	private DBConnection landscapeDB = GUIController.getLandscapeDB();
	
/*
 * Adds into Items table item with user information generated from the first and second TextField
 */
	public void addService() {
		landscapeDB.createItem(first.getText(), last.getText());	
	}
	
/*
 * Commits the information input by user into Items table in database
 * Closes window afterwards
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
 * 	Allows for opening without start()
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}

}

