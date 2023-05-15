package com.trantin.simpleweb.http.aop;

import com.trantin.simpleweb.http.exceptions.LinkException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Aspect
@Component
public class AdminAspect {

    /*@Around(value = "execution (* com.trantin.simpleweb.http.dao.*.delete*(..))")
    public void violationExceptionHandlingDeleteMethods(ProceedingJoinPoint joinPoint) throws Exception {
        try {
            System.out.println("Попытка удаления");
            joinPoint.proceed();

        } catch (Throwable e) {
            System.out.println("Ошибка удаления");
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new RuntimeException("Невозможно удалить объект, на запись сохранены ссылки");
            }
        }
    }*/

//    @Around(value = "execution (String com.trantin.simpleweb.http.controllers.*.*(..))")
//    public String ioExceptionHandling(ProceedingJoinPoint joinPoint) {
//        try {
//            return joinPoint.proceed().toString();
//        } catch (Throwable e) {
//            try {
//                return joinPoint.proceed().toString();
//            } catch (Throwable ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//    }
}
