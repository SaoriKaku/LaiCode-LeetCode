
/*
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

Example 2:
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/


// method 1: HashMap, one pass
// time complexity: O(4), space complexity: O(10)
class Solution {
    /*
    bull: right digit and position, cow: right digit wrong position
    1. if secret == guess at same position, count1++;
    2. int secret, secret count++, if index < 0, same char appear in guess, count2++
    3. int guess, secret count--, if index > 0, same char appear in secret, count2++
    */
    public String getHint(String secret, String guess) {
        int[] visited = new int[10];
        int count1 = 0, count2 = 0;
        for(int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if(s == g) {
                count1++;
            }
            else {
                if(visited[s] < 0) {
                    count2++;
                }
                visited[s]++;
                if(visited[g] > 0) {
                    count2++;
                }
                visited[g]--;
            }
        }
        return new String(count1 + "A" + count2 + "B");
    }
}

