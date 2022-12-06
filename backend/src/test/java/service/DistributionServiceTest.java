package service;
import model.Account;
import model.Card;
import model.Company;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DistributionServiceTest {

    @InjectMocks
    private DistributionService service;

    Company defaultCompany = Company.builder().id(1L).balance(100.0).build();
    Account defaultAccount = Account.builder().id(1L).build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void disributeGiftCard() throws NotEnoughCompanyBalanceException {
        // Given

        // When
        service.distributeGift(defaultCompany, defaultAccount, 20.0);
        // Then
        assertThat(defaultAccount.getGiftCards())
                .hasSize(1)
                .extracting(Card::getAmount,Card::getExpireAt,Card::isExpired)
                .containsExactly(Tuple.tuple(20.0, LocalDate.now().plusYears(1), false));
        assertThat(defaultCompany.getBalance()).isEqualTo(80.0);

    }

    @Test
    void distributeGiftCard_failed_notEnoughCompanyBalance() {
        // Given

        // When
        var thrown = assertThrows(NotEnoughCompanyBalanceException.class, () -> service.distributeGift(defaultCompany, defaultAccount, 120.0));
        // Then
        assertThat(thrown.getCurrentBalance()).isEqualTo(100.0);
        assertThat(thrown.getAmount()).isEqualTo(120.0);
        assertThat(defaultAccount.getGiftCards()).hasSize(0);
        assertThat(defaultCompany.getBalance()).isEqualTo(100.0);
    }

    @Test
    void disributeMealCard() throws NotEnoughCompanyBalanceException {
        // Given

        // When
        service.distributeMeal(defaultCompany, defaultAccount, 20.0);
        // Then
        var nextYear = LocalDate.now().plusYears(1);
        var expectedExpirationDate = LocalDate.of(nextYear.getYear(), 2, nextYear.isLeapYear() ? 29 : 28);
        assertThat(defaultAccount.getMealCards())
                .hasSize(1)
                .extracting(Card::getAmount,Card::getExpireAt,Card::isExpired)
                .containsExactly(Tuple.tuple(20.0, expectedExpirationDate, false));
        assertThat(defaultCompany.getBalance()).isEqualTo(80.0);

    }

    @Test
    void distributeMealCard_failed_notEnoughCompanyBalance() {
        // Given

        // When
        var thrown = assertThrows(NotEnoughCompanyBalanceException.class, () -> service.distributeMeal(defaultCompany, defaultAccount, 120.0));
        // Then
        assertThat(thrown.getCurrentBalance()).isEqualTo(100.0);
        assertThat(thrown.getAmount()).isEqualTo(120.0);
        assertThat(defaultAccount.getGiftCards()).hasSize(0);
        assertThat(defaultCompany.getBalance()).isEqualTo(100.0);
    }
}
