class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ans= new ArrayList<>();

        Arrays.sort(nums);

        int [] maxiMini=maxMin(nums);
        int maximum=maxiMini[0];
        int minimum=maxiMini[1];

        int expected=minimum;

        for(int i=0; i<nums.length ;i++){

            while(expected < nums[i]){
                ans.add(expected);
                expected++;
        }

        if(expected==nums[i]){
            expected++;
        }

    } 
    
    return ans;
    }

    private int [] maxMin(int [] nums){
        int maximum=Integer.MIN_VALUE;
        int minimum=Integer.MAX_VALUE;

        for(int num:nums){
            maximum=Math.max(maximum,num);
            minimum=Math.min(minimum, num);
        }
        return new int[]{maximum,minimum};
    }
}