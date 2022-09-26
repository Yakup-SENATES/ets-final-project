package com.etstur.finalproject.temp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class CurrentReservation {
    // temp class to filter data and get it from controller to database using services
    // current reservation fields and annotate to get the required data

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private Long id;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private int stayPeriod;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String room;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private int price;

    @NotNull(message = "is required")
    @Size(min=1, message = "is requrired")
    private int rooms;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private int persons;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private int children;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String openBuffet;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private Date arrivalDate;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private int userTId;


    @Override
    public String toString() {
        return "CurrentReservation{" +
                "id=" + id +
                ", stayPeriod=" + stayPeriod +
                ", room='" + room + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", persons=" + persons +
                ", children=" + children +
                ", openBuffet='" + openBuffet + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", userTId=" + userTId +
                '}';
    }
}
