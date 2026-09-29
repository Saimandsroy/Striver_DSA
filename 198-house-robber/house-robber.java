class Solution {
    public int rob(int[] nums) {


        int prev=nums[0];
        int prev2=0;
        
        int n=nums.length;


        for (int index = 1; index < n; index++) {


            int pick = nums[index];

            if (index > 1) {
                pick += prev2;
            }

            int notPick = 0 + prev;
          
            int curi = Math.max(pick, notPick);

            prev2=prev;
            prev=curi;

        }

        return prev;
    }

}