//package addInvoices;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//
//import addCustomerServices.AddCustomerServiceController;
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
//public class FindItemController implements Initializable {
//	private DBConnection landscapeDB = GUIController.getLandscapeDB();
//	@FXML
//	private TableView<Item> findTable;
//	@FXML
//	private TableColumn<Item, Integer> idColumn;
//	@FXML
//	private TableColumn<Item, String> firstColumn;
//	@FXML
//	private TableColumn<Item, String> lastColumn;
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
//	private String select = "SELECT * FROM Items ";
//	private String where;
//	
//	
//	public ResultSet rs;
//
//
//	private ObservableList<Item> fillTable() {
//		ObservableList<Item> oblist = FXCollections.observableArrayList();
//		try {
//			rs = landscapeDB.displayAllItems();
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt("item_id") + "   " + rs.getString("item_name") + "   " + rs.getString("item_description"));
//				oblist.add(new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_description")));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return oblist;	
//	}
//	private ObservableList<Item> fillTable(String SQL) {
//		ObservableList<Item> oblist = FXCollections.observableArrayList();
//		try {
//			rs = landscapeDB.displayAllItems(SQL);
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt("item_id") + "   " + rs.getString("item_name") + "   " + rs.getString("item_description"));
//				oblist.add(new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_description")));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return oblist;	
//	}
//
//	@FXML
//	public void findItem(ActionEvent event) {
//		String column = selectColumn.getValue();
//		String in = input.getText().toString();
//		where = " WHERE " + column + " LIKE '%" + in + "%'";
//		if (column.equalsIgnoreCase("[No Keyword]")) {
//			where = "";
//		}
//		String sql = select + where;
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
//		selectTable.getItems().addAll("Items");
//		selectColumn.getItems().addAll("item_name", "item_description");
//		selectColumn.setPromptText("Category");
//		input.setPromptText("KEYWORD");
//	}
//	
//	@FXML
//	public void getInfo(ActionEvent event) {
//		 Item i = findTable.getSelectionModel().getSelectedItem();
//		 FXMLLoader loader = new FXMLLoader();
//		 loader.setLocation(getClass().getResource("/addInvoices/AddInvoice.fxml"));
//		 try {
//			loader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		AddInvoiceController adding = loader.getController();
//		adding.hold.setItem_id(i.getItem_id());
//		Stage primaryStage = (Stage) getButton.getScene().getWindow();
//		primaryStage.close();
//	}
//	
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		idColumn.setCellValueFactory(new PropertyValueFactory<>("item_id"));
//		firstColumn.setCellValueFactory(new PropertyValueFactory<>("item_name"));
//		lastColumn.setCellValueFactory(new PropertyValueFactory<>("item_description"));
//		findTable.setItems(fillTable());
//		selectTable.setPromptText("Table");
//		selectTable.getItems().addAll("Items");
//		selectColumn.getItems().addAll("item_name", "item_description", "[No Keyword]");
//		selectColumn.setPromptText("Category");
//		input.setPromptText("KEYWORD");
//
//	}
//}
//
