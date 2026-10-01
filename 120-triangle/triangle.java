class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        int[] prev = new int[n];

        for(int j=0; j<n; j++){
            
            prev[j]=triangle.get(n-1).get(j);
        }

        for (int row= n - 2; row >= 0; row--) {

            int [] curr= new int[n];

            for (int col = row; col >= 0; col--) {

                int down = triangle.get(row).get(col) + prev[col];

                int diagonal = triangle.get(row).get(col) + prev[col+1];

               curr[col] = Math.min(down, diagonal);

            }

            prev=curr;
        }

        return prev[0];

    }

}
