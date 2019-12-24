package connectJDBC;

// Imports necessary classes
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import exceptions.LoginFailedException;
import java.sql.SQLException;

/*
 * Class is the access point to the database; all data manipulation methods are contained within
 */
public class DBConnection {
/*
 * Necessary class variables
 */
	private String user;
	private String password;
	private Connection dbConnection;
	private CallableStatement call = null;
	private ResultSet resultSet;
	public LoginFailedException loginFailed = new LoginFailedException("Login Failed");
	public int cID;
	public int itID;
	
/*
 * Creates a database connection from user name and password;
 * Info comes from login page, remains within DBConnection until logout
 */
	public DBConnection(String username, String pass) {
		this.user = username;
		this.password = pass;
	}
	
/*
 * Gets driver for mysql, creates connection to landscapeDB;
 */
	public void createConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/landscapeDB?autoReconnect=true&useSSL=false", this.user, this.password);	
		} 
		catch (Exception e) {
			System.out.println("Connection not made");
			throw this.loginFailed;
		}
	}

/*
 * Creates Customer using String fname, lname;
 * Inserts into Customers table;
 * Establishes connection, creates a call, passes strings into call;
 * Executes call and closes the class and connection;
 */
	public void createCustomer(String fName, String lName) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createCustomer(?,?,?)}");
			call.setString("fName", fName);
			call.setString("lName", lName);
			call.registerOutParameter("c_id", Types.INTEGER);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*
 * UNUSED CLASS: edit Customer using int cid, String fname, lname;
 * updates Customers table;
 * Establishes connection, creates a call, passes info into call;
 * Executes call and closes the class and connection;
 */
	public void editCustomer(int cid, String fName, String lName) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call updateCustomer(?,?,?)}");
			call.setInt("c_id", cid);
			call.setString("fName", fName);
			call.setString("lName", lName);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*
 * Create Number using int i (customer_id), String number, String description;
 * Inserts into Numbers table
 * Establishes connection, creates a call, passes info into call;
 * Executes call and closes resources;
 */
	public void createNumber(int i, String number, String description) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createNumber(?,?,?,?)}");
			call.setInt("c_id", i);
			call.setString("num", number);
			call.setString("descr", description);
			call.registerOutParameter("n_id", Types.INTEGER);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*
 * UNUSED METHOD: edit Number using int nid (number_id), String number, String description;
 * Updates Numbers table
 * Establishes connection, creates a call, passes info into call;
 * Executes call and closes resources;
 */	
	public void editNumber(int nid, String number, String description) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createNumber(?,?,?)}");
			call.setInt("n_id", nid);
			call.setString("num", number);
			call.setString("descr", description);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*
 * Create Address using int i (customer_id), Strings add_1, add_2, town, st, zip;
 * Inserts into Addresses table
 * Establishes connection, creates a call, passes info into call;
 * Executes call and closes resources;
 */
	public void createAddress(int i, String add_1, String add_2, String town, String st, String zip) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createAddress(?,?,?,?,?,?,?)}");
			call.setInt("c_id", i);
			call.setString("add_1", add_1);
			call.setString("add_2", add_2);
			call.setString("town", town);
			call.setString("st", st);
			call.setString("zip", zip);
			call.registerOutParameter("a_id", Types.INTEGER);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
 * UNUSED METHOD: Create Address using int aid (address_id), Strings add_1, add_2, town, st, zip;
 * Updates Addresses table
 * Establishes connection, creates a call, passes info into call;
 * Executes call and closes resources;
 */
	public void editAddress(int aid, String add_1, String add_2, String town, String st, String zip) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createAddress(?,?,?,?,?,?)}");
			call.setInt("a_id", aid);
			call.setString("add_1", add_1);
			call.setString("add_2", add_2);
			call.setString("town", town);
			call.setString("st", st);
			call.setString("zip", zip);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*
 * Creates a new invoice - adds onto customer object;
 * 
 * Creates sql.Date objects for all util.Date objects;
 * Inserts Invoice into db;
 * 
 * @param i					int ivoice_id
 * @param i_number			String invoice_number
 * @param i_date			String invoice_date
 * @param i_total			String invoice_total
 * @param p_total			String payment_total
 * @param c_total			String credit_total
 * @param i_due_date		String invoice_due_date
 * @param l_payment_date	String last_payment_date
 * @see holderObject.Invoice
 */
	public int createInvoice(int i, String i_number, String i_date, String i_total, String p_total, String c_total, String i_due_date, String l_payment_date) {
		int r = 0;
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createInvoice(?,?,?,?,?,?,?,?,?)}");
			call.setInt("c_id", i);
			call.setString("i_number", i_number);
			call.setString("i_date", i_date);
			call.setString("i_total", i_total);
			call.setString("p_total", p_total);
			call.setString("zip", c_total);
			call.setString("i_due_date", i_due_date);
			call.setString("l_payment_date", l_payment_date);
			call.registerOutParameter("in_id", Types.INTEGER);
			call.execute();
			r = call.getInt("in_id");
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
/*
 * UNUSED METHOD: edits an Invoice using an invoice id, i
 */
	public void editInvoice(int i, String i_number, String i_date, String i_total, String p_total, String c_total, String i_due_date, String l_payment_date) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createInvoice(?,?,?,?,?,?,?,?)}");
			call.setInt("in_id", i);
			call.setString("i_number", i_number);
			call.setString("i_date", i_date);
			call.setString("i_total", i_total);
			call.setString("p_total", p_total);
			call.setString("zip", c_total);
			call.setString("i_due_date", i_due_date);
			call.setString("l_payment_date", l_payment_date);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*
 * Creates a new item into the database - adds onto customer object;
 * 
 * Insert Item into db;
 * 
 * @param nme				String name
 * @param descr				String description
 * @see holderObject.Item
 */
	public void createItem(String nme, String descr) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createItem(?,?,?)}");
			call.setString("nme", nme);
			call.setString("descr", descr);
			call.registerOutParameter("it_id", Types.INTEGER);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
 * UNUSED METHOD: edits item using item_id i
 */
	public void editItem(int i, String nme, String descr) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createItem(?,?,?)}");
			call.setInt("it_id", i);
			call.setString("nme", nme);
			call.setString("descr", descr);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
