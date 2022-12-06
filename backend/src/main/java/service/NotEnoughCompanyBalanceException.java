package service;

import lombok.Value;

@Value
public class NotEnoughCompanyBalanceException extends Exception {
    double currentBalance;
    double amount;

    public NotEnoughCompanyBalanceException(double currentBalance, double amount) {
        super("Not enough balance");
        this.currentBalance = currentBalance;
        this.amount = amount;
    }
}
