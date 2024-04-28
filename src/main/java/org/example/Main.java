package org.example;

import static org.example.Utils.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double purchase_price = 520000;
        double down_payment = 210000;
        double annual_interest_rate = 0.065;
        double budget_for_monthly_mortgage = 4000;
        double fixed_extra_payment = 500;
        int mortgage_term = 30;

//        printMortgageMonthlyPaymentsWithBudgetAmount(purchase_price, down_payment, annual_interest_rate, mortgage_term, budget_for_monthly_mortgage);
        printMortgageMonthlyPaymentsWithFixedExtraPayment(purchase_price, down_payment, annual_interest_rate, mortgage_term, fixed_extra_payment);
//         printMortgageMonthlyPaymentsWithOneTimeExtraPaymentAndFixedExtraPayment(200000, 0.065, 1959.4108728281926, 30000, 500.0);
    }


}