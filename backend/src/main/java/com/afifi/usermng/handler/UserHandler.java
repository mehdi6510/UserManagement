package com.afifi.usermng.handler;

import com.afifi.usermng.model.User;
import com.afifi.usermng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        Long userId = Long.parseLong(request.pathVariable("id"));
        logger.info("Receive request to get user by id : {}", userId);

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findById(userId), User.class);
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        logger.info("Receive request to get all users");

        return ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(userService.findAll(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        final Mono<User> user = request.bodyToMono(User.class);
        logger.info("Receive request to save user : {}", user);

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(user.flatMap(userService::save), User.class));
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {
        final Mono<User> user = request.bodyToMono(User.class);
        logger.info("Receive request to update user with new detail: {}", user);

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(user.flatMap(userService::update), User.class));
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        Long userId = Long.parseLong(request.pathVariable("id"));
        logger.info("Receive request to delete user by id : {}", userId);

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.deleteById(userId), Void.class);
    }

}
