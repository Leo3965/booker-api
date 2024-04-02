package com.example.bookerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    Collection<Booking> bookings() {
        return this.service.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Booking> save(@RequestBody Booking booking) {
        Booking saved = service.save(booking);
        var uri = UriComponentsBuilder
                .fromPath("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping
    public ResponseEntity<Booking> update(@RequestBody Booking booking) {
        Booking saved = service.save(booking);
        return ResponseEntity.ok(saved);
    }
}
