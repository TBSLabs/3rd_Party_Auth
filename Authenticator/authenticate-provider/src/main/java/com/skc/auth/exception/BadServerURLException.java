package com.skc.auth.exception;

/**
 * <p> Exception to be thrown while there will be bad url passed to the server</p>
 * @author IgnatiusCipher(IgC)
 * */
public class BadServerURLException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 36696479024698189L;
	
	private String errorCode;
	private String errorMessage;
	
	
	public BadServerURLException(String errorCode,String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public BadServerURLException(Throwable throwable){
		super(throwable);
	}

	
	public String getErrorCode() {
		return errorCode;
	}

	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	public String getErrorMessage() {
		return errorMessage;
	}

	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
