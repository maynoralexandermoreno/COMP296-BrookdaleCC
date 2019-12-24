//package addInvoices;
//
//import java.math.BigDecimal;
//import java.net.URL;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//import application.GUIController;
//import connectJDBC.DBConnection;
//import holderObjects.Customer;
//import holderObjects.IdHolder;
//import holderObjects.Invoice;
//import holderObjects.LineItem;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Labeled;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//
//public class AddInvoiceController implements Initializable {
//
//	@FXML
//	private Button add;
//	@FXML
//	private Button searchID;
//	@FXML
//	private TextField first;
//	@FXML
//	private TextField second;
//	@FXML
//	private TextField third;
//	@FXML
//	private TextField fourth;
//	@FXML
//	private TextField fifth;
//	@FXML
//	private TextField last;
//	@FXML
//	private TextField quantity;
//	@FXML
//	private TableView lineItems;
//	@FXML
//	private TableColumn nameColumn;
//	@FXML
//	private TableColumn descColumn;
//	@FXML
//	private TableColumn priceColumn;
//	@FXML
//	private TableColumn quantityColumn;
//	@FXML
//	private TableColumn subtotalColumn;
//	
//	public ResultSet rs;
//
//	private DBConnection landscapeDB = GUIController.getLandscapeDB();
//	public static IdHolder hold = new IdHolder();
//	public static ObservableList<LineItem> oblist = FXCollections.observableArrayList();
//	public LineItem insert;
//
//	public DBConnection getLandscapeDB() {
//		return landscapeDB;
//	}
//
//	public void setLandscapeDB(DBConnection landscapeDB) {
//		this.landscapeDB = landscapeDB;
//	}
//
//	public void addInvoice() {
//		BigDecimal total = new BigDecimal(0);
//		for (int i = 0; i < oblist.size(); i++) {
//			total = total.add(new BigDecimal(oblist.get(i).getSubtotal()));
//		}
//		int inid = landscapeDB.createInvoice(hold.getCustomer_id(), first.getText(), second.getText(), total.toString(), third.getText(), fourth.getText(), fifth.getText(),  last.getText());
//		for (int i = 0; i < oblist.size(); i++) {
//			landscapeDB.createInvoiceLineItem(inid, oblist.get(i).getPrice_id(), oblist.get(i).getQuantity());
//		}
//		
//	}
//	
//	@FXML
//	public void addInvoiceButton(ActionEvent event) {
//		try {
//			addInvoice();
//			Stage primaryStage = (Stage) last.getScene().getWindow();
//			primaryStage.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@FXML
//	public void addLineButton(ActionEvent event) {
//		System.out.println(hold.getCustomer_id() + " " + hold.getItem_id());
//		rs = landscapeDB.getItem(hold.getCustomer_id(),hold.getItem_id());
//		String amnt = quantity.getText();
//		try {
//			while(rs.next()) {
//				insert = new LineItem(rs.getString("i.item_name"), rs.getString("i.item_description"), rs.getString("p.price"), amnt.toString(), rs.getInt("p.price_id"));
//				System.out.println(insert.getItem_description());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		lineItems.getItems().add(insert);
//	}
//	
//	@FXML
//	public void findCustomerId(ActionEvent event) {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/addInvoices/FindWindow.fxml"));
//			Scene scene = new Scene(root);
//			Stage primaryStage = new Stage();	
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Find Customer ID");
//			primaryStage.setResizable(false);
//			primaryStage.initModality(Modality.APPLICATION_MODAL);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@FXML
//	public void findItemId(ActionEvent event) {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/addInvoices/FindItemWindow.fxml"));
//			Scene scene = new Scene(root);
//			Stage primaryStage = new Stage();	
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Find Item ID");
//			primaryStage.setResizable(false);
//			primaryStage.initModality(Modality.APPLICATION_MODAL);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		nameColumn.setCellValueFactory(new PropertyValueFactory<>("item_name"));
//		descColumn.setCellValueFactory(new PropertyValueFactory<>("item_description"));
//		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//		subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
//		oblist.clear();
//		lineItems.setItems(oblist);
//	}
//
//
//}
