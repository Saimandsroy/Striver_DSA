class Solution {

    HashMap<String , Integer> distance= new HashMap<>();
    List<List<String>> answer= new ArrayList<>();
    String begin;


    public void dfs(String word, List<String>path){

        if(word.equals(begin)){
             List<String> newPath = new ArrayList<>(path);

            Collections.reverse(newPath);

            answer.add(newPath);

            return;
        }


         int steps = distance.get(word);

        
        for (int i = 0; i < word.length(); i++) {

            char[] chars = word.toCharArray();
            char original = chars[i];

            for (char ch = 'a'; ch <= 'z'; ch++) {

                chars[i] = ch;

                String newWord = new String(chars);

            
                if (distance.containsKey(newWord) &&
                    distance.get(newWord) == steps - 1) {

                    path.add(newWord);

                    dfs(newWord, path);

                    path.remove(path.size() - 1);
                }
            }

            chars[i] = original;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
       
        begin= beginWord;
        Set<String> set= new HashSet<>();

        for (String word : wordList) {
            set.add(word);
        }

        Queue<String> queue = new LinkedList<>();

        if (!set.contains(endWord)) {
            return answer;
        }

        queue.add(beginWord);

        set.remove(beginWord);

        distance.put(beginWord,1);

        while (!queue.isEmpty()) {

    

            String word = queue.poll();
            int steps = distance.get(word);

            if(word.equals(endWord)){
                break;
            }

            for (int i = 0; i < word.length(); i++) {

            char[] chars = word.toCharArray();

            char original=chars[i];

            for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;

                        String newWord = new String(chars);

                        if (set.contains(newWord)) {
                            set.remove(newWord);
                            queue.add(newWord);

                            distance.put(newWord, steps+1);
                        }

                    }

                    chars[i]=original;
                }

            }

            if(!distance.containsKey(endWord)){
                return answer;
            }

            List<String> path= new ArrayList<>();

            path.add(endWord);
            dfs(endWord,path);

        return answer;

    }
}