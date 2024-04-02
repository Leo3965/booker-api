package com.example.bookerapi;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Collection<Booking> listAll() {
        return this.repository.findAll();
    }

    public Booking findById(long id) {
        return repository.findById(id).orElse(new Booking());
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Booking save(Booking booking) {
        return repository.save(booking);
    }
}
