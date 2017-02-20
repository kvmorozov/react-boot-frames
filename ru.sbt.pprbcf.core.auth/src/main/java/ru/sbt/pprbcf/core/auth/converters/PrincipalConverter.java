package ru.sbt.pprbcf.core.auth.converters;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.sbt.pprbcf.core.auth.model.UsernamePasswordPrincipal;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sbt-morozov-kv on 13.02.2017.
 */
public class PrincipalConverter implements Converter<DBObject, UsernamePasswordPrincipal> {

    @Override
    public UsernamePasswordPrincipal convert(DBObject dbObject) {
        DBObject _token = (DBObject) dbObject.get("token");
        DBObject _principal = (DBObject) _token.get("principal");
        BasicDBList _authorities = (BasicDBList) _principal.get("authorities");

        List<GrantedAuthority> authorities = _authorities.stream()
                .map(o -> new SimpleGrantedAuthority((String)((DBObject) o).get("role")))
                .collect(Collectors.toList());

        User principal = new User((String) _principal.get("username"), " ", authorities);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, null);

        return new UsernamePasswordPrincipal(token);
    }
}
