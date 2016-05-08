package com.skc.auth.abs;

import com.skc.auth.Authenticate;
import com.skc.auth.util.AppConstant;
import com.skc.auth.vo.Parameter;
import com.skc.auth.vo.ServerRequest;
import com.skc.auth.vo.StreamingData;

/**
 * <p> Class to give the implementation for to construct the URL for server </p> 
 * @author IgnatiusCipher(IgC)
 * */
public abstract class AbstractAuthenticate implements Authenticate {
	
	public abstract ServerRequest authenticate(Parameter param);

	public abstract StreamingData collect(Parameter parameter);
	
	
	@Override
	public String toString() {
		return "AbstractAuthenticate : "+super.toString();
	}

	/**
	 * <p> Method to construct the server request based on the provider</p>
	 * @param serverRequest type of {@link ServerRequest}
	 * */
	@Override
	public String sendRequest(ServerRequest serverRequest) {
		StringBuilder builder = null;
		switch(serverRequest.getProvider()){
			case AppConstant.GMAIL_PROVIDER:
				 builder= new StringBuilder(serverRequest.getProviderUrl())
										.append(AppConstant.DELIM_QUESTION)
										.append(AppConstant.GMAIL_SCOPE)
										.append(AppConstant.DELIM_EQUALS)
										.append(AppConstant.GMAIL_EMAIL)
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.GMAIL_REDIRECT_URL)
										.append(AppConstant.DELIM_EQUALS)
										.append(serverRequest.getRedirectedUrl())
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.RESPONSE_TYPE)
										.append(AppConstant.DELIM_EQUALS)
										.append(AppConstant.GMAIL_CODE)
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.CLIENT_ID)
										.append(AppConstant.DELIM_EQUALS)
										.append(serverRequest.getClientId())
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.GMAIL_APPROVAL_PROMPT)
										.append(AppConstant.DELIM_EQUALS)
										.append(AppConstant.GMAIL_FORCE);
				 break;
			case AppConstant.FACEBOOK_PROVIDER:
				 builder= new StringBuilder(serverRequest.getProviderUrl())
										.append(AppConstant.DELIM_QUESTION)
										.append(AppConstant.GMAIL_SCOPE)
										.append(AppConstant.DELIM_EQUALS)
										.append(AppConstant.GMAIL_EMAIL)
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.GMAIL_REDIRECT_URL)
										.append(AppConstant.DELIM_EQUALS)
										.append(serverRequest.getRedirectedUrl())
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.CLIENT_ID)
										.append(AppConstant.DELIM_EQUALS)
										.append(serverRequest.getClientId());
				break;
				
		}
		return builder.toString();
	}
}
	