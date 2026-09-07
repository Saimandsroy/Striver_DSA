/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int maximum=Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        depth(root);

        return maximum;
    }

    public int depth(TreeNode root){

        if(root==null){
            return 0;
        }
        
        int leftHeight=Math.max(0, depth(root.left));
        int rightHeight=Math.max(0, depth(root.right));

        int candidate=leftHeight+root.val+rightHeight;

        maximum=Math.max(maximum, candidate);

        return root.val+(Math.max(leftHeight, rightHeight));
    }
}