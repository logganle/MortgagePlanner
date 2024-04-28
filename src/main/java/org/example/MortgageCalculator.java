package org.example;

import java.util.ArrayList;
import java.util.List;

public class MortgageCalculator {
    double principle_balance;
    double annual_interest_rate;
    double total_interests_paid;
    double fixed_monthly_payment_amt;
    int mortgage_term;
    List<MonthlyPayment> monthlyPayments;

    MortgageCalculator(double purchase_price, double down_payment, double annual_interest_rate, int mortgage_term) {
        this.principle_balance = purchase_price - down_payment;
        monthlyPayments = new ArrayList<>(mortgage_term * 12);
        this.mortgage_term = mortgage_term;
        this.annual_interest_rate = annual_interest_rate;
        this.fixed_monthly_payment_amt = calculateFixedMonthlyPaymentAmount();
    }

    MortgageCalculator(double remaining_principle, double annual_interest_rate, double fixed_monthly_payment_amt) {
        this.principle_balance = remaining_principle;
        this.annual_interest_rate = annual_interest_rate;
        monthlyPayments = new ArrayList<>();
        this.fixed_monthly_payment_amt = fixed_monthly_payment_amt;
    }

    private double calculateFixedMonthlyPaymentAmount() {
        int number_of_payments = this.mortgage_term * 12;
        double monthly_interest_rate = annual_interest_rate / 12;
        return principle_balance * monthly_interest_rate * Math.pow((1 + monthly_interest_rate), number_of_payments) / (Math.pow(1 + monthly_interest_rate, number_of_payments) - 1);
    }

    public void processMonthlyPayment(final MonthlyPayment monthlyPayment) {
        this.principle_balance -= monthlyPayment.principle_payment;
        this.total_interests_paid += monthlyPayment.interest_payment;
        this.monthlyPayments.add(monthlyPayment);
    }

    static class MonthlyPayment {
        private int payment_term;
        private double interest_payment, principle_payment, extra_payment_to_principle;

        public static MonthlyPayment getInstance(int payment_term, double remaining_principle_balance, double annual_interest_rate, double fixed_monthly_payment, double extra_payment_to_principle) {
            MonthlyPayment monthlyPayment = new MonthlyPayment();
            monthlyPayment.extra_payment_to_principle = extra_payment_to_principle;
            monthlyPayment.payment_term = payment_term;
            // Interest calculation
            monthlyPayment.interest_payment = annual_interest_rate / 12 * remaining_principle_balance;
            // Principle calculation
            double pure_monthly_principle_payment = fixed_monthly_payment - monthlyPayment.interest_payment; //principle payment without extra payment
            if (remaining_principle_balance > fixed_monthly_payment + monthlyPayment.extra_payment_to_principle) {
                monthlyPayment.principle_payment = pure_monthly_principle_payment + monthlyPayment.extra_payment_to_principle;
            } else if (remaining_principle_balance > fixed_monthly_payment) { // remaining_principle_balance <=  fixed_monthly_payment + monthlyPayment.extra_payment_to_principle
                monthlyPayment.principle_payment =  remaining_principle_balance;
                monthlyPayment.extra_payment_to_principle -= (remaining_principle_balance - pure_monthly_principle_payment);
            } else {
                monthlyPayment.principle_payment = remaining_principle_balance;
                monthlyPayment.extra_payment_to_principle = 0.0; // No need to apply extra payment
            }
            return monthlyPayment;
        }

        @Override
        public String toString() {
            return "MonthlyPayment:" +
                    "year=" + (payment_term / 12) +
                    ", month=" + (payment_term % 12) +
                    ", interest_payment=" + interest_payment +
                    ", principle_payment (including extra payment)=" + principle_payment +
                    ", extra_payment_to_principle=" + extra_payment_to_principle ;
        }
    }
}
