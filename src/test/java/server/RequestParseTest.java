package server;

import constants.HTTPMethod;
import constants.Path;
import org.junit.jupiter.api.Test;
import request.ClientRequest;
import request.RequestBuild;
import request.RequestParse;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RequestParseTest {
    String testRequest = "GET /simple_get HTTP/1.1\r\nAllow: */*\r\nContent-Length: 5\r\n\r\nhello";

    @Test
    public void testParseRetrievesVerb() {
        RequestParse parser = new RequestParse(testRequest);
        assertEquals(HTTPMethod.GET, parser.verb());
    }

    @Test
    public void testParseRetrievesPath() {
        RequestParse parser = new RequestParse(testRequest);
        assertEquals(Path.SIMPLE_GET, parser.path());
    }

    @Test
    public void testParseRetrievesHeaders() {
        var expectedHeader = Map.of("Allow", "*/*", "Content-Length", "5");
        RequestParse parser = new RequestParse(testRequest);
        assertEquals(expectedHeader, parser.headers());
    }


}
