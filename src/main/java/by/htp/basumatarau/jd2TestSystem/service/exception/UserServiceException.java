package by.htp.basumatarau.jd2TestSystem.service.exception;

public class UserServiceException extends ServiceException {
    public UserServiceException(String msg, Exception e) {
        super(msg, e);
    }

    public UserServiceException(Exception e) {
        super(e);
    }

    public UserServiceException(String msg) {
        super(msg);
    }
}
