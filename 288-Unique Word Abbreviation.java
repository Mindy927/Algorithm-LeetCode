/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example:

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

Author: Mindy927

*/

class ValidWordAbbr {
    Map<String, Integer> map;
    Set<String> dict;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        dict = new HashSet<>();
        for (String str:dictionary) {
            String key = abbr(str);
            if (!dict.contains(key)) { //new dict word
                map.put(key, map.containsKey(key)? map.get(key)+1:1);
                dict.add(str);
            }
        }
    }
    
    public boolean isUnique(String word) {
        String key = abbr(word);
        if (dict.contains(word)) return map.get(key) == 1;
        else return !map.containsKey(key);
    }
    
    public String abbr(String str){
         if (str.length() <= 2) return str;
         return "" + str.charAt(0) + (str.length()-2) + str.charAt(str.length()-1);
    }
}

