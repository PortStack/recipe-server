package com.teamz.recipe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class home {
    @GetMapping("/")
    @ResponseBody
    public String getRequest() {
        return "Hello Spring";
    }
}
