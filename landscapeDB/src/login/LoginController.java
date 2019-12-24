package login;

// Necessary Imports
import java.net.URL;
import java.util.ResourceBundle;

import application.GUIController;
import exceptions.LoginFailedException;
import connectJDBC.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
/*
 * 	Allows for loader to inject values into TextFields user, password 
 */
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Label error;

/*
 *  Method to attempt Login into landscapeDB and access to GUI upon click 
 *  Wrong password causes failed message to show;
 */
	@FXML
	public void loginAttempt(ActionEvent event) {
		try {
		    DBConnection loginAttempt = new DBConnection(this.user.getText(), this.password.getText());
			loginAttempt.createConnection();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/dbGUI.fxml"));
			Parent mainMenu = loader.load();
			GUIController mainController = loader.getController();			
			mainController.initData("root", "password");
			
			Scene mainView = new Scene(mainMenu);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setScene(mainView);
			primaryStage.setTitle("landscapeDB");
			primaryStage.show();

		} 
		catch (LoginFailedException e) {
			user.setText("");
			password.setText("");
			error.setText("LOGIN FAILED");
			System.out.println(e.getMessage());
		}
		catch (Exception e1) {
			System.out.println("ERROR");
			e1.printStackTrace();
			System.exit(0);
		}
	}
	
/*
 *  initialize(); Allows for the starting of Stage without the main method
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
/*
 *  GETTERS & SETTERS
 */
	public TextField getUser() {
		return user;
	}
	public void setUser(TextField user) {
		this.user = user;
	}
	public TextField getPassword() {
		return password;
	}
	public void setPassword(TextField password) {
		this.password = password;
	}
	public Label getError() {
		return error;
	}
	public void setError(Label error) {
		this.error = error;
	}
}