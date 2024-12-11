package core;

public enum StatusCode {

    SUCCESS(200, "Request has been successfully processed"),
    CREATED(201, "The resource has been created on the server"),
    NO_CONTENT(204, "The requested resource not found on the server");

    public final int code;
    public final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
