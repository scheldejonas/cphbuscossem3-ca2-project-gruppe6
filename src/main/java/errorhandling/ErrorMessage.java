package errorhandling;


import java.util.Arrays;

public class ErrorMessage
{
    private int code;
    private String message, stackTrace;
    private boolean debug = false;

    public ErrorMessage(String  msg, int code)
    {
        this.code = code;
        this.message = msg;
    }

    public ErrorMessage(String message, int code, StackTraceElement[] stackTrace)
    {
        this.code = code;
        this.message = message;
        if(debug)
        {
            this.stackTrace = Arrays.toString(stackTrace);
        }
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

    public String getStackTrace()
    {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace)
    {
        this.stackTrace = stackTrace;
    }
}
