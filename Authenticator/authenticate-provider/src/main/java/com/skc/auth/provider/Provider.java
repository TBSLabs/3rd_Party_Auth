package com.skc.auth.provider;

import java.net.URL;
import java.net.URLConnection;

import com.skc.auth.vo.Parameter;
import com.skc.auth.vo.ServerRequest;
import com.skc.auth.vo.StreamingData;

public interface Provider {
	StreamingData authorize(Parameter parameter); 
	ServerRequest authenticate();
	StringBuilder retrieveDataFromServer(URL url,URLConnection connection);
}
