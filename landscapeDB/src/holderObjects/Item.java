package holderObjects;

// Necessary Imports
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

// 
public class Item {
/*
 * SimpleProperty attributes to Customer;
 * Used so as to be able to interact with TableView
 * Public due to parameters of SimpleProperties, as understood by research
 */
	private SimpleIntegerProperty item_id;
	private SimpleStringProperty item_name;
	private SimpleStringProperty item_description;

/*
 * Constructor
 */
	public Item(int it_id, String nme, String descr) {
		this.item_id = new SimpleIntegerProperty(it_id);
		this.item_name = new SimpleStringProperty(nme);
		this.item_description = new SimpleStringProperty(descr);
	}
	
/*
 * SimpleProperty getters
 */
	public final SimpleIntegerProperty item_idProperty() {
		return item_id;
	}
	public SimpleStringProperty item_nameProperty() {
		return item_name;
	}
	public SimpleStringProperty item_descriptionProperty() {
		return item_description;
	}

/*
 * Normal Getters & Setters
 */
	public int getItem_id() {
		return item_id.get();
	}
	public String getItem_name() {
		return item_name.get();
	}
	public String getItem_description() {
		return item_description.get();
	}
	public void setItem_id(int item_id) {
		this.item_id.set(item_id);
	}
	public void setItem_name(String item_name) {
		this.item_name.set(item_name);
	}
	public void setItem_description(String item_description) {
		this.item_description.set(item_description);
	}

}
