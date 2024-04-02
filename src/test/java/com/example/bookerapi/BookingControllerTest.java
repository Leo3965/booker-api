package com.example.bookerapi;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @InjectMocks
    BookingController controller;
    @Mock
    BookingRepository repository;

    MockMvc mockMvc;
}