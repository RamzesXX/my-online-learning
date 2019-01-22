package com.khanchych.udemy.javaindepth.inheritence.hms.plan;

public interface InsuranceBrand {
    double computeMonthlyPremium(HealthInsurancePlan plan, int age, boolean smoking);
}