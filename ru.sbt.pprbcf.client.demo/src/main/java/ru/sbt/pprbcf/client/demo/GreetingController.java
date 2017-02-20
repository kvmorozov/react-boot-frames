package ru.sbt.pprbcf.client.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbt.pprbcf.core.auth.model.UsernamePasswordPrincipal;
import ru.sbt.pprbcf.core.auth.repository.UsernamePasswordTokenRepository;

/**
 * Created by sbt-morozov-kv on 08.02.2017.
 */

@Controller
@ComponentScan(basePackages = {"ru.sbt.pprbcf.core.auth.config"})
public class GreetingController {

    @Autowired
    private UsernamePasswordTokenRepository tokenRepository;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        UsernamePasswordPrincipal principal = tokenRepository.findAll().get(0);
        model.addAttribute("name", name + "(" + principal.getToken().toString() + ")");
        SecurityContextHolder.getContext().setAuthentication(principal.getToken());

        return "greeting";
    }
}
