package com.estateproperties.service;

import com.estateproperties.model.Apartment;

import java.util.List;

public interface ApartmentService {
    public List<Apartment> getAllApartments(String sort);
    public Apartment getApartment(int id);
}
