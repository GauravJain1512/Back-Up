package com.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.helper.JwtHelper;
import com.jwt.services.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtHelper jwtHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
			
	String requestTokenHeader = request.getHeader("Authorization");
	String username = null;
	String jwtToken = null;
	
	if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
		jwtToken = requestTokenHeader.substring(7);
		try {
			username = this.jwtHelper.extractUsername(jwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}else {
		System.out.println("Token is not validated...");
	}

		
}
	filterChain.doFilter(request, response);
}
}


