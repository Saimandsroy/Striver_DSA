class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        int [] inorder= new int[numCourses];

        List<Integer> [] adj= new ArrayList[numCourses];

        for(int i=0; i<numCourses; i++){
            adj[i]=new ArrayList<>();
        }

        for(int i=0; i<prerequisites.length; i++){
            int course=prerequisites[i][0];
            int prerequisiti=prerequisites[i][1];

            adj[prerequisiti].add(course);
            inorder[course]++;

        }


        Queue<Integer> queue= new LinkedList<>();

        List <Integer> courseOrder= new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            if(inorder[i]==0){
                queue.add(i);
            }
        }


        while(!queue.isEmpty()){

            int node= queue.poll();

            courseOrder.add(node);


            for(int next : adj[node]){
                inorder[next]--;
                if(inorder[next]==0){
                    queue.add(next);
                }
            }
            
        }


         if (courseOrder.size() != numCourses) {
            return new int[0];
        }

        int [] order= new int[numCourses];

        for(int i=0; i<numCourses; i++){
            order[i]=courseOrder.get(i);
        }


        return order;





    }
}