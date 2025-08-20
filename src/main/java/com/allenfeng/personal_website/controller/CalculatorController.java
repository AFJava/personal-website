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
import com.allenfeng.personal_website.math.IterMethods;

@Controller
@RequestMapping("/")
public class CalculatorController {
    //Maximum iterations allowed
    public final int maxIter = 1000;
    
    @GetMapping("/calculator")
    public String calculatorPage() {
        return "calculator";
    }

    @PostMapping("/calculator")
    public String runCalculator(@RequestParam char funcName,
                                @RequestParam char varName,
                                @RequestParam String function,
                                @RequestParam String derivative,
                                @RequestParam double value,
                                @RequestParam int iterations,
                                Model model) {
        boolean valid = true;
        List<Character> vars = new ArrayList<>();
        vars.add(varName);
        
        Expression func = new Expression(function, vars);
        Expression der = new Expression(derivative, vars);
        
        if(! func.getErrors().isEmpty()) {
            valid = false;
            model.addAttribute("functionErr", func.getErrors());
        }

        if(! der.getErrors().isEmpty()) {
            valid = false;
            model.addAttribute("derErr", der.getErrors());
        }

        if(iterations > 1000) {
            model.addAttribute("maxIterMsg", "Iterations capped at " + maxIter + " for performance.");
            iterations = 1000;
        }

        List<String> output = new ArrayList<>();
        if(valid) {
            for (int i = 0; i < iterations; i++) {
                value = IterMethods.nMethod(func, der, value);
                output.add("Iteration " + (i + 1) + ": " + value);
            }
        }
        
        model.addAttribute("nOutput", output);

        return "calculator";
    }
}
