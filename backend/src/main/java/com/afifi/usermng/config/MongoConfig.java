package com.afifi.usermng.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@Configuration
@EnableMongoRepositories(basePackages = "com.afifi.usermng.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    private static final String MONGO_DB_URL = "localhost";
    private static final Integer MONGO_DB_PORT = 27017;
    private static final String MONGO_DB_NAME = "embeded_db";

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(MONGO_DB_URL, MONGO_DB_PORT);
    }

    @Override
    protected String getDatabaseName() {
        return MONGO_DB_NAME;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }
}
