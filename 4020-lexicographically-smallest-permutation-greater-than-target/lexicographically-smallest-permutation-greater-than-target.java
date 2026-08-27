class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        /*
         * We try to keep the prefix equal to target.
         *
         * At every position i:
         *
         * 1. If target[i] is available, we can continue matching.
         * 2. Before consuming target[i], check if we can instead
         *    put the smallest character > target[i].
         *
         * However, we DON'T immediately return that candidate,
         * because matching target[i] gives us a smaller prefix.
         *
         * If matching becomes impossible, we use the last possible
         * position where we could have made the string bigger.
         */

        int[] bestFreq = null;
        int bestPos = -1;
        int bestChar = -1;

        for (int i = 0; i < n; i++) {

            int cur = target.charAt(i) - 'a';

            /*
             * Is there a character bigger than target[i]?
             *
             * If yes, this is a valid candidate:
             *
             * target[0 ... i-1] + bigger + sorted suffix
             */
            int bigger = findSmallestBigger(freq, cur);

            if (bigger != -1) {
                bestPos = i;
                bestChar = bigger;
                bestFreq = freq.clone();
            }

            /*
             * Can we keep target[i] equal?
             */
            if (freq[cur] == 0) {
                break;
            }

            freq[cur]--;
        }

        /*
         * If we found a position where we can make the string bigger,
         * construct the answer from the LAST such position.
         *
         * Why last?
         *
         * Example:
         *
         * target = bba
         *
         * Possible:
         * cab  -> change position 0
         * bca  -> change position 1
         *
         * bca is smaller, so we want the rightmost position.
         */
        if (bestPos != -1) {

            StringBuilder ans = new StringBuilder();

            // Equal prefix
            for (int i = 0; i < bestPos; i++) {
                ans.append(target.charAt(i));
            }

            // Make this position slightly bigger
            ans.append((char) ('a' + bestChar));

            // Remove the chosen bigger character
            bestFreq[bestChar]--;

            // Smallest possible suffix
            appendSorted(ans, bestFreq);

            return ans.toString();
        }

        return "";
    }


    private int findSmallestBigger(int[] freq, int cur) {

        for (int ch = cur + 1; ch < 26; ch++) {

            if (freq[ch] > 0) {
                return ch;
            }
        }

        return -1;
    }


    private void appendSorted(StringBuilder ans, int[] freq) {

        for (int ch = 0; ch < 26; ch++) {

            while (freq[ch] > 0) {
                ans.append((char) ('a' + ch));
                freq[ch]--;
            }
        }
    }
}