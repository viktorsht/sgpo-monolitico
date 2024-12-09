package com.sgpo.monolithicSGP.ticket.entities;

import com.sgpo.monolithicSGP.routes.entities.TravelRoutes;
import com.sgpo.monolithicSGP.users.entities.Users;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "travel_route_id", nullable = false, referencedColumnName = "id")
    private TravelRoutes travelRoute;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "number_of_tickets", nullable = false)
    private Integer numberOfTickets;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Ticket() {
    }

    public Ticket(Users user, TravelRoutes travelRoute, String cardNumber, Integer numberOfTickets) {
        this.user = user;
        this.travelRoute = travelRoute;
        this.cardNumber = cardNumber;
        this.numberOfTickets = numberOfTickets;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public TravelRoutes getTravelRoute() {
        return travelRoute;
    }

    public void setTravelRoute(TravelRoutes travelRoute) {
        this.travelRoute = travelRoute;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) + // Pode incluir id do usuário ou outros detalhes
                ", travelRoute=" + (travelRoute != null ? travelRoute.getId() : null) + // Similarmente, pode incluir id da viagem
                ", cardNumber='" + cardNumber + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                ", createdAt=" + createdAt +
                '}';
    }

}
