/*
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
The system have already tracked down the following sentences and their corresponding times: 
"i love you" : 5 times 
"island" : 3 times 
"ironman" : 2 times 
"i love leetcode" : 2 times 
Now, the user begins another search: 

Operation: input('i') 
Output: ["i love you", "island","i love leetcode"] 
Explanation: 
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

Operation: input(' ') 
Output: ["i love you","i love leetcode"] 
Explanation: 
There are only two sentences that have prefix "i ". 

Operation: input('a') 
Output: [] 
Explanation: 
There are no sentences that have prefix "i a". 

Operation: input('#') 
Output: [] 
Explanation: 
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search. 

Note:
The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.


Author: Mindy927*/


/*
Design:
-- hashmap (sentence:hot degree)
-- each trie node keeps a list of sentence with current trie node as prefix
-- create pq when query after get list from trie node

Considerations:
(1) ' ' at childeren[26]
(2) only add new sentence to dict

*/
class AutocompleteSystem {
    class TrieNode{
        char val;
        TrieNode[] children = new TrieNode[27]; //handle space
        List<String> list = new ArrayList<>(); //list of sentence with current trie node as prefix
        public TrieNode(){}
        public TrieNode(char val){
            this.val = val;
        }
    }
    
    String cur;
    Map<String, Integer> map; //sentence:cnt
    TrieNode head;
    public AutocompleteSystem(String[] sentences, int[] times) {
        cur = "";    
        map = new HashMap<>();
        head = new TrieNode();
        
        for (int i=0; i<sentences.length; i++){
            String str = sentences[i];
            map.put(str, times[i]);
            addToDict(str);
        }
    }
    
    public void addToDict(String str){
        TrieNode cur = head;
        for (int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            int index = c ==' '? 26: c - 'a';
            if (cur.children[index] == null) cur.children[index] = new TrieNode(c);
            cur = cur.children[index];
            cur.list.add(str);
        }
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        //end to sentence, add to dict, update map
        if (c == '#'){
            if (!map.containsKey(cur)) addToDict(cur); //add to dict only at first time
            map.put(cur, map.containsKey(cur)? map.get(cur)+1:1);
            cur = "";
            return result;
        }
        
        //search based on updated current string 
        cur = cur + c;
        List<String> temp = search(cur);
        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>(){
           public int compare(String a, String b){
               if (map.get(a) != map.get(b)) return map.get(a) - map.get(b); //min heap
               else return b.compareTo(a);//max heap
           } 
        });
        for (String str:temp){
            pq.offer(str);
            if (pq.size() > 3) pq.poll();
        }
        
        //add to result, last element in the queue is hottest, add to pos 0
        while (!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        
        return result;
    }
    
    public List<String> search(String str){
        TrieNode cur = head;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            int index = c ==' '? 26: c - 'a';
            if (cur.children[index]==null) return new ArrayList<>();
            cur = cur.children[index];
        }
        return cur.list;
    }
}
