package errorhandling;


public class ServerException extends Exception {

    public ServerException() {
        super("Server Error");
    }

    public ServerException(String message) {
        super(message);
    }

}
