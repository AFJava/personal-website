/*
 * @author Allen Feng
 * 
 * This program uses Newton's method to approximate a root of any non-trig/log function (these are text based).
 * Trig/log functions may be implemented later.
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewtonMethod {
    //Angles/Trig functions to be implemented
    //Maybe make function + derivative global variables?
    
    //private static boolean radians = true;
    //private static boolean degrees = false;
    //private static boolean gradians = false;
    private static Expression function, derivative;

    public static void main(String[] args) {
        boolean done = false;
        boolean validInput;
        
        String stop;

        //x and y value for tangent line, slope of tangent line (derivative at x) k, number of iterations
        double x = 0;
        int iterations = 0;


        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!done) {
                function = new Expression(); 
                derivative = function;
                
                //Get function, validate
                validInput = false;
                while (! validInput) {
                    System.out.print("Enter the original function: \ny = ");

                    String expression = br.readLine();
                    function = new Expression(expression);

                    validInput = function.getErrors().isEmpty();

                    System.out.println();
                    //System.out.println(variable);
                }
                
                validInput = false;
                //Get derivative, validate
                while (! validInput) {
                    System.out.print("Enter the function's derivative: \ny' = ");

                    String dExpression = br.readLine();
                    derivative = new Expression(dExpression);

                    validInput = derivative.getErrors().isEmpty();

                    System.out.println();
                }
                
                //Get starting value, validate
                validInput = false;
                while (!validInput) {
                    try {
                        if(function.getVariable() == ' ') function.setVariable('x');

                        System.out.print("Enter the starting point: \n" + function.getVariable() + " = ");
                        x = Double.parseDouble(br.readLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    
                    System.out.println();
                }

                //Get iterations, validate
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("How many iterations would you like to run? ");
                        iterations = Integer.parseInt(br.readLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }

                    System.out.println();
                }

                //System.out.println(function);
                for (int index = 0; index < iterations; index++) {
                    System.out.print("Iteration " + (index + 1) + ": ");
                    x = nMethod(function, derivative, x);
                    System.out.println(x);
                }
                
                System.out.println();

                //System.out.println(function.eval(x));
                validInput = false;

                while (!validInput) {
                    System.out.print("Continue? Y/N: ");
                    stop = br.readLine();
                    
                    if (stop.replaceAll("\\s", "").equalsIgnoreCase("y")) {
                        validInput = true;
                        done = false;

                        System.out.println();
                        break;
                    } else if (stop.replaceAll("\\s", "").equalsIgnoreCase("n")) {
                        validInput = true;
                        done = true;
                    } else {
                        System.out.println("Please enter a valid input - either Y or N. ");
                        System.out.println();
                    }
                }
            }
            //System.out.println("Program complete.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double nMethod(Expression function, Expression derivative, double input) {
        //System.out.println(function.eval(input));
        //System.out.println(derivative.eval(input));

        double nextInput = input - (function.eval(input) / derivative.eval(input));

        return nextInput;
    }
}
