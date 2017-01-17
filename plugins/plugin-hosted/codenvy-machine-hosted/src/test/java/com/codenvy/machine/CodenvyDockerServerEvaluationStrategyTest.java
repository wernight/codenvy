/*
 *  [2012] - [2017] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.machine;

import org.eclipse.che.plugin.docker.client.json.ContainerInfo;
import org.eclipse.che.plugin.docker.client.json.NetworkSettings;
import org.eclipse.che.plugin.docker.client.json.PortBinding;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Alexander Garagatyi
 */
@Listeners(MockitoTestNGListener.class)
public class CodenvyDockerServerEvaluationStrategyTest {
    private static final String HOST = "test.host.com";

    private static final String CHE_DOCKER_IP_EXTERNAL   = "my-external-host.org";


    @Mock
    private ContainerInfo   containerInfo;
    @Mock
    private NetworkSettings networkSettings;


    @BeforeMethod
    public void setUp() throws Exception {
        when(containerInfo.getNetworkSettings()).thenReturn(networkSettings);
        when(networkSettings.getPorts()).thenReturn(emptyMap());
    }

    @Test
    public void shouldReturnEmptyMapOnRetrievalOfInternalAddressesIfNoPortExposed() throws Exception {
        CodenvyDockerServerEvaluationStrategy strategy = new CodenvyDockerServerEvaluationStrategy(null);
        assertTrue(strategy.getInternalAddressesAndPorts(containerInfo, HOST).isEmpty());
    }

    @Test
    public void shouldReturnEmptyMapOnRetrievalOfExternalAddressesIfNoPortExposed() throws Exception {
        CodenvyDockerServerEvaluationStrategy strategy = new CodenvyDockerServerEvaluationStrategy(null);
        assertTrue(strategy.getExternalAddressesAndPorts(containerInfo, HOST).isEmpty());
    }

    @Test
    public void shouldUseProvidedInternalHostOnRetrievalOfInternalAddresses() throws Exception {
        CodenvyDockerServerEvaluationStrategy strategy = new CodenvyDockerServerEvaluationStrategy(null);
        Map<String, List<PortBinding>> exposedPorts = new HashMap<>();
        exposedPorts.put("8080/tcp", singletonList(new PortBinding().withHostIp("127.0.0.1").withHostPort("32789")));
        exposedPorts.put("9090/udp", singletonList(new PortBinding().withHostIp("192.168.0.1").withHostPort("20000")));
        when(networkSettings.getPorts()).thenReturn(exposedPorts);

        Map<String, String> internalAddressesAndPorts =
                strategy.getInternalAddressesAndPorts(containerInfo, HOST);

        for (Map.Entry<String, List<PortBinding>> entry : exposedPorts.entrySet()) {
            assertEquals(internalAddressesAndPorts.get(entry.getKey()),
                         HOST + ":" + entry.getValue().get(0).getHostPort());
        }
    }

    @Test
    public void shouldUseProvidedInternalHostOnRetrievalOfExternalAddresses() throws Exception {
        CodenvyDockerServerEvaluationStrategy strategy = new CodenvyDockerServerEvaluationStrategy(null);
        Map<String, List<PortBinding>> exposedPorts = new HashMap<>();
        exposedPorts.put("8080/tcp", singletonList(new PortBinding().withHostIp("127.0.0.1").withHostPort("32789")));
        exposedPorts.put("9090/udp", singletonList(new PortBinding().withHostIp("192.168.0.1").withHostPort("20000")));
        when(networkSettings.getPorts()).thenReturn(exposedPorts);

        Map<String, String> externalAddressesAndPorts =
                strategy.getExternalAddressesAndPorts(containerInfo, HOST);

        for (Map.Entry<String, List<PortBinding>> entry : exposedPorts.entrySet()) {
            assertEquals(externalAddressesAndPorts.get(entry.getKey()),
                         HOST + ":" + entry.getValue().get(0).getHostPort());
        }
    }


    /**
     * Test: If che.docker.ip.external is not null, that should take precedence for external address.
     * @throws Exception
     */
    @Test
    public void shouldUseExternalIpPropertyOnRetrievalOfExternalAddresses() throws Exception {
        // given
        CodenvyDockerServerEvaluationStrategy strategy = new CodenvyDockerServerEvaluationStrategy(CHE_DOCKER_IP_EXTERNAL);

        Map<String, List<PortBinding>> exposedPorts = new HashMap<>();
        exposedPorts.put("8080/tcp", singletonList(new PortBinding().withHostIp("127.0.0.1").withHostPort("32789")));
        exposedPorts.put("9090/udp", singletonList(new PortBinding().withHostIp("192.168.0.1").withHostPort("20000")));
        when(networkSettings.getPorts()).thenReturn(exposedPorts);

        Map<String, String> externalAddressesAndPorts =
                strategy.getExternalAddressesAndPorts(containerInfo, HOST);

        for (Map.Entry<String, List<PortBinding>> entry : exposedPorts.entrySet()) {
            assertEquals(externalAddressesAndPorts.get(entry.getKey()),
                         CHE_DOCKER_IP_EXTERNAL + ":" + entry.getValue().get(0).getHostPort());
        }
    }
}
