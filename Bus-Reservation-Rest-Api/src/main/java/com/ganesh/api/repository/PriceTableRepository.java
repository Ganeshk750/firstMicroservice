package com.ganesh.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.api.entity.PriceTable;

@Repository
public interface PriceTableRepository extends JpaRepository<PriceTable, Long> {
}
