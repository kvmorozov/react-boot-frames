package ru.sbt.pprbcf.core.auth.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Date;

/**
 * Created by sbt-morozov-kv on 09.02.2017.
 */

@Document(collection = "token")
public class UsernamePasswordPrincipal {

    private UsernamePasswordAuthenticationToken token;

    @Indexed(expireAfterSeconds = 600)
    private Date loginDate;

    public UsernamePasswordPrincipal(UsernamePasswordAuthenticationToken token) {
        this.token = token;
        this.loginDate = new Date();
    }

    public UsernamePasswordAuthenticationToken getToken() {
        return token;
    }

    public Date getLoginDate() {
        return loginDate;
    }
}
