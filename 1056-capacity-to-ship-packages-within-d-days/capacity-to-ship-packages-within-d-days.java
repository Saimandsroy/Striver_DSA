class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int [] resmaxMin=maxMin(weights);
        int low = resmaxMin[0];
        int high= resmaxMin[1];
        int ans=high;

        while(low<=high){
            int mid=low + (high-low)/2;
            int  requireDays=capacity(weights , mid);

            if(requireDays <= days){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }

        }
        return ans;

    }


    private int [] maxMin(int [] weights){
        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int num: weights){
             max=Math.max(max, num);
            sum+=num;
        }
        return new int[]{max, sum};
    }

    private int capacity(int [] weights, int capacity){

        int currLoad=0;
        int  days=1;
        for(int i=0; i<weights.length; i++){
            if(currLoad + weights[i] <= capacity){
                currLoad+=weights[i];
            }
            else{
                days++;
                currLoad=weights[i];
            }
        }
        return days;
    }
}

/// this is the coapacity to ship package