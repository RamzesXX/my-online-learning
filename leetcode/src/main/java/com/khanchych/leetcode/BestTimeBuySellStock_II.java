package com.khanchych.leetcode;

public class BestTimeBuySellStock_II {
    public int maxProfit(int[] prices) {
        int earnedMoney = 0;
        for(int i=0; i< prices.length -1; i++) {
            earnedMoney += Math.max(0, prices[i + 1] - prices[i]);
        }

        return earnedMoney;
    }
}
