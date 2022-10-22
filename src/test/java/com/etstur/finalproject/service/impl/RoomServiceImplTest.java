package com.etstur.finalproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.entity.Room;
import com.etstur.finalproject.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoomServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoomServiceImplTest {
    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    /**
     * Method under test: {@link RoomServiceImpl#findAllByHotelId(Long)}
     */
    @Test
    void testFindAllByHotelId() {
        ArrayList<Room> roomList = new ArrayList<>();
        when(roomRepository.findAllByHotelId((Long) any())).thenReturn(roomList);
        List<Room> actualFindAllByHotelIdResult = roomServiceImpl.findAllByHotelId(123L);
        assertSame(roomList, actualFindAllByHotelIdResult);
        assertTrue(actualFindAllByHotelIdResult.isEmpty());
        verify(roomRepository).findAllByHotelId((Long) any());
    }
}

