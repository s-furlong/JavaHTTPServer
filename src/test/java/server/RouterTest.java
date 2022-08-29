package server;

import Router.Router;
import constants.HTTPMethod;
import constants.Path;
import org.junit.jupiter.api.Test;
import request.ClientRequest;
import response.ServerResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {
    public static ServerResponse simpleGetResponse() {
        String serverline = "HTTP/1.1 200 OK";
        return new ServerResponse(serverline, null, null);
    }

    public static ServerResponse fullGetRespsonse() {
        String serverLine = "HTTP/1.1 200 OK";
        List<String> headers = List.of("Content-Length: 11");
        byte[] body = "Hello World".getBytes(StandardCharsets.UTF_8);
        return new ServerResponse(serverLine, headers, body);
    }

    public static ServerResponse echoBodyResponse() {
        String serverline = "HTTP/1.1 200 OK";
        List<String> headers = List.of("Content-Length: 5");
        byte[] body = "hello".getBytes();

        return new ServerResponse(serverline, headers, body);
    }


    @Test
    public void handleSimpleGetRequest() throws IOException {
        ClientRequest request = new ClientRequest(HTTPMethod.GET, Path.SIMPLE_GET, null, null);

        ServerResponse expected = simpleGetResponse();
        ServerResponse actual = new Router().generateResponse(request);

        assertEquals(expected, actual);
    }

    @Test
    public void handleSimpleGetWithRequest() throws IOException {
        ClientRequest request = new ClientRequest(HTTPMethod.GET, Path.SIMPLE_GET_WITH_BODY, null, null);

        ServerResponse expected = fullGetRespsonse();
        ServerResponse actual = new Router().generateResponse(request);

        assertEquals(expected.serverLine(), actual.serverLine());
        assertEquals(expected.headers(), actual.headers());
        assertEquals(Arrays.toString(expected.body()), Arrays.toString(actual.body()));
    }

    @Test
    public void handleHeadRequest() throws IOException {
        ClientRequest request = new ClientRequest(HTTPMethod.HEAD, Path.HEAD_REQUEST, null, null);

        ServerResponse expected = simpleGetResponse();
        ServerResponse actual = new Router().generateResponse(request);

        assertEquals(expected, actual);
    }

    @Test
    public void returnsCorrectResponseForPostRequestToEchoBody() throws IOException {
        ClientRequest request = new ClientRequest(HTTPMethod.POST, Path.ECHO_BODY, null, "hello");

        ServerResponse expectedResponse = echoBodyResponse();
        ServerResponse actualResponse = new Router().generateResponse(request);

        assertEquals(expectedResponse.serverLine(), actualResponse.serverLine());
        assertEquals(expectedResponse.headers(), actualResponse.headers());
        assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
    }
}
