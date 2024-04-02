package com.example.bookerapi;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @InjectMocks
    BookingController controller;
    @Mock
    BookingService service;

    MockMvc mockMvc;

    Booking booking;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();

        booking = new Booking("Booked");
    }

    @Test
    @DisplayName("Given a valid new booking the API should returns the new book and put the id on response header")
    void givenANewBookThenReturnNewBook() throws Exception {
        var book = new Booking("Leo");
        when(service.save(book)).thenReturn(booking);

        var gson = new Gson();
        var json = gson.toJson(book);

        mockMvc.perform(
                        post("/bookings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        verify(service).save(book);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("Given an invalid book then return 400")
    void shouldReturn400errorWhenTheBookIsInvalid() throws Exception {
        var book = new Booking();
        var gson = new Gson();
        var json = gson.toJson(book);

        mockMvc.perform(
                        post("/bookings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        verifyNoInteractions(service);
    }

    @Test
    @DisplayName("Given an valid id then return the book with this id")
    void shouldReturnBookByItsID() throws Exception {
        var id = 1L;
        when(service.findById(anyLong())).thenReturn(booking);

        mockMvc.perform(
                        get("/bookings/" + id)
                                .param("id", String.valueOf(id))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(service).findById(id);
        verifyNoMoreInteractions(service);
    }
}