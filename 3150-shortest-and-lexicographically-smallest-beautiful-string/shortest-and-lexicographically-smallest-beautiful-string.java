class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        int first = -1;
        int ones = 0;

        int bestStart = -1;
        int bestLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;

                if (first == -1) {
                    first = right;
                }
            }

            if (ones == k) {

                int len = right - first + 1;

                if (len < bestLen ||
                    (len == bestLen &&
                     (bestStart == -1 ||
                      isSmaller(s, first, bestStart, len)))) {

                    bestLen = len;
                    bestStart = first;
                }

                // Remove the first 1 from the window
                ones--;

                // Move first to the next 1
                first++;

                while (first < n && s.charAt(first) == '0') {
                    first++;
                }
            }
        }

        return bestStart == -1
                ? ""
                : s.substring(bestStart, bestStart + bestLen);
    }

    private boolean isSmaller(String s, int a, int b, int len) {

        for (int i = 0; i < len; i++) {

            if (s.charAt(a + i) != s.charAt(b + i)) {
                return s.charAt(a + i) < s.charAt(b + i);
            }
        }

        return false;
    }
}