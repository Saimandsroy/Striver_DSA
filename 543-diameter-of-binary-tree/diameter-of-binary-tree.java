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

    int maximum=0;
    public int diameterOfBinaryTree(TreeNode root) {

        depth(root);
        return maximum;
    
    }


    public int depth(TreeNode root){

        if(root==null){
            return 0;
        }
        


        int leftHeight=depth(root.left);

        int rightHeight=depth(root.right);

        int diameter=leftHeight+rightHeight;


        maximum= Math.max(maximum, diameter);

        return 1+(Math.max(leftHeight, rightHeight));
    }
}