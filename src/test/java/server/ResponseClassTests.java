package server;

import constants.HTTPMethod;
import constants.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import request.Request;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseClassTests {
    String simpleRawRequest = "GET /simple_get HTTP/1.1\r\n\r\n";
    String rawRequestWithBody = "GET /simple_get HTTP/1.1\r\nAllow: */*\r\nContent-Length: 5\r\n\r\nhello";

    @Test
    public void testParseFromRawRequestOnlyHead() {
        Request request = new Request(simpleRawRequest);
        request.parse();
        assertEquals(HTTPMethod.GET, request.verb);
        assertEquals(Path.SIMPLE_GET, request.path);
        assertEquals("HTTP/1.1", request.version);
    }

    @Test
    public void testParseFromRawRequestFullRequest() {
        Request request = new Request(rawRequestWithBody);
        request.parse();

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Length", "5");
        expectedHeaders.put("Allow", "*/*");

        assertEquals(HTTPMethod.GET, request.verb);
        assertEquals(Path.SIMPLE_GET, request.path);
        assertEquals("HTTP/1.1", request.version);
        assertEquals(expectedHeaders, request.headers);
    }

    @Test
    public void testStringToBeParsed() {
        Request request = new Request(simpleRawRequest);
        String testRequest = request.extractComponents();
        assertEquals("GET /simple_get HTTP/1.1\r\n\r\n", testRequest);
    }

    @Test
    public void testFullStringToBeParsed() {
        Request request = new Request(rawRequestWithBody);
        String testRequest = request.extractComponents();
        assertEquals("GET /simple_get HTTP/1.1\r\nAllow: */*\r\nContent-Length: 5\r\n\r\nhello", testRequest);
    }

    @Test
    public void testGettingVerb() {
        Request request = new Request(simpleRawRequest);
        HTTPMethod testVerb = request.getVerb();
        assertEquals(HTTPMethod.GET, testVerb);
    }

    @Test
    public void testGettingPath() {
        Request request = new Request(simpleRawRequest);
        Path testPath = request.getPath();
        assertEquals(Path.SIMPLE_GET, testPath);
    }

    @Test
    public void testGettingVersion() {
        Request request = new Request(simpleRawRequest);
        String testVersion = request.getVersion();
        assertEquals("HTTP/1.1", testVersion);
    }

    @Test
    public void testGettingHeaders() {
        Request request = new Request(rawRequestWithBody);

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Length", "5");
        expectedHeaders.put("Allow", "*/*");

        HashMap<String, String> testHeaders = request.getHeaders();

        assertEquals(expectedHeaders, testHeaders);
    }

    @Test
    public void testGettingBody() {
        Request request = new Request(rawRequestWithBody);
        String testBody = request.getBody();
        assertEquals("hello", testBody);
    }
}
