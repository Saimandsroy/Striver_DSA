class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n+1][2];

        dp[n][0] = 0;
        dp[n][1] = 0;

        for (int index = n - 1; index >= 0; index--) {

            

            for(int buy=0; buy<=1; buy++){
                int profit=0;

            if (buy == 1) {
                int take = -prices[index] + dp[index+1][0];
                int dntTake = dp[index+1][1];

                profit = Math.max(take, dntTake);
            } else {
                int sell = prices[index] + dp[index+1][1];
                int dntSell = dp[index+1][0];

                profit = Math.max(sell, dntSell);
            }

            dp[index][buy] = profit;

        }

        }

        return dp[0][1];


    }


}

//  int min=prices[0];
//         int max=0;

//         for(int i=1; i<prices.length; i++){

//             int profit=prices[i]-min;

//             if(profit>0){
//                 max+=profit;
//             }

//             min=prices[i];

//         }

//         return max;
