/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2  capacity  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Author:Mindy927  */
 /*
    Double Linked List to keep tack of keys requested, least recent -> most recent
    HashMap to store key:DNode 
    Move nodes to end of list when requested (delete node & add node again)
    when delete, update both map and linkedlist
    */

class LRUCache {
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    //add before tail, FIFO
    public void add(Node node){
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }
    
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void moveToTail(Node node){
        remove(node);
        add(node);
    }
    
    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> map; //key:node
    public LRUCache(int capacity) {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToTail(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (get(key) == -1){  //update existing node
            Node node = new Node(key, value);
            add(node);
            map.put(key, node);
        }else {
            map.get(key).val = value;
        }
        
        if (map.size() > capacity){ //remove head.next
            Node lru = head.next;
            map.remove(lru.key);
            remove(lru);
        }
    }
}

