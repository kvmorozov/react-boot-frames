package ru.sbt.pprbcf.client.host.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sbt.pprbcf.client.base.discovery.Discoverer;
import ru.sbt.pprbcf.client.base.impl.ClientInfoBase;
import ru.sbt.pprbcf.client.host.HostConfiguration;

import java.util.stream.Collectors;

/**
 * Created by sbt-morozov-kv on 15.02.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HostConfiguration.class})
public class DiscovererTest {

    @Test
    public void listenerTest() throws Exception {
        Discoverer discoverer = new Discoverer();

        while(true) {
            try {
                Thread.sleep(1000);
                System.out.println(String.join(", ", discoverer.getClients().stream().map(ClientInfoBase::getAppName).collect(Collectors.toList())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
