package edu.wm.cs420.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class LoginController {

    @RequestMapping(value="/login")
    public String login() {
       
        return "login";
    }
    @RequestMapping(value="/loginfailed", method=RequestMethod.GET)
    public String loginerror(ModelMap model) {
    	model.addAttribute("error", "true");
    	return "login";
    }

    
    
    @RequestMapping(value="/superSecretPage")
    public String superSecretPage() {
    	return "superSecretPage";
    }
    
    @RequestMapping(value="/secretPage")
    public String secretPage() {
    	return "secretPage";
    }
}
