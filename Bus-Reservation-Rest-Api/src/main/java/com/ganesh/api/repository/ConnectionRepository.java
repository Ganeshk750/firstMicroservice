package com.ganesh.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.api.entity.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
	
}
