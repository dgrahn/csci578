package us.grahn.trojanow.data;

import java.io.Serializable;

/**
 * A data object storing information on results. Will be populated by the various managers when
 * information is retrieved from the server.
 *
 * This is used to display information to the user regarding the results of the transaction with the
 * server.
 *
 * @us.grahn.class     Result
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Result implements Serializable {

    public static final int LOGIN_FAIL    = 1;
    public static final int LOGIN_SUCCESS = 2;

    private int code = -1;
    private String message = null;

    /**
     * Gets the code for the result. This is the general category for the result.
     *
     * @return the code for the result
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the message for the result. This is the specifics of the result.
     *
     * @return the message for the result
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the code for the result.
     *
     * @param code the code for the result
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Sets the message for the result.
     *
     * @param message the message for the result
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
