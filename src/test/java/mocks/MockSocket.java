package mocks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MockSocket extends Socket {
    private final String message;

    public MockSocket(String message) {
        this.message = message;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        var stream = new ByteArrayInputStream(message.getBytes());
        return stream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return System.out;
    }
}
