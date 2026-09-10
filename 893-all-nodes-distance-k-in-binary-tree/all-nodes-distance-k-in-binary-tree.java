/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public void parentMap(TreeNode root, Map<TreeNode, TreeNode> parentmap){

        Queue<TreeNode> queue= new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            TreeNode node= queue.poll();

            if(node.left!=null){
                parentmap.put(node.left, node);
                queue.offer(node.left);
            }

            if(node.right!=null){
                parentmap.put(node.right, node);
                queue.offer(node.right);
            }

        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        List<Integer> ans=new ArrayList<>();

        if(root==null){
            return ans;
        }

        Map<TreeNode , TreeNode> map= new HashMap<>();
        parentMap(root, map);

        Queue<TreeNode> queue= new LinkedList<>();
        Set<TreeNode> visited= new HashSet<>();

        queue.offer(target);
        visited.add(target);

        int distance=0;

        while(!queue.isEmpty()){

            int size= queue.size();

            if(distance==k){
                while(!queue.isEmpty()){
                    ans.add(queue.poll().val);
                }
                return ans;
            }


            for(int i=0; i<size; i++){
                TreeNode node= queue.poll();

                if(node.left != null && !visited.contains(node.left)){
                    visited.add(node.left);
                    queue.offer(node.left);
                }

                if(node.right != null && !visited.contains(node.right)){
                    visited.add(node.right);
                    queue.offer(node.right);
                }


                TreeNode parent= map.get(node);

                if(parent != null && !visited.contains(parent)){
                    visited.add(parent);
                    queue.offer(parent);
                }
            }

            distance++;
        

        }

        return ans;


    }
}