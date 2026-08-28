class Solution {
    public int maxScore(int[] cardPoints, int k) {

        int totalSum=0;

        int n=cardPoints.length;

        for(int i=0; i<n; i++){
            totalSum+=cardPoints[i];
        }   
        int windowSize=n-k;
        int windowSum=0;

        for(int i=0; i<n-k; i++){
            windowSum += cardPoints[i];
        }

        int minWindow=windowSum;

        int left=0;

        for(int right=windowSize; right<n; right++){
            windowSum+=cardPoints[right];
            windowSum-=cardPoints[left];

            left++;

            minWindow=Math.min(minWindow, windowSum);
        }

        return totalSum-minWindow;

    }
}