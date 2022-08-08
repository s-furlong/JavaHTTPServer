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


class EchoServerTests {
    MockInputOutWrapper mockInputOutputWrapper = new MockInputOutWrapper();
    MockSocketWrapper mockSocketWrapper = new MockSocketWrapper();
    ServerLog mockLog = new ServerLog(completeServerLog);

    @BeforeEach
    void init() {

        mockInputOutputWrapper.setReceivedMessage("hello");
        mockInputOutputWrapper.setReceivedMessage("off");

    }
    @Test
    public void testRunAcceptsClient() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockSocketWrapper.getNumberOfCallsToAcceptClient());
    }

    @Test
    public void testRunCreatesInputStream() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockInputOutputWrapper.getNumberOfCallsToCreateInputStream());
    }

    @Test
    public void testRunCreatesOutputStream() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockInputOutputWrapper.getNumberOfCallsToCreateOutStream());
    }

    @Test
    public void testRunReceivesMessages() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);
        String s = "hello";

        assertEquals("hello", mockInputOutputWrapper.setReceivedMessage(s));
    }

    @Test
    public void testRunCallsReceivedMessage() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(2, mockInputOutputWrapper.getNumberOfCallsReceiveMessages());
    }

    @Test
    public void testRunCallsEchoMessages() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);
        ArrayList<String> s = new ArrayList<>(List.of("hello"));

        assertEquals(s, mockInputOutputWrapper.getEchoedMessages());
    }

    @Test
    public void testRunCallsCloseConnection() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockInputOutputWrapper.getNumberOfCallsToCloseConnection());
    }
    @Test
    public void testRunCallsCloseClientConnection() throws IOException {

        EchoServer.run(mockInputOutputWrapper, mockSocketWrapper, mockLog);

        assertEquals(1, mockSocketWrapper.getNumberOfCallsToCloseClientConnection());
    }


}