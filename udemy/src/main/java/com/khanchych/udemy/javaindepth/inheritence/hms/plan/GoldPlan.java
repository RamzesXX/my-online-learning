package com.khanchych.udemy.javaindepth.inheritence.hms.plan;

public class GoldPlan  extends HealthInsurancePlan {
    public GoldPlan() {
        super(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary * 0.07;
    }
}
