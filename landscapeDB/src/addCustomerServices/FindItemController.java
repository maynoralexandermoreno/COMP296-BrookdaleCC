package addCustomerServices;

// Necessary Imports
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import addCustomerServices.AddCustomerServiceController;
import application.GUIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import connectJDBC.DBConnection;
import holderObjects.*;

public class FindItemController implements Initializable {
/*
 * Connections to fxml objects; allows for setting and getting information
 */		
	@FXML
	private TableView<Item> findTable;
	@FXML
	private TableColumn<Item, Integer> idColumn;
	@FXML
	private TableColumn<Item, String> firstColumn;
	@FXML
	private TableColumn<Item, String> lastColumn;
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
	private String select = "SELECT * FROM Items ";
	private String where;
	
/*
 * Fills a table based on a general customer search
 * Returns an ObservableList to set into a table
 */
	private ObservableList<Item> fillTable() {
		ObservableList<Item> oblist = FXCollections.observableArrayList();
		try {
			rs = landscapeDB.displayAllItems();	
			while (rs.next()) {
				oblist.add(new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_description")));
			}
		} catch (SQLException e) {
		}
		return oblist;	
	}
/*
 * Fills a table based on specific user choices (String SQL)
 * Returns an ObservableList to set into a table
 */	
	private ObservableList<Item> fillTable(String SQL) {
		ObservableList<Item> oblist = FXCollections.observableArrayList();
		try {
			rs = landscapeDB.displayAllItems(SQL);	
			while (rs.next()) {
				oblist.add(new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_description")));
			}
		} catch (SQLException e) {
		}
		return oblist;	
	}

/*
 * Takes information TextFields to derive a string used to search the database for a specific item;
 * Displays the reduced list within the table
 */
	@FXML
	public void findItem(ActionEvent event) {
		String column = selectColumn.getValue();
		String in = input.getText().toString();
		where = " WHERE " + column + " LIKE '%" + in + "%'";
		if (column.equalsIgnoreCase("[No Keyword]")) {
			where = "";
		}
		String sql = select + where;
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
		selectTable.getItems().addAll("Items");
		selectColumn.getItems().addAll("item_name", "item_description");
		selectColumn.setPromptText("Category");
		input.setPromptText("KEYWORD");
	}

/*
 * On button click, get the Item selected within the table and retrieves that item_id
 * Sets the customer_id within the holder object of the main controller of this package
 * Closes this window
 */
	@FXML
	public void getInfo(ActionEvent event) {
		 Item i = findTable.getSelectionModel().getSelectedItem();
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("/addCustomerServices/AddCustomerService.fxml"));
		 try {
			loader.load();
		} catch (IOException e) {
		}
		AddCustomerServiceController adding = loader.getController();
		adding.hold.setItem_id(i.getItem_id());
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
		idColumn.setCellValueFactory(new PropertyValueFactory<>("item_id"));
		firstColumn.setCellValueFactory(new PropertyValueFactory<>("item_name"));
		lastColumn.setCellValueFactory(new PropertyValueFactory<>("item_description"));
		findTable.setItems(fillTable());
		selectTable.setPromptText("Table");
		selectTable.getItems().addAll("Items");
		selectColumn.getItems().addAll("item_name", "item_description", "[No Keyword]");
		selectColumn.setPromptText("Category");
		input.setPromptText("KEYWORD");

	}
}

