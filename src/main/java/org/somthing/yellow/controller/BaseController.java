package org.somthing.yellow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.somthing.yellow.util.UnifiedResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    private static Logger LOGGER= LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Throwable.class)
    public UnifiedResponse HandleAllException(Throwable e) {
        LOGGER.error("Controller层抛出未知异常",e);
        return new UnifiedResponse(1, e.getMessage());
    }
}