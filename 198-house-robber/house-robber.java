class Solution {
    public int rob(int[] nums) {

        int n= nums.length;

        int [] dp= new int[n];

        dp[0] = nums[0];



        for (int index = 1; index < n; index++) {


            int pick = nums[index];

            if (index > 1) {
                pick += dp[index - 2];
            }

            int notPick = 0 + dp[index - 1];
          
            dp[index] = Math.max(pick, notPick);

        }

        return dp[n-1];
    }

}