package errorhandling;

/**
 * Created by CHRIS on 14-Mar-17.
 */
public class ServerException extends Exception {

    public ServerException() {
        super("Server Error");
    }

    public ServerException(String message) {
        super(message);
    }

}
