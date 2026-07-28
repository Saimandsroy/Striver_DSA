class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;


        if(n1>n2){
            return findMedianSortedArrays(nums2, nums1);
        }

        int low=0;
        int high=n1;



        int left= (n1+n2+1)/2;
        int total =n1+n2;

        while(low<=high){

            int cut1= low + (high-low)/2;
            int cut2= left-cut1;

            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;

            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;


            if(cut1>0){
                l1=nums1[cut1-1];
            }
            if(cut1<n1){
                r1=nums1[cut1];
            }

            if(cut2>0){
                l2=nums2[cut2-1];
            } 

            if(cut2<n2){
                r2=nums2[cut2];
            }


            if(l1<= r2 && l2<=r1){
                if(total%2==1){
                    return Math.max(l1,l2);
                }
                return (Math.max(l1,l2)+ Math.min(r1,r2))/2.0;
            }

            else if(l1>r2){
                high=cut1-1;
            }else{
                low=cut1+1;
            }
        }
        return 0.0;

    }
}

// this is the question for 2 sorted arrays..