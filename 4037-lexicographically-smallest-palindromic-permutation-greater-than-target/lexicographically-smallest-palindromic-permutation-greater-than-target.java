class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // -----------------------------------------
        // 1. Count characters
        // -----------------------------------------
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // -----------------------------------------
        // 2. Check whether palindrome is possible
        // -----------------------------------------
        int odd = 0;
        int middle = -1;

        for (int c = 0; c < 26; c++) {

            if (freq[c] % 2 == 1) {
                odd++;
                middle = c;
            }
        }

        if (odd > 1) {
            return "";
        }

        // -----------------------------------------
        // 3. Characters available in first half
        // -----------------------------------------
        int m = n / 2;

        int[] halfFreq = new int[26];

        for (int c = 0; c < 26; c++) {
            halfFreq[c] = freq[c] / 2;
        }

        String targetHalf = target.substring(0, m);

        // -----------------------------------------
        // 4. Find how many characters of targetHalf
        //    can actually be matched.
        // -----------------------------------------
        int[] remaining = halfFreq.clone();

        int matched = 0;

        while (matched < m) {

            int c = targetHalf.charAt(matched) - 'a';

            if (remaining[c] == 0) {
                break;
            }

            remaining[c]--;
            matched++;
        }

        // -----------------------------------------
        // CASE 1:
        // Entire targetHalf can be constructed.
        // -----------------------------------------
        if (matched == m) {

            // Build palindrome using exactly targetHalf
            String candidate =
                    buildPalindrome(targetHalf, middle, n);

            // It may already be greater than target.
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }

            /*
             * candidate <= target.
             *
             * Since targetHalf is exactly constructible,
             * find the next permutation of targetHalf.
             */
            String nextHalf =
                    nextPermutation(targetHalf);

            if (nextHalf == null) {
                return "";
            }

            return buildPalindrome(nextHalf, middle, n);
        }

        // -----------------------------------------
        // CASE 2:
        // targetHalf cannot be completely formed.
        //
        // We need the smallest half > targetHalf.
        // -----------------------------------------

        /*
         * prefixCount[p] = characters used in
         * targetHalf[0 ... p-1].
         *
         * We store counts for every possible pivot.
         *
         * This costs O(26 * m), which is O(n)
         * because alphabet size = 26.
         */
        int[][] prefixCount = new int[m + 1][26];

        for (int i = 0; i < m; i++) {

            for (int c = 0; c < 26; c++) {
                prefixCount[i + 1][c] =
                        prefixCount[i][c];
            }

            int c = targetHalf.charAt(i) - 'a';

            prefixCount[i + 1][c]++;
        }

        /*
         * A prefix is feasible if we don't use
         * more characters than halfFreq.
         */
        boolean[] feasible = new boolean[m + 1];

        feasible[0] = true;

        for (int i = 0; i < m; i++) {

            feasible[i + 1] = feasible[i];

            int c = targetHalf.charAt(i) - 'a';

            if (prefixCount[i + 1][c] > halfFreq[c]) {
                feasible[i + 1] = false;
            }
        }

        /*
         * Try the rightmost possible pivot.
         *
         * We need target[0 ... pivot-1] to be feasible.
         */
        for (int pivot = m - 1; pivot >= 0; pivot--) {

            if (!feasible[pivot]) {
                continue;
            }

            int targetChar =
                    targetHalf.charAt(pivot) - 'a';

            /*
             * Build remaining character counts after
             * using targetHalf[0 ... pivot-1].
             */
            int[] count = halfFreq.clone();

            for (int i = 0; i < pivot; i++) {

                int c = targetHalf.charAt(i) - 'a';

                count[c]--;
            }

            /*
             * Choose the smallest character strictly
             * greater than targetHalf[pivot].
             */
            for (int c = targetChar + 1; c < 26; c++) {

                if (count[c] == 0) {
                    continue;
                }

                count[c]--;

                StringBuilder half = new StringBuilder();

                // Equal prefix
                half.append(targetHalf, 0, pivot);

                // First greater character
                half.append((char) ('a' + c));

                // Smallest possible suffix
                for (int x = 0; x < 26; x++) {

                    while (count[x] > 0) {
                        half.append((char) ('a' + x));
                        count[x]--;
                    }
                }

                return buildPalindrome(
                        half.toString(),
                        middle,
                        n
                );
            }
        }

        return "";
    }


    // ------------------------------------------------
    // Next lexicographical permutation of a string
    // with possible duplicate characters.
    // ------------------------------------------------
    private String nextPermutation(String s) {

        char[] a = s.toCharArray();

        int i = a.length - 2;

        // Find rightmost a[i] < a[i + 1]
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }

        // Already the largest permutation
        if (i < 0) {
            return null;
        }

        // Find smallest character greater than a[i]
        int j = a.length - 1;

        while (a[j] <= a[i]) {
            j--;
        }

        // Swap
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        // Reverse suffix
        int left = i + 1;
        int right = a.length - 1;

        while (left < right) {

            temp = a[left];
            a[left] = a[right];
            a[right] = temp;

            left++;
            right--;
        }

        return new String(a);
    }


    // ------------------------------------------------
    // Build palindrome from first half
    // ------------------------------------------------
    private String buildPalindrome(
            String half,
            int middle,
            int n) {

        StringBuilder result = new StringBuilder();

        // First half
        result.append(half);

        // Middle for odd length
        if (n % 2 == 1) {
            result.append((char) ('a' + middle));
        }

        // Reverse first half
        for (int i = half.length() - 1; i >= 0; i--) {
            result.append(half.charAt(i));
        }

        return result.toString();
    }
}