package com.application.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class jwdTokenGenerator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authentication=context.getAuthentication();
		
		if(authentication!=null) {
			Date isuDate=new Date();
			Date ExpDate=new Date();
			
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(ExpDate);
			calendar.add(calendar.HOUR, 24);
			
			SecretKey key=Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			
			String jwt=Jwts.builder().setIssuer("MasaiTruckApp").setSubject("JWT Token")
					.claim("username",authentication.getName())
					.claim("authorities", authentication.getAuthorities())
					.setIssuedAt(isuDate)
					.setExpiration(calendar.getTime())
					.signWith(key).compact();
			
			System.out.println(jwt);
			
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
		}
		filterChain.doFilter(request, response);
	}
	
	
	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        
    	Set<String> authoritiesSet = new HashSet<>();
        
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
        return !request.getServletPath().equals("/signIn");	
	}

}
