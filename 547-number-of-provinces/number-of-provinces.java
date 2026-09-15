class Solution {

    public static void dfs(int node, int [] vis, List<List<Integer>>adj){

        vis[node]=1;

        for(int ad : adj.get(node)){
            if(vis[ad]==0){
                dfs(ad, vis, adj);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        
        List<List<Integer>> adj= new ArrayList<>();

        for(int i=0; i<isConnected.length; i++){
            adj.add(new ArrayList<>());
        }


        for(int i=0; i<isConnected.length; i++){
            for(int j=0; j<isConnected.length; j++){
                if(isConnected[i][j]==1 && i!=j){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        int [] vis= new int[isConnected.length];

        int count=0;

        for(int i=0; i<isConnected.length; i++){
            if(vis[i]==0){
                count++;
                dfs(i, vis, adj);
            }
        }
        return count;
    }
}