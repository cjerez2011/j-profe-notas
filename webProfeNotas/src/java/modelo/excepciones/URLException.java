/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.excepciones;

/**
 *
 * @author Fco
 */
public class URLException extends Exception {

    /**
     * Creates a new instance of <code>URLException</code> without detail
     * message.
     */
    public URLException() {
    }

    /**
     * Constructs an instance of <code>URLException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public URLException(String msg) {
        super(msg);
    }
}
