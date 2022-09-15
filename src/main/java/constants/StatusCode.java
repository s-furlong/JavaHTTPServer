package constants;

public enum StatusCode {
    OK("200 OK"),
    REDIRECTED("301 Moved Permanently"),
    NOT_FOUND("404 Not Found"),
    NOT_ALLOWED("405 Not Allowed");

    private final String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String formatFromCode() {
        return this.code;
    }

}
