package by.htp.basumatarau.jd2TestSystem.config.advice;

import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String globalHandler404Error(Model model, Exception e){
        model.addAttribute("exception", e);
        return "page-not-found-404";
    }

    @ExceptionHandler(ServiceException.class)
    public String globalServiceExceptionHandler(Model model, Exception e){
        model.addAttribute("exception", e);
        return "service-failure";
    }
}
