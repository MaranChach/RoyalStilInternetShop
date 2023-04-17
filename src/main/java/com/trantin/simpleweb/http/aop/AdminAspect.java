package com.trantin.simpleweb.http.aop;

import com.trantin.simpleweb.http.exceptions.LinkException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdminAspect {

    @Around(value = "execution (* com.trantin.simpleweb.http.dao.*.delete*(..))")
    public void violationExceptionHandlingDeleteMethods(ProceedingJoinPoint joinPoint) throws LinkException {
        try {
            System.out.println("Попытка удаления");
            joinPoint.proceed();

        } catch (Throwable e) {
            System.out.println("Ошибка удаления");
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new LinkException();
            }
        }
    }
}
