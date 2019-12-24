package addAddresses;

// Necessary Imports
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
 * Controller for AddAddress.fxml
 */
public class AddAddressController implements Initializable {
/*
 * Connections to fxml objects; allows for setting and getting information
 */	
	@FXML
	private Button add;
	@FXML
	private Button searchID;
	@FXML
	private TextField first;
	@FXML
	private TextField second;
	@FXML
	private TextField third;
	@FXML
	private TextField fourth;
	@FXML
	private TextField fifth;
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
 * Method calls createNumber method within DBConnection class
 * Inserts into Numbers gathered data
 */
	public void addAddress() {
		landscapeDB.createAddress(hold.getCustomer_id(), second.getText(), third.getText(), fourth.getText(), fifth.getText(), last.getText());	
	}

/*
 * Commits all gathered information into Addresses table
 * Closes window
 */
	@FXML
	public void addAddressButton(ActionEvent event) {
		try {
			addAddress();
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
			Parent root = FXMLLoader.load(getClass().getResource("/addAddresses/FindWindow.fxml"));
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
 * 	Allows for opening without start()
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}


}
