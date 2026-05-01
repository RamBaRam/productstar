package ru.productStar;

import java.util.ArrayList;
import java.util.List;

public class DifferentiatedCalculator implements ICalculator {

    private double principal;
    private double annualInterestRate;
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

    @Override
    public void calculatePayments() {
        if (principal <= 0 || years <= 0) {
            throw new IllegalArgumentException("Некорректные параметры кредита");
        }

        payments = new ArrayList<>();

        int months = years * 12;
        double monthlyRate = annualInterestRate / (12 * 100);
        double remainingDebt = principal;
        double principalPayment = principal / months;

        for (int month = 1; month <= months; month++) {
            double interestPayment = remainingDebt * monthlyRate;

            // корректировка последнего платежа
            if (month == months) {
                principalPayment = remainingDebt;
            }

            remainingDebt -= principalPayment;

            payments.add(new Payment(
                    month,
                    principalPayment,
                    interestPayment
            ));
        }

        overpayments = getTotalPayment() - principal;
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