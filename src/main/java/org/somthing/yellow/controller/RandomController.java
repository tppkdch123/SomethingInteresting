package org.somthing.yellow.controller;

import org.somthing.yellow.service.RandomService;
import org.somthing.yellow.util.UnifiedResponse;
import org.somthing.yellow.vo.FirstVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangshizhe on 2018/8/18
 */

@RestController
@RequestMapping("/api/random")
public class RandomController {

    @Autowired
    RandomService randomService;

    @RequestMapping("")
    public FirstVO getResult(Double value, String params){
       return randomService.getResult(params,value);
    }

    @RequestMapping("/str")
    public String getResult2(Double value, String params){
        return randomService.getResultByString(params, value);
    }
}
