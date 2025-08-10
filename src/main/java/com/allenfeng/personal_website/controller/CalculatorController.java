package com.allenfeng.personal_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class CalculatorController {
    @GetMapping("/calculator")
    public String calculatorPage() {
        return "calculator";
    }

    @PostMapping("/calculator")
    public String runCalculator(@RequestParam String function,
                                @RequestParam String derivative,
                                @RequestParam double value,
                                @RequestParam int iterations) {
        

        return "calculator";
    }
}
