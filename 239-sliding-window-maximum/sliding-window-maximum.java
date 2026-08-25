class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;

        int[] ans = new int[n - k + 1];

        Deque<Integer> dq = new ArrayDeque<>();

        int ansIndex = 0;

        for (int i = 0; i < n; i++) {

            // Remove indices outside the window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller elements from the back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // Add current index
            dq.offerLast(i);

            // Window is ready
            if (i >= k - 1) {
                ans[ansIndex] = nums[dq.peekFirst()];
                ansIndex++;
            }
        }

        return ans;
    }
}