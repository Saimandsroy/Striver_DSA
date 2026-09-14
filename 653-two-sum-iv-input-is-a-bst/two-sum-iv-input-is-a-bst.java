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

    Stack<TreeNode> st1 = new Stack<>();
    Stack<TreeNode> st2 = new Stack<>();

    public void leftPush(TreeNode root) {
        while (root != null) {
            st1.push(root);
            root = root.left;
        }
    }

    public void rightPush(TreeNode root) {
        while (root != null) {
            st2.push(root);
            root = root.right;
        }
    }

    public TreeNode nextSmallest() {
        TreeNode node = st1.pop();

        if (node.right != null) {
            leftPush(node.right);
        }

        return node;
    }

    public TreeNode nextLargest() {
        TreeNode node = st2.pop();

        if (node.left != null) {
            rightPush(node.left);
        }

        return node;
    }

    public boolean findTarget(TreeNode root, int k) {

        leftPush(root);
        rightPush(root);

        TreeNode left = nextSmallest();
        TreeNode right = nextLargest();

        while (left.val < right.val) {

            if (left.val + right.val == k) {
                return true;
            }

            else if (left.val + right.val < k) {
                left = nextSmallest();
            }

            else {
                right = nextLargest();
            }
        }

        return false;
    }
}