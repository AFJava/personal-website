package com.allenfeng.personal_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allenfeng.personal_website.math.Expression;
import com.allenfeng.personal_website.math.NewtonMethod;

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
        boolean valid = true;
        Expression func = new Expression(function);
        Expression der = new Expression(derivative);
        
        if(! func.getErrors().isEmpty()) {
            valid = false;
            //Send errors to HTML/Thymeleaf
        }
        if(! der.getErrors().isEmpty()) {
            valid = false;
            //Send errors to HTML/Thymeleaf
        }

        //Value and iterations validated on frontend?

        for (int i = 0; i < iterations; i++) {
            value = NewtonMethod.nMethod(func, der, value);
            String output = "Iteration " + (i + 1) + ": " + value;
            //Give output to HTML/Thymeleaf
        }
        return "calculator";
    }
}
