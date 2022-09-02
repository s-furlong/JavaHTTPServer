package server;

import constants.StatusCode;
import org.junit.jupiter.api.Test;
import response.Response;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseClassTest {
    private final Map<String, String> headers = Map.of("Content-Length", "5");

    @Test
    public void TestOKResponse() {
        Response response = new Response(StatusCode.OK, headers);
        response.parse();
        assertEquals("HTTP/1.1", response.version);
        assertEquals("200 OK", response.status);
        assertEquals("Content-Length: 5\r\n", response.headers);
    }

    @Test
    public void testAccessDefaultVersion() {
        Response response = new Response(StatusCode.OK);
        var expected = "HTTP/1.1";
        var actual = response.getVersion();

        assertEquals(expected, actual);
    }

    @Test
    public void testAccessStatusCode() {
        Response response = new Response(StatusCode.OK);
        var expected = "200 OK";
        var actual = response.getStatus();

        assertEquals(expected, actual);
    }


    @Test
    public void testAccessHeaders() {
        Response response = new Response(StatusCode.OK, headers);
        response.parse();

        var expected = "Content-Length: 5\r\n";
        var actual = response.getHeaders();

        assertEquals(expected, actual);
    }

    @Test
    public void testFormattedResponse() {
        Response response = new Response(StatusCode.OK, headers);
        response.parse();

        var expected = "HTTP/1.1 200 OK\r\n\"Content-Length: 5\r\n\r\n";
        var actual = response.format();

    }
}
