class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

      
        int[] best = new int[n];

        int INF = n + 1;

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int ans = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Since all numbers are positive,
            // shrink while sum is greater than target.
            while (sum > target) {
                sum -= arr[left++];
            }

            // We found a target-sum subarray [left ... right]
            if (sum == target) {

                int length = right - left + 1;

                // Is there a previous non-overlapping subarray?
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, length + best[left - 1]);
                }

                // Keep shortest target subarray seen so far.
                minLength = Math.min(minLength, length);
            }

            // Store the best subarray up to this index.
            best[right] = minLength;
        }

        return ans == INF ? -1 : ans;
    }
}