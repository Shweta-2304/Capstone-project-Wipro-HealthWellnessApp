package com.health.auth.dto;



public class JwtResponse {
    private String token;
    
    public JwtResponse() {
    	
    }
    
    public JwtResponse(String token) {
    	this.setToken(token);
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
