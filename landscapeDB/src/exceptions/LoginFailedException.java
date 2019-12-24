package exceptions;

/*
 * Custom exception for Login.java
 */

public class LoginFailedException extends Exception {
	
	public LoginFailedException(String ex) {
		super(ex);
	}
	public String getMessage() {
		return super.getMessage();
	}
}
