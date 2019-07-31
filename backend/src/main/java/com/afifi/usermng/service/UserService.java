package com.afifi.usermng.service;

import com.afifi.usermng.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {

    Mono<User> findById(Long userId);

    Flux<User> findAll();

    Mono<User> save(User user);

    Mono<User> update(User updatingUser);

    Mono<Void> deleteById(Long userId);

}
