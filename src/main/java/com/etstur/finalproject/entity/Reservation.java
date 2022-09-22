package com.etstur.finalproject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "reservation_room")
    private String room;

    @Column(name = "reservation_price")
    private double price;

    @Column(name = "reservation_num_of_rooms")
    private int rooms;

    @Column(name = "reservation_num_of_persons")
    private int persons;

    @Column(name = "reservation_num_of_children")
    private int children;

    @Column(name = "reservation_open_buffet")
    private String openBuffet;

    @Column(name = "reservation_from_date")
    private Date arrivalDate;

    @Column(name = "reservation_stay_days")
    private int stayDays;

    @Column(name = "reservation_user_id")
    private Long userId;


    public Reservation() {
    }

    public Reservation(Long id, String room, double price, int rooms, int persons, int children, String openBuffet, Date arrivalDate, int stayDays, Long userId) {
        this.id = id;
        this.room = room;
        this.price = price;
        this.rooms = rooms;
        this.persons = persons;
        this.children = children;
        this.openBuffet = openBuffet;
        this.arrivalDate = arrivalDate;
        this.stayDays = stayDays;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getOpenBuffet() {
        return openBuffet;
    }

    public void setOpenBuffet(String openBuffet) {
        this.openBuffet = openBuffet;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getStayDays() {
        return stayDays;
    }

    public void setStayDays(int stayDays) {
        this.stayDays = stayDays;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
