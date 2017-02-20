package ru.sbt.pprbcf.client.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sbt.pprbcf.client.base.discovery.DiscoveryClient;
import ru.sbt.pprbcf.client.demo.ClientConfiguration;

import static org.springframework.util.Assert.isTrue;

/**
 * Created by sbt-morozov-kv on 15.02.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClientConfiguration.class})
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void registerTest() throws Exception {
        isTrue(discoveryClient.isReady());
        discoveryClient.register("test_1", "test_2".getBytes());
    }
}
