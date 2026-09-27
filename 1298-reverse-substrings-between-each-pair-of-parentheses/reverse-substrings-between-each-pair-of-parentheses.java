class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();

        int[] pair = new int[n];

        java.util.Stack<Integer> stack = new java.util.Stack<>();

      
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                int j = stack.pop();

                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;
        int direction = 1;

        while (i >= 0 && i < n) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == ')') {
                
                i = pair[i];

        
                direction = -direction;
            } else {
                ans.append(ch);
            }

            i += direction;
        }

        return ans.toString();
    }
}