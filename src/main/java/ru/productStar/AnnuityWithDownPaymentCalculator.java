package ru.productStar;

import java.util.ArrayList;
import java.util.List;

public class AnnuityWithDownPaymentCalculator implements ICalculator {

    private double principal;
    private double downPayment;
    private double annualInterestRate;
    private int years;

    private double overpayments;
    private List<Payment> payments;

    @Override
    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
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
        if (downPayment >= principal) {
            throw new IllegalArgumentException("Первоначальный взнос >= суммы кредита");
        }

        payments = new ArrayList<>();

        double loanAmount = principal - downPayment;
        double monthlyRate = annualInterestRate / (100 * 12);
        int months = years * 12;

        double monthlyPayment = loanAmount *
                (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        double remainingBalance = loanAmount;

        for (int month = 1; month <= months; month++) {
            double interestPayment = remainingBalance * monthlyRate;
            double principalPayment = monthlyPayment - interestPayment;

            if (month == months) {
                principalPayment = remainingBalance;
                monthlyPayment = principalPayment + interestPayment;
            }

            remainingBalance -= principalPayment;

            payments.add(new Payment(
                    month,
                    principalPayment,
                    interestPayment
            ));
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