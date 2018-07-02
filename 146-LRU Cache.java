/*

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Author:Mindy927 */

public class LRUCache {
    /*
    Double Linked List to keep tack of requests, most recent -> least recent
    HashMap to store key:value 
    Move nodes to beginning of list when requested (delete node & add node again)
    */
    class Dnode{
        Dnode prev;
        Dnode next;
        int key;
        int val;
        public Dnode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
            //Always inseart after head
    public void add(Dnode node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
   
    public void delete(Dnode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
    }   
        //Move node in between -> after head
    public void move(Dnode node){
            delete(node);
            add(node);
        }
        
    int capacity;
    Map<Integer,Dnode> map;
    Dnode head, tail;
    int count;
    public LRUCache(int capacity) { //Remember to link head to tail when initialize
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Dnode(0,0);
        head.prev = null;
        tail = new Dnode(0,0);
        tail.next = null;
        head.next = tail;
        tail.prev = head;
        count = 0;
    }
    
    public int get(int key) {
        if (map.containsKey(key)){
            //update req list
            Dnode node = map.get(key);
            move(node);
            return node.val;
        } else return -1;
    }
    
    public void put(int key, int value) {
        if (get(key)!= -1){//update existing key
            map.get(key).val = value;
            return;
        }else{
            Dnode node = new Dnode(key, value);
            map.put(key, node);
            add(node);
            count++;
            if (count > capacity) {
                map.remove(tail.prev.key);
                delete(tail.prev);
                count--;
            }
            return;
        }
    }
}