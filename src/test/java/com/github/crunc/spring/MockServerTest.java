package com.github.crunc.spring;

import org.junit.After;
import org.junit.Before;
import org.mockserver.client.server.ForwardChainExpectation;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.socket.PortFactory;

public class MockServerTest {

    private String host;
    private int port;
    private ClientAndServer clientAndServer;

    @Before
    public void startMockServer() {
        host = "localhost";
        port = PortFactory.findFreePort();
        clientAndServer = ClientAndServer.startClientAndServer(port);
    }

    @After
    public void stopMockServer() {
        if (clientAndServer != null) {
            clientAndServer.stop();
        }
    }

    protected String host() {
        return host;
    }

    protected int port() {
        return port;
    }

    protected ForwardChainExpectation when(final HttpRequest request) {
        return clientAndServer.when(request);
    }
}
