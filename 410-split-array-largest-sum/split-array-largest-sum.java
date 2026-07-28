class Solution {
    public int splitArray(int[] nums, int k) {
        int [] maxiSum=maxSum(nums);
        int low=maxiSum[0];
        int high=maxiSum[1];

        int ans=-1;

        while(low<=high){
            int mid= low + (high-low)/2;

            if(isPossible(nums,k, mid)){
                ans=mid;
                high=mid-1;

            }else{
                low=mid+1;
            }

        }
        return ans;

    }

    private int [] maxSum(int [] nums){

        int max=Integer.MIN_VALUE;
        int sum=0;

        for(int num: nums){
            max=Math.max(max,num);
            sum+=num;
        }
        return new int[]{max,sum};
    }
    private boolean isPossible(int [] nums, int subArr, int maxPoss){
        int subArrCount=1;
        int currSum=0;

        for(int num: nums){
            if(currSum + num <= maxPoss){
                currSum+=num;
            }else{
                subArrCount++;
                currSum=num;

                if(subArrCount > subArr){
                    return false;
                }
            }
           
        }
        return true;
    }

}

// this is the different approach then this is the class of saimands 