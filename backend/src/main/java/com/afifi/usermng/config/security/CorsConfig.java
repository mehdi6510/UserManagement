package com.afifi.usermng.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFlux
public class CorsConfig implements WebFluxConfigurer {

    private static final String ALLOWED_MAPPING = "/**";
    private static final String ALLOWED_ORIGIN = "http://localhost:4200/**";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS";
    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, " +
            "credential, X-XSRF-TOKEN , Cache-Control, WWW-Authenticate";
    private static final String EXPOSE_HEADERS = "xsrf-token";
    private static final String MAX_AGE = "3600";

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();

            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = ctx.getResponse();

                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                headers.add("Access-Control-Expose-Headers", EXPOSE_HEADERS);
                headers.add("Access-Control-Max-Age", MAX_AGE);

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }

            return chain.filter(ctx);
        };
    }

//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping(ALLOWED_MAPPING)
//                .allowedOrigins(ALLOWED_ORIGIN)
//                .allowedMethods(ALLOWED_METHODS)
//                .allowedHeaders(ALLOWED_HEADERS)
//                .exposedHeaders(EXPOSE_HEADERS)
//                .maxAge(MAX_AGE);
//    }

}
