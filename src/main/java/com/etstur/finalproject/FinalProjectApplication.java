package com.etstur.finalproject;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.repository.HotelRepository;
import com.etstur.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class FinalProjectApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUserName("Ertugrul");
        user.setPassword(encoder.encode("123"));
        user.setEmail("q@q.q");
        user.setReservations(null);
        user.setRoles(List.of());
        userRepository.save(user);


        Hotel hotel = new Hotel();
        hotel.setCity("Istanbul");
        hotel.setCountry("Turkey");
        hotel.setName("Istanbul Hotel");
        hotel.setRooms(null);
        hotel.setAddress("Istanbul");
        hotel.setDescription("Jacop -> Aliquam ac lacus volutpat, dictum risus at, scelerisque nulla. Nullam sollicitudin at augue venenatis eleifend. Nulla ligula ligula, egestas sit amet viverra id, iaculis sit amet ligula");
        hotel.setImage("img/img-01.jpg");
        hotel.setPhone("123456789");
        hotel.setEmail("hotel@q.q");
        hotelRepository.save(hotel);

    }
}
