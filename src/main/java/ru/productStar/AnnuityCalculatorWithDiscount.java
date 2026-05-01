package ru.productStar;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculatorWithDiscount implements IDiscount {

    private double principal;
    private double annualInterestRate;
    private double discount;
    private int years;

    private double overpayments;
    private List<Payment> payments;

    @Override
    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    @Override
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public void setYears(int years) {
        this.years = years;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public void calculatePayments() {
        payments = new ArrayList<>();

        if (discount >= principal) {
            throw new IllegalArgumentException("Скидка не может быть больше или равна сумме кредита");
        }

        double loanAmount = principal - discount;
        double monthlyRate = annualInterestRate / 12 / 100;
        int months = years * 12;

        double monthlyPayment = loanAmount *
                (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        double remainingDebt = loanAmount;

        for (int month = 1; month <= months; month++) {
            double interestPayment = remainingDebt * monthlyRate;
            double principalPayment = monthlyPayment - interestPayment;

            remainingDebt -= principalPayment;

            payments.add(new Payment(month, principalPayment, interestPayment));
        }

        overpayments = getTotalPayment() - loanAmount;
    }

    @Override
    public double getTotalPayment() {
        return payments.stream()
                .mapToDouble(Payment::getTotalPayment)
                .sum();
    }

    @Override
    public double getTotalInterest() {
        return payments.stream()
                .mapToDouble(Payment::getInterestPayment)
                .sum();
    }

    @Override
    public double getOverpayments() {
        return overpayments;
    }

    @Override
    public List<Payment> getPaymentsSchedule() {
        return payments;
    }
}