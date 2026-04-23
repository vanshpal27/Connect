package com.project.connect.api_gateway.Filters;

import com.project.connect.api_gateway.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
// Through the filter we achievce the authentication and authorization in micro-service
@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

  public  AuthenticationGatewayFilterFactory()
  {
      super(Config.class);
  }
    @Autowired
    private JwtService jwtService;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->
        {
            String authenticationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (authenticationHeader == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return chain.filter(exchange);
            }

            String token = authenticationHeader.split("Bearer ")[1];


            Long userId = jwtService.userIdFromToken(token);
            if(userId==null)
            {
                exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            }
              ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(r->r.header("X-User-Id", String.valueOf(userId)))
                    .build();
            return chain.filter(modifiedExchange);
        };
    }

   public  static class Config
    {

    }
}
