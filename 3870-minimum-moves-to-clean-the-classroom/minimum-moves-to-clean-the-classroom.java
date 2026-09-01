import java.util.*;

class Solution {

    static class State {
        int row;
        int col;
        int energy;
        int mask;

        State(int row, int col, int energy, int mask) {
            this.row = row;
            this.col = col;
            this.energy = energy;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Give every litter a unique ID
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        // Find S and assign IDs to L
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                if (classroom[r].charAt(c) == 'S') {
                    startRow = r;
                    startCol = c;
                }

                if (classroom[r].charAt(c) == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int finalMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << litterCount];

        Queue<State> queue = new ArrayDeque<>();

        queue.offer(
                new State(
                        startRow,
                        startCol,
                        energy,
                        0
                )
        );

        visited[startRow][startCol][energy][0] = true;

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {

                State current = queue.poll();

                // All litter collected
                if (current.mask == finalMask) {
                    return moves;
                }

                // No energy to make another move
                if (current.energy == 0) {
                    continue;
                }

                for (int[] dir : directions) {

                    int nr = current.row + dir[0];
                    int nc = current.col + dir[1];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                            nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Moving costs 1 energy
                    int newEnergy = current.energy - 1;

                    // Reset area
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    // Update litter mask
                    int newMask = current.mask;

                    if (classroom[nr].charAt(nc) == 'L') {

                        int id = litterId[nr][nc];

                        newMask |= (1 << id);
                    }

                    // Already visited this exact state
                    if (visited[nr][nc][newEnergy][newMask]) {
                        continue;
                    }

                    visited[nr][nc][newEnergy][newMask] = true;

                    queue.offer(
                            new State(
                                    nr,
                                    nc,
                                    newEnergy,
                                    newMask
                            )
                    );
                }
            }

            moves++;
        }

        return -1;
    }
}