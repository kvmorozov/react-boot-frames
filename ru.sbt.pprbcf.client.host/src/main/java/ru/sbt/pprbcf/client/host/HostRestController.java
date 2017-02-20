package ru.sbt.pprbcf.client.host;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.pprbcf.client.base.discovery.Discoverer;
import ru.sbt.pprbcf.client.base.impl.ClientInfoBase;

import java.util.List;

/**
 * Created by sbt-morozov-kv on 15.02.2017.
 */

@RestController
public class HostRestController {

    private static final Discoverer discoverer = new Discoverer();

    @RequestMapping("/clients/{login}")
    public List<ClientInfoBase> clients(@PathVariable String login) throws Exception {
        return discoverer.getClients();
    }
}
