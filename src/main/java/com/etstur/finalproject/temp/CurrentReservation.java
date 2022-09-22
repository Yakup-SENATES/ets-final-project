package com.etstur.finalproject.temp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

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



    public CurrentReservation() {
    }

    public CurrentReservation(@NotNull(message = "is required")
                              @Size(min = 1, message = "is required") Long id,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") int stayPeriod,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") String room,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") int price,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") int persons,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") int children,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") String openBuffet,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") Date arrivalDate,
                              @NotNull(message = "is required")
                              @Size(min = 1, message = "is required") int userTId) {
        this.id = id;
        this.stayPeriod = stayPeriod;
        this.room = room;
        this.price = price;
        this.persons = persons;
        this.children = children;
        this.openBuffet = openBuffet;
        this.arrivalDate = arrivalDate;
        this.userTId = userTId;
    }


    @Override
    public String toString() {
        return "CurrentReservation{" +
                "id=" + id +
                ", stayPeriod=" + stayPeriod +
                ", room='" + room + '\'' +
                ", price=" + price +
                ", persons=" + persons +
                ", children=" + children +
                ", openBuffet='" + openBuffet + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", userTId=" + userTId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStayPeriod() {
        return stayPeriod;
    }

    public void setStayPeriod(int stayPeriod) {
        this.stayPeriod = stayPeriod;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getUserTId() {
        return userTId;
    }

    public void setUserTId(int userTId) {
        this.userTId = userTId;
    }
}
