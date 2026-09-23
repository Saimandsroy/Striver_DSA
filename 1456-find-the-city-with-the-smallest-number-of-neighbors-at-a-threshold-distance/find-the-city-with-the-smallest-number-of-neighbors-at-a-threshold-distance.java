class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        
            int [][] dist = new int[n][n];


            for(int i=0; i<n; i++){
                Arrays.fill(dist[i], (int) 1e9);
                dist[i][i]=0;
            }

            for(int i=0; i<edges.length; i++){
                int u= edges[i][0];
                int v=edges[i][1];
                int wt=edges[i][2];

                dist[u][v]=wt;
                dist[v][u]=wt;


            }
             
                for(int k=0; k<n; k++){

                for(int i=0; i< n; i++){
               

                for(int j=0; j<n; j++){

                dist[i][j] = Math.min(dist[i][j], dist[i][k]     +    dist[k][j]);


                }
                    }
                }


                int answer=-1;

                int minCount=Integer.MAX_VALUE;

                for(int i=0; i<n; i++){
                    int count=0;
                    for(int j=0; j<n; j++){

                        if(dist[i][j]<=distanceThreshold){
                            count++;
                        }

                    }

                    if(count<=minCount){
                        minCount=count;
                        answer=i;
                    }
                }


                return answer;

    }
}