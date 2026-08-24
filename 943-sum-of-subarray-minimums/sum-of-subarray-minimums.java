class Solution {

    private static final long MOD = 1_000_000_007L;

    public int sumSubarrayMins(int[] arr) {

        long total = 0;

        int[] prev = previousSmaller(arr);
        int[] next = nse(arr);

        for (int i = 0; i < arr.length; i++) {

            long left = i - prev[i];
            long right = next[i] - i;

            long contribution = (left * right) % MOD;
            contribution = (contribution * arr[i]) % MOD;

            total = (total + contribution) % MOD;
        }

        return (int) total;
    }

    private int[] previousSmaller(int[] arr) {

        int n = arr.length;
        int[] prev = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            prev[i] = st.isEmpty() ? -1 : st.peek();

            st.push(i);
        }

        return prev;
    }

    private int[] nse(int[] arr) {

        int n = arr.length;
        int[] next = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            next[i] = st.isEmpty() ? n : st.peek();

            st.push(i);
        }

        return next;
    }
}