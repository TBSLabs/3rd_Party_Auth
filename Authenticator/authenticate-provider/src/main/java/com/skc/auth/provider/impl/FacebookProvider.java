package com.skc.auth.provider.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.skc.auth.builder.ServerRequestBuilder;
import com.skc.auth.exception.BadServerURLException;
import com.skc.auth.provider.abs.AbstractProvider;
import com.skc.auth.util.AppConstant;
import com.skc.auth.vo.Parameter;
import com.skc.auth.vo.ServerRequest;
import com.skc.auth.vo.StreamingData;

@Component("facebookProvider")
public class FacebookProvider extends AbstractProvider{	
	
	@Value("${app.facebook.client.id}")
	String	clientId;

	@Value("${app.facebook.client.secret}")
	String	clientCertId;

	@Value("${app.facebook.auth.url}")
	String	facebookUrl;

	@Value("${app.redirect.url}")
	String	redirectUrl;

	@Value("${app.facebook.auth.data.retrieve}")
	String dataRetrieveUrl;
	
	@Value("${app.facebook.auth.token}")
	String tokenRetrieveUrl;
	
	@Value("${app.facebook.auth.stream}")
	String dataStreamingUrl;

	@Override
	public StreamingData authorize(Parameter parameter) {
		StringBuilder stringBuilder = new StringBuilder(tokenRetrieveUrl)
										.append(AppConstant.DELIM_QUESTION)
										.append(AppConstant.CLIENT_ID)
										.append(AppConstant.DELIM_EQUALS)
										.append(clientId)
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.GMAIL_REDIRECT_URL)
										.append(AppConstant.DELIM_EQUALS)
										.append(redirectUrl)
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.CLIENT_SECRET)
										.append(AppConstant.DELIM_EQUALS)
										.append(clientCertId)
										.append(AppConstant.DELIM_AND)
										.append(AppConstant.GMAIL_CODE)
										.append(AppConstant.DELIM_EQUALS)
										.append(parameter.getCode());
		URL url=null;
		URLConnection fbConnection=null;
		StringBuilder b = null;
		try {
				url = new URL(stringBuilder.toString());
				fbConnection = url.openConnection();
			    b = super.retrieveDataFromServer(url, fbConnection);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadServerURLException(AppConstant.ERR_BAD, AppConstant.ERR_BAD_MSG);
		}

		String accessToken = b.toString();
		if (accessToken.startsWith(AppConstant.DELIM_OPEN_CURLY)) {
			throw new BadServerURLException(AppConstant.ERR_INVD, AppConstant.ERR_INVD_MSG);
		}
		
		try {
			url = new URL(dataRetrieveUrl+accessToken);
			fbConnection= url.openConnection();
			b = super.retrieveDataFromServer(url, fbConnection);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new BadServerURLException(AppConstant.ERR_INVD, AppConstant.ERR_INVD_MSG);
		}
		
		return new StreamingData(b.toString());
	}

	@Override
	public ServerRequest authenticate() {
		return ServerRequestBuilder.getInstance().withClientId(clientId).withClientCertId(clientCertId)
				.withProvider(AppConstant.FACEBOOK_PROVIDER).withProviderUrl(facebookUrl)
				.withRedirectURL(redirectUrl)
				.build();
	}
	
}
