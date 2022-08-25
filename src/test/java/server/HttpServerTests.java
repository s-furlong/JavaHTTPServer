package server;


import mocks.MockInputOutWrapper;
import mocks.MockSocketWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static server.ServerLog.completeServerLog;


class HttpServerTests {
    MockInputOutWrapper mockInputOutputWrapper = new MockInputOutWrapper();
    MockSocketWrapper mockSocketWrapper = new MockSocketWrapper();
    ServerLog mockLog = new ServerLog(completeServerLog);

    @BeforeEach
    void init() {

        mockInputOutputWrapper.setReceivedMessage("hello");

    }

    @Test
    public void testRunAcceptsClient() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockSocketWrapper.getNumberOfCallsToAcceptClient());
    }

    @Test
    public void testRunCreatesInputStream() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockInputOutputWrapper.getNumberOfCallsToCreateInputStream());
    }

    @Test
    public void testRunCreatesOutputStream() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockInputOutputWrapper.getNumberOfCallsToCreateOutStream());
    }

    @Test
    public void testRunReceivesMessages() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);
        String s = "hello";

        assertEquals("hello", mockInputOutputWrapper.setReceivedMessage(s));
    }

    @Test
    public void testRunCallsReceivedMessage() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(2, mockInputOutputWrapper.getNumberOfCallsReceiveMessages());
    }

    @Test
    public void testRunCallsEchoMessages() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);
        ArrayList<String> s = new ArrayList<>(List.of("hello"));

        assertEquals(s, mockInputOutputWrapper.getEchoedMessages());
    }

    @Test
    public void testRunCallsCloseConnection() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockInputOutputWrapper.getNumberOfCallsToCloseConnection());
    }

    @Test
    public void testRunCallsCloseClientConnection() throws IOException {

        HttpServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockSocketWrapper.getNumberOfCallsToCloseClientConnection());
    }


}