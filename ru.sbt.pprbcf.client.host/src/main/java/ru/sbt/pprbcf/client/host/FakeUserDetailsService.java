package ru.sbt.pprbcf.client.host;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sbt-morozov-kv on 08.02.2017.
 */
@Component
public class FakeUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        switch (name) {
            case "admin":
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                authorities.add(new SimpleGrantedAuthority("USER"));
                return new User(name, "test", authorities);
            case "user":
                return new User(name, "test", Collections.singleton(new SimpleGrantedAuthority("USER")));
            default:
                return new User(name, "test", Collections.EMPTY_LIST);
        }
    }
}
