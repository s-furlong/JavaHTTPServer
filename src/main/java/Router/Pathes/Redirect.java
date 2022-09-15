package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.util.List;

public class Redirect implements PathHandler {
    @Override
    public List<HTTPMethod> accessVerb() {
        return List.of(HTTPMethod.GET, HTTPMethod.HEAD);
    }

    @Override
    public Response getResponse(Request request) {
        var response = new Response(StatusCode.REDIRECTED);
        response.addHeader("Location", "http://127.0.0.1:5000/simple_get");
        return response;

    }
}
