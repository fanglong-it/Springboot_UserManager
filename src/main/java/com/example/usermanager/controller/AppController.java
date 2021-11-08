package com.example.usermanager.controller;


import com.example.usermanager.entity.User;
import com.example.usermanager.respository.UserRepository;
import com.example.usermanager.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @GetMapping("/")
    public String homePage(){
        return "index";
    }
    
    @GetMapping("/login_page")
    public String loginPage(){
        return "login";
    }
    
    @GetMapping("/manager")
    public String managerPage(){
        return "manager";
    }
    
    @GetMapping("/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
    
    
  

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());

        return "singup_form";
    }
    
    @PostMapping(value = "logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!= null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}

    @PostMapping(value = "process_register")
    public String processRegistration(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        String newPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(newPassword);
        repo.save(user);
        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model){

        List<User> listUser = repo.findAll();
        model.addAttribute("LIST_USER", listUser);

        return "users";
    }
    
    
    



}
