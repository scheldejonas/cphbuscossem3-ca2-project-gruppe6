package errorhandling;


public class ErrorMessage
{
    private int code;
    private String message;

    public ErrorMessage(String  msg, int code)
    {
        this.code = code;
        this.message = msg;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
