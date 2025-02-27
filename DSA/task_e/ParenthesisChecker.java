package task_e;

import java.util.Stack;

public class ParenthesisChecker {
    static boolean isBalanced(String str) {
        Stack<Character> st = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '{' || ch == '(' || ch == '[') {
                st.push(ch);
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                char latestOpenedPar = st.pop();

                if (latestOpenedPar == '{' && ch != '}') {
                    return false;
                } else if (latestOpenedPar == '(' && ch != ')') {
                    return false;
                } else if (latestOpenedPar == '[' && ch != ']') {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    static void printIsBalanced(String s) {
        System.out.println(s + " is " + (isBalanced(s) ? "" : "not ") + "Balanced");
    }

    public static void main(String[] args) {
        printIsBalanced("[{()}]"); // true
        printIsBalanced("[({(})]"); // false
        printIsBalanced("{[}"); // false
        printIsBalanced("({}{}([{}]))"); // true
        printIsBalanced("({"); // false
    }
}
