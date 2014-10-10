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
