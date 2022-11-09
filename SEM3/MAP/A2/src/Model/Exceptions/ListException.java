package Model.Exceptions;

public class ListException extends Exception{
    private String msg;

    public ListException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ListException() {
        super("List ERROR.");
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
