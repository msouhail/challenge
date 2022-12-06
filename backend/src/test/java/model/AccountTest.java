package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getGiftsBalance() {
        // Given
        var account = Account.builder().build();
        account.getGiftCards().addAll(List.of(
                Card.builder().amount(20.0).expireAt(LocalDate.now().plusDays(1)).build(),
                Card.builder().amount(100.0).expireAt(LocalDate.now().minusDays(1)).build(),
                Card.builder().amount(60.0).expireAt(LocalDate.now().plusDays(2)).build(),
                Card.builder().amount(200.0).expireAt(LocalDate.now().minusDays(2)).build()
        ));
        // Then
        assertThat(account.getGiftsBalance()).isEqualTo(80.0);
    }

    @Test
    void getMealsBalance() {
        // Given
        var account = Account.builder().build();
        account.getMealCards().addAll(List.of(
                Card.builder().amount(20.0).expireAt(LocalDate.now().plusDays(1)).build(),
                Card.builder().amount(100.0).expireAt(LocalDate.now().minusDays(1)).build(),
                Card.builder().amount(60.0).expireAt(LocalDate.now().plusDays(2)).build(),
                Card.builder().amount(200.0).expireAt(LocalDate.now().minusDays(2)).build()
        ));
        // Then
        assertThat(account.getMealsBalance()).isEqualTo(80.0);
    }
}