class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int len = m + n - 1;

        // Valid parentheses string must have even length
        if ((len & 1) == 1) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] != '(') {
            return false;
        }

        // Last character must be ')'
        if (grid[m - 1][n - 1] != ')') {
            return false;
        }

        /*
         * dp[j][balance]
         *
         * Represents the possible balances at column j
         * in the currently processed row.
         */
        boolean[][] dp = new boolean[n][len + 1];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Starting cell
                if (i == 0 && j == 0) {
                    dp[j][1] = true;
                    continue;
                }

                int remaining = len - (i + j + 1);

                boolean[] cur = new boolean[len + 1];

                // State from top
                boolean[] top = (i > 0) ? dp[j] : null;

                // State from left
                boolean[] left = (j > 0) ? dp[j - 1] : null;

                for (int balance = 0; balance <= len; balance++) {

                    boolean reachable = false;

                    if (top != null && top[balance]) {
                        reachable = true;
                    }

                    if (left != null && left[balance]) {
                        reachable = true;
                    }

                    if (!reachable) {
                        continue;
                    }

                    int newBalance;

                    if (grid[i][j] == '(') {
                        newBalance = balance + 1;
                    } else {
                        newBalance = balance - 1;
                    }

                    // Balance can never be negative
                    if (newBalance < 0) {
                        continue;
                    }

                    // Not enough cells remaining to close the balance
                    if (newBalance > remaining) {
                        continue;
                    }

                    cur[newBalance] = true;
                }

                // Replace old top states with current cell states
                dp[j] = cur;
            }
        }

        // At destination, valid string requires balance = 0
        return dp[n - 1][0];
    }
}