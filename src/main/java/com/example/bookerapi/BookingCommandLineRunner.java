package com.example.bookerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookingCommandLineRunner implements CommandLineRunner {
    @Autowired
    private BookingRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //printAllBookings();
    }

    private void printAllBookings() {
        for (var b : this.repository.findAll()) {
            System.out.println(b.toString());
        }
    }
}
