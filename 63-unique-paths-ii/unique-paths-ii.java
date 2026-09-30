class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] prev = new int [n];

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        for (int row = 0; row < m; row++) {

            int [] temp= new int[n];
            for (int col = 0; col < n; col++) {

                if (row == 0 && col == 0) {
                    temp[col] = 1;
                } else {

                    int up = 0;
                    int left = 0;

                    if (row > 0 && obstacleGrid[row][col] != 1) {
                        up += prev[col];
                    }

                    if (col > 0 && obstacleGrid[row][col] != 1) {
                       left += temp[col - 1];
                    }

                    temp[col] = left + up;
                }

            }

            prev=temp;

        }

        return prev[n-1];

    }

}