package Router;

import Router.Pathes.*;
import constants.HTTPMethod;
import constants.Path;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.util.HashMap;
import java.util.List;

public class Router {
    public Response generateResponse(Request request) {
        HashMap<Path, PathHandler> routeAccessor = retrieveRoutes();
        PathHandler route = routeAccessor.get(request.path);

        if (route != null) {
            List<HTTPMethod> usableVerbs = route.accessVerb();

            if (usableVerbs.contains(request.verb)) {
                return route.getResponse(request);
            } else {
                var response = new Response(StatusCode.NOT_ALLOWED);
                response.addAllowHeader(usableVerbs);
                return response;
            }
        }
        return new Response(StatusCode.NOT_FOUND);
    }

    public HashMap<Path, PathHandler> retrieveRoutes() {
        HashMap<Path, PathHandler> routes = new HashMap<>();
        routes.put(Path.SIMPLE_GET, new SimpleGet());
        routes.put(Path.REDIRECT, new Redirect());
        routes.put(Path.HEAD_REQUEST, new HeadRequest());
        routes.put(Path.METHOD_OPTIONS, new MethodOptions());
        routes.put(Path.METHOD_OPTIONS2, new MethodOptions2());
        routes.put(Path.SIMPLE_GET_WITH_BODY, new SimpleGetWithBody());
        routes.put(Path.ECHO_BODY, new SimplePost());
        return routes;
    }

}
