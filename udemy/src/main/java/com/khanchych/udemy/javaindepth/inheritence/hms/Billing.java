package com.khanchych.udemy.javaindepth.inheritence.hms;

import com.khanchych.udemy.javaindepth.inheritence.hms.plan.*;

public class Billing {
    public static double[] computePaymentAmount(Patient patient, double amount) {
        HealthInsurancePlan plan = patient.getInsurancePlan();
        double coverage = plan == null ? 0.0 : plan.getCoverage();
        double discount = 20.;

        if (plan instanceof PlatinumPlan) {
            discount = 50.;
        }
        else if (plan instanceof GoldPlan) {
            discount = 40.;
        }
        else if (plan instanceof SilverPlan) {
            discount = 30.;
        }
        else if (plan instanceof BronzePlan) {
            discount = 25;
        }
        double icPayment = amount * coverage;
        double userPayment = Math.max(0, amount  - icPayment - discount);

        return new double[] {icPayment, userPayment };
    }
}
