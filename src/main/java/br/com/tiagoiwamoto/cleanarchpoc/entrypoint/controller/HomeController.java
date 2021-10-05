package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.controller;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 21:55
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/pages")
public class HomeController {

    @GetMapping(path = "/home")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
