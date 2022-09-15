package constants;

public enum Path {
    SIMPLE_GET("/simple_get"),
    SIMPLE_GET_WITH_BODY("/simple_get_with_body"),
    HEAD_REQUEST("/head_request"),
    ECHO_BODY("/echo_body"),
    REDIRECT("/redirect"),
    METHOD_OPTIONS("/method_options"),
    METHOD_OPTIONS2("/method_options2");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public static Path findPath(String stringPath) {
        for (Path link : values()) {
            if (link.path.equals(stringPath)) {
                return link;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return path;
    }
}
