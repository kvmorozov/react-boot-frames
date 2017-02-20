package ru.sbt.pprbcf.core.auth.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.sbt.pprbcf.core.auth.converters.PrincipalConverter;

import java.util.Collections;

/**
 * Created by sbt-morozov-kv on 28.11.2016.
 */

@Configuration
@EnableMongoRepositories(basePackages = "ru.sbt.pprbcf.core.auth.repository")
public class MongoConfiguration extends AbstractMongoConfiguration {

    public static final String AUTH_MONGO_DB_NAME = "AUTH";

    @Override
    protected String getDatabaseName() {
        return AUTH_MONGO_DB_NAME;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }

    @Override
    protected String getMappingBasePackage() {
        return "ru.sbt.pprbcf.core.auth.model";
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Collections.singletonList(new PrincipalConverter()));
    }
}
