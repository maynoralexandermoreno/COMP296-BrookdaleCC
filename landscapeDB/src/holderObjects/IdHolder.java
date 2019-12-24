package holderObjects;
/*
 * Holder object strictly for integer id's
 * Holds any id integers obtained from other classes and used for identification
 */
public class IdHolder {
/*
 * Integer properties associated with IdHolder object;	
 */
	public int customer_id;
	public int item_id;
	public int address_id;
	public int invoice_id;

/*
 * Constructor
 */
	public IdHolder() {
	}
	
/*
 * Getters & Setters
 */
	public int getCustomer_id() {
		return customer_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
}
