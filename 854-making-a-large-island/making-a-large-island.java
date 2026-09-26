class DisJoint {

    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    public DisJoint(int n) {

        for (int i = 0; i < n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findParent(int node) {

        if (parent.get(node) == node) {
            return node;
        }

        int ultimateParent = findParent(parent.get(node));

        parent.set(node, ultimateParent);
        return ultimateParent;
    }

    public boolean find(int u, int v) {
        return findParent(u) == findParent(v);
    }

    public void unionBySize(int u, int v){

        int parentU=findParent(u);
        int parentV=findParent(v);
        

        if(parentU==parentV){
            return;
        }

        if(size.get(parentU)< size.get(parentV)){
            parent.set(parentU, parentV);
            size.set(parentV, size.get(parentU)+size.get(parentV));
        }

        else{
            parent.set(parentV, parentU);
            size.set(parentU, size.get(parentU)+size.get(parentV));
        }

    }

    public int getSize(int node) {
        int parentNode = findParent(node);
        return size.get(parentNode);
    }
}

class Solution {

    public int largestIsland(int[][] grid) {
        int n = grid.length;

        DisJoint ds = new DisJoint(n*n);

        int[] dRow = { -1, 0, 1, 0 };
        int[] dCol = { 0, 1, 0, -1 };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    int node = i * n + j;

                    for (int k = 0; k < 4; k++) {
                        int nRow = i + dRow[k];
                        int nCol = j + dCol[k];

                        if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1) {

                            int adjNode=nRow * n + nCol;
                           
                           if(!ds.find(node, adjNode)){
                            ds.unionBySize(node,adjNode);
                           }
                        }
                    }
                }
            }
        }


        int maxArea=0;

        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){

                    int node=i*n+j;

                    maxArea=Math.max(maxArea,ds.getSize(node));

                }
            }
        }


        for(int i=0; i<n; i++){
            for(int j=0; j< n; j++){

                if(grid[i][j]==0){
                    HashSet<Integer> set= new HashSet<>();
                
                    int currArea=1;

                    for(int k=0; k<4; k++){
                        int nRow=i+dRow[k];
                        int nCol=j+dCol[k];

                        if(nRow>=0 && nRow<n && nCol>=0 && nCol<n && grid[nRow][nCol]==1){
                            int adjNode=nRow *n +nCol;

                            int parent= ds.findParent(adjNode);

                            if(set.add(parent)){
                                currArea+=ds.getSize(parent);
                            }
                        }

                    }

                    maxArea=Math.max(maxArea, currArea);

                }

             
            }
        }

       return maxArea;


    }
}