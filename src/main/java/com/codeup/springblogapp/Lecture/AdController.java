package com.codeup.springblogapp.Lecture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {
    @GetMapping("/ads")
    public String showAds(Model model){
        List<Ad> adList =  new ArrayList<>();
        Ad ad = new Ad("1967 Buick Wildcat", "Cherry car");
        adList.add(ad);
        ad = new Ad("Wooden Desk", "Rolltop");
        adList.add(ad);
        model.addAttribute("ads", adList);
        return "ads/index";

    }
}
