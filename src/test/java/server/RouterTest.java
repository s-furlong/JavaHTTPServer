package server;

import Router.Pathes.SimpleGet;
import Router.Router;
import constants.Path;
import constants.StatusCode;
import mocks.MockInputOutWrapper;
import org.junit.jupiter.api.Test;
import request.Request;
import response.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    MockInputOutWrapper inputOutputMethods = new MockInputOutWrapper();


    @Test
    public void testRetrieveRoutes() {
        Router router = new Router();
        var routes = router.retrieveRoutes();
        var expected = new SimpleGet();
        assertEquals(expected.getClass(), routes.get(Path.SIMPLE_GET).getClass());
    }

    @Test
    public void handleSimpleGetRequest() throws IOException {
        Request request = new Request("GET /simple_get HTTP/1.1\r\n\r\n", inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.OK, response.rawStatus);
    }

    @Test
    public void handleNotFound() throws IOException {
        Request request = new Request("GET /get HTTP/1.1\r\n\r\n", inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.NOT_FOUND, response.rawStatus);
    }

    @Test
    public void handleRedirect() throws IOException {
        Request request = new Request("GET /redirect HTTP/1.1\r\n\r\n", inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.REDIRECTED, response.rawStatus);
        assertEquals("http://127.0.0.1:5000/simple_get", response.mapHeaders.get("Location"));
    }

    @Test
    public void testValidVerbs() throws IOException {
        Request request = new Request("POST /simple_get HTTP/1.1\r\n\r\n", inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.NOT_ALLOWED, response.rawStatus);
    }

    @Test
    public void testIncorrectVerbHeadRequest() throws IOException {
        Request request = new Request("GET /head_request HTTP/1.1\r\n\r\n", inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.NOT_ALLOWED, response.rawStatus);
    }

    @Test
    public void testCorrectMethodOptionsRoute() throws IOException {
        Request request = new Request("GET /method_options HTTP/1.1\r\n\r\n", inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        response.parse();

        assertEquals("HTTP/1.1 200 OK\r\nallow: GET, HEAD, OPTIONS\r\n\r\n", response.format());
    }

    @Test
    public void testCorrectSimpleGetBodyRoute() throws IOException {
        String simpleGetWithABody = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello world";
        Request request = new Request(simpleGetWithABody, inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        response.parse();

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world", response.format());
    }

    @Test
    public void testCorrectSimplePostRoute() throws IOException {
        String simplePutWithABody = "POST /echo_body HTTP/1.1\r\nContent-Length: 5\r\n\r\n";
        inputOutputMethods.setReceivedMessage("Hello");
        Request request = new Request(simplePutWithABody, inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        response.parse();

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 5\r\n\r\nHello", response.format());
    }

    @Test
    public void testIncorrectVerbHeadRequestAllowsHeaders() throws IOException {
        Request request = new Request("GET /head_request HTTP/1.1\r\n\r\n", inputOutputMethods);
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        response.parse();
        var expected = "HTTP/1.1 405 Not Allowed\r\nallow: HEAD, OPTIONS\r\n\r\n";
        var actual = response.format();
        assertEquals(expected, actual);
    }

}
