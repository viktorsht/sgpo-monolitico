package com.sgpo.monolithicSGP.ticket.controller;

import com.sgpo.monolithicSGP.routes.entities.TravelRoutes;
import com.sgpo.monolithicSGP.routes.repository.TravelRoutesRepository;
import com.sgpo.monolithicSGP.ticket.dto.TicketRequestDto;
import com.sgpo.monolithicSGP.ticket.dto.TicketResponseDto;
import com.sgpo.monolithicSGP.ticket.entities.Ticket;
import com.sgpo.monolithicSGP.ticket.repository.TicketRepository;
import com.sgpo.monolithicSGP.users.entities.Users;
import com.sgpo.monolithicSGP.users.mapper.UserMapper;
import com.sgpo.monolithicSGP.users.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TravelRoutesRepository travelRoutesRepository;

    public TicketController(
            TicketRepository ticketRepository,
            UserRepository userRepository,
            TravelRoutesRepository travelRoutesRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.travelRoutesRepository = travelRoutesRepository;
    }

    @GetMapping
    public List<TicketResponseDto> getAllTickets() {
        var list = ticketRepository.findAll();
        List<TicketResponseDto> responseDtoList =  list.stream()
                .map( ticket -> new TicketResponseDto(ticket.getId(),
                        UserMapper.userToMeUserDTO(ticket.getUser()),
                        ticket.getTravelRoute(),
                        ticket.getCardNumber(),
                        ticket.getNumberOfTickets(),
                        ticket.getCreatedAt()))
                .collect(Collectors.toList());
        System.out.println(responseDtoList.size());
        return responseDtoList;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TicketResponseDto>> getTicketByUser(@PathVariable UUID userId) {
        var ticketList = ticketRepository.findAll();

        List<TicketResponseDto> responseDtoList = ticketList.stream()
                .filter(ticket -> ticket.getUser().getId().equals(userId))  // Filtra pelos tickets do usuário
                .map(ticket -> new TicketResponseDto(
                        ticket.getId(),
                        UserMapper.userToMeUserDTO(ticket.getUser()),
                        ticket.getTravelRoute(),
                        ticket.getCardNumber(),
                        ticket.getNumberOfTickets(),
                        ticket.getCreatedAt()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        Users user = userRepository.findById(ticketRequestDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TravelRoutes travelRoute = travelRoutesRepository.findById(ticketRequestDto.travelRouteId())
                .orElseThrow(() -> new RuntimeException("TravelRoute not found"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTravelRoute(travelRoute);
        ticket.setCardNumber(ticketRequestDto.cardNumber());
        ticket.setNumberOfTickets(ticketRequestDto.numberOfTickets());
        ticket.setCreatedAt(LocalDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);
        System.out.println(savedTicket.toString());

        return ResponseEntity.ok().build();
    }


    // PUT: Atualiza um ticket existente
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setUser(ticketDetails.getUser());
            ticket.setTravelRoute(ticketDetails.getTravelRoute());
            ticket.setCardNumber(ticketDetails.getCardNumber());
            ticket.setNumberOfTickets(ticketDetails.getNumberOfTickets());
            Ticket updatedTicket = ticketRepository.save(ticket);
            return ResponseEntity.ok(updatedTicket);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: Remove um ticket pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
