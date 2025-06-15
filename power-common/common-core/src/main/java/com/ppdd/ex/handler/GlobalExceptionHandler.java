package com.ppdd.ex.handler;

import com.ppdd.constants.BusinessEnum;
import com.ppdd.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常: {}", e.getMessage());
        return Result.error(BusinessEnum.SERVER_INTERNAL_ERROR);
    }

    @ExceptionHandler(AccessException.class)
    public Result<String> handleAccessException(AccessException e) {
        log.error("权限异常: {}", e.getMessage());
        return Result.error(BusinessEnum.ACCESS_DENIED);
    }
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage());
        return Result.error(BusinessEnum.SERVER_INTERNAL_ERROR);
    }
}
