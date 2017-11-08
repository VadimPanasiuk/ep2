package com.estateproperties.service;

import com.estateproperties.model.Apartment;
import com.estateproperties.repository.ApatrmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    @Autowired
    private ApatrmentRepo apatrmentRepo;


    @PostConstruct
    private void loadApartments() {
        apatrmentRepo.save(new Apartment("1", "A", "Economy", 1000));
        apatrmentRepo.save(new Apartment("2", "C", "Business", 2000));
        apatrmentRepo.save(new Apartment("3", "B", "Business", 45));
    }

    public List<Apartment> getAllApartments(String sort) {


        List<Apartment> allApartments = new ArrayList();
        apatrmentRepo.findAll().forEach(allApartments::add);
        if(sort.equals("name")){
            allApartments = allApartments.stream().sorted(Comparator.comparing(Apartment::getName)).collect(Collectors.toList());
        } else
            if(sort.equals("price")) {
                allApartments = allApartments.stream().sorted(Comparator.comparingInt(Apartment::getPrice)).collect(Collectors.toList());
            }
        return allApartments;
    }

    public Apartment getApartment(String id) {
        Apartment one = apatrmentRepo.findOne(id);
        return one;
    }
}
