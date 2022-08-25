package Logic;

import response.ResponseFormat;
import response.ServerResponse;
import wrappers.InputOutputWrappers;

import java.io.IOException;

public class ResponseLogic {
    public final InputOutputWrappers responseMethods;

    public ResponseLogic(InputOutputWrappers responseMethods) {
        this.responseMethods = responseMethods;
    }

    public void outputResponse(ServerResponse response) throws IOException {
        if (response != null) {
            byte[] responseInBytes = ResponseFormat.toBytes(response);
            responseMethods.httpResponse(responseInBytes);
        }
    }
}
