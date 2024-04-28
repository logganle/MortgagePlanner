package org.example;

import java.io.IOException;

public class Utils {
    public static void writeToFile(String fileName, String content) throws IOException {

    }

    public static void printMortgageMonthlyPaymentsWithOneTimeExtraPaymentAndFixedExtraPayment(double remaining_principle, double annual_interest_rate, double current_fixed_monthly_payment, double one_time_extra_amt, double fixed_extra_amt) {
        MortgageCalculator mortgageCalculator = new MortgageCalculator(remaining_principle - one_time_extra_amt, annual_interest_rate, current_fixed_monthly_payment);
        processMortgagePaymentsWithFixedExtraPayments(mortgageCalculator, fixed_extra_amt);
        System.out.println("Fixed monthly payment: " + mortgageCalculator.fixed_monthly_payment_amt);
        mortgageCalculator.monthlyPayments.forEach(System.out::println);
        System.out.println("Total interests paid: " + mortgageCalculator.total_interests_paid);
    }

    public static void printMortgageMonthlyPaymentsWithFixedExtraPayment(double purchase_price, double down_payment, double annual_interest_rate, int mortgage_term, double fixed_extra_payment) {
        MortgageCalculator mortgageCalculator = new MortgageCalculator(purchase_price, down_payment, annual_interest_rate, mortgage_term);
        processMortgagePaymentsWithFixedExtraPayments(mortgageCalculator, fixed_extra_payment);

        System.out.println("Fixed monthly payment: " + mortgageCalculator.fixed_monthly_payment_amt);
        mortgageCalculator.monthlyPayments.forEach(System.out::println);
        System.out.println("Total interests paid: " + mortgageCalculator.total_interests_paid);
    }

    public static void printMortgageMonthlyPaymentsWithBudgetAmount(double purchase_price, double down_payment, double annual_interest_rate, int mortgage_term, double budget_for_monthly_mortgage) {
        MortgageCalculator mortgageCalculator = new MortgageCalculator(purchase_price, down_payment, annual_interest_rate, mortgage_term);
        final double fixed_extra_monthly_payment = Math.max(0, budget_for_monthly_mortgage - mortgageCalculator.fixed_monthly_payment_amt);
        processMortgagePaymentsWithFixedExtraPayments(mortgageCalculator, fixed_extra_monthly_payment);

        System.out.println("Fixed monthly payment: " + mortgageCalculator.fixed_monthly_payment_amt);
        mortgageCalculator.monthlyPayments.forEach(System.out::println);
        System.out.println("Total interests paid: " + mortgageCalculator.total_interests_paid);
    }

    private static void processMortgagePaymentsWithFixedExtraPayments(MortgageCalculator mortgageCalculator, double fixed_extra_monthly_payment) {
        for (int payment = 0; mortgageCalculator.principle_balance > 0; payment++) {
            double extra_payment_to_principle = Math.min(fixed_extra_monthly_payment, mortgageCalculator.principle_balance);
            MortgageCalculator.MonthlyPayment monthlyPayment = MortgageCalculator.MonthlyPayment.getInstance(payment, mortgageCalculator.principle_balance, mortgageCalculator.annual_interest_rate, mortgageCalculator.fixed_monthly_payment_amt, extra_payment_to_principle);
            mortgageCalculator.processMonthlyPayment(monthlyPayment);
        }
    }
}
