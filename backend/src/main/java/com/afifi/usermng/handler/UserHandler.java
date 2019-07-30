package com.afifi.usermng.handler;

import com.afifi.usermng.model.User;
import com.afifi.usermng.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        Long id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findById(id), User.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findAll(), User.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<User> user = request.bodyToMono(User.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(user.flatMap(userService::save), User.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.deleteById(id), Void.class);
    }

}
