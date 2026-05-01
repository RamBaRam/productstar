package ru.productStar;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculator implements ICalculator {

    private double principal;
    private double annualInterestRate;
    private int years;
    private double downPayment;

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

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    @Override
    public void calculatePayments() {
        payments = new ArrayList<>();

        double loanAmount = principal - downPayment;
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

        double totalPayment = getTotalPayment();
        overpayments = totalPayment - loanAmount;
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