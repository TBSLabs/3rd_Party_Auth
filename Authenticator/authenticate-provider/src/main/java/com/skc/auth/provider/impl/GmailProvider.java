package com.skc.auth.provider.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skc.auth.builder.ServerRequestBuilder;
import com.skc.auth.exception.BadServerURLException;
import com.skc.auth.provider.abs.AbstractProvider;
import com.skc.auth.util.AppConstant;
import com.skc.auth.vo.Parameter;
import com.skc.auth.vo.ServerRequest;
import com.skc.auth.vo.StreamingData;

@Component("gmailProvider")
public class GmailProvider extends AbstractProvider{

	@Value("${app.gmail.client.id}")
	String	clientId;

	@Value("${app.gmail.client.secret}")
	String	clientCertId;

	@Value("${app.gmail.auth.url}")
	String	googleUrl;
	

	@Value("${app.redirect.url}")
	String	redirectUrl;

	@Value("${app.gmail.auth.data.retrieve}")
	String dataRetrieveUrl;
	
	@Value("${app.gmail.auth.token}")
	String tokenRetrieveUrl;
	
	@Value("${app.gmail.auth.stream}")
	String dataStreamingUrl;
	
	@Override
	public StreamingData authorize(Parameter parameter) {
		StringBuilder stringBuilder = new StringBuilder(AppConstant.GMAIL_CODE)
											.append(AppConstant.DELIM_EQUALS)
											.append(parameter.getCode())
											.append(AppConstant.DELIM_AND)
											.append(AppConstant.CLIENT_ID)
											.append(AppConstant.DELIM_EQUALS)
											.append(clientId)
											.append(AppConstant.DELIM_AND)
											.append(AppConstant.CLIENT_SECRET)
											.append(AppConstant.DELIM_EQUALS)
											.append(clientCertId)
											.append(AppConstant.DELIM_AND)
											.append(AppConstant.GMAIL_REDIRECT_URL)
											.append(AppConstant.DELIM_EQUALS)
											.append(redirectUrl)
											.append(AppConstant.DELIM_AND)
											.append(AppConstant.GMAIL_GRANT_TYPE)
											.append(AppConstant.DELIM_EQUALS)
											.append(AppConstant.GMAIL_AUTH_CODE);
		URL url = null;
		URLConnection connection=null;
		try {
			url = new URL(tokenRetrieveUrl);
			connection = url.openConnection();
			connection.setDoOutput(Boolean.TRUE);
			OutputStreamWriter outputStreamWriter =null;
			outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(stringBuilder.toString());
			outputStreamWriter.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new BadServerURLException(AppConstant.ERR_BAD, AppConstant.ERR_BAD_MSG);
		}
		String line, outputString = AppConstant.DELIM_EMPTY;
        BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) {
			    outputString += line;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new BadServerURLException(AppConstant.ERR_INVD, AppConstant.ERR_INVD_MSG);
		}
        
        Gson gson = new Gson();
        Map<String,String> accessTokenMap = gson.fromJson(outputString, new TypeToken<Map<String, String>>(){}.getType());
        
        try {
            url = new URL(dataStreamingUrl+ accessTokenMap.get(AppConstant.ACCESS_TOKEN));
			connection = url.openConnection();
			outputString = AppConstant.DELIM_EMPTY;
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        while ((line = reader.readLine()) != null) {
	            outputString += line;
	        }
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new BadServerURLException(AppConstant.ERR_INVD, AppConstant.ERR_INVD_MSG);
		}
		return new StreamingData(outputString);
	}
	
	@Override
	public ServerRequest authenticate() {
		return ServerRequestBuilder.getInstance().withClientId(clientId).withClientCertId(clientCertId)
				.withProvider(AppConstant.GMAIL_PROVIDER).withProviderUrl(googleUrl)
				.withRedirectURL(redirectUrl)
				.build();
	}
}
