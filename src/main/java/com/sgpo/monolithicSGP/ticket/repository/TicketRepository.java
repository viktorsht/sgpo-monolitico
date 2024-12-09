package com.sgpo.monolithicSGP.ticket.repository;

import com.sgpo.monolithicSGP.ticket.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
