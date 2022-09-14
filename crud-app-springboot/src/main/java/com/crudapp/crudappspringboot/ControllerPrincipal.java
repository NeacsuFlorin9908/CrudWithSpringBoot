package com.crudapp.crudappspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerPrincipal {

    @GetMapping("")
    public String afiseazaPaginaPrincipala() {
        System.out.println("The main controller just refreshed!");
        return "index";
    }

}
