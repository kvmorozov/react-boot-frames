package ru.sbt.pprbcf.client.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sbt.pprbcf.client.base.discovery.DiscoveryClient;

/**
 * Created by sbt-morozov-kv on 08.02.2017.
 */

@SpringBootApplication
public class ClientDemoApp {

    public static void main(String[] args) {
        try {
            (new DiscoveryClient()).register("Demo client", "http://localhost:9001/greeting".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(ClientDemoApp.class, args);
    }
}
