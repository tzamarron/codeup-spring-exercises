package com.codeup.springblogapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String addNums(@PathVariable int num1, @PathVariable int num2){
        return num1 + " + " + num2 + " = " + (num1 + num2);
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtractNums(@PathVariable int num1, @PathVariable int num2){
        return num2 + " - " + num1 + " = " + (num2 - num1);
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiplyNums(@PathVariable int num1, @PathVariable int num2){
        return num1 + " x " + num2 + " = " + (num1 * num2);
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divideNums(@PathVariable int num1, @PathVariable int num2){
        return num1 + " / " + num2 + " = " + (num1 / num2);
    }

    //Casey way
//    @GetMapping("/{operation}/{firstNum}/{keyword}/{secondNum}")
//    @ResponseBody
//    public String doMath(@PathVariable String operation, @PathVariable int firstNum, @PathVariable String keyword, @PathVariable int secondNum) {
//        switch(operation){
//            case "add":
//                int sum;
//                sum = firstNum + secondNum;
//                return Integer.toString(sum);
//            case "subtract":
//                int diff;
//                diff = secondNum - firstNum;
//                return Integer.toString(diff);
//            case "multiply":
//                int product;
//                product = firstNum * secondNum;
//                return Integer.toString(product);
//            case "divide":
//                int quotient;
//                quotient = firstNum / secondNum;
//                if (firstNum % secondNum != 0){
//                    int remainder = firstNum % secondNum;
//                    return  Integer.toString(quotient) + " remainder " + Integer.toString(remainder);
//                } else {
//                    return Integer.toString(quotient);
//                };
//            default:
//                return "Ed Sheerean hasn't written this album yet.";
//        }
//    }
}
