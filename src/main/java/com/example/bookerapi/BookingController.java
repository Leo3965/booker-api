package com.example.bookerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingRepository repository;
    private final BookingService service;

    public BookingController(BookingRepository repository, BookingService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    Collection<Booking> bookings() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> findById(@PathVariable("id") long id) {
        Optional<Booking> booking = repository.findById(id);
        return ResponseEntity.ok(booking.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Booking> save(@RequestBody Booking booking) {
        Booking saved = repository.save(booking);
        var uri = UriComponentsBuilder
                .fromPath("/seller/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping
    public ResponseEntity<Booking> update(@RequestBody Booking booking) {
        Booking saved = repository.save(booking);
        return ResponseEntity.ok(saved);
    }
}
