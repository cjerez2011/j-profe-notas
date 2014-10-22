package modelo.excepciones;

/**
 *
 * @author Fco
 */
public class SessionFailException extends Exception {

    /**
     * Creates a new instance of <code>SessionFailException</code> without
     * detail message.
     */
    public SessionFailException() {
    }

    /**
     * Constructs an instance of <code>SessionFailException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SessionFailException(String msg) {
        super(msg);
    }
}
