package constants;

public enum HTTPMethod {
    DELETE("DELETE"),
    GET("GET"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PATCH("PATCH"),
    POST("POST"),
    PUT("PUT");

    private final String verb;

    HTTPMethod(String verb) {
        this.verb = verb;
    }

    public static HTTPMethod findVerb(String stringMethod) {
        for (HTTPMethod method : values()) {
            if (method.verb.equals(stringMethod)) {
                return method;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return verb;
    }
}
