class Solution {

    class Node {
        int len;
        int prefix;
        int suffix;
        int best;

        char leftChar;
        char rightChar;

        Node(int len, int prefix, int suffix, int best,
             char leftChar, char rightChar) {

            this.len = len;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;

            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }
    }

    Node[] tree;

    public int[] longestRepeating(String s,
                                  String queryCharacters,
                                  int[] queryIndices) {

        int n = s.length();

        tree = new Node[4 * n];

        build(1, 0, n - 1, s);

        int k = queryIndices.length;
        int[] ans = new int[k];

        char[] arr = s.toCharArray();

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build segment tree
    void build(int node, int start, int end, String s) {

        if (start == end) {

            char ch = s.charAt(start);

            tree[node] = new Node(
                1,      // len
                1,      // prefix
                1,      // suffix
                1,      // best
                ch,     // leftChar
                ch      // rightChar
            );

            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid, s);

        build(node * 2 + 1, mid + 1, end, s);

        tree[node] = merge(tree[node * 2],
                            tree[node * 2 + 1]);
    }

    // Update one position
    void update(int node, int start, int end,
                int index, char ch) {

        if (start == end) {

            tree[node] = new Node(
                1,
                1,
                1,
                1,
                ch,
                ch
            );

            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {

            update(
                node * 2,
                start,
                mid,
                index,
                ch
            );

        } else {

            update(
                node * 2 + 1,
                mid + 1,
                end,
                index,
                ch
            );
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // Merge two adjacent segments
    Node merge(Node left, Node right) {

        int len = left.len + right.len;

        int prefix = left.prefix;
        int suffix = right.suffix;

        int best = Math.max(left.best, right.best);

        // Can we join the suffix of left
        // with the prefix of right?
        if (left.rightChar == right.leftChar) {

            best = Math.max(
                best,
                left.suffix + right.prefix
            );

            // Entire left segment has same character
            if (left.prefix == left.len) {

                prefix = left.len + right.prefix;
            }

            // Entire right segment has same character
            if (right.suffix == right.len) {

                suffix = right.len + left.suffix;
            }
        }

        return new Node(
            len,
            prefix,
            suffix,
            best,
            left.leftChar,
            right.rightChar
        );
    }
}