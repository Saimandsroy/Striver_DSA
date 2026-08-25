class Solution {
    public int maximalRectangle(char[][] matrix) {
        
        int [] heights= new int[matrix[0].length];

        int maxArea=0;

        for(int i=0; i<matrix.length; i++){
            for(int j=0;j<matrix[0].length; j++){
                if(matrix[i][j]=='1'){
                    heights[j]++;
                }else{
                    heights[j]=0;
                }
            }
            int area=largestRectArea(heights);

            maxArea=Math.max(maxArea, area);
        }
        return maxArea;
    }

    private int largestRectArea(int [] heights){
        Stack<Integer> st= new Stack<>();

        int maxArea=0;

        for(int i=0; i<=heights.length; i++){
            
            int current= (i==heights.length) ? 0 : heights[i];

            while(!st.isEmpty() && current<heights[st.peek()]){
                int index=st.pop();
        

            int height=heights[index];
            int left=st.isEmpty() ? -1 : st.peek();

            int width=i-left-1;

            int area=height * width;

            maxArea=Math.max(maxArea, area);

            }

            if(i<heights.length){
                st.push(i);
            }
        }
        return maxArea;
    }
}