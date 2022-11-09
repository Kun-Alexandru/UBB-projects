package Model.Exceptions;

public class StatementException extends Exception{
    private String msg;

    public StatementException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public StatementException() {
        super("Statement ERROR.");
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
