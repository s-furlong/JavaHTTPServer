package server;

import Interfaces.InputOutputInterfaces;
import constants.HTTPMethod;
import constants.Path;
import mocks.MockInputOutWrapper;
import org.junit.jupiter.api.Test;
import request.Request;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RequestClassTests {
    String simpleRawRequest = "GET /simple_get HTTP/1.1\r\n\r\n";
    String rawRequestWithBody = "GET /simple_get HTTP/1.1\r\nAllow: */*\r\nContent-Length: 5\r\n\r\nstare";
    InputOutputInterfaces inputOutputMethods = new MockInputOutWrapper();

    @Test
    public void testParseFromRawRequestOnlyHead() {
        Request request = new Request(simpleRawRequest, inputOutputMethods);
        request.parse();
        assertEquals(HTTPMethod.GET, request.verb);
        assertEquals(Path.SIMPLE_GET, request.path);
        assertEquals("HTTP/1.1", request.version);
    }

    @Test
    public void testParseFromRawRequestFullRequest() {
        Request request = new Request(rawRequestWithBody, inputOutputMethods);
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
        Request request = new Request(simpleRawRequest, inputOutputMethods);
        String testRequest = request.extractComponents();
        assertEquals("GET /simple_get HTTP/1.1\r\n\r\n", testRequest);
    }

    @Test
    public void testFullStringToBeParsed() {
        Request request = new Request(rawRequestWithBody, inputOutputMethods);
        String testRequest = request.extractComponents();
        assertEquals("GET /simple_get HTTP/1.1\r\nAllow: */*\r\nContent-Length: 5\r\n\r\nstare", testRequest);
    }

    @Test
    public void testGettingVerb() {
        Request request = new Request(simpleRawRequest, inputOutputMethods);
        HTTPMethod testVerb = request.getVerb();
        assertEquals(HTTPMethod.GET, testVerb);
    }

    @Test
    public void testGettingPath() {
        Request request = new Request(simpleRawRequest, inputOutputMethods);
        Path testPath = request.getPath();
        assertEquals(Path.SIMPLE_GET, testPath);
    }

    @Test
    public void testGettingVersion() {
        Request request = new Request(simpleRawRequest, inputOutputMethods);
        String testVersion = request.getVersion();
        assertEquals("HTTP/1.1", testVersion);
    }

    @Test
    public void testFormatHeaders() {
        Request request = new Request(rawRequestWithBody, inputOutputMethods);

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Length", "5");
        expectedHeaders.put("Allow", "*/*");

        HashMap<String, String> testHeaders = request.formatHeaders();

        assertEquals(expectedHeaders, testHeaders);
    }

    @Test
    public void testGettingBody() throws IOException {
        String s = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length: 11\r\n\r\n";
        var inputOutputMethods = new MockInputOutWrapper();
        inputOutputMethods.setReceivedMessage("Hello world");
        Request request = new Request(s, inputOutputMethods);
        request.parse();
        String testBody = request.getBody();
        assertEquals("Hello world", testBody);
    }


}
