package ru.sbt.pprbcf.client.base.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Value;
import ru.sbt.pprbcf.client.base.impl.ClientInfoBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sbt-morozov-kv on 15.02.2017.
 */
public class Discoverer {

    public static final String REGISTRATION_ROOT = "/clients";
    public static final String DEFAULT_HOST_PORT = "localhost:2181";

    private final CuratorFramework zooClient;

    public Discoverer(@Value("#{systemProperties['zookeeper.hostport']}") String hostPort) {
        zooClient = CuratorFrameworkFactory.newClient(hostPort, new ExponentialBackoffRetry(1000, 3));
        zooClient.start();
    }

    public Discoverer() {
        this(DEFAULT_HOST_PORT);
    }

    public List<ClientInfoBase> getClients() throws Exception {
        try {
            List<String> clientNames = zooClient.getChildren().watched().forPath(REGISTRATION_ROOT);

            return clientNames.stream().map(clientName -> {
                try {
                    return new ClientInfoBase(clientName, new String(zooClient.getData().forPath(REGISTRATION_ROOT + "/" + clientName)));
                } catch (Exception e) {
                    return null;
                }
            }).collect(Collectors.toList());
        }
        catch(Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
}
