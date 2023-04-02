package com.xhu.controller;

import com.xhu.ResponseStatusEnum;
import com.xhu.Result;
import com.xhu.entity.User;
import com.xhu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "首页模块")
@RestController
@CrossOrigin
public class IndexController {
    @Autowired
    private UserService userService;

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    @ResponseBody
    public Result sayHi(@RequestParam(value = "name")String name, @RequestParam(value = "password")String password){
        User user = userService.LoginBy(name, password);
        if (user!=null){
            return new Result(ResponseStatusEnum.SUCCESS,user);
        }
        else {
            return new Result(ResponseStatusEnum.FAILED);
        }
    }
}