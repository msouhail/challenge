package service;

import model.Account;
import model.Card;
import model.Company;

import java.time.LocalDate;

public class DistributionService {

    public void distributeGift(Company company, Account account, double amount) throws NotEnoughCompanyBalanceException {
        checkBalance(company.getBalance(), amount);
        account.getGiftCards().add(Card.builder().amount(amount).expireAt(LocalDate.now().plusYears(1)).build());
        company.setBalance(company.getBalance() - amount);
    }

    public void distributeMeal(Company company, Account account, double amount) throws NotEnoughCompanyBalanceException {
        checkBalance(company.getBalance(), amount);
        var nextYear = LocalDate.now().plusYears(1);
        var expirationDate = LocalDate.of(nextYear.getYear(), 2, nextYear.isLeapYear() ? 29 : 28);
        account.getMealCards().add(Card.builder().amount(amount).expireAt(expirationDate).build());
        company.setBalance(company.getBalance() - amount);
    }

    private void checkBalance(double balance, double amount) throws NotEnoughCompanyBalanceException {
        if(amount > balance) {
            throw new NotEnoughCompanyBalanceException(balance, amount);
        }
    }
}
