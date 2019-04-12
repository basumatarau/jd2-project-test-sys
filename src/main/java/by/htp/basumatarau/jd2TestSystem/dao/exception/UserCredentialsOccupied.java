package by.htp.basumatarau.jd2TestSystem.dao.exception;

public class UserCredentialsOccupied extends DaoException {
    public UserCredentialsOccupied(String msg, Exception e) {
        super(msg, e);
    }

    public UserCredentialsOccupied(Exception e) {
        super(e);
    }
}
