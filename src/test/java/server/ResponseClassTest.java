package server;

import constants.HTTPMethod;
import constants.StatusCode;
import org.junit.jupiter.api.Test;
import response.Response;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResponseClassTest {
    private final Map<String, String> headers = Map.of("Content-Length", "5");
    private final List<HTTPMethod> verbs = List.of(HTTPMethod.HEAD, HTTPMethod.OPTIONS);
    private final String body = "hello";
    private final String noBody = "";

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

        var expected = "allow: HEAD, OPTIONS\r\n";
        var actual = response.headers;

        assertEquals(expected, actual);
    }

    @Test
    public void testNullHeaders() {
        assertThrows(IllegalArgumentException.class, () -> {
            Response response = new Response(StatusCode.OK, null, "body");
        });
    }

    @Test
    public void testAccessBody() {
        Response response = new Response(StatusCode.OK, headers, body);
        var expected = "hello";
        var actual = response.getBody();
        assertEquals(expected, actual);
    }

    @Test
    public void testContentLength() {
        Response response = new Response(StatusCode.OK);
        response.contentLengthHeader(body);
        response.parse();

        var expected = "Content-Length: 5\r\n";
        var actual = response.headers;

        assertEquals(expected, actual);
    }

    @Test
    public void testContentLengthNone() {
        Response response = new Response(StatusCode.OK);
        response.contentLengthHeader(noBody);
        response.parse();

        var expected = "Content-Length: 0\r\n";
        var actual = response.headers;

        assertEquals(expected, actual);
    }

    @Test
    public void testResponseHeaders() {
        Response response = new Response(StatusCode.OK);
        response.contentLengthHeader(noBody);
        response.parse();

        var expected = "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n";
        var actual = response.format();

        assertEquals(expected, actual);
    }
}
