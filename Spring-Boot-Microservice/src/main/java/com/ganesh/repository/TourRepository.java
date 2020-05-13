package com.ganesh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.entity.Tour;

@Repository
public interface TourRepository extends CrudRepository<Tour, Integer> {

}
