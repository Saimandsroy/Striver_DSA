class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(prices, dp, 0, 1);
    }

    public int solve(int[] prices, int[][] dp, int index, int buy) {

        int n = prices.length;

        if (index == n) {
            return 0;
        }


        if(dp[index][buy] !=-1){
            return dp[index][buy];
        }

        int profit;

        if (buy==1) {
            int take = -prices[index] + solve(prices, dp, index + 1, 0);
            int dntTake = solve(prices, dp, index + 1, 1);

            profit = Math.max(take, dntTake);
        } else {
            int sell = prices[index] + solve(prices, dp, index + 1, 1);
            int dntSell =  solve(prices, dp, index + 1, 0);

            profit = Math.max(sell, dntSell);
        }

        return dp[index][buy]=profit;

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
