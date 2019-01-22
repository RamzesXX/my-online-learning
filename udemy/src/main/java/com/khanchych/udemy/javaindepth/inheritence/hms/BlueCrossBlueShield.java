package com.khanchych.udemy.javaindepth.inheritence.hms;

import com.khanchych.udemy.javaindepth.inheritence.hms.plan.*;

public class BlueCrossBlueShield implements InsuranceBrand {
    private long id;
    private String name;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public double computeMonthlyPremium(HealthInsurancePlan plan, int age, boolean smoking) {
        double premium = 0.;

        if (plan instanceof PlatinumPlan) {
            premium += (age > 55 ? 200 : 0) + (smoking ? 100 : 0);
        }
        else if (plan instanceof GoldPlan) {
            premium += (age > 55 ? 150 : 0) + (smoking ? 90 : 0);
        }
        else if (plan instanceof SilverPlan) {
            premium += (age > 55 ? 100 : 0) + (smoking ? 80 : 0);
        }
        else if (plan instanceof BronzePlan) {
            premium += (age > 55 ? 50 : 0) + (smoking ? 70 : 0);
        }

        return premium;
    }
}
