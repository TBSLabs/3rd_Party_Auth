package com.skc.auth.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skc.auth.impl.AuthenticateEndpoint;
import com.skc.auth.util.AppConstant;
import com.skc.auth.vo.Parameter;

/**
 * <p> End point for all incoming and outgoing server call </p>
 * @author IgnatiusCipher(IgC)
 * */
@Controller
public class AuthenticateController {

	
	@Autowired
	AuthenticateEndpoint endPoint;
	
	
	/**
	 * <p>Method to land the home page</p>
	 * */
	@RequestMapping("/")
	public String landingPage(){
		return AppConstant.INDEX;
	}
	
	/**
	 * <p>Method to get the request for the authentication provider details</p>
	 * */
	@RequestMapping("/request/auth")
	public String requestForAuthorize(HttpServletRequest httpServletRequest){
		Parameter parameter = new Parameter();
		parameter.setProvider(httpServletRequest.getParameter(AppConstant.PROVIDER));
		httpServletRequest.getSession(true).setAttribute(AppConstant.PROVIDER, parameter.getProvider());
		return AppConstant.REDIRECT+endPoint.sendRequest(endPoint.authenticate(parameter));
	}
	
	/**
	 * <p>Method to be used as a call back end point for all OAuth response </p>
	 * */
	@RequestMapping("/oauth/callback")
	public @ResponseBody String callBackDataReciever(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession(Boolean.TRUE);
		Parameter parameter = new Parameter();
		parameter.setProvider((String) httpSession.getAttribute(AppConstant.PROVIDER));
		httpSession.removeAttribute(AppConstant.PROVIDER);
		parameter.setCode(httpServletRequest.getParameter(AppConstant.GMAIL_CODE));
		return endPoint.collect(parameter).getDataMap().get(AppConstant.DATA);
	}
	
}
