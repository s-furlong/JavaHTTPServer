package server;

import org.junit.jupiter.api.Test;
import response.ResponseBuild;
import response.ServerResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseBuildTest {
    private final String serverLine = "HTTP/1.1 200 OK";
    private final List<String> headers = List.of("Content-Length: 5");
    private final byte[] body = "Hello".getBytes();

    @Test
    public void createResponseWithLineHeaderBody() {
        ResponseBuild createResponse = new ResponseBuild();
        createResponse.setServerLine(serverLine);
        createResponse.contentLengthHeader(body.length);
        createResponse.setBody(body);

        ServerResponse expected = new ServerResponse(serverLine, headers, body);
        ServerResponse actual = createResponse.buildResponse();

        assertEquals(expected, actual);
    }

    @Test
    public void createResponseWithNoBody() {
        ResponseBuild createResponse = new ResponseBuild();
        createResponse.setServerLine(serverLine);
        createResponse.contentLengthHeader(body.length);

        ServerResponse expected = new ServerResponse(serverLine, headers, null);
        ServerResponse actual = createResponse.buildResponse();

        assertEquals(expected, actual);
    }

    @Test
    public void createResponseOnlyServerLine() {
        ResponseBuild createResponse = new ResponseBuild();
        createResponse.setServerLine(serverLine);

        ServerResponse expected = new ServerResponse(serverLine, null, null);
        ServerResponse actual = createResponse.buildResponse();

        assertEquals(expected, actual);
    }
}
