package com.sgpo.monolithicSGP.users.entities;

import com.sgpo.monolithicSGP.routes.entities.TravelRoutes;
import jakarta.persistence.*;

@Entity
@Table(name = "travel_history")
public class TravelHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private TravelRoutes travelRoute;

    @Column(name = "travel_date", nullable = false)
    private String travelDate;

    public TravelHistory() {
    }

    public TravelHistory(Users user, TravelRoutes travelRoute, String travelDate) {
        this.user = user;
        this.travelRoute = travelRoute;
        this.travelDate = travelDate;
    }

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

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }
}

