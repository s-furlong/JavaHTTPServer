package server;

import com.sun.jdi.connect.Connector;
import constants.HTTPMethod;
import constants.StatusCode;
import org.junit.jupiter.api.Test;
import response.Response;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResponseClassTest {
    private final Map<String, String> headers = Map.of("Content-Length", "5");
    private List<HTTPMethod> verbs = List.of(HTTPMethod.HEAD, HTTPMethod.OPTIONS);

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

        var expected = "HTTP/1.1 200 OK\r\nContent-Length: 5\r\n\r\n";
        var actual = response.format();
        assertEquals(expected, actual);

    }

    @Test
    public void testCreateAllowHeader() {
        Response response = new Response(StatusCode.OK);
        response.addAllowHeader(verbs);
        response.parse();

        var expected = "Allow: HEAD, OPTIONS\r\n";
        var actual = response.headers;

        assertEquals(expected, actual);
    }

    @Test
    public void testNullHeaders() {
        assertThrows(IllegalArgumentException.class, () -> {
            Response response = new Response(StatusCode.OK, null);
        });


    }
}
