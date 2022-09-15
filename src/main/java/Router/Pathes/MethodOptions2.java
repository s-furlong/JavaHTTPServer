package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.util.List;
import java.util.Map;


public class MethodOptions2 implements PathHandler {
    @Override
    public List<HTTPMethod> accessVerb() {
        return List.of(HTTPMethod.GET, HTTPMethod.HEAD, HTTPMethod.OPTIONS, HTTPMethod.PUT, HTTPMethod.POST);
    }

    @Override
    public Response getResponse(Request request) {
        var methods = accessVerb().stream().map(HTTPMethod::toString).toList();
        return new Response(StatusCode.OK, Map.of("allow", String.join(", ", methods)));
    }
}
