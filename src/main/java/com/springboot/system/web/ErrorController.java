package com.springboot.system.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/error"})
public class ErrorController {
    private final int ERR_404 = 404;
    private final int ERR_500 = 400;


    public String index(){
        return getErrorCode(ERR_404);
    }

    private String getErrorCode(int code){
        switch (code){
            case ERR_404:
                return "/systemLayout/error/404";
            case ERR_500:
                return "/systemLayout/error/500";
            default:
                return "/systemLayout/error/404";
        }
    }
}
