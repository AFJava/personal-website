/*
 * @author Allen Feng
 *
 * Test cases (eval):
 * 5(-x+1)
 * Make sure the program can evaluate parenthesis and do negative variables correctly
 * 
 * 5+(5+6*-4+8-9)+(10*4) + 5
 * Should be: 5+-20+40+5
 * = 30
 * Make sure the program evaluates multiple sets of parenthesis
 * Also, order of operations
 * (55+x)(88-x^2)
 * Test adjacent parenthesis
 *
 * Test cases (nMethod):
 * y = (x+10)^(1/3) - 3
 * y' = (1/3)*(x+10)^(-2/3)
 * Correct answer: 17
 * 
 * y = (55+x)(88+x^2)
 * y' = 3x^2 + 110x + 88
 * Correct answer: -55
 * 
 * y = (1/8)x^2
 * y' = (1/4)x
 * Correct answer: 0
 * 
 * //TODO: See if this case can be resolved, currently prints NaN after first iteration
 * Final test:
 * y = (5-x)/(23-x^3)+86(x^(1/3))
 * y' = ((23-x^3)(-x)-(5-x)(-3x^2))/(23-x^3)^2+29x^(-2/3)
 * Correct answers: 0, 2.8446
 * 
 */
package com.allenfeng.personal_website.math;

import java.util.ArrayList;

public class IterMethods {
    //private static boolean radians = true;
    //private static boolean degrees = false;
    //private static boolean gradians = false;
    private static Expression function, derivative;

    public static void main(String[] args) {
        ArrayList<Character> vars = new ArrayList<>();
        vars.add('x');
        function = new Expression("-x + -(5 * -x)", vars);
        derivative = new Expression("4", vars);
    }


    public static double nMethod(Expression function, Expression derivative, double input) {
        //System.out.println(function.eval(input));
        //System.out.println(derivative.eval(input));

        double nextInput = input - (function.eval(input) / derivative.eval(input));

        return nextInput;
    }

    //Takes up to two inputs for the differential equation
    public static double eMethod(Expression function, Expression derivative, double[] input, double step) {
        return 0.0;
    }
}
