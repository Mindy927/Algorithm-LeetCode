/*
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Author: Mindy927*/

/*
Three hashmap
- valueMap   key:value
- freqMap 	 key:frequecy
- buckets    freq: a linkedHashSet of all keys with same freq
*/

class LFUCache {
    Map<Integer, Integer> valueMap; //key:value
    Map<Integer, Integer> freqMap; //key:frequency
    Map<Integer, LinkedHashSet<Integer>> buckets; //freq:a set of keys with same cnt
    int min; //least frequent cnt
    int capacity;
    int cnt;
    public LFUCache(int capacity) {
        valueMap = new HashMap<>();
        freqMap = new HashMap<>();
        buckets = new HashMap<>();
        buckets.put(1, new LinkedHashSet<>());
        min = -1;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!valueMap.containsKey(key)) return -1;
        //update freq map
        int prevFreq = freqMap.get(key);
        freqMap.put(key, prevFreq + 1);
        //update buckets
        buckets.get(prevFreq).remove(key);
        if (prevFreq == min && buckets.get(prevFreq).size() == 0) min++;
            
        if (!buckets.containsKey(prevFreq + 1)) buckets.put(prevFreq+1, new LinkedHashSet<>()); 
        buckets.get(prevFreq+1).add(key);
        return valueMap.get(key);
    }
    
    public void put(int key, int value) {
        if (capacity<=0) return;
        //case 1: key exist, get(key) to update frequency and update value map
        if (get(key) != -1){ 
            valueMap.put(key, value);
            return;
        }
        
        //case 2: key not exist, remove LFU when necessary and set min = 1
        if (valueMap.size() >= capacity){ //remove LFU
            int evit = buckets.get(min).iterator().next();
            buckets.get(min).remove(evit);
            valueMap.remove(evit);
        }
        
        valueMap.put(key, value);
        freqMap.put(key, 1);
        buckets.get(1).add(key);
        min = 1;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */