package response;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record ServerResponse(String serverLine, List<String> headers, byte[] body) {
}
//    public String serverline;
//    public List<String> headers;
//    public byte[] body;
//
//    public ServerResponse(String serverline, List<String> headers, byte[] body) {
//        this.serverline = serverline;
//        this.headers = headers;
//        this.body = body;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ServerResponse that = (ServerResponse) o;
//        return Objects.equals(serverline, that.serverline) && Objects.equals(headers, that.headers) && Arrays.equals(body, that.body);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(serverline, headers);
//        result = 31 * result + Arrays.hashCode(body);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "ServerResponse{" +
//                "serverline='" + serverline + '\'' +
//                ", headers=" + headers +
//                ", body=" + Arrays.toString(body) +
//                '}';
//    }
//
//    public String clientResponse() {
//        StringBuilder response = new StringBuilder();
//        response.append(serverline);
//        response.append(headers);
//        response.append(body);
//
//        return response.toString();
//
//    }
//}
