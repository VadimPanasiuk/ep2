package com.estateproperties.repository;

import com.estateproperties.model.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApatrmentRepo extends CrudRepository<Apartment, String> {
}
