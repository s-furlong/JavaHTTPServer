package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.ClientRequest;
import request.Request;
import response.Response;
import response.ResponseBuild;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SimpleGet implements PathHandler {
    @Override
    public Set<HTTPMethod> accessVerb() {
        return new LinkedHashSet<>(List.of(HTTPMethod.GET, HTTPMethod.HEAD));
    }

    @Override
    public Response getResponse(Request request) {
        return new Response(StatusCode.OK);
    }
}
