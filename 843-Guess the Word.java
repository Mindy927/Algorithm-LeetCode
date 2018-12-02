/*
This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.

Author: Mindy927*/

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */

//use number of words matched to trim wordlist
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        Random rnd = new Random();
        List<String> list = new ArrayList<>();
        for (String word:wordlist) list.add(word);
        
        for (int i=0; i<10; i++){ //guess 10 times;
            String guessWord = list.get(rnd.nextInt(list.size())); //get a random word from list
            int cnt = master.guess(guessWord);
            if (cnt == 6) break; //secret word found
            List<String> temp = new ArrayList<>();
            for (String word:list){ //only word that has match 'cnt' cound be secret
                if (match(word, guessWord) == cnt) temp.add(word);
            }
            list = temp;
        }
        
    }
    
    //cnt number of chars matched for two string
    public int match(String a, String b){
        int cnt = 0;
        for (int i=0; i<6; i++){
            if (a.charAt(i) == b.charAt(i)) cnt++;
        }
        
        return cnt;
    }
}


/*
improvement:we guess the word with minimum 0 matches 
 -> since target word will have high possiblities(80%) falls into 0 matches with the word guessed, guess the word with min 0 matches will reduces the candidate sets faster
The probability of two words with 0 match is (25/26)^6 = 80%. That is to say, for a candidate word, we have 80% chance to see 0 match with the secret word. 
In this case, we had 80% chance to eliminate the candidate word and its "family" words which have at least 1 match. 
Additionally, in order to delete a max part of words, we select a candidate who has a big "family" (fewest 0 match).
*/




