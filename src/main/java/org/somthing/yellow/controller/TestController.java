package org.somthing.yellow.controller;

import org.somthing.yellow.util.UnifiedResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangshizhe on 2018/8/12
 */

@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController{

    @RequestMapping(method = RequestMethod.GET,value = "")
    public String test(){
        return "Hello World";
    }


}
