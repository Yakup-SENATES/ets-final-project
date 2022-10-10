package com.etstur.finalproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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

    @Column(name = "destination")
    private String destination;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", persons=" + persons +
                ", children=" + children +
                ", openBuffet='" + openBuffet + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", stayDays=" + stayDays +
                ", userId=" + userId +
                ", destination='" + destination + '\'' +
                '}';
    }
}
