package Router;

import constants.HTTPMethod;
import request.Request;
import response.Response;

import java.util.List;

public interface PathHandler {

    List<HTTPMethod> accessVerb();

    Response getResponse(Request request);

}
