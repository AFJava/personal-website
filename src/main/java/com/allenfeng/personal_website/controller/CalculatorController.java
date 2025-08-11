package com.allenfeng.personal_website.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                                @RequestParam int iterations,
                                Model model) {
        boolean valid = true;
        Expression func = new Expression(function);
        Expression der = new Expression(derivative);
        
        if(! func.getErrors().isEmpty()) {
            valid = false;
            model.addAttribute("functionErr", func.getErrors());
        }
        if(! der.getErrors().isEmpty()) {
            valid = false;
            model.addAttribute("derErr", der.getErrors());
        }

        //Value and iterations validated on frontend?

        List<String> output = new ArrayList<>();
        if(valid) {
            for (int i = 0; i < iterations; i++) {
                value = NewtonMethod.nMethod(func, der, value);
                output.add("Iteration " + (i + 1) + ": " + value);
            }
        }
        
        model.addAttribute("nOutput", output);

        return "calculator";
    }
}
