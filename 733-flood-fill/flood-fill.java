class Solution {

    class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int m = image.length;
        int n = image[0].length;

        int originalColor = image[sr][sc];


        if (originalColor == color) {
            return image;
        }

        Queue<Pair> queue = new LinkedList<>();


        image[sr][sc] = color;
        queue.add(new Pair(sr, sc));

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            int r = queue.peek().row;
            int c = queue.peek().col;

            queue.remove();

            for (int i = 0; i < 4; i++) {

                int nRow = r + dRow[i];
                int nCol = c + dCol[i];

                if (nRow >= 0 && nRow < m &&
                    nCol >= 0 && nCol < n &&
                    image[nRow][nCol] == originalColor) {

                    image[nRow][nCol] = color;

                    queue.add(new Pair(nRow, nCol));
                }
            }
        }

        return image;
    }
}