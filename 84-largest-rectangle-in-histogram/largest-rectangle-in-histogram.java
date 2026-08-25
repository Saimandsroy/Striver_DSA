class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st=new Stack<>();

        int maxArea=0;

        for(int i=0; i<=heights.length; i++){

            int current = (i==heights.length) ? 0 : heights[i];
            while(!st.isEmpty() && heights[st.peek()]>current){
                int index=st.pop();


                int left = st.isEmpty() ? -1 : st.peek();

                int right=i;

                int width=right-left-1;

                int area=heights[index] * width;

                maxArea=Math.max(maxArea,area);
            }
            st.push(i);
        }
        return maxArea;
    }
}