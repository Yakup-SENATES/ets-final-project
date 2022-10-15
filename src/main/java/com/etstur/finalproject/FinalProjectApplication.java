package com.etstur.finalproject;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.entity.Room;
import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.repository.HotelRepository;
import com.etstur.finalproject.repository.RoomRepository;
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
    private final RoomRepository roomRepository;

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
        hotel.setRooms(List.of(new Room()));
        hotel.setAddress("Istanbul");
        hotel.setDescription("Jacop -> Aliquam ac lacus volutpat, dictum risus at, scelerisque nulla. Nullam sollicitudin at augue venenatis eleifend. Nulla ligula ligula, egestas sit amet viverra id, iaculis sit amet ligula");
        hotel.setImage("img/img-01.jpg");
        hotel.setPhone("123456789");
        hotel.setEmail("hotel@q.q");
        hotelRepository.save(hotel);

        Hotel hotel2 = new Hotel();
        hotel2.setCity("Istanbul");
        hotel2.setCountry("Turkey");
        hotel2.setName("Istanbul-2 Hotel");
        hotel2.setRooms(List.of(new Room()));
        hotel2.setAddress("Istanbul");
        hotel2.setDescription("Jacop -> Aliquam ac lacus volutpat, dictum risus at, scelerisque nulla. Nullam sollicitudin at augue venenatis eleifend. Nulla ligula ligula, egestas sit amet viverra id, iaculis sit amet ligula");
        hotel2.setImage("img/img-01.jpg");
        hotel2.setPhone("123456789");
        hotel2.setEmail("hotel@q.q");
        hotelRepository.save(hotel2);



        Room roomDto = Room.builder()
                .description("Bu oda 1 kişilik")
                .image("img/img-01.jpg")
                .price(100)
                .hotel(hotel)
                .build();

        Room roomDto2 = Room.builder()
                .description("Bu oda 4 kişilik")
                .image("img/img-02.jpg")
                .price(400)
                .hotel(hotel2)
                .build();
        roomRepository.save(roomDto);
        roomRepository.save(roomDto2);

    }
}
