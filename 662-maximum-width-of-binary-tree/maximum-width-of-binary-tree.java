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
    class Pair{
        TreeNode node;
        long index;

        Pair(TreeNode node , long index){
            this.node=node;
            this.index=index;

        }

    }
    public int widthOfBinaryTree(TreeNode root) {
    
        Queue<Pair> queue= new LinkedList<>();

        queue.offer(new Pair(root,0));

        int maxWidth=0;
        while(!queue.isEmpty()){

            int size=queue.size();

            long firstIndex=queue.peek().index;

            long lastIndex=0;
                
            for(int i=0; i<size; i++){
                Pair current = queue.poll();

                TreeNode node=current.node;

                long currentIndex=current.index-firstIndex;

                lastIndex=currentIndex;


                if(node.left != null){
                    queue.offer(new Pair(node.left, currentIndex * 2 +1));
                }


                if(node.right != null){
                    queue.offer(new Pair(node.right, currentIndex * 2+2));
                }
            }
            long width = lastIndex+1;


            maxWidth=Math.max(maxWidth, (int) width);
        }

        return maxWidth;

    }
}