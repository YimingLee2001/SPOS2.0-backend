package cn.bupt.dssc.common.exception;

public class LlmException extends CommonException {

    public LlmException(String message) {
        super(message, 500);
    }

    public LlmException(String message, Throwable cause) {
        super(message, cause, 500);
    }

    public LlmException(Throwable cause) {
        super(cause, 500);
    }
}
