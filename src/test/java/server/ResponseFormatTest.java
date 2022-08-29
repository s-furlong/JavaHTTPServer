package server;

import org.junit.jupiter.api.Test;
import response.ResponseFormat;
import response.ServerResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseFormatTest {
    public static ServerResponse fullRespsonse() {
        String serverLine = "HTTP/1.1 200 OK";
        List<String> headers = List.of("Content-Length: 5");
        byte[] body = "Hello".getBytes(StandardCharsets.UTF_8);
        return new ServerResponse(serverLine, headers, body);
    }

    public static ServerResponse simpleRespsonse() {
        String serverLine = "HTTP/1.1 200 OK";
        return new ServerResponse(serverLine, null, null);
    }

    public static ServerResponse noBodyRespsonse() {
        String serverLine = "HTTP/1.1 200 OK";
        List<String> headers = List.of("Content-Length: 0");
        return new ServerResponse(serverLine, headers, null);
    }

    @Test
    public void formatResonseWithLineHeaderBody() throws IOException {
        ServerResponse response = fullRespsonse();

        byte[] expected = "HTTP/1.1 200 OK\r\nContent-Length: 5\r\n\r\nHello".getBytes();
        byte[] actual = ResponseFormat.toBytes(response);

        assertEquals(Arrays.toString(expected), Arrays.toString(actual));

    }

    @Test
    public void formatResonseWithOnlyLine() throws IOException {
        ServerResponse response = simpleRespsonse();

        byte[] expected = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
        byte[] actual = ResponseFormat.toBytes(response);

        assertEquals(Arrays.toString(expected), Arrays.toString(actual));

    }

    @Test
    public void formatResonseWithNoBody() throws IOException {
        ServerResponse response = noBodyRespsonse();

        byte[] expected = "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n".getBytes();
        byte[] actual = ResponseFormat.toBytes(response);

        assertEquals(Arrays.toString(expected), Arrays.toString(actual));

    }
}
