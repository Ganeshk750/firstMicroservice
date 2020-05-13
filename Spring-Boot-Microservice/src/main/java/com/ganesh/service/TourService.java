package com.ganesh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.entity.Difficulty;
import com.ganesh.entity.Region;
import com.ganesh.entity.Tour;
import com.ganesh.entity.TourPackage;
import com.ganesh.repository.TourPackageRepository;
import com.ganesh.repository.TourRepository;

@Service
public class TourService {
	
	private TourRepository tourRepository;
	
	private TourPackageRepository tourPackageRepository;
	
	@Autowired
	public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
		this.tourRepository = tourRepository;
		this.tourPackageRepository = tourPackageRepository;
	}
	
	/*
	 * Create a new Tour Object and persist it to the Database.
	 * 
	 * @param title title
	 * @param description
	 * @param blurb blurb
	 * @param price price
	 * @param duration duration
	 * @param bullets 
	 * @param keywords
	 * @param tourPackageName
	 * @param difficulty
	 * @param region
	 * @return Tour Entity
	 * 
	 */
	
	public Tour createTour(String title, String description, String blurb, Integer price, String duration, String bullets,
			String keywords, String tourPackageName, Difficulty difficulty, Region region) {
		
		TourPackage tourPackge = tourPackageRepository.findByName(tourPackageName)
				.orElseThrow(() -> new RuntimeException("Tour package does not exists"+ tourPackageName));
	      return tourRepository.save(new Tour(title, description, blurb,
			   price, duration, bullets, keywords,
			   tourPackge, difficulty, region));
	}
	
	
/*
 * 
 * Calculate the number of Tour in the Database.
 * 
 * @return the total
 * 
 */
	public long total() {
		return tourRepository.count();
	}
	

}
