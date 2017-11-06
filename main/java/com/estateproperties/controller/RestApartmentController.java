package com.estateproperties.controller;

import com.estateproperties.message.Response;
import com.estateproperties.model.Apartment;
import com.estateproperties.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/apartment")
public class RestApartmentController {

    @Autowired
    ApartmentService apartmentService;

    @GetMapping(value = "/all")
    public Response getResource(@RequestParam String sort) {
        Response response = new Response("Done", apartmentService.getAllApartments(sort));
        return response;
    }

    @PostMapping(value = "/save")
    public Response postCustomer(@RequestBody Apartment customer) {
//        apartmentService.add(customer);

        // Create Response Object
        Response response = new Response("Done", customer);
        return response;
    }
}
