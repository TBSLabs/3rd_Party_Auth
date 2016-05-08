package com.skc.auth.builder;

import com.skc.auth.vo.ServerRequest;

public class ServerRequestBuilder {
	
	private static ServerRequestBuilder serverRequestBuilder;
	private static ServerRequest serverRequest;
	
	
	private ServerRequestBuilder(ServerRequest request) {
		request = serverRequest;
	}
	
	public  ServerRequestBuilder() {
		serverRequest = new ServerRequest();
	}
	
	public static ServerRequestBuilder getInstance(){
		serverRequest = new ServerRequest();
		serverRequestBuilder = new ServerRequestBuilder(serverRequest);
		return serverRequestBuilder;
	}
	
	
	public ServerRequestBuilder withProvider(String provider){
		serverRequest.setProvider(provider);
		return this;
	}
	
	public ServerRequestBuilder withClientId(String clientId){
		serverRequest.setClientId(clientId);
		return this;
	}
	
	public ServerRequestBuilder withProviderUrl(String url){
		serverRequest.setProviderUrl(url);
		return this;
	}
	
	public ServerRequestBuilder withClientCertId(String clientCert){
		serverRequest.setClientCertId(clientCert);
		return this;
	}
	
	public ServerRequestBuilder withRedirectURL(String url){
		serverRequest.setRedirectedUrl(url);
		return this;
	}
	
	public ServerRequest build(){
		return serverRequest;
	}
}
