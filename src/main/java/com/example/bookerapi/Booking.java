package com.example.bookerapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "booking")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Booking {
    @Id
    @GeneratedValue
    private long id;
    private String bookingName;

    public Booking(String bookingName) {
        this.bookingName = bookingName;
    }
}
