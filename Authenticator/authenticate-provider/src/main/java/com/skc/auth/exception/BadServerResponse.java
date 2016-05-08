package com.skc.auth.exception;


/**
 * <p> Exception to be thrown while there is an invalid response</p>
 * @author IgnatiusCipher(IgC)
 * */
public class BadServerResponse extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -478218997865427562L;
	
	private String errorCode;
	private String errorMessage;
	
	
	public BadServerResponse(Throwable throwable) {
		super(throwable);
	}
	
	public BadServerResponse(String errorCode,String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
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
