package ua.socialnetwork.exception;


import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import ua.socialnetwork.exception.NullEntityReferenceException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //ToDO add different exceptions here


    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ModelAndView nullEntityReferenceExceptionHandler(HttpServletRequest request, NullEntityReferenceException exception) {
        return getModelAndView(request, HttpStatus.NO_CONTENT, exception);
    }

    @ExceptionHandler(HttpServerErrorException.BadGateway.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ModelAndView badGatewayExceptionHandler(HttpServletRequest request, HttpServerErrorException.BadGateway exception) {
        return getModelAndView(request, HttpStatus.NO_CONTENT, exception);
    }

    private ModelAndView getModelAndView(HttpServletRequest request, HttpStatus httpStatus, Exception exception) {
        log.error("Exception raised = {} :: URL = {}", exception.getMessage(), request.getRequestURL());

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code", httpStatus.value() + " / " + httpStatus.getReasonPhrase());
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}
