/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    
    public String serialize(TreeNode root) {
        StringBuilder sb= new StringBuilder();

        serializeHelper(root,sb);

        return sb.toString();
    }

    public void serializeHelper(TreeNode root, StringBuilder sb){
        if(root== null){
            sb.append("null,");
            return;
        }


        sb.append(root.val).append(',');

        serializeHelper(root.left,sb);
        serializeHelper(root.right,sb);
    }

    int index=0;
    public TreeNode deserialize(String data) {
        String [] values = data.split(",");

        index=0;
        return deserializeHelper(values);
    }
        public TreeNode deserializeHelper(String [] values){
            if(values[index].equals("null")){
                index++;
                return null;
            }

            TreeNode root= new TreeNode(Integer.parseInt(values[index]));
            index++;
            root.left=deserializeHelper(values);
            root.right=deserializeHelper(values);

            return root;
        }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));