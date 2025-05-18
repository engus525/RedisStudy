package kdh.redisstudy.common.exception;

public class Exception extends RuntimeException {

    private final Interface inter;

    public Exception(Interface i) {
        super(i.getMessage());
        this.inter = i;
    }
}
