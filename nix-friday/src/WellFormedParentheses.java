//Given a string containing just the characters ‘(’ and ‘)’, find the length of the longest valid (well-formed) parentheses substring.
//
//    For “(()“, the longest valid parentheses substring is “()“, which has length = 2.
//
//    Another example is “)()())“, where the longest valid parentheses substring is “()()“, which has length = 4.

public class WellFormedParentheses {

    public int solve(String input) {
        int sum = 0;
        int start = 0;
        int best = 0;

        for (int i = 0; i < input.length(); i++) {
            sum += '(' == input.charAt(i) ? 1 : -1;
            if (sum < 0) {
                sum++;
                start++;
                best = Math.max(best, i - start);
            }
        }

        while (sum > 0) {
            sum--;
            start++;
        }

        best = Math.max(best, input.length() - start);

        return best;
    }


}
