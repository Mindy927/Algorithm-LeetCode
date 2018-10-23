/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree

 



 

as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Note:

N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Author: Mindy927*/

/*
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

// pre-order
// serialize by recursively building list of (root.val, children.size)
// deserialize using queue
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list = new ArrayList<>();
        serializeHelper(root, list);
        if (list.size() == 0) return "";
        return String.join(",", list);
    }
    
    //for each node: (1) add node.val to list (2) add # of node's children to list (3) recursion call for children
    public void serializeHelper(Node root, List<String> list){
        if (root == null) return;
        list.add(String.valueOf(root.val));
        list.add(String.valueOf(root.children.size()));
        
        for (Node child:root.children){
            serializeHelper(child, list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0) return null;
        
        String[] strs = data.split(",");
        Queue<Integer> q = new LinkedList<>();
        for (String str:strs) q.offer(Integer.parseInt(str));
        
        return deserializeHelper(q);
    }
    
    public Node deserializeHelper(Queue<Integer> q){
        Node root = new Node();
        root.val = q.poll();
        int size = q.poll(); //size of children;
        root.children=new ArrayList<Node>(size);
        for (int i=0; i<size; i++){
            root.children.add(deserializeHelper(q));
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));