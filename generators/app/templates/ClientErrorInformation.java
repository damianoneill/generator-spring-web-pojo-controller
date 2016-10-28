package <%= packageName %>;


import java.io.Serializable;

/**
 * The Client error information holder.
 */
public class ClientErrorInformation implements Serializable {

    private final String requestURI;
    private final String exception;

    /**
     * Instantiates a new Client error information.
     *
     * @param exception  the exception
     * @param requestURI the request uri
     */
    public ClientErrorInformation(final String exception, final String requestURI) {
        this.exception = exception;
        this.requestURI = requestURI;
    }

    /**
     * Gets request uri.
     *
     * @return the request uri
     */
    public String getRequestURI() {
        return requestURI;
    }

    /**
     * Gets exception.
     *
     * @return the exception
     */
    public String getException() {
        return exception;
    }

}
