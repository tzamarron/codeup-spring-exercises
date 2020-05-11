//package com.codeup.springblogapp;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller // dictates that his class is a controller (in MVC)
//public class HelloController {
//
//    @GetMapping("/") // when GET request to "localhost:8080/
//        // fun the method directly beneath this
//        // also return the method return String as HTML
//    @ResponseBody
//    public String index(){
//        return "Index page";
//    }
//
//    @GetMapping("/hello")
//    @ResponseBody
//    public String springHello(){
//        return "Hello from Spring!";
//    }
//
////    Path variables
//    @GetMapping("/hello/{name}")
//    @ResponseBody
//    public String sayHello(@PathVariable String name){
//        return "Hello " + name + "!";
//    }
//
//    @RequestMapping(path="/increment/{number}",method = RequestMethod.GET)
//    @ResponseBody
//    public String addOne(@PathVariable int number){
//        return number + " plus on is " + (number + 1) + "!";
//    }
//
//    @GetMapping("/hi/{name}")
//    @ResponseBody
//    public String sayHi(@PathVariable String name){
//        return "Hi " + name;
//    }
//
//    @GetMapping("/defined-ad/{id}/{version}")
//    @ResponseBody
//    public String showAd(@PathVariable long id, @PathVariable int version){
//        return " showing details for ad with id: " + id + ". Version: " + version;
//    }
//
//}
