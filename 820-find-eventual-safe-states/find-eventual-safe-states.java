class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        List<Integer> [] reverse= new ArrayList[graph.length];

        for(int i=0; i<graph.length; i++){
            reverse[i]=new ArrayList<>();
        }

        int [] outdegree= new int[graph.length];
        
        for(int i=0; i<graph.length; i++){
            for(int next : graph[i]){
                reverse[next].add(i);
                outdegree[i]++;
            }
        }


        Queue<Integer> queue= new LinkedList<>();

        for(int i=0; i<graph.length; i++){
            if(outdegree[i]==0){
                queue.add(i);
            }
        }

        List <Integer> safeNode= new ArrayList<>();

        while(!queue.isEmpty()){

            int node=queue.poll();

            safeNode.add(node);

            for(int prev : reverse[node]){
                outdegree[prev]--;
                if(outdegree[prev]==0){
                    queue.add(prev);
                }
            }


        }
        
        Collections.sort(safeNode);

        return safeNode;

        
    }
}