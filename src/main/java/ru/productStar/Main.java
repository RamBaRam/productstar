package ru.productStar;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double principal = readPositiveDouble(scanner, "Введите сумму кредита:");
        int years = readPositiveInt(scanner, "Введите срок кредита в годах:");
        double annualInterestRate = readPositiveDouble(scanner, "Введите процентную ставку:");

        double downPayment = readNonNegativeDouble(scanner, "Введите первоначальный взнос:");
        if (downPayment >= principal) {
            throw new RuntimeException("Первоначальный взнос не может быть >= суммы кредита");
        }

        int paymentType = readPaymentType(scanner);

        ICalculator calculator = createCalculator(paymentType);

        calculator.setPrincipal(principal);
        calculator.setYears(years);
        calculator.setAnnualInterestRate(annualInterestRate);

        if (calculator instanceof AnnuityCalculator) {
            ((AnnuityCalculator) calculator).setDownPayment(downPayment);
        }
        if (calculator instanceof AnnuityWithDownPaymentCalculator) {
            ((AnnuityWithDownPaymentCalculator) calculator).setDownPayment(downPayment);
        }

        calculator.calculatePayments();

        printSchedule(calculator);
    }

    private static double readPositiveDouble(Scanner scanner, String message) {
        double value;
        while (true) {
            System.out.println(message);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value > 0) break;
            } else {
                scanner.next();
            }
            System.out.println("Ошибка! Введите положительное число.");
        }
        return value;
    }

    private static double readNonNegativeDouble(Scanner scanner, String message) {
        double value;
        while (true) {
            System.out.println(message);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= 0) break;
            } else {
                scanner.next();
            }
            System.out.println("Ошибка! Введите число >= 0.");
        }
        return value;
    }

    private static int readPositiveInt(Scanner scanner, String message) {
        int value;
        while (true) {
            System.out.println(message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) break;
            } else {
                scanner.next();
            }
            System.out.println("Ошибка! Введите положительное целое число.");
        }
        return value;
    }

    private static int readPaymentType(Scanner scanner) {
        int type;
        while (true) {
            System.out.println("Выберите вид платежа:");
            System.out.println("1 - аннуитетный");
            System.out.println("2 - дифференцированный");

            if (scanner.hasNextInt()) {
                type = scanner.nextInt();
                if (type == 1 || type == 2) {
                    return type;
                }
            } else {
                scanner.next();
            }

            System.out.println("Ошибка! Введите 1 или 2.");
        }
    }

    private static ICalculator createCalculator(int type) {
        switch (type) {
            case 1:
                return new AnnuityWithDownPaymentCalculator();
            case 2:
                return new DifferentiatedCalculator();
            default:
                throw new IllegalArgumentException("Неизвестный тип платежа");
        }
    }

    private static void printSchedule(ICalculator calculator) {
        System.out.println("\nГрафик платежей:");

        for (Payment payment : calculator.getPaymentsSchedule()) {
            System.out.printf(
                    "Месяц: %d, Основной долг: %.2f, Проценты: %.2f, Платеж: %.2f%n",
                    payment.getMonth(),
                    payment.getPrincipalPayment(),
                    payment.getInterestPayment(),
                    payment.getTotalPayment()
            );
        }

        System.out.printf("\nОбщая сумма выплат: %.2f%n", calculator.getTotalPayment());
        System.out.printf("Общая сумма процентов: %.2f%n", calculator.getTotalInterest());
        System.out.printf("Сумма переплаты: %.2f%n", calculator.getOverpayments());
    }
}