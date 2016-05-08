package com.skc.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skc.auth.abs.AbstractAuthenticate;
import com.skc.auth.factory.ProviderFactory;
import com.skc.auth.vo.Parameter;
import com.skc.auth.vo.ServerRequest;
import com.skc.auth.vo.StreamingData;

/**
 * <p> End point for authenticate and authorization detail </p>
 * @author IgnatiusCipher(IgC)
 * */
@Component("authenticate")
public class AuthenticateEndpoint extends AbstractAuthenticate {
	
	@Autowired
	ProviderFactory factory;

	@Override
	public ServerRequest authenticate(Parameter param) {
		return factory.getInstance(param.getProvider()).authenticate();
	}

	@Override
	public StreamingData collect(Parameter parameter) {
		return factory.getInstance(parameter.getProvider()).authorize(parameter);
	}

	@Override
	public String sendRequest(ServerRequest authenticate) {
		return super.sendRequest(authenticate);
	}

}
