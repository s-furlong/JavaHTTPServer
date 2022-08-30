package server;


import mocks.MockInputOutWrapper;
import mocks.MockSocketWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wrappers.InputOutputWrappers;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static server.ServerLog.completeServerLog;


class HttpServerTests {
    MockInputOutWrapper mockInputOutputWrapper = new MockInputOutWrapper();
    private Socket clientSocket;
    private InputOutputWrappers inputOutputWrappers;
    MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(clientSocket, inputOutputWrappers);
    ServerLog mockLog = new ServerLog(completeServerLog);

    @BeforeEach
    void init() {

        mockInputOutputWrapper.setReceivedMessage("hello");
        mockSocketWrapper.setMessage("GET / HTTP/1.1\r\n\r\n");

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