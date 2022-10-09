package com.etstur.finalproject.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private String password;

    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_user_id")
    private Collection<Reservation> reservations;

    //No Args Constructor
    public User() {
    }
    //All Args Constructor
    public User(String userName, String password, String email, Collection<Role> roles, Collection<Reservation> reservations) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        Collection<Role> role1 = new ArrayList<>();
        role1.add(new Role("ROLE_EMPLOYEE"));
        this.roles = role1;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", reservations=" + reservations +
                '}';
    }
}
