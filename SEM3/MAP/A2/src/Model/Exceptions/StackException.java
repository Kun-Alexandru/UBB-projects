package Model.Exceptions;

public class StackException extends Exception{
    private String msg;

    public StackException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public StackException() {
        super("Stack ERROR.");
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
