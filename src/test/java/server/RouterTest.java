package server;

import Router.Pathes.SimpleGet;
import Router.Router;
import constants.Path;
import constants.StatusCode;
import org.junit.jupiter.api.Test;
import request.Request;
import response.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {


    @Test
    public void testRetrieveRoutes() throws IOException {
        Router router = new Router();
        var routes = router.retrieveRoutes();
        var expected = new SimpleGet();
        assertEquals(expected.getClass(), routes.get(Path.SIMPLE_GET).getClass());
    }

    @Test
    public void handleSimpleGetRequest() throws IOException {
        Request request = new Request("GET /simple_get HTTP/1.1\r\n\r\n");
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.OK, response.rawStatus);
    }

    @Test
    public void handleNotFound() throws IOException {
        Request request = new Request("GET /get HTTP/1.1\r\n\r\n");
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.NOT_FOUND, response.rawStatus);
    }

    @Test
    public void handleRedirect() throws IOException {
        Request request = new Request("GET /redirect HTTP/1.1\r\n\r\n");
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.REDIRECTED, response.rawStatus);
        assertEquals("http://0.0.0.0:5000/simple_get", response.mapHeaders.get("Location"));
    }

    @Test
    public void testValidVerbs() throws IOException {
        Request request = new Request("POST /simple_get HTTP/1.1\r\n\r\n");
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.NOT_ALLOWED, response.rawStatus);
    }

    @Test
    public void testIncorrectVerbHeadRequest() throws IOException {
        Request request = new Request("GET /head_request HTTP/1.1\r\n\r\n");
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.NOT_ALLOWED, response.rawStatus);
    }

    @Test
    public void testCorrectHeadRequestWithAllowHeader() throws IOException {
        Request request = new Request("GET /head_request HTTP/1.1\r\n\r\n");
        request.parse();
        Router router = new Router();
        Response response = router.generateResponse(request);
        assertEquals(StatusCode.NOT_ALLOWED, response.rawStatus);
    }
}
