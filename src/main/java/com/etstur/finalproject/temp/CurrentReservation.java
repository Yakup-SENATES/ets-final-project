package com.etstur.finalproject.temp;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
public class CurrentReservation {
    // temp class to filter data and get it from controller to database using services
    // current reservation fields and annotate to get the required data

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static Long id;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static int stayPeriod;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static String room;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static int price;

    @NotNull(message = "is required")
    @Size(min=1, message = "is requrired")
    private static int rooms;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static int persons;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static int children;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static String openBuffet;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static Date arrivalDate;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static int userTId;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private static String destination;


    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        CurrentReservation.id = id;
    }

    public static int getStayPeriod() {
        return stayPeriod;
    }

    public static void setStayPeriod(int stayPeriod) {
        CurrentReservation.stayPeriod = stayPeriod;
    }

    public static String getRoom() {
        return room;
    }

    public static void setRoom(String room) {
        CurrentReservation.room = room;
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice(int price) {
        CurrentReservation.price = price;
    }

    public static int getRooms() {
        return rooms;
    }

    public static void setRooms(int rooms) {
        CurrentReservation.rooms = rooms;
    }

    public static int getPersons() {
        return persons;
    }

    public static void setPersons(int persons) {
        CurrentReservation.persons = persons;
    }

    public static int getChildren() {
        return children;
    }

    public static void setChildren(int children) {
        CurrentReservation.children = children;
    }

    public static String getOpenBuffet() {
        return openBuffet;
    }

    public static void setOpenBuffet(String openBuffet) {
        CurrentReservation.openBuffet = openBuffet;
    }

    public static Date getArrivalDate() throws ParseException {
        return arrivalDate;
    }

    public static void setArrivalDate(Date arrivalDate) {
        CurrentReservation.arrivalDate = arrivalDate;
    }

    public static int getUserTId() {
        return userTId;
    }

    public static void setUserTId(int userTId) {
        CurrentReservation.userTId = userTId;
    }

    public static String getDestination() {
        return destination;
    }

    public static void setDestination(String destination) {
        CurrentReservation.destination = destination;
    }

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
                ", destination='" + destination + '\'' +
                '}';
    }
}
