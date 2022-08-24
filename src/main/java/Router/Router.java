package Router;

import request.Request;
import response.Response;

import java.util.Map;

public class Router {
//    public Router() {
//        //do something
//    }

    public static Response handle(Request request) {
        if ("/head_request".equals(request.path)) {
            if (request.verb.equals("HEAD") |
                    request.verb.equals("OPTIONS")) {
                return new Response(405);
            }
        } else if ("/simple_get".equals(request.path)) {
            if (request.verb.equals("GET")) {
                return new Response(200);
            } else {
                return new Response(405);
            }
        } else if ("/method_options".equals(request.path)) {
            if (request.verb.equals("GET")) {
                return new Response(200);
            } else {
                return new Response(405);
            }
        } else if ("/echo_body".equals(request.path)) {
            if (request.verb.equals("GET")) {
                return new Response(200);
            } else {
                return new Response(405);
            }
        } else if ("/redirect".equals(request.path)) {
            if (request.verb.equals("GET")) {
                return new Response(301);
            } else {
                return new Response(405);
            }
        } else {
            return new Response(404);
        }
        return new Response(405);
    }
}
