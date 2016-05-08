package com.skc.auth.provider.abs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.skc.auth.exception.BadServerURLException;
import com.skc.auth.provider.Provider;
import com.skc.auth.util.AppConstant;
import com.skc.auth.vo.Parameter;
import com.skc.auth.vo.ServerRequest;
import com.skc.auth.vo.StreamingData;

public abstract class AbstractProvider implements Provider{
	
	public abstract ServerRequest authenticate();
	
	public abstract StreamingData authorize(Parameter parameter);
	
	@Override
	public StringBuilder retrieveDataFromServer(URL url, URLConnection connection) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader in;
		try {
		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			stringBuilder.append(inputLine + "\n");
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new BadServerURLException(AppConstant.ERR_INVD, AppConstant.ERR_INVD_MSG);
		}
		return stringBuilder;
	}
	
}
