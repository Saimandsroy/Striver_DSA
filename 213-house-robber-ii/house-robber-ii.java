class Solution {
    public int rob(int[] nums) {
        int n=nums.length;

        if(n==1){
            return nums[0];
        }
        
        int case1= helpRob(nums, 0,n-2);
        int case2= helpRob(nums, 1, n-1);

        return Math.max(case1, case2);

    }

    public int helpRob(int [] nums, int start, int end){
        
        int n= nums.length;
        int [] dp= new int[end-start+1];

        int m=dp.length;

         dp[0]=nums[start];

        for(int i=start+1; i<=end; i++){
            
            int take= nums[i];

            if(i>start+1){
                take+=dp[i-start-2];
            }

            int dntTake=0+dp[i-start-1];

            dp[i-start]= Math.max(take, dntTake);
        
        }

        return dp[m-1];
    }

}