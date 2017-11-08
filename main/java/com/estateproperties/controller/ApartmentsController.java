package com.estateproperties.controller;

import com.estateproperties.model.Apartment;
import com.estateproperties.model.User;
import com.estateproperties.service.ApartmentService;
import com.estateproperties.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApartmentsController {

//    @Value("${welcome.message}")
//    private String message;
//
//    @Value("${error.message}")
//    private String errorMessage;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private UserService userService;


 @RequestMapping(value = { "/buyApartment/{id}" }, method = RequestMethod.GET)
    public ModelAndView getApartmentsById(Model model, @PathVariable String id) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        Apartment apartment = apartmentService.getApartment(id);
        modelAndView.addObject("apartment", apartment);
        modelAndView.setViewName("buyApartment");
        return modelAndView;
    }

    @PostMapping("/sendApplication")
    public String buyApartment(@ModelAttribute Apartment apartment) {
        return "result";
    }
}
