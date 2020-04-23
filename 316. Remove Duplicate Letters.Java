
/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:
Input: "bcabc"
Output: "abc"

Example 2:
Input: "cbacdcbc"
Output: "acdb"
*/


// method 1: Stack + HashMap
// time complexity: O(n), space complexity: O(n)
class Solution {
    /* find most length increasing sequence
       Map: store char and its count
       set: record char in stack
       stack: push char in ascending order or there is only one char left
    */
    public String removeDuplicateLetters(String s) {
        
        Map<Character, Integer> map = new HashMap<>();
        for(char curr: s.toCharArray()) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        //System.out.println("map: " + map);
        Set<Character> inStackSet = new HashSet<>();
        Deque<Character> stack = new ArrayDeque<>();
        for(char curr: s.toCharArray()) {
            map.put(curr, map.get(curr) - 1);
            if(inStackSet.contains(curr)) continue;
            while(!stack.isEmpty() && stack.peek() > curr && map.get(stack.peek()) > 0) {
                char prev = stack.pop();
                inStackSet.remove(prev);
            }
            stack.push(curr);
            inStackSet.add(curr);
            //System.out.println("stack: " + stack);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return String.valueOf(sb);
    }
}





