package com.ganesh.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.entity.TourPackage;


public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
  
	Optional<TourPackage> findByName(String name);
}



