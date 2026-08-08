class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result= new ArrayList<>();

        StringBuilder sb= new StringBuilder();
        
        generate(sb,0,0,n, result);

        return result;
    }

    void generate(StringBuilder sb , int open, int close, int n, List<String> result){

        if(sb.length()==n*2){
            result.add(sb.toString());
            return;
        }

        if(open<n){

            sb.append('(');

            generate(sb, open+1, close, n , result);

            sb.deleteCharAt(sb.length()-1);
        }

    if(close<open){

        sb.append(')');
        generate(sb, open, close+1,  n , result);
        sb.deleteCharAt(sb.length()-1);

        } 
    }
}