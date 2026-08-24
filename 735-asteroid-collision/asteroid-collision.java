class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer>st = new Stack<>();
        
        for(int i=0; i<asteroids.length; i++){

            int current=asteroids[i];
            boolean alive= true;

            while(!st.isEmpty() && st.peek()>0 && current<0){
               
               if(st.peek()<Math.abs(current)){
                st.pop();

               }

               else if(st.peek()==Math.abs(current)){
                st.pop();
                alive=false;
                break;
             }

             else{
                alive=false;
                break;
               }
        }


        if(alive){
            st.push(current);
        }

     }
     
        int [] result=new int[st.size()];
        for(int i=0; i<st.size(); i++){
            result[i]=st.get(i);
        }
        return result;
        
    }
}