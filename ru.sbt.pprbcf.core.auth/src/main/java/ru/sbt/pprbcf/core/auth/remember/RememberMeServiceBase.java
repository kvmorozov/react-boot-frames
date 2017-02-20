package ru.sbt.pprbcf.core.auth.remember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Component;
import ru.sbt.pprbcf.core.auth.model.UsernamePasswordPrincipal;
import ru.sbt.pprbcf.core.auth.repository.UsernamePasswordTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sbt-morozov-kv on 09.02.2017.
 */

@Component
@ComponentScan(basePackages = {"ru.sbt.pprbcf.core.auth.config"})
public class RememberMeServiceBase implements RememberMeServices {

    @Autowired
    private UsernamePasswordTokenRepository tokenRepository;

    @Override
    public Authentication autoLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }

    @Override
    public void loginFail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public void loginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken)
            tokenRepository.save(new UsernamePasswordPrincipal((UsernamePasswordAuthenticationToken) authentication));
    }
}
