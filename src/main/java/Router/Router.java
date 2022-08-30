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
        // Needs to be renamed -- see below.  The things being called IRouters are really IControllers
        IRouter route = routeAccessor.get(request.path);

        if (route == null) {
            // TD: note all this is commented out
            return new ServerResponse(StatusCode.INVALID.formatFromCode(), null, null);
        }

        if (route != null) {
            // TD: Do these methods like accessVerb and convertRequest exist yet?
            Set<HTTPMethod> validVerb = route.accessVerb();

            if (validVerb.contains(request.verb)) {
                // TD I see where you're going after staring at this.  I like it.  Polymorphism.
                return route.convertRequest(request);
            } else {
                return invalidMethod(validVerb);
            }
        }
        return new ServerResponse(StatusCode.NOT_FOUND.formatFromCode(), null, null);

    }

    // The second objects here are not Routers (so no IRouter) -- they are controllers
    // The router examines the route and then funnels the request to the correct controller
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
