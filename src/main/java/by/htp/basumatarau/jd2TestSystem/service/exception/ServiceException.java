package by.htp.basumatarau.jd2TestSystem.service.exception;

public class ServiceException extends Exception {
    public ServiceException(String msg, Exception e){
        super(msg, e);
    }
    public ServiceException(Exception e){
        super(e);
    }
    public ServiceException(String msg){
        super(msg);
    }
}
