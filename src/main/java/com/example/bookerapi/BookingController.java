package com.example.bookerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingRepository repository;

    @GetMapping
    Collection<Booking> bookings() {
        return this.repository.findAll();
    }
}
