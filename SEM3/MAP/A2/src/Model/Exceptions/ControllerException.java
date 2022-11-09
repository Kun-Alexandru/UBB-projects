package Model.Exceptions;

public class ControllerException extends Exception{
    private String msg;

    public ControllerException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ControllerException() {
        super("Controller ERROR.");
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
