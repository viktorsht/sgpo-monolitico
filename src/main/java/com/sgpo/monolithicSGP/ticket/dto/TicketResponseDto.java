package com.sgpo.monolithicSGP.ticket.dto;

import com.sgpo.monolithicSGP.routes.entities.TravelRoutes;
import com.sgpo.monolithicSGP.users.dto.MeUserDTO;
import com.sgpo.monolithicSGP.users.entities.Users;

import java.time.LocalDateTime;

public record TicketResponseDto(
        Long id,
        MeUserDTO user,
        TravelRoutes travelRoute,
        String cardNumber,
        Integer numberOfTickets,
        LocalDateTime createdAt
) {}
