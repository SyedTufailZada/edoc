package com.example.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration 
public class CustomSuccesshandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Set<String> roles= AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(roles.contains("ROLE_DOCTOR")) {
			response.sendRedirect("/doctor/home");
		}
		else if(roles.contains("ROLE_PATIENT")) {
			response.sendRedirect("/patient/home");
		}
		else if(roles.contains("ROLE_NURSE")) {
			response.sendRedirect("/nurse/home");
		}
		else if(roles.contains("ROLE_PHYSIOTHERAPIST")) {
			response.sendRedirect("/physio/home");
		}
	}

}
