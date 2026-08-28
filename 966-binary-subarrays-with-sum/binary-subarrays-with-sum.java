class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMostCount(nums,goal)-atMostCount(nums,goal-1);
    }

    public int atMostCount(int [] nums, int goal){


        int left=0;
        int right=0;

        int sum=0; 
        int count=0;


        while(right<nums.length){

            while(goal<0){
                return 0;
            }

            sum+=nums[right];

            while(sum>goal){
                sum-=nums[left];
                left++;
            }

            count+=right-left+1;

            right++;

        }

        return count;


    }
}











