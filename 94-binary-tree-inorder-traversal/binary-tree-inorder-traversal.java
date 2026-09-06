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

    List<Integer> ans= new ArrayList<>();

    void inOrder(TreeNode node){
        if(node==null){
            return ;
        }

        inOrder(node.left);
         ans.add(node.val);
        inOrder(node.right);
        
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        
        inOrder(root);

        return ans;
    }
}