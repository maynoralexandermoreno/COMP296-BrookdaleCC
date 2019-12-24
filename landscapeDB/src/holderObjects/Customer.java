package holderObjects;
// Necessary Imports
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
/*
 * SimpleProperty attributes to Customer;
 * Used so as to be able to interact with TableView
 * Public due to parameters of SimpleProperties, as understood by research
 */
	public SimpleIntegerProperty customer_id;
	public SimpleStringProperty first_name;
	public SimpleStringProperty last_name;

/*
 * Constructor
 */
	public Customer(int customer_id, String first_name, String last_name) {
		this.customer_id = new SimpleIntegerProperty(customer_id);
		this.first_name = new SimpleStringProperty(first_name);
		this.last_name = new SimpleStringProperty(last_name);
	}

/*
 * SimpleProperty getters
 */
	public SimpleIntegerProperty customer_idProperty() {
		return customer_id;
	}
	public SimpleStringProperty first_nameProperty() {
		return first_name;
	}
	public SimpleStringProperty last_nameProperty() {
		return last_name;
	}

/*
 * Normal getters & setters
 */
	public int getCustomer_id() {
		return customer_id.get();
	}
	public String getFirst_name() {
		return first_name.get();
	}
	public String getLast_name() {
		return last_name.get();
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id.set(customer_id);
	}
	public void setFirst_name(String first_name) {
		this.first_name.set(first_name);
	}
	public void setLast_name(String last_name) {
		this.last_name.set(last_name);
	}
}
