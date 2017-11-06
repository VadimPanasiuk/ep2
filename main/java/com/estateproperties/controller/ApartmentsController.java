package com.estateproperties.controller;

import com.estateproperties.model.Apartment;
import com.estateproperties.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApartmentsController {

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }



//    @GetMapping(value = { "/apartments" })
//    public String personList(Model model) {
//
//        model.addAttribute("apartments", apartmentService.getAllApartments(sort));
//
//        return "/apartments/apartmentList";
//    }

    @RequestMapping(value = { "/apartments/{id}" }, method = RequestMethod.GET)
    public String getApartmentsById(Model model, @PathVariable String id) {

        Apartment apartment = apartmentService.getApartment(id);
        model.addAttribute("apartment", apartment);

        return "/apartments/apartmentById";
    }
}
