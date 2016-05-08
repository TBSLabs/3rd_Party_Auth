package com.skc.auth;

import com.skc.auth.vo.Parameter;
import com.skc.auth.vo.ServerRequest;
import com.skc.auth.vo.StreamingData;

/**
 * <p> Interface to provide the authentication and authorization methods</p>
 * @author IgnatiusCipher(IgC)
 * */
public interface Authenticate {
	/**
	 * <p>Method to authenticate the data from the provider. Need to pass the <code>Parameter</code> VO object having all the data </p>
	 * @param param type of {@link Parameter}
	 * @return {@link ServerRequest}
	 * */
	 ServerRequest authenticate(Parameter param);
	 
	 /**
	  * <p>Method to authorize the access_token and retrieve the required information from the provider using token </p>
	  * @param parameter type of {@link Parameter}
	  * @return {@link StreamingData}
	  * */
	 StreamingData collect(Parameter parameter);
	 
	 /**
	  * <p> Method to construct the server url with the appropriate parameter to send to server for authorization </p>
	  * @param serverRequest type of {@link ServerRequest}
	  * @return {@link String}
	  * */
	 String sendRequest(ServerRequest serverRequest);
}
