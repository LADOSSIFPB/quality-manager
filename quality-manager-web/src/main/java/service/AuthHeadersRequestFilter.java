package service;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class AuthHeadersRequestFilter implements ClientRequestFilter {

    private String authToken;

    public AuthHeadersRequestFilter() {
        this.authToken = "auth-token";
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add("Authorization", authToken);
    }
}