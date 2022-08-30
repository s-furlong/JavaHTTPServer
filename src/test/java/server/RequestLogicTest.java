package server;

import Logic.RequestLogic;
import mocks.MockInputOutWrapper;
import mocks.MockSocketWrapper;
import wrappers.InputOutputWrappers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestLogicTest {
    RequestLogic logic;

    MockInputOutWrapper mockInputOutputWrapper = new MockInputOutWrapper();
    private Socket clientSocket;
    private InputOutputWrappers inputOutputWrappers;
    MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(clientSocket, inputOutputWrappers);

}
