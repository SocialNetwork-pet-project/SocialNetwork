package ua.socialnetwork.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @GetMapping("/{account_id}")
    public String getAccount(@PathVariable("account_id") Integer account_id, Model model ){


    }

}
