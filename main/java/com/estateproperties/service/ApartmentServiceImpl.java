package com.estateproperties.service;

import com.estateproperties.model.Apartment;
import com.estateproperties.repository.ApartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Qualifier("apartmentRepository")
    @Autowired
    private ApartmentRepo apartmentRepository;


    @PostConstruct
    private void loadApartments() {
        if (!apartmentRepository.findAll().iterator().hasNext()) {
            apartmentRepository.save(new Apartment("Dmitrovka", "City Skyline Views: Charming, naturally well-lit 3 bedroom 2 bathroom apartment in Old Town neighborhood. Close to the red line, shops and nightlife on Well’s Street, and walking distance to the Whole Foods.", 1000));
            apartmentRepository.save(new Apartment("Polianka", "This is such a stupid problem, I’ll be the first to admit that, but it’s still a problem. I did not buy a camera because it had xx megapixels and xx shutter speed.", 2000));
            apartmentRepository.save(new Apartment("Rublevka", "This is such a stupid problem, I’ll be the first to admit that, but it’s still a problem. I did not buy a camera because it had xx megapixels and xx shutter speed.", 45));
        }
    }

    public List<Apartment> getAllApartments(String sort) {


        List<Apartment> allApartments = new ArrayList();
        apartmentRepository.findAll().forEach(allApartments::add);
        if(sort.equals("name")){
            allApartments = allApartments.stream().sorted(Comparator.comparing(Apartment::getName)).collect(Collectors.toList());
        } else
            if(sort.equals("price")) {
                allApartments = allApartments.stream().sorted(Comparator.comparingInt(Apartment::getPrice)).collect(Collectors.toList());
            }
        return allApartments;
    }

    public Apartment getApartment(int id) {
        return apartmentRepository.findOne(id);
    }
}
