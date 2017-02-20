package ru.sbt.pprbcf.core.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sbt.pprbcf.core.auth.model.UsernamePasswordPrincipal;

import java.awt.print.Book;

/**
 * Created by sbt-morozov-kv on 09.02.2017.
 */
public interface UsernamePasswordTokenRepository extends MongoRepository<UsernamePasswordPrincipal, String> {
}
