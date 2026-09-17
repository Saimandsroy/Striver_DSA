class Solution {
    class Pair{
        String words;
        int step;

        Pair(String words, int step){
            this.words=words;
            this.step=step;
        }

    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        Queue<Pair> queue= new LinkedList<>();

        Set<String> set= new HashSet<>();

        for(String word : wordList){
            set.add(word);
        }

        if(!set.contains(endWord)){
            return 0;
        }

        queue.add(new Pair(beginWord,1));
        set.remove(beginWord);

        while(!queue.isEmpty()){

            Pair current=queue.poll();

            String word= current.words;
            int steps=current.step;


            if(word.equals(endWord)){
                return steps;
            }



            for(int i=0; i<word.length(); i++){
                
                char [] chars= word.toCharArray();

                for(char ch='a'; ch<='z'; ch++){
                    chars[i]=ch;

                    String newWord= new String(chars);

                    if(set.contains(newWord)){
                       set.remove(newWord);
                       queue.add(new Pair(newWord, steps+1)); 
                    }


                }
            }
        }

        return 0;
    }
}