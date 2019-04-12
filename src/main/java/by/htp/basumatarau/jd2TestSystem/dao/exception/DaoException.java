package by.htp.basumatarau.jd2TestSystem.dao.exception;

public class DaoException extends Exception {
    public DaoException(String msg, Exception e){
        super(msg, e);
    }
    public DaoException(Exception e){
        super(e);
    }
}
