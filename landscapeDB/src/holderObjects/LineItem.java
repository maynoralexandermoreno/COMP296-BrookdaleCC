/*
 * Unused Class; Kept as launching point for future development of invoice creator
 */

//package holderObjects;
//
//import java.math.BigDecimal;
//
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//
//public class LineItem {
//	public SimpleStringProperty item_name;
//	public SimpleStringProperty item_description;
//	public SimpleStringProperty price;
//	public SimpleStringProperty quantity;
//	public SimpleStringProperty subtotal;
//	public SimpleIntegerProperty price_id;
//	
//	public LineItem() {
//		
//	}
//	public LineItem(String item_name, String item_description, String Price, String Quantity, int pid) {
//		BigDecimal p = new BigDecimal(Double.parseDouble(Price));
//		BigDecimal q = new BigDecimal(Double.parseDouble(Quantity));
//		p = p.multiply(q);
//		this.item_name = new SimpleStringProperty("item_name");
//		this.item_description = new SimpleStringProperty("item_description");
//		this.price = new SimpleStringProperty("Price");	
//		this.quantity = new SimpleStringProperty("Quantity");
//		this.subtotal = new SimpleStringProperty(p.toString());
//		this.price_id = new SimpleIntegerProperty(pid);
//	}
//	public SimpleStringProperty item_descriptionProperty() {
//		return item_description;
//	}
//	public SimpleStringProperty item_nameProperty() {
//		return item_name;
//	}
//	public SimpleStringProperty priceProperty(){
//		return price;
//	}
//	public SimpleStringProperty quantityProperty() {
//		return quantity;
//	}
//	public SimpleStringProperty subtotalProperty() {
//		return subtotal;
//	}
//	public SimpleIntegerProperty price_idProperty() {
//		return price_id;
//	}
//	
//	
//	public String getItem_description() {
//		return item_description.get();
//	}
//	public String getItem_name() {
//		return item_name.get();
//	}
//	public String getPrice() {
//		return price.get();
//	}
//	public String getQuantity() {
//		return quantity.get();
//	}
//	public String getSubtotal() {
//		return subtotal.get();
//	}
//	public int getPrice_id() {
//		return price_id.get();
//	}
//	public void setItem_description(String item_description) {
//		this.item_description = new SimpleStringProperty(item_description);
//	}
//	public void setItem_name(String item_name) {
//		this.item_name = new SimpleStringProperty(item_name);
//	}
//	public void setPrice(String price) {
//		this.price = new SimpleStringProperty(price);
//	}
//	public void setQuantity(String quantity) {
//		this.quantity = new SimpleStringProperty(quantity);
//	}
//	public void setSubtotal(String subtotal) {
//		this.subtotal = new SimpleStringProperty(subtotal);
//	}
//	public void setPrice_id(int price_id) {
//		this.price_id = new SimpleIntegerProperty(price_id);
//	}
//	
//}
