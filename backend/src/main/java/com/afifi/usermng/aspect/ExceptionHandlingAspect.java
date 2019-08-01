package com.afifi.usermng.aspect;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.exception.ServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * Aspect for exception handling of service and repository.
 *
 * @author Mehdi Afifi
 */
@Aspect
@Component
public class ExceptionHandlingAspect {

    /**
     * Pointcut that matches all repositories, services.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)")
    public void springServicePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that handle exceptions.
     *
     * @param joinPoint join point for advice
     * @param ex        exception
     */
    @AfterThrowing(pointcut = "springServicePointcut()", throwing = "ex")
    public void AfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
        if (ex instanceof ResourceNotFoundException || ex instanceof MethodArgumentNotValidException) {
            throw ex;
        } else {
            throw new ServiceException("Unfortunately an exception occurred in the server.", ex);
        }
    }

}
