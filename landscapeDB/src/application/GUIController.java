package application;

// Necessary Imports
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import connectJDBC.DBConnection;
import holderObjects.Customer;

public class GUIController implements Initializable{
//	Static DBConnection; passed into controller when 
	private static DBConnection landscapeDB;
//	Allows for loader to inject values into TextFields user, password 
	@FXML
	private String user = null;
	@FXML
	private String password = null;
	@FXML
	public MenuBar menu;
	@FXML
	private TableView<Customer> table;
	@FXML
	private TableColumn<Customer, Integer> first;
	@FXML
	private TableColumn<Customer, String> second;
	@FXML
	private TableColumn<Customer, String> third;
	public ResultSet rs;
	
/*
 * 	Method to set the GUI's static DBConnection to that of the set User; Can be modified in later versions to add or remove functionality from GUI
 */
	public void initData(String user, String password) {
		this.setUser(user);
		this.setPassword(password);
		landscapeDB = new DBConnection(user,password);
	}

//	FILE MENU ITEM ACTION METHODS	
/*
 * 	Action for Log Out Menu Item; Returns program LoginWindow and LoginController
 */
	@FXML
	public void logout(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/login/LoginWindow.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) menu.getScene().getWindow();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("landscapeDB Login");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
/*
 * 	Action for Exit Menu Item; Exits program;
 */
	@FXML
	public void exit(ActionEvent event) {
		System.exit(0);
	}
	

//	ADD MENU ITEM ACTION METHODS
	// CUSTOMER MENU ITEM ACTION METHODS
	/*
	 * 	Action for New Customer;
	 * 	Opens new Stage; 
	 */
	@FXML
	public void addCustomer(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addCustomers/AddCustomer.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();	
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add New Customer");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 	Action for Address;
	 * 	Opens new Stage;
	 */
	@FXML
	public void addAddress(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addAddresses/AddAddress.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("New Customer Address");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 	Action for Phone Number;
	 * 	Opens new Stage;
	 */
	@FXML
	public void addPhoneNumber(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addNumbers/AddPhoneNumber.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("New Customer Phone Number");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 *  UNUSED METHOD;
	 * 	Action for Invoice;
	 * 	Opens new Stage
	 */
	@FXML
	public void addInvoice(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addInvoices/AddInvoice.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("New Customer Invoice");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 	Action for Service;
	 * 	Opens new Stage;
	 */
	@FXML
	public void addCustomerService(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addCustomerServices/AddCustomerService.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("New Customer Service");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * UNUSED METHOD; Goes to editCustomers/FindWindow.fxml
	 */
	@FXML
	public void editCustomer(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/editCustomers/FindWindow.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Find Customer");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			
		}
	}
	// SERVICE MENU ITEM ACTION METHODS
	/*
	 * Action for add Service (the general item insertion, not specific to customer)
	 */
	@FXML
	public void addService(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/addServices/AddService.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("New Service");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Displays the table within the screen
	 */
	@FXML
	public void displayAllCustomers(ActionEvent event) {
		try {
			table.setItems(fillTable());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Fills ObservableList with all customers
	 */
	private ObservableList<Customer> fillTable() {
		ObservableList<Customer> oblist = FXCollections.observableArrayList();
		try {
			rs = landscapeDB.displayAllCustomers();
			
			while (rs.next()) {
				System.out.println(rs.getInt("customer_id") + "   " + rs.getString("first_name") + "   " + rs.getString("last_name"));
				oblist.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oblist;	
	}
	/*
	 * Goes to FindCustomerService; searches for price of item
	 */
	@FXML
	public void findPrice(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/FindCustomerService.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Find Customer Service");
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
/*
 * Allows for execution of class without start method (non-Javadoc)
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		first.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
		second.setCellValueFactory(new PropertyValueFactory<>("first_name"));
		third.setCellValueFactory(new PropertyValueFactory<>("last_name"));
	}
//	GETTERS AND SETTERS	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static DBConnection getLandscapeDB() {
		return landscapeDB;
	}
	public void setLandscapeDB(DBConnection landscapeDB) {
		this.landscapeDB = landscapeDB;
	}
}

