package com.khanchych.udemy.javaindepth.inheritence.hms.plan;

public class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;

    public HealthInsurancePlan(double coverage) {
        this.coverage = coverage;
    }

    public double getCoverage() {
        return coverage;
    }

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
}
