class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int [] ans = new int[nums.length];

        Stack<Integer>st= new Stack<>();

        int n=nums.length;

        Arrays.fill(ans,-1);

        for(int i=0; i<2*n; i++){
            int index=i%n;

            while(!st.isEmpty() && nums[index]>nums[st.peek()]){
                ans[st.pop()]=nums[index];
            }

            if(i<n){
                st.push(index);
            }
        }
        return ans;
    }
}