class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[3], b[3]);
        });

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = arr[i][0];
        }

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (starts[mid] <= arr[i][1]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            next[i] = l;
        }

        long[][] dp = new long[n + 1][5];
        int[][][] best = new int[n + 1][5][];

        for (int k = 0; k <= 4; k++) {
            best[n][k] = new int[0];
        }

        for (int i = n - 1; i >= 0; i--) {
            best[i][0] = new int[0];

            for (int k = 1; k <= 4; k++) {
                long skipScore = dp[i + 1][k];
                int[] skip = best[i + 1][k];

                long takeScore = arr[i][2] + dp[next[i]][k - 1];
                int[] base = best[next[i]][k - 1];

                int[] take = new int[base.length + 1];
                int p = 0;

                while (p < base.length && base[p] < arr[i][3]) {
                    take[p] = base[p];
                    p++;
                }

                take[p] = arr[i][3];

                while (p < base.length) {
                    take[p + 1] = base[p];
                    p++;
                }

                if (takeScore > skipScore) {
                    dp[i][k] = takeScore;
                    best[i][k] = take;
                } else if (takeScore < skipScore) {
                    dp[i][k] = skipScore;
                    best[i][k] = skip;
                } else {
                    if (lexicographicallySmaller(take, skip)) {
                        dp[i][k] = takeScore;
                        best[i][k] = take;
                    } else {
                        dp[i][k] = skipScore;
                        best[i][k] = skip;
                    }
                }
            }
        }

        return best[0][4];
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}