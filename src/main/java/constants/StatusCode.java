package constants;

public enum StatusCode {
    OK("200 OK"),
    REDIRECTED("301 Moved Permanently"),
    INVALID("400 Bad Request"),
    NOT_FOUND("404 Not Found"),
    NOT_ALLOWED("405 Not Allowed");

    private String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String formatFromCode() {
        StringBuilder line = new StringBuilder();
        line.append("HTTP/1.1 ");
        line.append(this.code);
        return line.toString();
    }

}
