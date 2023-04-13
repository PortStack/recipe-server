package com.teamz.recipe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class UserController {

    @PostMapping("/auth/register")
    @ResponseBody
    public String register(@RequestBody HashMap<String,Object> map){
        return "test";
    }

}
