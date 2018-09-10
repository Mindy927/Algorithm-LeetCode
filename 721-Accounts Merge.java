/*
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Author: Mindy927 */

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owner = new HashMap<>(); //email:owner
        Map<String, String> parent = new HashMap<>(); //email:parent
        Map<String, TreeSet<String>> union = new HashMap<>(); //email:set of emails belong to same group
        
        //inisialize owner & parent
        for (List<String> acc:accounts){
            for (int i=1; i<acc.size(); i++){
                owner.put(acc.get(i), acc.get(0));
                parent.put(acc.get(i), acc.get(i));
            }
        }
        
       //Union with first email
       //update the rest of emails to parent of first email(connect to upper tree)
        for (List<String> acc : accounts) {
            String p = find(parent,acc.get(1));
            for (int i = 2; i < acc.size(); i++)
                parent.put(find(parent,acc.get(i)), p); //find update new root for current root!!
        }
        
        //Union all emails and build union map(root:all its children)
        for (List<String> acc:accounts){
            for (int i=1; i<acc.size(); i++){ //current email, acc.get(i)
                String root = find(parent, acc.get(i)); //KEY step: union with other subtree
                if (!union.containsKey(root)) union.put(root, new TreeSet<>());
                union.get(root).add(acc.get(i));
            }
        }
        
        //build result
        List<List<String>> result = new ArrayList<>();
        for (String root:union.keySet()){
            List<String> temp = new ArrayList<>();
            String name = owner.get(root);
            temp.add(name);
            for (String email:union.get(root)){
                temp.add(email);
            }
            result.add(temp);
        } 
        return result; 
    }
    
    public String find(Map<String, String> parent, String email){
        while (email != parent.get(email)){
            email = parent.get(email);
            parent.put(email, parent.get(email)); //path compression
        }
        return email;
    }
}