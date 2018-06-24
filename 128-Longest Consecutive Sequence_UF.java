/*Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Author: Mindy927 */

class Solution {
    class UF{
        int[] parent;
        int[] size;
        int n;
        int maxSize = 1;
        public UF(int n){
            n = n;
            parent = new int[n];
            size = new int[n];
            for (int i=0; i<n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find (int x){
            while (parent[x]!=x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (size[rootX] < size[rootY]){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                maxSize = Math.max(maxSize, size[rootY]);
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
                maxSize = Math.max(maxSize, size[rootX]);
            }
        }
        
        public int getMaxSize(){
            return maxSize;
        }
    }
    
    public int longestConsecutive(int[] nums) {
        if (nums.length==0) return 0;
        UF uf = new UF(nums.length);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) map.put(nums[i], i);
        
        for (int num : nums){
           if (map.containsKey(num+1)){
               uf.union(map.get(num), map.get(num+1));
           } 
        }
        
        // Fail for case [1,2,0,1] 
        // for (int i=0; i<nums.length; i++){
        //      if (map.containsKey(nums[i]+1)){
        //             uf.union(map.get(nums[i]+1), i);  
        //      }
        // } 
        
        return uf.getMaxSize();
    }
}