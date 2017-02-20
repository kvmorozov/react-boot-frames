package ru.sbt.pprbcf.client.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.pprbcf.client.base.discovery.DiscoveryClient;

/**
 * Created by sbt-morozov-kv on 15.02.2017.
 */

@Configuration
@ComponentScan(basePackages = {"ru.sbt.pprbcf.client.base.discovery"})
public class ClientConfiguration {

    @Autowired
    private DiscoveryClient discoveryClient;

    public DiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }
}
