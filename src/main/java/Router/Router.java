package Router;

import Router.Pathes.HeadRequest;
import Router.Pathes.Redirect;
import Router.Pathes.SimpleGet;
import constants.HTTPMethod;
import constants.Path;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Router {
    public Response generateResponse(Request request) throws IOException {
        HashMap<Path, PathHandler> routeAccessor = retrieveRoutes();
        PathHandler route = routeAccessor.get(request.path);

        if (route != null) {
            List<HTTPMethod> usableVerbs = route.accessVerb();

            if (usableVerbs.contains(request.verb)) {
                return route.getResponse(request);
            } else {
                return new Response(StatusCode.NOT_ALLOWED);
            }
        }

        return new Response(StatusCode.NOT_FOUND);
    }

    public HashMap<Path, PathHandler> retrieveRoutes() {
        HashMap<Path, PathHandler> routes = new HashMap<>();
        routes.put(Path.SIMPLE_GET, new SimpleGet());
        routes.put(Path.REDIRECT, new Redirect());
        routes.put(Path.HEAD_REQUEST, new HeadRequest());
//        routes.put(Path.SIMPLE_GET_WITH_BODY, new SimpleGetWithBody());
//        routes.put(Path.ECHO_BODY, new EchoBody());
        return routes;
    }

}
