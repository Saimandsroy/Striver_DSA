class Solution {

    public int myAtoi(String s) {

        int sign = 1;
        int i = 0;

       
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

      
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        long ans = parse(s, i, 0);

        ans *= sign;

        if (ans > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (ans < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) ans;
    }

    private long parse(String s, int index, long num) {

        // Base Case HAI
        if (index == s.length())
            return num;

        char ch = s.charAt(index);

        // Stop when a non-digit is found
        if (!Character.isDigit(ch))
            return num;

        int digit = ch - '0';

        // Overflow check BEFORE updating num
        if (num > (Long.MAX_VALUE - digit) / 10) {
            return Long.MAX_VALUE;
        }

        num = num * 10 + digit;

        return parse(s, index + 1, num);
    }
}