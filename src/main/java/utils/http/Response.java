package utils.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utilities class for wrapping responses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String message;
    private Object data;

    @JsonIgnore
    private HttpStatus httpStatus;

    /**
     * Create a new instance with HTTP OK.
     *
     * @return New instance.
     */
    public static Response getInstance() {
        return new Response().status(HttpStatus.OK);
    }

    /**
     * Create an instance of ResponseEntity with the specified message, and HTTP OK.
     *
     * @param msg Message.
     * @return Instance.
     */
    public static ResponseEntity<Response> ok(final String msg) {
        return ok(msg, null);
    }

    /**
     * Create an instance of ResponseEntity with the specified data, and HTTP OK.
     *
     * @param data Data.
     * @return Instance.
     */
    public static ResponseEntity<Response> ok(final Object data) {
        return ok(null, data);
    }

    /**
     * Create an instance of ResponseEntity with the specified message and data, and HTTP OK.
     *
     * @param msg  Message.
     * @param data Data.
     * @return Instance.
     */
    public static ResponseEntity<Response> ok(final String msg, final Object data) {
        return getInstance().message(msg).data(data).build();
    }

    /**
     * Create an instance of ResponseEntity with the specified message, and HTTP UNPROCESSABLE_ENTITY.
     *
     * @param msg Message.
     * @return Instance.
     */
    public static ResponseEntity<Response> invalid(final String msg) {
        return invalid(msg, null);
    }

    /**
     * Create an instance of ResponseEntity with the specified data, and HTTP UNPROCESSABLE_ENTITY.
     *
     * @param data Data.
     * @return Instance.
     */
    public static ResponseEntity<Response> invalid(final Object data) {
        return invalid(null, data);
    }

    /**
     * Create an instance of ResponseEntity with the specified message and data, and HTTP UNPROCESSABLE_ENTITY.
     *
     * @param msg  Message.
     * @param data Data.
     * @return Instance.
     */
    public static ResponseEntity<Response> invalid(final String msg, final Object data) {
        return getInstance().message(msg).data(data).status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Response message(final String message) {
        this.message = message;
        return this;
    }

    public Response data(final Object data) {
        this.data = data;
        return this;
    }

    public Response status(final HttpStatus status) {
        this.httpStatus = status;
        return this;
    }

    public ResponseEntity<Response> build() {
        return new ResponseEntity<Response>(this, httpStatus);
    }

}
