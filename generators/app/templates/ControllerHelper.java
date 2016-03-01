package <%= packageName %>;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Helper class for Controllers.
 */
public class ControllerHelper {

    private ControllerHelper() {
    }

    /**
     * Generate a ResponseEntity from the HttpEntity's body and header; and add in a status
     *
     * @param <T>              the type parameter
     * @param he               HttpEntity with (optional) body and (optional) headers
     * @param statusWhenNoBody status to populate if a body not available at this time
     * @return ResponseEntity for sending on the wire
     */
    public static <T> ResponseEntity<T> toResponseEntity(HttpEntity<T> he, HttpStatus statusWhenNoBody) {
        return new ResponseEntity<>(
                he.getBody(),
                he.getHeaders(),
                he.getBody() == null ? statusWhenNoBody : HttpStatus.OK
        );
    }

    /**
     * To ok response entity response entity.
     *
     * @param <T>     the type parameter
     * @param body    the body
     * @param headers the headers
     * @return the response entity
     */
    public static <T> ResponseEntity<List<T>> toOkResponseEntity(List<T> body, HttpHeaders headers) {
        return new ResponseEntity<>(
                body,
                headers,
                HttpStatus.OK
        );
    }

}
