package addCustomerServices;

//Necessary Imports
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import application.GUIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import connectJDBC.DBConnection;
import holderObjects.*;

public class FindController implements Initializable {
/*
 * Connections to fxml objects; allows for setting and getting information
 */	
	@FXML
	private TableView<Customer> findTable;
	@FXML
	private TableColumn<Customer, Integer> idColumn;
	@FXML
	private TableColumn<Customer, String> firstColumn;
	@FXML
	private TableColumn<Customer, String> lastColumn;
	@FXML
	private ComboBox<String> selectTable;
	@FXML
	private ComboBox<String> selectColumn;
	@FXML
	private TextField input;
	@FXML
	private Button searchButton;
	@FXML
	private Button resetButton;
	@FXML
	private Button getButton;
	
	private DBConnection landscapeDB = GUIController.getLandscapeDB();
	public ResultSet rs;
	
/*
 * Strings used to create a final string to pass into an Statement	
 */	
	private String select = "SELECT customer_id, first_name, last_name FROM CUSTOMERS c";
	private String join;
	private String where;

/*
 * Fills a table based on a general customer search
 * Returns an ObservableList to set into a table
 */
	private ObservableList<Customer> fillTable() {
		ObservableList<Customer> oblist = FXCollections.observableArrayList();
		try {
			rs = landscapeDB.displayAllCustomers();
			
			while (rs.next()) {
				oblist.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return oblist;	
	}
/*
 * Creates a table based on specific user choices (String SQL)
 * Returns an ObservableList to set into a table
 */	
	private ObservableList<Customer> fillTable(String SQL) {
		ObservableList<Customer> oblist = FXCollections.observableArrayList();
		try {
			rs = landscapeDB.displayAllCustomers(SQL);
			
			while (rs.next()) {
				oblist.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return oblist;	
	}

/*
 * Dynamically changes the options within the ComboBox setCategory based on the information within ComboBox selectTable
 */
	@FXML
	public void setCategory(ActionEvent event) {
		String s = selectTable.getValue();
		if (s == null) {
			s = "";
		}
		switch (s) {
		case "customers":
			selectColumn.getItems().clear();
			selectColumn.getItems().addAll("first_name", "last_name", "[No Keyword]");
			join = "";
			selectColumn.setPromptText("Category");
			break;
		case "addresses":
			selectColumn.getItems().clear();
			selectColumn.getItems().addAll("address_id", "address_1", "address_2", "city", "state", "zip_code", "[No Keyword]");
			join = " JOIN addresses x ON c.customer_id = x.customer_ id";
			selectColumn.setPromptText("Category");
			break;
		case "phone numbers":
			selectColumn.getItems().clear();
			selectColumn.getItems().addAll("phone_number_id", "phone_number", "description", "[No Keyword]");
			join = " JOIN phone_numbers x ON c.customer_id = x.customer_ id";
			selectColumn.setPromptText("Category");
			break;
		case "invoices":
			selectColumn.getItems().clear();
			selectColumn.getItems().addAll("invoice_id", "invoice_number", "invoice_date", "invoice_total", "payment_total", "credit_total", "invoice_due_date", "last_payment_date", "[No Keyword]");
			join = " JOIN invoices x ON c.customer_id = x.customer_ id";
			selectColumn.setPromptText("Category");
			break;
		default:
			selectTable.setPromptText("Table");
			selectColumn.setPromptText("Category");
			input.setPromptText("KEYWORD");
		}
	}

/*
 * Takes information from ComboBoxes and TextFields to derive a string used to search the database for a specific customer;
 * Displays the reduced list within the table
 */
	@FXML
	public void findCustomer(ActionEvent event) {
		String column = selectColumn.getValue();
		String in = input.getText().toString();
		where = " WHERE x." + column + " LIKE '%" + in + "%'";
		if(selectTable.getValue().equalsIgnoreCase("customers")) {
			where = " WHERE c." + column + " LIKE '%" + input.getText() + "%'";
		}
		if (column.equalsIgnoreCase("[No Keyword]")) {
			where = "";
		}
		String sql = select + join + where;
		findTable.getItems().clear();
		findTable.setItems(fillTable(sql));
	}

/*
 * Resets all information to the default based on reset button click
 * Resets table to show all customers
 */
	@FXML
	public void resetScene(ActionEvent event) {
		findTable.getItems().clear();
		selectTable.getItems().clear();
		selectColumn.getItems().clear();
		input.setText("");
		findTable.setItems(fillTable());
		selectTable.setPromptText("Table");
		selectTable.getItems().addAll("customers", "addresses", "phone numbers", "invoices");
		selectColumn.setPromptText("Category");
		input.setPromptText("KEYWORD");
	}
	
/*
 * On button click, get the Item selected within the table and retrieves that customer_id
 * Sets the customer_id within the holder object of the main controller of this package
 * Closes this window
 */
	@FXML
	public void getInfo(ActionEvent event) {
		 Customer c = findTable.getSelectionModel().getSelectedItem();
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("/addCustomerServices/AddCustomerService.fxml"));
		 try {
			loader.load();
		} catch (IOException e) {
		}
		AddCustomerServiceController adding = loader.getController();
		adding.hold.setCustomer_id(c.getCustomer_id());
		Stage primaryStage = (Stage) getButton.getScene().getWindow();
		primaryStage.close();
	}

/*
 * Allows for the starting of this class without a start()
 * (non-Javadoc)
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
		firstColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
		lastColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
		findTable.setItems(fillTable());
		selectTable.setPromptText("Table");
		selectTable.getItems().addAll("customers", "addresses", "phone numbers", "invoices");
		selectColumn.setPromptText("Category");
		input.setPromptText("KEYWORD");

	}
}
