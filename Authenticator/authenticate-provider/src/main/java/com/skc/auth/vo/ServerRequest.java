package com.skc.auth.vo;

import org.springframework.context.annotation.Scope;

@Scope(scopeName="prototype")
public class ServerRequest {
	
	private String provider;
	private String clientId;
	private String clientCertId;
	private String providerUrl;
	private String redirectedUrl;
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientCertId() {
		return clientCertId;
	}
	
	public void setClientCertId(String clientCertId) {
		this.clientCertId = clientCertId;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public String getRedirectedUrl() {
		return redirectedUrl;
	}

	public void setRedirectedUrl(String redirectedUrl) {
		this.redirectedUrl = redirectedUrl;
	}

	
}
