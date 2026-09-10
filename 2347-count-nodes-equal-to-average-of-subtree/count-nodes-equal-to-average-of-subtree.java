class Solution {

    int ans = 0;

    class Pair {
        int sum;
        int count;

        Pair(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    public int averageOfSubtree(TreeNode root) {

        dfs(root);

        return ans;
    }

    public Pair dfs(TreeNode node) {

        if (node == null) {
            return new Pair(0, 0);
        }

      
        Pair left = dfs(node.left);


        Pair right = dfs(node.right);

        int sum = left.sum + right.sum + node.val;
        int count = left.count + right.count + 1;

        
        int average = sum / count;

        
        if (average == node.val) {
            ans++;
        }

        
        return new Pair(sum, count);
    }
}