/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

Author: Mindy927 */

class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        List<int[]> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length==0) return result;
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(n*m, new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){ //p1[0]: index of nums1, p1[1] index of nums2
                return nums1[p1[0]] + nums2[p1[1]] - (nums1[p2[0]] + nums2[p2[1]]);
            }
        });
            
        //Key Step: Add all [i,0] to pq
        for (int i=0; i<n; i++){
            pq.offer(new int[]{i,0});
        }
        
        while (!pq.isEmpty() && k--> 0){
            int[] pair = pq.poll();
            int i = pair[0]; //index of nums1
            int j = pair[1]; //index of nums2
            result.add(new int[]{nums1[i], nums2[j]});
            
            if( j+1 < m) pq.offer(new int[]{i, j+1});
            
        }
        return result;
    
    }
}

/*
Remark:
offer [0,0] and add[i+1,j] & [i,j+1] may cause duplication
*/