package ru.sbt.pprbcf.client.host;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sbt-morozov-kv on 08.02.2017.
 */

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", SecurityContextHolder.getContext().getAuthentication().getName());
        return "greeting";
    }
}
