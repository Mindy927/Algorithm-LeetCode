/*
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.

Author: Mindy927*/

/*
corner case: 
-- leading zero for IPv4
-- s.split("\\.") to split by "."
-- each str[i].length() is not empty and length <=3
*/

class Solution {
    /*
    1. check if first or last char is ".",":" before split
    2. -- for strs[] verify array length after split, 
       -- for each string, verify length, not empty
       -- verify each char
    3. for IPv4, verify all are digits, then string a -> int -> string b, verify a.equals(b)
    4. for IPv6, toLoweCase()
    */
    
    public String validIPAddress(String IP) {
        if (IP.indexOf('.') != -1) return valid4(IP);
        if (IP.indexOf(":") != -1 ) return valid6(IP);
        return "Neither";
    }
    
    public String valid4(String IP){
        if (IP.indexOf("\\.") == 0 || IP.charAt(IP.length()-1)=='.') return "Neither";
        String[] strs = IP.split("\\.");
        if (strs.length != 4) return "Neither";
        for (String str:strs){
            if (str.equals("")) return "Neither";
            if (str.length() > 3) return "Neither";
            for (char c:str.toCharArray()) {
                if (!Character.isDigit(c)) return "Neither";
            }
            int num = Integer.parseInt(str);
            if (num < 0 || num > 255) return "Neither";
            if (!str.equals(String.valueOf(num))) return "Neither"; //get rid of leading 0
        }
        return "IPv4";
    }
    
    public String valid6(String IP){
        if (IP.indexOf(":") == 0 || IP.charAt(IP.length()-1)==':') return "Neither";
        String[] strs = IP.split(":");
        if (strs.length != 8) return "Neither";
        for (String str:strs){
            if (str.equals("")) return "Neither";
            if (str.equals("0") || str.equals("0000")) continue;
            str = str.toLowerCase();
            if (str.length() > 4) return "Neither";
            for (int i=0; i<str.length(); i++){
                char c = str.charAt(i);
                if (!(Character.isDigit(c) || (c >= 'a' && c <= 'f'))) return "Neither";
            }
        }
        return "IPv6";
    }
}