class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        

        int [][] dist= new int[n][n];

        for(int i=0; i<n; i++){
            Arrays.fill(dist[i],(int)1e9);

            dist[i][i]=0;

        }

        for(int i=0;i<times.length; i++){
            int u=times[i][0]-1;
            int v=times[i][1]-1;
            int wt=times[i][2];

            dist[u][v]=wt;
        }

        for(int pair=0; pair<n; pair++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    dist[i][j]=Math.min(dist[i][j], dist[i][pair]+ dist[pair][j]);
                }
            }
        }

        int max=0;

        int source=k-1;

        for(int i=0; i<dist.length; i++){
            if(dist[source][i]==(int)1e9){
                return -1;
            }

            max=Math.max(max, dist[source][i]);
        }

        return max;

    }
}