package com.sgpo.monolithicSGP.routes.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "travel_routes")
public class TravelRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_name", nullable = false)
    private String routeName;

    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    @Column(name = "arrival_location", nullable = false)
    private String arrivalLocation;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    public TravelRoutes() {
    }

    public TravelRoutes(String routeName, String departureLocation, String arrivalLocation, LocalTime departureTime, Double price, Integer seats) {
        this.routeName = routeName;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.price = price;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "TravelRoutes{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", departureTime=" + departureTime +
                ", price=" + price +
                ", seats=" + seats +
                '}';
    }
}
