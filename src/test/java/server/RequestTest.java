package server;

import constants.HTTPMethod;
import constants.Path;
import org.junit.jupiter.api.Test;
import request.ClientRequest;
import request.RequestBuild;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RequestTest {

    private final HashMap<String, String> headers = testHeaders();

    public static HashMap<String, String> testHeaders() {
        HashMap<String, String> testHeaders = new HashMap<>();
        testHeaders.put("User-Agent", "PostmanRuntime/7.29.2");
        testHeaders.put("Accept", "*/*");
        testHeaders.put("Host", "0.0.0.0:5000");
        testHeaders.put("Accept-Encoding", "gzip, deflate, br");
        testHeaders.put("Connection", "keep-alive");

        return testHeaders;
    }

    @Test
    public void testRequestWithNoHeaderOrBody() {
        ClientRequest request = new RequestBuild()
                .setVerb(HTTPMethod.GET)
                .setPath(Path.SIMPLE_GET)
                .buildRequest();
        assertEquals(HTTPMethod.GET, request.verb);
        assertEquals(Path.SIMPLE_GET, request.path);
        assertNull(request.headers);
        assertNull(request.body);
    }

    @Test
    public void testRequestWithHeaderAndBody() {
        String b = "Hello World";
        ClientRequest request = new RequestBuild()
                .setVerb(HTTPMethod.GET)
                .setPath(Path.SIMPLE_GET)
                .setHeaders(headers)
                .setBody(b)
                .buildRequest();
        assertEquals(HTTPMethod.GET, request.verb);
        assertEquals(Path.SIMPLE_GET, request.path);
        assertEquals(testHeaders(), request.headers);
        assertEquals(b, request.body);

    }

}
