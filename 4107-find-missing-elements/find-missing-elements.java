class Solution {
    public List<Integer> findMissingElements(int[] nums) {

        int maximum=Integer.MIN_VALUE;
        int minimum=Integer.MAX_VALUE;

        List<Integer> list= new ArrayList<>();

        HashSet<Integer> set= new HashSet<>();

        for(int i=0; i<nums.length ; i++){
            maximum=Math.max(maximum,nums[i]);
            minimum=Math.min(minimum,nums[i]);
            set.add(nums[i]);
        }
        
        for(int i=minimum+1; i<maximum; i++){
            if(!set.contains(i)){
                list.add(i);
        }

        }

        return list;

    }
}