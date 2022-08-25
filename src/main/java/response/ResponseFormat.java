package response;

import constants.Format;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ResponseFormat {
    public static byte[] toBytes(ServerResponse response) throws IOException {
        String startLine = formatServerLine(response.serverLine());
        String headers = formatHeaders(response.headers());

        StringBuilder createResponse = new StringBuilder();
        createResponse.append(startLine);
        createResponse.append(headers);
        createResponse.append(Format.NEWLINE);

        byte[] formattedResponse = createResponse.toString().getBytes();

        if (response.body() == null) {
            return formattedResponse;
        }

        ByteArrayOutputStream responseContainsBody = new ByteArrayOutputStream();
        responseContainsBody.write(formattedResponse);
        responseContainsBody.write(response.body());
        return responseContainsBody.toByteArray();
    }

    public static String formatServerLine(String serverline) {
        StringBuilder formattedLine = new StringBuilder();
        formattedLine.append(serverline);
        formattedLine.append(Format.NEWLINE);

        return formattedLine.toString();
    }

    public static String formatHeaders(List<String> headers) {
        if (headers == null) {
            return "";
        }

        StringBuilder formattedHeaders = new StringBuilder();
        for (String header : headers) {
            formattedHeaders.append(header);
            formattedHeaders.append((Format.NEWLINE));
        }

        return formattedHeaders.toString();
    }


}
