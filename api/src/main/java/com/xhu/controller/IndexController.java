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
    @PostMapping("/sayHi")
    @ResponseBody
    public Result sayHi(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = userService.LoginBy(username, password);
        if (user!=null){
            return new Result(ResponseStatusEnum.SUCCESS,user1);
        }
        else {
            return new Result(ResponseStatusEnum.FAILED);
        }
    }
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User requser){
        String username=requser.getUsername();
        String password=requser.getPassword();
        User user=userService.LoginBy(username,password);
        if (user!=null){
            return new Result(ResponseStatusEnum.SUCCESS,user);
        }
        else {
            return new Result(ResponseStatusEnum.FAILED);
        }
    }
}