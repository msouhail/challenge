package model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class Account {
    Long id;
    List<Card> giftCards = new ArrayList<>();
    List<Card> mealCards = new ArrayList<>();


    public double getGiftsBalance() {
        return getBalance(this.giftCards);
    }

    public double getMealsBalance() {
        return getBalance(this.mealCards);
    }

    private double getBalance(List<Card> cards) {
        return cards.stream()
                .filter( giftCard -> !giftCard.isExpired() )
                .mapToDouble(Card::getAmount)
                .sum();
    }
}
