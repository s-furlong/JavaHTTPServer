package server;

import org.junit.jupiter.api.Test;
import request.Request;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {
    @Test
    public void testParsesCorrectly() {
        var request = new Request("GET / HTTP/1.1\r\n\r\n");
        request.parse();
        assertEquals("GET", request.verb);
        assertEquals("/", request.path);
        assertEquals("HTTP/1.1", request.version);
        assertEquals("", request.body);
    }

    @Test
    public void testParseBody() {
        var request = new Request("GET / HTTP/1.1\r\n\r\nhello World");
        request.parse();
        assertEquals("GET", request.verb);
        assertEquals("/", request.path);
        assertEquals("HTTP/1.1", request.version);
        assertEquals("hello World", request.body);
    }

    @Test
    public void testParseEmptyHeader() {
        var request = new Request("GET / HTTP/1.1\r\n\r\nhello World");
        request.parse();
        assertEquals("GET", request.verb);
        assertEquals("/", request.path);
        assertEquals("HTTP/1.1", request.version);
        assertEquals("hello World", request.body);
        assertEquals(new HashMap<>(), request.headers);
    }

    @Test
    public void testParseHeader() {
        var request = new Request("GET / HTTP/1.1\r\ncontent-type: text/html\r\n\r\nhello World");
        request.parse();
        var expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("content-type", "text/html");
        assertEquals("GET", request.verb);
        assertEquals("/", request.path);
        assertEquals("HTTP/1.1", request.version);
        assertEquals("hello World", request.body);
        assertEquals(expectedHeaders, request.headers);
    }

    @Test
    public void testEmptyBody() {
        var request = new Request("GET / HTTP/1.1\r\ncontent-type: text/html\r\n\r\n");
        request.parse();
        assertEquals("", request.body);

    }
}
