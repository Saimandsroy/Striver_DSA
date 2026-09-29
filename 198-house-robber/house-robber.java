class Solution {
    public int rob(int[] nums) {

        int n=nums.length;
        
        int [] dp= new int[n];

        Arrays.fill(dp, -1);

        return solve(nums, dp, n-1);
    }

    public int solve(int [] nums, int [] dp, int index){
        
        if(index==0){
            return nums[index];
        }

        if(index<0){
            return 0;
        }


        if(dp[index] != -1){
            return dp[index];
        }

        int pick = nums[index] + solve(nums, dp, index-2);

        int notPick= 0 + solve(nums, dp, index-1);

        return dp[index]=Math.max(pick , notPick);

    }
}