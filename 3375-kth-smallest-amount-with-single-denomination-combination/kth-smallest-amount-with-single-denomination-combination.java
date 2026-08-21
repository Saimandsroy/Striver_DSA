class Solution {

    public long findKthSmallest(int[] coins, int k) {

        int n = coins.length;

        // Generate LCM for every subset
        long[] lcms = new long[1 << n];

        lcms[0] = 1;

        for (int mask = 1; mask < (1 << n); mask++) {

            // Get one set bit
            int bit = Integer.numberOfTrailingZeros(mask);

            int previousMask = mask & (mask - 1);

            long prevLcm = lcms[previousMask];

            long g = gcd(prevLcm, coins[bit]);

            long lcm = (prevLcm / g) * coins[bit];

            lcms[mask] = lcm;
        }

        // Minimum possible answer is 1
        long left = 1;

        // kth amount cannot be greater than
        // k * smallest coin
        long minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long right = minCoin * (long) k;

        // Binary Search
        while (left < right) {

            long mid = left + (right - left) / 2;

            if (count(mid, lcms, n) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    private long count(long x, long[] lcms, int n) {

        long result = 0;

        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = lcms[mask];

            long multiples = x / lcm;

            int bits = Integer.bitCount(mask);

            if ((bits & 1) == 1) {
                result += multiples;
            } else {
                result -= multiples;
            }
        }

        return result;
    }


    private long gcd(long a, long b) {

        while (b != 0) {

            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}