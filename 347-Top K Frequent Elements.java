/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Author: Mindy927 */

//Method 1: hashmap + heap: time: O(nlgk)
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


//method 2: bucket sort, O(n)
//bucket[i] : a list of nums with frequency i
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer>  map = new HashMap<>(); //val: cnt
        
        for (int num:nums){
            map.put(num, map.containsKey(num)? map.get(num)+1:1);
        }
        
        List<Integer>[] bucket = new List[nums.length+1];
        
        for (int key:map.keySet()){
            int cnt = map.get(key);
            if (bucket[cnt] == null) bucket[cnt] = new ArrayList<>();
            bucket[cnt].add(key);
        }
        
        int cnt = 0;
        for (int i=nums.length; i>=0; i--){
            List<Integer> temp = bucket[i];
            if (temp == null) continue;
            for (int val:temp){
                if (cnt++ < k) result.add(val);
                else break;
            }
            if (cnt >= k) break;
        }
        
        return result;

