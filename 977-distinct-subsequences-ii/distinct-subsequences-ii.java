class Solution {

    public int distinctSubseqII(String s) {

        final long MOD = 1_000_000_007L;

        long total = 0;
        long[] last = new long[26];

        for (int i = 0; i < s.length(); i++) {

            int c = s.charAt(i) - 'a';

            long oldTotal = total;

            long newEnding = (oldTotal + 1) % MOD;

         
            total = (2 * oldTotal + 1 - last[c] + MOD) % MOD;

            last[c] = newEnding;
        }

        return (int) total;
    }
}