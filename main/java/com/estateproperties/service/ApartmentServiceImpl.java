package com.estateproperties.service;

import com.estateproperties.model.Apartment;
import com.estateproperties.repository.ApartmentRepo;
import com.estateproperties.service.utility.SortType;
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

    public Apartment getApartment(int id) {
        return apartmentRepository.findOne(id);
    }

    public List<Apartment> getApartments(String sort, String pricefilter) {
        SortType sortType = SortType.valueOf(sort.toUpperCase());
        int priceInt = Integer.parseInt(pricefilter);
        List<Apartment> apartments = new ArrayList();

        switch (sortType) {
            case NAME_SORT:
            case PRICE_SORT:
                apartments = getSortedApartments(sortType);
                break;
            case PRICE_LESS_FILTER:
                apartments = getSortedApartments(sortType, priceInt);
                break;
        }

        return apartments;
    }

    private List<Apartment> getSortedApartments(SortType sortType) {
        List<Apartment> allApartments = new ArrayList();
        apartmentRepository.findAll().forEach(allApartments::add);
        switch (sortType) {
            case NAME_SORT:
                allApartments = allApartments.stream()
                        .sorted(Comparator.comparing(Apartment::getName))
                        .collect(Collectors.toList());
                break;
            case PRICE_SORT:
                allApartments = allApartments.stream()
                        .sorted(Comparator.comparingInt(Apartment::getPrice))
                        .collect(Collectors.toList());
                break;
        }
        return allApartments;
    }

    private List<Apartment> getSortedApartments(SortType sortType, int priceInt) {
        List<Apartment> allApartments = new ArrayList();
        //todo: refactoring with Querydsl
        apartmentRepository.findAll().forEach(allApartments::add);
        switch (sortType) {
            case PRICE_LESS_FILTER:
                allApartments = allApartments.stream()
                        .filter((a) -> (a.getPrice() < priceInt))
                        .collect(Collectors.toList());
                break;
        }
        return allApartments;
    }
}
