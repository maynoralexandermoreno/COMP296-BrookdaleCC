/*
 * Unused class; Kept for launching point of future developments
 */

//package holderObjects;
//import java.util.Date;
//
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//
//public class Invoice {
//
//	public SimpleIntegerProperty invoice_id;
//
//	public SimpleIntegerProperty customer_id;
//
//	public SimpleStringProperty invoice_number;
//
//	public SimpleStringProperty invoice_date;
//
//	public SimpleStringProperty invoice_total;
//
//	public SimpleStringProperty payment_total;
//
//	public SimpleStringProperty credit_total;
//
//	public SimpleStringProperty invoice_due_date;
//
//	public SimpleStringProperty last_payment_date;
//	
///**
// * CONSTRUCTOR 
// * 
// * @param in_id				int invoice_id
// * @param c_id				int customer_id
// * @param i_number			String invoice_number
// * @param i_date			Date invoice_date
// * @param i_total			BigDecimal invoice_total
// * @param p_total			BigDecimal payment_total
// * @param c_total			BigDecimal credit_total
// * @param i_due_date		Date invoice_due_date
// * @param l_payment_date	Date last_payment_date
// */
//	public Invoice(int in_id, int c_id, String i_number, String i_date, String i_total, String p_total, String c_total, String i_due_date, String l_payment_date) {
//		this.invoice_id = SimpleIntegerProperty(in_id);		
//		this.customer_id = SimpleIntegerProperty(c_id);			
//		this.invoice_number = SimpleStringProperty(i_number);	
//		this.invoice_date = SimpleStringProperty(i_date);	
//		this.invoice_total = SimpleStringProperty(i_total);
//		this.payment_total = SimpleStringProperty(p_total);
//		this.credit_total = SimpleStringProperty(c_total);
//		this.invoice_due_date = SimpleStringProperty(i_due_date);
//		this.last_payment_date = new SimpleStringProperty(l_payment_date);
//	}
//
///**
// * getter method
// * @return invoice_id
// */
//	public int getInvoiceId() {
//		return this.invoice_id;
//	}
///**
// * getter method
// * @return customer_id
//*/
//	public int getCustomerId() {
//		return this.customer_id;
//	}
///**
//* getter method
//* @return invoice+number
//*/
//	public String getInvoiceNumber() {
//		return this.invoice_number;
//	}
///**
//* getter method
//* @return invoice_date
//*/
//	public Date getInvoiceDate() {
//		return this.invoice_date;
//	}
///**
//* getter method
//* @return invoice_total
//*/
//	public BigDecimal getInvoiceTotal() {
//		return this.invoice_total;
//	}
///**
//* getter method
//* @return payment_total
//*/
//	public BigDecimal getPaymentTotal() {
//		return this.payment_total;
//	}
///**
//* getter method
//* @return credit_total
//*/
//	public BigDecimal getCreditTotal() {
//		return this.credit_total;
//	}
///**
//* getter method
//* @return invoice_due_date
//*/
//	public Date getInvoiceDueDate() {
//		return this.invoice_due_date;
//	}	
///**
//* getter method
//* @return last_payment_date
//*/
//	public Date getLastPaymentDate() {
//		return this.last_payment_date;
//	}
//
///**
// * Adds LineItem into ArrayList
// * @param l			LineItem l
// */
//	public void addInvoiceLineItem(LineItem l) {
//		LineItems.add(l);
//	}
//
///**
// * toString method
// * @return s		String s
// */
//	public String toString() {
//		String s = this.invoice_id + " " + this.customer_id + " " +  this.invoice_number + " " + this.invoice_date + " " + this.invoice_total + " " + this.payment_total + " " + this.credit_total
//				+ " " + this.invoice_due_date + " " + this.last_payment_date;
//		return s;
//	}
//}
