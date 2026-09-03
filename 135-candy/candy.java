class Solution {
    public int candy(int[] ratings) {

        int n=ratings.length;
        int minCandies=0;


        int [] candies=new int[ratings.length];

        Arrays.fill(candies,1);

        for(int i=1; i<n; i++){
            if(ratings[i]>ratings[i-1]){
                candies[i]=candies[i-1]+1;
            }
        }

        for(int j=n-2; j>=0; j--){
            if(ratings[j]>ratings[j+1]){
                candies[j]=Math.max(candies[j], candies[j+1]+1);
            }
        }

        for(int rating: candies){
            minCandies+=rating;
        }

        return minCandies;

    }
}