package Router;

import Router.Pathes.EchoBody;
import Router.Pathes.HeadRequest;
import Router.Pathes.SimpleGet;
import Router.Pathes.SimpleGetWithBody;
import constants.HTTPMethod;
import constants.Path;
import constants.StatusCode;
import request.ClientRequest;
import response.ResponseBuild;
import response.ServerResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Router {
    public static ServerResponse generateResponse(ClientRequest request) throws IOException {
        HashMap<Path, IRouter> routeAccessor = retrieveRoutes();
        IRouter route = routeAccessor.get(request.path);

        if (route == null) {
            return new ServerResponse(StatusCode.INVALID.formatFromCode(), null, null);
        }

        if (route != null) {
            Set<HTTPMethod> validVerb = route.accessVerb();

            if (validVerb.contains(request.verb)) {
                return route.convertRequest(request);
            } else {
                return invalidMethod(validVerb);
            }
        }
        return new ServerResponse(StatusCode.NOT_FOUND.formatFromCode(), null, null);

    }

    public static HashMap<Path, IRouter> retrieveRoutes() {
        HashMap<Path, IRouter> routes = new HashMap<>();
        routes.put(Path.SIMPLE_GET, new SimpleGet());
        routes.put(Path.SIMPLE_GET_WITH_BODY, new SimpleGetWithBody());
        routes.put(Path.HEAD_REQUEST, new HeadRequest());
        routes.put(Path.ECHO_BODY, new EchoBody());
        return routes;
    }


    public static ServerResponse invalidMethod(Set<HTTPMethod> validVerb) {
        ResponseBuild buildResponse = new ResponseBuild();
        buildResponse.setServerLine(StatusCode.NOT_ALLOWED.formatFromCode());
        buildResponse.addAllowHeader(validVerb);
        return buildResponse.buildResponse();
    }
}
