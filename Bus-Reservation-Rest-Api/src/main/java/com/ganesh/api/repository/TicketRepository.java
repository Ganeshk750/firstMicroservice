package com.ganesh.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.api.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);
    
}