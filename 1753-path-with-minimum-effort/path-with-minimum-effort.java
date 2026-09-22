class Solution {

    class Pair {
        int distance;
        int row;
        int col;

        Pair(int distance, int row, int col) {
            this.distance = distance;
            this.row = row;
            this.col = col;
        }
    }

    public int minimumEffortPath(int[][] heights) {

        PriorityQueue<Pair> pq =
            new PriorityQueue<>((a, b) -> a.distance - b.distance);

        int m = heights.length;
        int n = heights[0].length;

        int[][] dist = new int[m][n];

        for(int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = 0;

        pq.add(new Pair(0, 0, 0));

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        while(!pq.isEmpty()) {

            Pair current = pq.poll();

            int distance = current.distance;
            int row = current.row;
            int col = current.col;

            // Destination
            if(row == m-1 && col == n-1) {
                return distance;
            }

            for(int i = 0; i < 4; i++) {

                int nRow = row + dRow[i];
                int nCol = col + dCol[i];

                if(nRow >= 0 && nRow < m &&
                   nCol >= 0 && nCol < n) {

                    int newEffort = Math.max(
                        Math.abs(heights[nRow][nCol] - heights[row][col]),
                        distance
                    );

                    if(newEffort < dist[nRow][nCol]) {

                        dist[nRow][nCol] = newEffort;

                        pq.add(new Pair(newEffort, nRow, nCol));
                    }
                }
            }
        }

        return 0;
    }
}