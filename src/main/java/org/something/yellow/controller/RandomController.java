package org.something.yellow.controller;

import io.swagger.annotations.ApiOperation;
import org.something.yellow.service.RandomService;
import org.something.yellow.vo.FirstVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangshizhe on 2018/8/18
 */

@RestController
@RequestMapping("/api/random")
public class RandomController {

    @Autowired
    RandomService randomService;

    @ApiOperation(value = "获取JSON格式的返回值",notes = "测试", response = FirstVO.class)
    @RequestMapping(value = "",method = RequestMethod.GET)
    public FirstVO getResult(Double value, String params){
       return randomService.getResult(params,value);
    }

    @ApiOperation(value = "获取字符串格式的返回值", notes = "测试", response = String.class)
    @RequestMapping(value = "/str", method = RequestMethod.GET)
    public String getResult2(Double value, String params){
        return randomService.getResultByString(params, value);
    }
}
