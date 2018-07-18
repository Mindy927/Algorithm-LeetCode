

//Method 1: dp:sum Array with len n+1 ==> O(n^2)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n+1]; //sum[i]:sum from nums[0] to nums[i-1]
        sum[0] = 0;
        for (int i=1; i<n+1; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        
        for (int i=0; i<n-1; i++){
            for (int j=i+2;j<n+1; j++){
                if (k==0 && (sum[j] - sum[i])==0 ) return true; //corner case:k==0
                if (k!=0 && (sum[j] - sum[i])%k==0) return true;
            }
        }
        
        return false;
    }
}


//Method 2: hash map <curSum%k :index>  O(n)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // curSum%k : first index
        map.put(0, -1);
        
        int curSum=0;
        for (int i=0; i<nums.length; i++){
            curSum += nums[i];
            if (k!=0) curSum %= k;  //Be careful with k==0
            if (!map.containsKey(curSum)) map.put(curSum, i);
            else {
                if (i - map.get(curSum) > 1) return true;
            }
        }
        
        return false;
    }
}