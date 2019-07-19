package com.afifi.usermng.repository;

import com.afifi.usermng.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
