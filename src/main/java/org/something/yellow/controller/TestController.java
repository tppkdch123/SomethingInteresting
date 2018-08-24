package org.something.yellow.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangshizhe on 2018/8/12
 */

@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController{

    @ApiOperation(value = "Hello world!", notes = "test", response = String.class)
    @RequestMapping(method = RequestMethod.GET,value = "")
    public String test(){
        return "Hello World";
    }

}
