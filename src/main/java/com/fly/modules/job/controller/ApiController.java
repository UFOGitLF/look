package com.fly.modules.job.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xinshidai on 17/9/25.
 */

@RestController
public class ApiController {

    @RequestMapping("/test")
    public String index(){
        return "niHaoYK";
    }
}
