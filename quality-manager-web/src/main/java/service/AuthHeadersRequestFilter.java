package service;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import br.edu.ifpb.qmanager.util.StringUtil;

public class AuthHeadersRequestFilter implements ClientRequestFilter {

	private String id;
    private String authorizationKey;
    
    public AuthHeadersRequestFilter(String id, String authorizationKey) {
    	this.id = id;
        this.authorizationKey = authorizationKey;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add("Authorization", 
        		StringUtil.criptografarBase64(this.id + ":" 
        				+ this.authorizationKey));
    }
}