package by.htp.basumatarau.jd2TestSystem.service.exception;

public class UserCredentialsOccupied extends UserServiceException {
    public UserCredentialsOccupied(String msg, Exception e) {
        super(msg, e);
    }

    public UserCredentialsOccupied(Exception e) {
        super(e);
    }
}
