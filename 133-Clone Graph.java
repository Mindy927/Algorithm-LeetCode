/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/


Author: Mindy927 */

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

//version 1
public class Solution {
    //Need global map here for case when node connected to itself, return from map instead create a duplicate one
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap(); 
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (map.containsKey(node)) return map.get(node);
        if (node == null) return node;
         //old node:new node
        
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        for (UndirectedGraphNode nei:node.neighbors){
            if (!map.containsKey(nei)) cloneGraph(nei);
            copy.neighbors.add(map.get(nei));
        }
        
        return copy;
    }


//version 2
public class Solution {
    //map record the nodes which has been cloned
    Map<Integer, UndirectedGraphNode> map = new HashMap<>(); //old node:new node
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }
    
    public UndirectedGraphNode clone(UndirectedGraphNode node){
        if (node==null) return node;
        
        if(map.containsKey(node.label)){
           return map.get(node.label);
        }
        
        //clone node
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(copy.label, copy);  //put copy to map first, otherwise self-cycle case will cause stack overflow
        //clone neighbors
        for (UndirectedGraphNode nei:node.neighbors){
            copy.neighbors.add(clone(nei));
        }
        
        return copy;
    }
}