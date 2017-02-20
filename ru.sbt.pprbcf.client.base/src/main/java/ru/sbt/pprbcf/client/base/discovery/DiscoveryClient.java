package ru.sbt.pprbcf.client.base.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static ru.sbt.pprbcf.client.base.discovery.Discoverer.DEFAULT_HOST_PORT;
import static ru.sbt.pprbcf.client.base.discovery.Discoverer.REGISTRATION_ROOT;

/**
 * Created by sbt-morozov-kv on 15.02.2017.
 */

@Component
public class DiscoveryClient {

    private final CuratorFramework client;
    private boolean ready = false;

    public DiscoveryClient(@Value("#{systemProperties['zookeeper.hostport']}") String hostPort) {
        client = CuratorFrameworkFactory.newClient(hostPort, new ExponentialBackoffRetry(10, 3));
        client.start();
        ready = true;
    }

    public DiscoveryClient() {
        this(DEFAULT_HOST_PORT);
    }

    public void register(String appName, byte[] regInfo) throws Exception {
        client.create().orSetData().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(REGISTRATION_ROOT + "/" + appName, regInfo);
    }

    public boolean isReady() {
        return ready;
    }
}