/*
 *  Creates a new price into the database -- used within createItem so no issues
 *  
 *  Inserts price into
 *  
 * @param i				int c_id
 * @param it_id			int item_id
 * @param cost			String cost
 */
	public void createPrice(int i, int it_id, String cost) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createPrice(?,?,?,?)}");
			call.setInt("c_id", i);
			call.setInt("it_id", it_id);
			call.setString("cost", cost);
			call.registerOutParameter("p_id", Types.INTEGER);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
 * UNUSED METHOD: Using cid (customer_id) and it_id(item_id) to change cost
 * Updates Price table
 */
	public void editPrice(int cid, int it_id, String cost) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createPrice(?,?,?)}");
			call.setInt("c_id", cid);
			call.setInt("it_id", it_id);
			call.setString("cost", cost);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*
 * UNUSED METHOD: Creates an invoice line item to add onto an invoice;
 * Does not need to store price, subtotals, etc, since they can all be derived
 */
	public void createInvoiceLineItem(int in_id, int pid, String quantity) {
		try {
			this.createConnection();
			this.call = dbConnection.prepareCall("{call createInvoiceLineItem(?,?,?,?)}");
			call.setInt("in_id", in_id);
			call.setInt("p_id", pid);
			call.setString("line_quantity", quantity);
			call.registerOutParameter("in_sequence", Types.INTEGER);
			call.execute();
			call.close();
			this.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// SEARCH FUNCTIONS FOR DATABASE
/*
 * Takes String SQL, and creates a statement using the string;
 * returns the formed resultSet;
 */
	public ResultSet displayAllCustomers() {
		String SQLSearch = "SELECT * FROM CUSTOMERS c";
		try {
			this.createConnection();
			resultSet = dbConnection.createStatement().executeQuery(SQLSearch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
/*
 * Takes String SQL , ad creates a statement using the string;
 * returns the formed resultSet;
 */
	public ResultSet displayAllCustomers(String SQLSearch) {
		try {
			this.createConnection();
			resultSet = dbConnection.createStatement().executeQuery(SQLSearch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
/*
 * Takes String SQL , ad creates a statement using the string;
 * returns the formed resultSet;
 */
	public ResultSet displayAllItems() {
		String SQLSearch = "Select * FROM ITEMS i";
		try {
			this.createConnection();
			resultSet = dbConnection.createStatement().executeQuery(SQLSearch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
/*
 * Takes String SQL , ad creates a statement using the string;
 * returns the formed resultSet;
 */
	public ResultSet displayAllItems(String SQLSearch) {
		try {
			this.createConnection();
			resultSet = dbConnection.createStatement().executeQuery(SQLSearch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
/*
 * Takes String SQL , ad creates a statement using the string;
 * returns the formed resultSet;
 */
	public ResultSet getItem(int i, int j) {
		try {
			this.createConnection();
			String sql = "SELECT i.item_name, i.item_description, p.price, p.price_id FROM Customers c JOIN Prices p ON c.customer_id = p.customer_id "
					+ "JOIN Items i ON p.item_id = i.item_id WHERE c.customer_id = " + i + " AND p.item_id = " + j;
			resultSet = dbConnection.createStatement().executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

/*
 * Getters & Setters
 */
	public Connection getDbConnection() {
		return dbConnection;
	}
	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public int getItID() {
		return itID;
	}
	public void setItID(int itID) {
		this.itID = itID;
	}
}
