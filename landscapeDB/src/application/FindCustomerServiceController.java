package application;

// Necessary Imports
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Controller for FindCustomerService.fxml
 */
public class FindCustomerServiceController implements Initializable {
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
	@FXML
	private Label result;
	
/*
 * Gets the DBConnection to perform inserts
 */
	private DBConnection landscapeDB = GUIController.getLandscapeDB();
/*
 * IdHolder object holds necessary id's to use for insertions
 */
	public static IdHolder hold = new IdHolder();

/*
 * Method creates a statement that returns a ResultSet;
 * Resultset is turned into String;
 * String is returned
 */	
	public String findPrice(int c, int it) {
		String sql = "Select price from Prices P WHERE customer_id = " + c + " and item_id = " + it;
		String holder = "25";
		ResultSet rs = landscapeDB.displayAllCustomers(sql);
		try {
			while (rs.next()) {
				holder = rs.getString("price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return holder;
	}
	
/*
 * Opens FindWindow.fxml within the package to search for the customer_id
 */
	@FXML
	public void findCustomerId(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/FindWindow.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Find Customer ID");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

/*
 * Opens FindItemWindow.fxml within the package to search for the item_id
 */
	@FXML
	public void findItemId(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/FindItemWindow.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Find Item ID");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

/*
 * Commits all gathered information into Label result
 */
	@FXML
	public void addServiceButton(ActionEvent event) {
		try {
			result.setText(findPrice(hold.getCustomer_id(), hold.getItem_id()));

		} catch(Exception e) {
			e.printStackTrace();
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