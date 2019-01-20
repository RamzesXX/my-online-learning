package com.khanchych.udemy.javaindepth.inheritence.hms.plan;

public class SilverPlan extends HealthInsurancePlan {
    public SilverPlan() {
        super(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary * 0.06;
    }
}
