package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.ClientRequest;
import request.Request;
import response.Response;
import response.ResponseBuild;
import response.ServerResponse;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Redirect implements PathHandler {
    @Override
    public Set<HTTPMethod> accessVerb() {
        return new LinkedHashSet<>(List.of(HTTPMethod.GET, HTTPMethod.HEAD));
    }

    @Override
    public Response getResponse(Request request) throws IOException {
        var response = new Response(StatusCode.REDIRECTED);
        response.addHeader("Location", "http://0.0.0.0:5000/simple_get");
        return response;

    }
}
