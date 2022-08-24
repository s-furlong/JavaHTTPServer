package constants;

public enum TypeOfContent {
    TEXT("text/plain;charset=utf-8"),
    HTML("text/html;charset=utf-8");

    private final String type;

    TypeOfContent(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
