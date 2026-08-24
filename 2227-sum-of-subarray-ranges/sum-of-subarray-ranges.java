class Solution {
    public long subArrayRanges(int[] nums) {
        
        long total=0;
        int  [] prev=prevSmall(nums);
        int  [] next=nextSmall(nums);

        int [] prevBig=prevGreater(nums);
        int [] nextBig=nextGreater(nums);



        for(int i=0; i<nums.length; i++){
            long left=i-prev[i];
            long right= next[i]-i;

            long leftGreater=i-prevBig[i];
            long  rightGreater=nextBig[i]-i;


            long controMin=left*right*nums[i];
            long controMax=leftGreater*rightGreater*nums[i];


            total+=(controMax-controMin);
        }

        return total;


    }

    public int [] prevSmall(int [] nums){
        Stack<Integer> st= new Stack<>();

        int n=nums.length;
        
        int [] prev=new int[n];

        for(int i=0; i<n; i++){
            while(!st.isEmpty() && nums[st.peek()] > nums[i]){
                st.pop();
            }

            prev[i]=st.isEmpty()? -1 : st.peek();

            st.push(i);
        }

        return prev;
    }

    public int [] nextSmall(int [] nums){
        Stack<Integer> st=new Stack<>();

        int n=nums.length;
        int [] next=new int[n];

        for(int i=n-1;i>=0; i--){
            while(!st.isEmpty() && nums[st.peek()]>=nums[i]){
                st.pop();
            }
            next[i]=st.isEmpty() ? n : st.peek();

            st.push(i);
        }
        return next;
    }


    public int [] prevGreater(int [] nums){
        Stack<Integer> st=new Stack<>();

        int n= nums.length;
        int [] prev= new int[n];

        for(int i=0; i<n; i++){
            while(!st.isEmpty() && nums[st.peek()] < nums[i]){
                st.pop();
            }
            prev[i]=st.isEmpty() ? -1 : st.peek();
            st.push(i);

        }
        return prev;
    }

    public int [] nextGreater(int [] nums){
        Stack<Integer>st = new Stack<>();

        int n=nums.length;
        int [] next=new int[n];

        for(int i=n-1; i>=0; i--){
            while(!st.isEmpty() && nums[st.peek()]<=nums[i]){
                st.pop();

            }
            next[i]=st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return next;
    }
    
}