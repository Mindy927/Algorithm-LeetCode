/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Author: Mindy927 */


class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // value:cnt
        List<Integer> result = new ArrayList<>();
        
        
        for (int i=0; i<nums.length; i++){
            int key = nums[i];
            map.put(key, map.containsKey(key)? map.get(key)+1:1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(map.size(), new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return map.get(b) - map.get(a);
            }
        });
        
        for (Integer key:map.keySet()){
            pq.offer(key);
        }
        
    
        while (k>0){
            result.add(pq.poll());
            k--;
        }
        
        return result;
    }
}
