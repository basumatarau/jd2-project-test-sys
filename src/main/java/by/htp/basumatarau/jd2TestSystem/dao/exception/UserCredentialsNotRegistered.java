package by.htp.basumatarau.jd2TestSystem.dao.exception;

public class UserCredentialsNotRegistered extends DaoException {
    public UserCredentialsNotRegistered(String msg, Exception e) {
        super(msg, e);
    }
    public UserCredentialsNotRegistered(Exception e) {
        super(e);
    }
}
