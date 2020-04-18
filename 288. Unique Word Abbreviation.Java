
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
*/


// method 1: HashMap
// time complexity: O(n), space complexity: O(n)
class ValidWordAbbr {
    // key: abbr, value: word
    Map<String, Set<String>> map = new HashMap<>();
    public ValidWordAbbr(String[] dictionary) {
        for(String word: dictionary) {
            String key = getAbbr(word);
            map.putIfAbsent(key, new HashSet<>());
            map.get(key).add(word);
        }
    }
    
    public boolean isUnique(String word) {
        String key = getAbbr(word);
        if(!map.containsKey(key) || map.get(key).size() == 1 && map.get(key).contains(word)) return true;
        return false;
    }
    
    public String getAbbr(String input) {
        if(input.length() <= 2) return input;
        int n = input.length();
        return input.charAt(0) + String.valueOf(n - 2) + input.charAt(n - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */

