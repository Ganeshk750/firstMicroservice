package com.ganesh.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ganesh.entity.Tour;


//public interface TourRepository extends CrudRepository<Tour, Integer> {
//	
//	List<Tour> findByTourPackageCode(@Param("code") String code);
//
//}

public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {
	
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);

}
