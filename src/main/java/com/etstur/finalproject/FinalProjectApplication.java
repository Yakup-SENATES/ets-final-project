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
        user.setUserName("Jacop");
        user.setPassword(encoder.encode("123"));
        user.setEmail("q@q.q");
        user.setReservations(null);
        user.setRoles(List.of());
        userRepository.save(user);


        Hotel hotel = new Hotel();
        hotel.setCity("Antalya");
        hotel.setCountry("Turkey");
        hotel.setName("Granada Luxury Belek");
        hotel.setRooms(List.of(new Room()));
        hotel.setAddress("Kadriye, Belek, Antalya");
        hotel.setDescription("Aliquam ac lacus volutpat, dictum risus at, scelerisque nulla. Nullam sollicitudin at augue venenatis eleifend. Nulla ligula ligula, egestas sit amet viverra id, iaculis sit amet ligula");
        hotel.setImage("img/img-011.png");
        hotel.setPhone("123456789");
        hotel.setEmail("hotel@q.q");
        hotelRepository.save(hotel);

        Hotel hotel2 = new Hotel();
        hotel2.setCity("Antalya");
        hotel2.setCountry("Turkey");
        hotel2.setName("Papillon Ayscha");
        hotel2.setRooms(List.of(new Room()));
        hotel2.setAddress("İleribaşı Mevkii, Belek, Antalya");
        hotel2.setDescription("Jacop Aliquam ac lacus volutpat, dictum risus at, scelerisque nulla. Nullam sollicitudin at augue venenatis eleifend. Nulla ligula ligula, egestas sit amet viverra id, iaculis sit amet ligula");
        hotel2.setImage("img/img-012.png");
        hotel2.setPhone("123456789");
        hotel2.setEmail("hotel@q.q");
        hotelRepository.save(hotel2);

        Hotel hotel3 = new Hotel();
        hotel3.setCity("Istanbul");
        hotel3.setCountry("Turkey");
        hotel3.setName("Alva Donna Exclusive Hotel & Spa");
        hotel3.setRooms(List.of(new Room()));
        hotel3.setAddress("Belek Boğazkent, Belek, Antalya");
        hotel3.setDescription("Antalya’nın Boğazkent mevkiinde yer alan Alva Donna Exclusive Hotel & Spa, her yaşa hitap eden konaklama ve aktivite imkanları ile konforlu ve eğlenceli bir tatil deneyimi vadediyor.");
        hotel3.setImage("img/img-013.png");
        hotel3.setPhone("1234256789");
        hotel3.setEmail("hotel@q2.q");
        hotelRepository.save(hotel3);

        Room roomDto = Room.builder()
                .description("Bu oda 1 kişilik")
                .image("img/room1.png")
                .price(100)
                .hotel(hotel)
                .title("1 kişilik oda")
                .build();
        Room roomDto2 = Room.builder()
                .description("Bu oda 4 kişilik")
                .image("img/room4.png")
                .price(400)
                .hotel(hotel2)
                .title("4 kişilik oda")
                .build();
        Room roomDto3 = Room.builder()
                .description("Bu oda 2 kişilik")
                .image("img/room2.png")
                .price(200)
                .hotel(hotel)
                .title("2 kişilik oda")
                .build();
        Room roomDto4 = Room.builder()
                .description("Bu oda 3 kişilik")
                .image("img/room3.png")
                .price(500)
                .title("3 kişilik oda")
                .hotel(hotel2)
                .build();
        Room roomDto5 = Room.builder()
                .description("Bu oda 1 kişilik")
                .image("img/room2.png")
                .price(2990)
                .hotel(hotel3)
                .title("2 kişilik oda")
                .build();
        Room roomDto6 = Room.builder()
                .description("Bu oda 3 kişilik")
                .image("img/room5.png")
                .price(3650)
                .title("3 kişilik oda")
                .hotel(hotel3)
                .build();


        roomRepository.save(roomDto);
        roomRepository.save(roomDto2);
        roomRepository.save(roomDto3);
        roomRepository.save(roomDto4);
        roomRepository.save(roomDto5);
        roomRepository.save(roomDto6);

    }
}
