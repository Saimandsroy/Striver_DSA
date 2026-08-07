class Solution {

    private static final long MOD=1000000007;

    public int countGoodNumbers(long n) {
        long evenCount=(n+1)/2;
        long oddCount=n/2;


        long evenWays=evenOdd(5, evenCount);
        long oddWays=evenOdd(4, oddCount);

        return (int) ((evenWays * oddWays)%MOD);
    }

    private long evenOdd(int x, long n){

            if(n == 0)
            return 1;

        long half=evenOdd(x, n/2);

        if(n%2==0){
            return (half*half)%MOD;
        }

        return (x * half *half)%MOD;
    }
}