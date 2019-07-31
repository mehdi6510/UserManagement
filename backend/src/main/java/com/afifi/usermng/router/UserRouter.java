package com.afifi.usermng.router;

import com.afifi.usermng.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler handler) {
        return RouterFunctions
                .route(GET("/usermanagement/api/users/{id}").and(accept(APPLICATION_JSON)), handler::getUserById)
                .andRoute(GET("/usermanagement/api/users").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::getAllUsers)
                .andRoute(POST("/usermanagement/api/users").and(accept(APPLICATION_JSON)), handler::createUser)
                .andRoute(PUT("/usermanagement/api/users").and(accept(APPLICATION_JSON)), handler::updateUser)
                .andRoute(DELETE("/usermanagement/api/users/{id}").and(accept(APPLICATION_JSON)), handler::deleteUser);
    }
}
