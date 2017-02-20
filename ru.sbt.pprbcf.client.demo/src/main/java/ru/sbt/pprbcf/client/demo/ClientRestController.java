package ru.sbt.pprbcf.client.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.pprbcf.client.base.IRestClient;
import ru.sbt.pprbcf.client.base.PluggableFeature;
import ru.sbt.pprbcf.client.base.impl.ClientInfoBase;
import ru.sbt.pprbcf.client.demo.sample.SampleData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by sbt-morozov-kv on 13.02.2017.
 */

@RestController
public class ClientRestController implements IRestClient {

    @Override
    @RequestMapping("init")
    public ClientInfoBase init() {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = ((User) token.getPrincipal()).getAuthorities();
        ClientInfoBase clientInfo = new ClientInfoBase("Demo Client", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(auth -> {
                    PluggableFeature feature = new PluggableFeature(auth,
                            PluggableFeature.FeatureType.BUTTON,
                            getRenderComponentByAuth(auth),
                            getRequireScriptByAuth(auth));

                    switch (auth) {
                        case "ADMIN":
                            feature.add(linkTo(methodOn(ClientRestController.class).adminContent()).withRel("ADMIN"));
                            break;
                        case "USER":
                        default:
                            feature.add(linkTo(methodOn(ClientRestController.class).userContent()).withRel("USER"));
                    }

                    return feature;
                }).collect(Collectors.toList()));

        clientInfo.setCurrentUser(token.getName());

        return clientInfo;
    }

    @RequestMapping("/content/admin")
    public List<SampleData> adminContent() {
        List<SampleData> data = new ArrayList<>();
        data.add(new SampleData(1, "admin1", "mega admin1"));
        data.add(new SampleData(2, "admin2", "mega admin2"));
        data.add(new SampleData(3, "admin3", "mega admin3"));

        return data;
    }

    @RequestMapping("/content/user")
    public List<SampleData> userContent() {
        List<SampleData> data = new ArrayList<>();
        data.add(new SampleData(1, "user1", "humble user1"));
        data.add(new SampleData(2, "user2", "humble user2"));
        data.add(new SampleData(3, "user3", "humble user3"));

        return data;
    }

    private String getRenderComponentByAuth(String authority) {
        switch (authority) {
            case "ADMIN":
                return "ContentDemo1";
            case "USER":
            default:
                return "ContentDemo2";
        }
    }

    private String getRequireScriptByAuth(String authority) {
        switch (authority) {
            case "ADMIN":
                return "contentDemo1";
            case "USER":
            default:
                return "contentDemo2";
        }
    }
}
