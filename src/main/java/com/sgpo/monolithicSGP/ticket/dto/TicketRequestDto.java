package com.sgpo.monolithicSGP.ticket.dto;

import java.util.UUID;

public record TicketRequestDto(
        UUID userId,
        Long travelRouteId,
        String cardNumber,
        Integer numberOfTickets
) {}

