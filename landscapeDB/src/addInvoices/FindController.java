//package addInvoices;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import addAddresses.AddAddressController;
//import application.GUIController;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import connectJDBC.DBConnection;
//import holderObjects.*;
//
//public class FindController implements Initializable {
//	private DBConnection landscapeDB = GUIController.getLandscapeDB();
//
//	private ObservableList<String> narrow = FXCollections.observableArrayList("Customer Id", "First Name", "Last Name");
//	@FXML
//	private TableView<Customer> findTable;
//	@FXML
//	private TableColumn<Customer, Integer> idColumn;
//	@FXML
//	private TableColumn<Customer, String> firstColumn;
//	@FXML
//	private TableColumn<Customer, String> lastColumn;
//	@FXML
//	private ComboBox<String> selectTable;
//	@FXML
//	private ComboBox<String> selectColumn;
//	@FXML
//	private TextField input;
//	@FXML
//	private Button searchButton;
//	@FXML
//	private Button resetButton;
//	@FXML
//	private Button getButton;
//	
//	private Label setID;
//	
//	private String select = "SELECT customer_id, first_name, last_name FROM CUSTOMERS c";
//	private String join;
//	private String where;
//	
//	
//	public ResultSet rs;
//
//
//	private ObservableList<Customer> fillTable() {
//		ObservableList<Customer> oblist = FXCollections.observableArrayList();
//		try {
//			rs = landscapeDB.displayAllCustomers();
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt("customer_id") + "   " + rs.getString("first_name") + "   " + rs.getString("last_name"));
//				oblist.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name")));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return oblist;	
//	}
//	private ObservableList<Customer> fillTable(String SQL) {
//		ObservableList<Customer> oblist = FXCollections.observableArrayList();
//		try {
//			rs = landscapeDB.displayAllCustomers(SQL);
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt("customer_id") + "   " + rs.getString("first_name") + "   " + rs.getString("last_name"));
//				oblist.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name")));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return oblist;	
//	}
//	
//	@FXML
//	public void setCategory(ActionEvent event) {
//		String s = selectTable.getValue();
//		if (s == null) {
//			s = "";
//		}
//		switch (s) {
//		case "customers":
//			selectColumn.getItems().clear();
//			selectColumn.getItems().addAll("first_name", "last_name", "[No Keyword]");
//			join = "";
//			selectColumn.setPromptText("Category");
//			break;
//		case "addresses":
//			selectColumn.getItems().clear();
//			selectColumn.getItems().addAll("address_1", "address_2", "city", "state", "zip_code", "[No Keyword]");
//			join = " JOIN addresses x ON c.customer_id = x.customer_ id";
//			selectColumn.setPromptText("Category");
//			break;
//		case "phone numbers":
//			selectColumn.getItems().clear();
//			selectColumn.getItems().addAll("phone_number", "description", "[No Keyword]");
//			join = " JOIN phone_numbers x ON c.customer_id = x.customer_ id";
//			selectColumn.setPromptText("Category");
//			break;
//		case "invoices":
//			selectColumn.getItems().clear();
//			selectColumn.getItems().addAll("invoice_number", "invoice_date", "invoice_total", "payment_total", "credit_total", "invoice_due_date", "last_payment_date", "[No Keyword]");
//			join = " JOIN invoices x ON c.customer_id = x.customer_ id";
//			selectColumn.setPromptText("Category");
//			break;
//		default:
//			selectTable.setPromptText("Table");
//			selectColumn.setPromptText("Category");
//			input.setPromptText("KEYWORD");
//		}
//	}
//
//	@FXML
//	public void findCustomer(ActionEvent event) {
//		String column = selectColumn.getValue();
//		String in = input.getText().toString();
//		where = " WHERE x." + column + " LIKE '%" + in + "%'";
//		if(selectTable.getValue().equalsIgnoreCase("customers")) {
//			where = " WHERE c." + column + " LIKE '%" + input.getText() + "%'";
//		}
//		if (column.equalsIgnoreCase("[No Keyword]")) {
//			where = "";
//		}
//		String sql = select + join + where;
//		System.out.println(sql);
//		findTable.getItems().clear();
//		findTable.setItems(fillTable(sql));
//	}
//	
//	@FXML
//	public void resetScene(ActionEvent event) {
//		findTable.getItems().clear();
//		selectTable.getItems().clear();
//		selectColumn.getItems().clear();
//		input.setText("");
//		findTable.setItems(fillTable());
//		selectTable.setPromptText("Table");
//		selectTable.getItems().addAll("customers", "addresses", "phone numbers", "invoices");
//		selectColumn.setPromptText("Category");
//		input.setPromptText("KEYWORD");
//	}
//	
//	@FXML
//	public void getInfo(ActionEvent event) {
//		 Customer c = findTable.getSelectionModel().getSelectedItem();
//		 FXMLLoader loader = new FXMLLoader();
//		 loader.setLocation(getClass().getResource("/addInvoices/AddInvoice.fxml"));
//		 try {
//			loader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		AddInvoiceController adding = loader.getController();
//		adding.hold.setCustomer_id(c.getCustomer_id());
//		Stage primaryStage = (Stage) getButton.getScene().getWindow();
//		primaryStage.close();
//	}
//	
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		idColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
//		firstColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
//		lastColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
//		findTable.setItems(fillTable());
//		selectTable.setPromptText("Table");
//		selectTable.getItems().addAll("customers", "addresses", "phone numbers", "invoices");
//		selectColumn.setPromptText("Category");
//		input.setPromptText("KEYWORD");
//
//	}
//}
