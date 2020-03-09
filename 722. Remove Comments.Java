
// remove code after //
// remove code between /* */
// the left code should not be empty
// input: source code, a string of each line 
// output: a string of each line after remove // and /* */

/*
Note:

The length of source is in the range [1, 100].
The length of source[i] is in the range [0, 80].
Every open block comment is eventually closed.
There are no single-quote, double-quote, or control characters in the source code.
*/


// method 1: string
// time complexity: O(n * m), space complexity: O(m)
class Solution {
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        if(source == null || source.length == 0) {
            return result;
        }
        boolean inBlock = false;
        StringBuilder curr = new StringBuilder();
        for(String s: source) {
            char[] line = s.toCharArray();
            for(int i = 0; i < line.length; i++) { 
                // when meet /*
                if(!inBlock && line[i] == '/' && i + 1 < line.length && line[i + 1] == '*') {
                    inBlock = true;
                    i++;
                } 
                // when meet //
                else if(!inBlock && line[i] == '/' && i + 1 < line.length && line[i + 1] == '/') {
                    break;
                }
                // when meet */
                else if(inBlock && line[i] == '*' && i + 1 < line.length && line[i + 1] == '/') {
                    inBlock = false;
                    i++;
                }
                else if(!inBlock) {
                    curr.append(line[i]);
                }
            }
            if(!inBlock && curr.length() > 0) {
                result.add(String.valueOf(curr));
                curr = new StringBuilder();
            }
        }
        return result;
    }
}



