package model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Card {
    double amount;
    LocalDate expireAt;


    public boolean isExpired() {
        return this.expireAt.isBefore(LocalDate.now());
    }
}
